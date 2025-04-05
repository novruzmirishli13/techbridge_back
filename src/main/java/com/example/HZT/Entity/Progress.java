package com.example.HZT.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "progresses")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String lessonName;
    private int progressLevel;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getLessonName() {
        return lessonName;
    }
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
    public int getProgressLevel() {
        return progressLevel;
    }
    public void setProgressLevel(int progressLevel) {
        this.progressLevel = progressLevel;
    }
    
    
}