package com.futbolliga.service;

import com.futbolliga.dto.LoginRequest;
import com.futbolliga.dto.RegisterRequest;
import com.futbolliga.dto.TokenResponse;
import com.futbolliga.entity.Token;
import com.futbolliga.entity.User;
import com.futbolliga.repository.TokenRepository;
import com.futbolliga.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public TokenResponse register(final RegisterRequest request) {
        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password())) //Hash criptográfico de contraseña (BCrypt)
                .role(request.role())
                .build();
        var savedUser = userRepository.save(user); //Se almacena en la BBDD
        var jwtToken = jwtService.generateToken(savedUser); //Token de acceso rápido
        var refreshToken = jwtService.generateRefreshToken(savedUser); //Token duradero
        saveUserToken(savedUser, jwtToken); //Registro del token en la BBDD
        return new TokenResponse(jwtToken, refreshToken); //Se devuelven los tokens
    }

    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        var user = userRepository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user); //Quitar sesiones anteriores
        saveUserToken(user, jwtToken); //Salvar el token vigente
        return new TokenResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(final User user) {
        final List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens); //Persistencia masiva
        }
    }

    //Genera un nuevo token de acceso usando el token duradera
    public TokenResponse refreshToken(final String authHeader) {
        //Verificación si viene con el formato  'Bearer '
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Bearer token");
        }

        final String refreshToken = authHeader.substring(7); //Corta la palabra 'Bearer ' para sacar el token puro
        final String userEmail = jwtService.extractUsername(refreshToken); //Abre el sobre para leer el email

        if (userEmail == null) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        //Verificacion en BBDD de existencia del correo
        final User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(userEmail));

        //Validacion de token de refresco
        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        //Renovacion de token de acceso
        final String accessToken = jwtService.generateToken(user);

        revokeAllUserTokens(user); //Se quitan los tokens antiguos
        saveUserToken(user, accessToken); //Se guarda el nuevo token vigente en la BBDD

        return new TokenResponse(accessToken, refreshToken); //Se devuelven los tokens
    }



}
