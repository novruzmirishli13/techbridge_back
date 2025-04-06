package com.example.HZT.Dto;

public class GameResult {
    private boolean success;
    private String message;
    private int finalX;
    private int finalY;
    private String hint;

    public GameResult(boolean success, String message, int finalX, int finalY, String hint) {
        this.success = success;
        this.message = message;
        this.finalX = finalX;
        this.finalY = finalY;
        this.hint = hint;
    }

    public boolean isSuccess() { 
        return success; 
    }
    public String getMessage() { 
        return message; 
    }
    public int getFinalX() { 
        return finalX; 
    }
    public int getFinalY() { 
        return finalY; 
    }
    public String getHint() { 
        return hint; 
    }
}