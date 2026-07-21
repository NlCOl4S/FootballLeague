package com.futbolliga.service;

import com.futbolliga.dto.PlayerDTO;
import com.futbolliga.entity.Player;
import com.futbolliga.mapper.PlayerMapper;
import com.futbolliga.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PlayerService {

    private final PlayerRepository repository;

   public PlayerDTO savePlayer(PlayerDTO playerDTO){
       Player player = PlayerMapper.toEntity(playerDTO);
       Player saved = repository.save(player);
       return PlayerMapper.toDTO(saved);
   }


    public List<PlayerDTO> getAllPlayers(){
        return null;
    }


}
