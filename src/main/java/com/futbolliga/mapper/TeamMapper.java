package com.futbolliga.mapper;

import com.futbolliga.dto.TeamDTO;
import com.futbolliga.entity.Team;

public class TeamMapper {

    public static TeamDTO toDTO(Team team){
        return new TeamDTO(
                team.getId(),
                team.getName(),
                team.getCity()
        );
    }

    public static Team toEntity(TeamDTO dto){

        Team team = new Team();
        team.setId(dto.id());
        team.setName(dto.name());
        team.setCity(dto.city());

        return team;
    }

}
