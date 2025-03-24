package com.example.HZT.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HZT.Entity.PlayerState;
import com.example.HZT.Repository.PlayerRepository;

@Service
public class GameService {
    private final int GRID_SIZE = 10;
    private final PlayerRepository playerRepository;

    public GameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerState processCommands(List<String> commands) {
        PlayerState playerState = playerRepository.findById(1L).orElse(new PlayerState(0, 0));

        for (String command : commands) {
            switch (command.toLowerCase()) {
                case "şimal": // North
                    if (playerState.getY() > 0) playerState.setY(playerState.getY() - 1);
                    break;
                case "cənub": // South
                    if (playerState.getY() < GRID_SIZE - 1) playerState.setY(playerState.getY() + 1);
                    break;
                case "şərq": // East
                    if (playerState.getX() < GRID_SIZE - 1) playerState.setX(playerState.getX() + 1);
                    break;
                case "qərb": // West
                    if (playerState.getX() > 0) playerState.setX(playerState.getX() - 1);
                    break;
            }
        }

        playerRepository.save(playerState);
        return playerState;
    }
}
