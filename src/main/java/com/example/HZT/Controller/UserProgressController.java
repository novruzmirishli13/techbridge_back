package com.example.HZT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HZT.Entity.LevelRequest;
import com.example.HZT.Service.UserProgressService;
   
@RestController
@RequestMapping("/api/progress")
public class UserProgressController {

    @Autowired
    private UserProgressService service;

    @GetMapping("/{username}")
    public int getUnlockedLevel(@PathVariable String username) {
        return service.getUnlockedLevel(username);
    }

@PostMapping("/unlock")
public String unlockNextLevel(@RequestBody LevelRequest request) {
    boolean unlocked = service.unlockNextLevel(request.getUsername(), request.getCompletedLevel());
    return unlocked ? "Next level unlocked!" : "Level not unlocked.";
    }

}
