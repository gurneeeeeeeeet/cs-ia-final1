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
public class Batsmen extends Player{
    private int fours;
    private int sixes;
    private double strikeRate;
    private String howOut;
    private int dotBalls;
    private int runs;
    private int balls;
    private int totalRuns;
    private String runsStr;

    public Batsmen(String name,int runs,int balls) {
        super(name,runs,balls);
        this.fours = 0;
        this.sixes = 0;
        this.strikeRate = 0;
        this.howOut = "Yet to Bat";
        this.dotBalls = 0;
        this.totalRuns = 0;
    }

    public int getFours() {
        return fours;
    }

    public void incrementFours() {
        fours++;
    }

    public int getSixes() {
        return sixes;
    }

    public void incrementSixes() {
        sixes++;
    }

    public double calculateStrikeRate() {

        strikeRate = ( (double ) super.getRuns() / super.getBalls()) * 100;
        Math.round((strikeRate * 100) / 100);
        return strikeRate;
    }

    public String getHowOut() {
        return howOut;
    }

    public void howOut(String str) {
        this.howOut = str;
    }

    public void setBatsmenStrikeRate(double num) {
        this.strikeRate = num;
    }

    public int getDotBalls() {
        return dotBalls;
    }

    public void increaseDotBalls() {
        this.dotBalls++;
    }
    public void increaseBalls() {
        this.balls++;
    }
    @Override
    public int getRuns() {
        return super.getRuns();
    }

    @Override
    public void setRuns(int runs) {
        super.setRuns(runs);
        this.runs += runs;
        this.runsStr = ""+this.runs;
    }

    @Override
    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
        this.runsStr = ""+totalRuns;
    }

    public String getRunsStr() {
        return runsStr;
    }

    public void setRunsStr(String runsStr) {
        this.runsStr = runsStr;
    }
}
    
    
    
  
     
    
    
   