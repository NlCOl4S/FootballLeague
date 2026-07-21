package com.futbolliga.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Builder
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    private String role;
    private String password; //Codificada en la bbdd


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //Un user tiene muchos tokens por lo tanto es enviado a la variable user
    private List<Token> tokens;



}


