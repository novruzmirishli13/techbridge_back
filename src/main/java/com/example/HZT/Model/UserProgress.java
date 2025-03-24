package com.example.HZT.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int highestLevelUnlocked = 1;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getHighestLevelUnlocked() {
		return highestLevelUnlocked;
	}
	public void setHighestLevelUnlocked(int highestLevelUnlocked) {
		this.highestLevelUnlocked = highestLevelUnlocked;
	}

}
