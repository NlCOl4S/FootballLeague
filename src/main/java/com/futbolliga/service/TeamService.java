package com.futbolliga.service;

import com.futbolliga.dto.TeamDTO;
import com.futbolliga.entity.Team;
import com.futbolliga.mapper.TeamMapper;
import com.futbolliga.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TeamService {

    private final TeamRepository repository;

    @Transactional
    public TeamDTO saveTeam(TeamDTO teamDTO){
        Team team = TeamMapper.toEntity(teamDTO);
        Team saved = repository.save(team);
        return TeamMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<TeamDTO> getAllTeams(){
        return repository.findAll()
                .stream()
                .map(TeamMapper::toDTO)
                .toList();
    }

    @Transactional
    public TeamDTO updateTeam(Long id, TeamDTO teamDTO){
        return repository.findById(id)
                .map(team -> {
                    team.setCity(teamDTO.city());
                    team.setName(teamDTO.name());
                    return repository.save(team);
                })
                .map(TeamMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
    }

    @Transactional
    public void deleteTeam(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Team not found with id: " + id);
        }
            repository.deleteById(id);

    }

}