package com.futbolliga.controller;

import com.futbolliga.dto.TeamDTO;
import com.futbolliga.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public  ResponseEntity <List<TeamDTO>> getAllTeams(){
       List<TeamDTO> allTeams= teamService.getAllTeams();
       return ResponseEntity.ok(allTeams);
    }

    @PostMapping
    public ResponseEntity<TeamDTO> saveTeam(@RequestBody TeamDTO teamDTO){
        TeamDTO saveTeam = teamService.saveTeam(teamDTO);
        return ResponseEntity.ok(saveTeam);
    }
}
