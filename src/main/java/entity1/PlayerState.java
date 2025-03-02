package entity1;
package com.example.blockgame.model;

import javax.persistence.*;

@Entity
public class PlayerState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int x;
    private int y;

    public PlayerState() {}

    public PlayerState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Long getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
