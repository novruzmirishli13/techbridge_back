package com.example.HZT.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String date;
    private String description;
    private String imageUrl;

    public Event() {

    }

    public Event(String title, String date, String description, String imageUrl) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id;
    }

    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getDate() { 
        return date; 
    }
    public void setDate(String date) {
         this.date = date; 
        }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }

    public String getImageUrl() { 
        return imageUrl; 
    }
    public void setImageUrl(String imageUrl) {
         this.imageUrl = imageUrl; 
        }
}