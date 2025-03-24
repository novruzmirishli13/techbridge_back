package com.example.HZT.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HZT.Model.UserProgress;
import com.example.HZT.Repository.UserProgressRepository;

@Service
public class UserProgressService {

    @Autowired
    private UserProgressRepository repository;

    public int getUnlockedLevel(String username) {
        return repository.findByUsername(username)
                .map(UserProgress::getHighestLevelUnlocked)
                .orElse(1);
    }

    public boolean unlockNextLevel(String username, int completedLevel) {
        Optional<UserProgress> userProgress = repository.findByUsername(username);

        if (userProgress.isPresent()) {
            UserProgress progress = userProgress.get();
            if (completedLevel == progress.getHighestLevelUnlocked()) {
                progress.setHighestLevelUnlocked(progress.getHighestLevelUnlocked() + 1);
                repository.save(progress);
                return true;
            }
        }
        return false;
    }
}