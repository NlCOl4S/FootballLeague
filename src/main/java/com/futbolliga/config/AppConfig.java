package com.futbolliga.config;

import com.futbolliga.entity.User;
import com.futbolliga.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {


    private final UserRepository repository;

    //De que forma Spring puede ver si el usuario existe (pide el email)
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final User user = repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            //Formateo el rol para asegurar que tenga el prefijo "ROLE_"
            String roleName = user.getRole().startsWith("ROLE_") ? user.getRole() : "ROLE_" + user.getRole();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(roleName) // Asiga un rol
                    .build();
        };
    }

    //Proveedor de autenticación que combina la búsqueda del usuario (email) y la verificación del hash de la contraseña
    @Bean
    public AuthenticationProvider authenticationProvider() {
        //Invocamos los métodos @Bean definidos abajo en la misma clase
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    //Algoritmo de hashing criptográfico para encriptar y comparar contraseñas (BCrypt)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}