package com.mycompany.anotheria;

import javafx.collections.ObservableList;

public class Inning {

    private ObservableList<Batsmen> batters = BattingTeamController.getPlayers();
    private  ObservableList<Bowler> bowlers = BowlingTeamController.getPlayers();
    
    private int totalRuns = 0;
    private int totalWickets = 0;private String teamName;

    public ObservableList<Batsmen> getBatters() {
        return batters;
    }

    public void setBatters(ObservableList<Batsmen> batters) {
        this.batters = batters;
    }

    public ObservableList<Bowler> getBowlers() {
        return bowlers;
    }

    public void setBowlers(ObservableList<Bowler> bowlers) {
        this.bowlers = bowlers;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
