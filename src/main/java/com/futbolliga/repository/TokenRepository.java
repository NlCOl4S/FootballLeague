package com.futbolliga.repository;

import com.futbolliga.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    //Recupera todos los tokens activos (no expirados y no revocados) vinculados a un usuario específico.
    //Se ejecuta durante el 'Login' para aplicar un borrón y cuenta nueva, invalidando de forma lógica
    //las sesiones concurrentes anteriores en la RAM y garantizando un único token válido por usuario en la red.

    @Query("select t from Token t join t.user u where u.id = :userId and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);


}
