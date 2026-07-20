package com.futbolliga.mapper;


import com.futbolliga.dto.PlayerDTO;
import com.futbolliga.entity.Player;

public class PlayerMapper {

    public static PlayerDTO toDTO(Player player){
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getAge(),
                player.getPosition()
        );
    }

    public static Player toEntity(PlayerDTO dto){

        Player player = new Player();
        player.setId(dto.id());
        player.setName(dto.name());
        player.setAge(dto.age());
        player.setPosition(dto.position());

        return player;
    }

}
