package com.futbolliga.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "team") //Un equipo tiene muchos jugadores por lo tanto es enviado a la variable team
    private List<Player> players = new ArrayList<>();

}
