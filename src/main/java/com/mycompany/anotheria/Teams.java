/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anotheria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sandeepsingh
 */
public class Teams {
    private ObservableList<Batsmen> batsmenlist = FXCollections.observableArrayList();
    private ObservableList<Bowler> bowlerlist = FXCollections.observableArrayList();
    
    private int balls;
    private int runs;
    private int extrasNoBalls;
    private int extrasWides;
    private int extrasLegByes;
    private int extrasByes;
    private int wickets;
    private int dotBalls;
    private static int totalOversAvailable;
    private double oversDone;
    
    public Teams(Batsmen p1, Batsmen p2, Batsmen p3, Batsmen p4, Batsmen p5, 
            Batsmen p6, Batsmen p7, Batsmen p8, Batsmen p9, Batsmen p10, Batsmen p11) {
        
        batsmenlist.addAll (p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
        balls = 0;
        extrasNoBalls = 0;
        extrasWides = 0;
        extrasLegByes = 0;
        extrasByes = 0;
        wickets = 0;
        dotBalls = 0;
    }
    
    public Teams(Bowler p1, Bowler p2, Bowler p3, Bowler p4, Bowler p5, 
            Bowler p6, Bowler p7, Bowler p8, Bowler p9, Bowler p10, Bowler p11) {
        
        bowlerlist.addAll (p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);
        balls = 0;
    }
    
    public ObservableList<Batsmen> getBatsmenTeam() {
        return batsmenlist;
    }
    
    public ObservableList<Bowler> getBowlerTeam() {
        return bowlerlist;
    }
    
    public void increaseBalls() {
        balls++;
    }
    
    public void increaseRuns(int i) {
        runs+=i;
    }
    
    public int getBalls() {
        return balls;
    }
    
    public int getRuns() {
        return runs;
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

    public double getOversDone() {
        return oversDone;
    }

    public void setOversDone(double oversDone) {
        this.oversDone = oversDone;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }

    public Boolean totalOversDone(){
        int totalBallsAvailable = (BattingTeamController.getTeam().totalOversAvailable) * 6;

        System.out.println("totalBallsAvailable : "+totalBallsAvailable);
        System.out.println("get Balls: "+BattingTeamController.getTeam().getBalls());

        if( BattingTeamController.getTeam().getBalls() == totalBallsAvailable){
            return true;
        }
        else{
            return false;
        }
    }

    public static int getTotalOversAvailable() {
        return totalOversAvailable;
    }

    public static void setTotalOversAvailable(int totalOversAvailable) {
        Teams.totalOversAvailable = totalOversAvailable;
    }    
}