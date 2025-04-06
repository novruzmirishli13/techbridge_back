package com.example.HZT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HZT.Dto.GameResult;
import com.example.HZT.Dto.ProgramDto;
import com.example.HZT.Entity.Program;
import com.example.HZT.Service.GameEngineService;

@RestController
@RequestMapping("/api/game")
public class GameController1 {

    @Autowired
    private GameEngineService gameEngineService;

    @PostMapping("/execute")
    public ResponseEntity<GameResult> execute(@RequestBody ProgramDto programDTO) {
        Program program = new Program();
        program.setBlocks(programDTO.getBlocks());
        
        GameResult result = gameEngineService.executeProgram(program);
        return ResponseEntity.ok(result);
 
    }
        @GetMapping("/status")
        public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Game status: Active");
}

}