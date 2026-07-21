package com.futbolliga.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    public enum TokenType {
        BEARER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 500)
    private String token;

    @Enumerated(EnumType.STRING) //Tipo de token (BEARER) (32 caracteres)
    public TokenType tokenType = TokenType.BEARER;

    private boolean expired;

    private boolean revoked;

    @ManyToOne @JoinColumn(name = "user_id") //Almacena los tokens de la List a un solo user
    private User user;

}
