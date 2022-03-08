/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anotheria;

/**
 *
 * @author sandeepsingh
 */
public class Player {
    private String name;
    private int runs;
    private int balls;
    
    public Player(String name, int runs,int balls) {
    this.name = name;
    this.balls = 0;
    this.runs = 0;
    }
    public Player(String name) {
        this.name = name;
        this.balls = 0;
        this.runs = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBalls() {
        return balls;
    }
    
    public int getRuns() {
        return runs;
    }
    
    public void setRuns(int runs) {
        balls++;
        this.runs+= runs; 
    }
    
    public void incrementRuns(int runs) {
        this.runs+= runs;
    }
    
    public String toString() {
        return name;
    }    
}
