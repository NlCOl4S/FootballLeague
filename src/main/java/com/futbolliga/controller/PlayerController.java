package com.futbolliga.controller;

import com.futbolliga.dto.PlayerDTO;
import com.futbolliga.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDTO> savePlayer(@RequestBody PlayerDTO playerDTO){
        PlayerDTO savePlayer = playerService.savePlayer(playerDTO);
        return ResponseEntity.ok(savePlayer);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(){
        List<PlayerDTO> AllPlayers = playerService.getAllPlayers();
        return ResponseEntity.ok(AllPlayers);
    }

}
