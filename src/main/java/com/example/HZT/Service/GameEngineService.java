package com.example.HZT.Service;

import org.springframework.stereotype.Service;

import com.example.HZT.Dto.GameResult;
import com.example.HZT.Entity.Block;
import com.example.HZT.Entity.Program;

@Service
public class GameEngineService {

    private final int targetX = 0;
    private final int targetY = -1;

    public GameResult executeProgram(Program program) {
        int x = 0, y = 0;

        for (Block block : program.getBlocks()) {
            switch (block.getDirection()) {
                case "north": y++; break;
                case "south": y--; break;
                case "east": x++; break;
                case "west": x--; break;
            }
        }

        boolean success = (x == targetX && y == targetY);
        String message = success ? "Təbriklər! Hədəfə çatdınız." : "Hədəfə çatılmadı.";
        String hint = generateHint(x, y);

        return new GameResult(success, message, x, y, hint);
    }

    private String generateHint(int currentX, int currentY) {
        if (currentY < targetY) return "Bir neçə Şimal blok əlavə et.";
        if (currentY > targetY) return "Bir neçə Cənub blok əlavə et.";
        if (currentX < targetX) return "Bir neçə Şərq blok əlavə et.";
        if (currentX > targetX) return "Bir neçə Qərb blok əlavə et.";
        return "Hər şey qaydasındadır.";
    }
}