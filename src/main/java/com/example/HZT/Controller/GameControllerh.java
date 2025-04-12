package com.example.HZT.Controller;

import com.example.HZT.Dto.GameResult;
import com.example.HZT.Entity.Block;
import com.example.HZT.Entity.Program;
import com.example.HZT.Repository.ProgramRepository;
import com.example.HZT.Service.GameEngineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/game")
public class GameControllerh {

    @Autowired 
    private GameEngineService gameEngineService;

    @Autowired 
    private ProgramRepository programRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        System.out.println("Showing form...");
        model.addAttribute("directions", "");
        return "index";
    }
    

    @PostMapping("/run")
    public String runProgram(@RequestParam("directions") String directions, Model model){
        List<Block> blocks = Arrays.stream(directions.split(","))
                .map(String::trim)
                .map(dir -> {
                    Block b = new Block();
                    b.setDirection(dir.toLowerCase());
                    b.setAction("move");
                    return b;
                }).collect(Collectors.toList());

        Program program = new Program();
        program.setBlocks(blocks);
        programRepository.save(program);

        GameResult result = gameEngineService.executeProgram(program);
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/programs")
    public String showPrograms(Model model) {
        model.addAttribute("programs", programRepository.findAll());
        return "programs";
    }
}