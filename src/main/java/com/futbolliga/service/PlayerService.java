package com.futbolliga.service;

import com.futbolliga.dto.PlayerDTO;
import com.futbolliga.entity.Player;
import com.futbolliga.mapper.PlayerMapper;
import com.futbolliga.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PlayerService {

    private final PlayerRepository repository;

    @Transactional
    public PlayerDTO savePlayer(PlayerDTO playerDTO){
        Player player = PlayerMapper.toEntity(playerDTO);
        Player saved = repository.save(player);
        return PlayerMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<PlayerDTO> getAllPlayers(){
        return repository.findAll()
                .stream()
                .map(PlayerMapper::toDTO)
                .toList();
    }

    @Transactional
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO){
        return repository.findById(id)
                .map(player -> {
                    player.setAge(playerDTO.age());
                    player.setName(playerDTO.name());
                    player.setPosition(playerDTO.position());
                    return repository.save(player);
                })
                .map(PlayerMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    @Transactional
    public void deletePlayer(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Player not found with id: " + id);
        }
        repository.deleteById(id);
    }


}
