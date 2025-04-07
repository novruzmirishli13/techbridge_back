package com.example.HZT.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.HZT.Entity.Progress;
import com.example.HZT.Repository.ProgressRepository;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressRepository progressRepository;

    @PostMapping("/update")
    public ResponseEntity<Progress> updateProgress(@RequestBody Progress progress) {
        Optional<Progress> existing = progressRepository.findByUserIdAndLessonName(
            progress.getUserId(),
            progress.getLessonName()
        );

        Progress savedProgress;

        if (existing.isPresent()) {
            Progress existingProgress = existing.get();
            existingProgress.setProgressLevel(progress.getProgressLevel());
            savedProgress = progressRepository.save(existingProgress);
        } else {
            savedProgress = progressRepository.save(progress);
        }

        return ResponseEntity.ok(savedProgress);
    }

    @GetMapping("/{userId}/{lessonName}")
    public ResponseEntity<Progress> getProgress(
        @PathVariable("userId") Long userId,
        @PathVariable("lessonName") String lessonName
    ) {
        Optional<Progress> progress = progressRepository.findByUserIdAndLessonName(userId, lessonName);

        if (progress.isPresent()) {
            return ResponseEntity.ok(progress.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
