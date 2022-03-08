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
public class Bowler extends Player {
    private int overs;
    private double economy;
    private int extrasNoBalls;
    private int extrasWides;
    private int extrasLegByes;
    private int extrasByes;
    private int wickets;
    private int dotBalls;
    private String completeOvers;
    private int dotPointBalls;
    
    public Bowler(String name) {
        super(name);
        this.overs = 0;
        this.economy = 0;
        this.extrasNoBalls = 0;
        this.extrasWides = 0;
        this.extrasLegByes = 0;
        this.extrasByes = 0;
        this.wickets = 0;
        this.dotBalls = 0; 
        this.completeOvers = "";
        this.dotPointBalls = 0;
    }
    
    public String getCompleteOvers() {
        return completeOvers = "" + overs + "." + dotPointBalls;     
    }
    
    public int getDotPointBalls() {
        return dotPointBalls;
    }
    
    public void increaseDotPointBalls() {
        dotPointBalls++;
        
        if (dotPointBalls >= 6) {
            dotPointBalls = 0;
        }
    }
    
    public double getOvers() {
        return overs;
    }

    public void incrementOvers() {
        if (super.getBalls() % 6 == 0) {
            overs++;
        }
    }
    public void calculateEconomy() {
        economy = ((double) super.getRuns() / super.getBalls()) * 6;   
    }
    
    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
       this.economy = economy;
    }
    
    public void increaseRuns(int runs) {
        super.incrementRuns(runs);
    }
    
    public int getExtrasNoBalls() {
        return extrasNoBalls;
    }
    
    public void increaseExtrasNoBalls() {
        extrasNoBalls++;
    }
    
    public int getExtrasWides() {
        return extrasWides;
    }
    
    public void increaseExtrasWides(int i) {
        extrasWides+=i;
    }
    
    public int getExtrasLegByes() {
        return extrasLegByes;
    }
    
    public void increaseExtrasLegByes(int i) {
        extrasLegByes+=i;
    }
    
    public int getExtrasByes() {
        return extrasByes;
    }
    
    public void increaseExtrasByes(int i) {
        extrasByes+=i;
    }
    
    public int getWickets() {
        return wickets;
    }

    public void increaseWickets() {
        this.wickets++;
    }
    
    public int getDotBalls() {
        return dotBalls;
    }
    
    public void increaseDotBalls() {
        this.dotBalls++;
    }
    
    public String toString(){
        return this.getName();
    }
}