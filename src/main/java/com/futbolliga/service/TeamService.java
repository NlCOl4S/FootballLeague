package com.futbolliga.service;

import com.futbolliga.dto.TeamDTO;
import com.futbolliga.entity.Team;
import com.futbolliga.mapper.TeamMapper;
import com.futbolliga.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TeamService {

    private final TeamRepository repository;

    public TeamDTO saveTeam(TeamDTO teamDTO){
        Team team = TeamMapper.toEntity(teamDTO);
        Team saved = repository.save(team);
        return TeamMapper.toDTO(saved);
    }

    public List<TeamDTO> getAllTeams(){
        return null;
    }

}
