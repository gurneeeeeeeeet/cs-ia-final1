/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anotheria;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author sandeepsingh
 */
public class BowlingTeamController implements Initializable {
    @FXML
    private Button submit;
    
    @FXML
    private TextField p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    
    @FXML
    private TextField j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11;
    
    private static Teams bowlingTeam;

    private static String teamName;

    @FXML 
    private void submitAndGoBack() throws IOException {  ///We go back to the information recorder file
        if(!checkEmptyFields()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter all the team members");
            a.show();
        }
        else {
            Bowler b1 = new Bowler(p1.getText() + ", " + j1.getText());
            Bowler b2 = new Bowler(p2.getText() + ", " + j2.getText());
            Bowler b3 = new Bowler(p3.getText() + ", " + j3.getText());
            Bowler b4 = new Bowler(p4.getText() + ", " + j4.getText());
            Bowler b5 = new Bowler(p5.getText() + ", " + j5.getText());
            Bowler b6 = new Bowler(p6.getText() + ", " + j6.getText());
            Bowler b7 = new Bowler(p7.getText() + ", " + j7.getText());
            Bowler b8 = new Bowler(p8.getText() + ", " + j8.getText());
            Bowler b9 = new Bowler(p9.getText() + ", " + j9.getText());
            Bowler b10 = new Bowler(p10.getText() + ", " + j10.getText());
            Bowler b11 = new Bowler(p11.getText() + ", " + j11.getText());

            bowlingTeam = new Teams(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11);
            this.teamName = InformationRecorderController.getAwayTeamName();
            System.out.println("Bowling team name : " + teamName);
            switchToSelection(submit, "Information Recorder", "informationRecorder");
        }

    }

    public Boolean checkEmptyFields(){
        if(p1.getText().isEmpty() || p1.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p2.getText().isEmpty() || p2.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p3.getText().isEmpty() || p3.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p4.getText().isEmpty() || p4.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p5.getText().isEmpty() || p5.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p6.getText().isEmpty() || p6.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p7.getText().isEmpty() || p7.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p8.getText().isEmpty() || p8.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p9.getText().isEmpty() || p9.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p9.getText().isEmpty() || p9.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p10.getText().isEmpty() || p10.getText().isBlank()){
            return Boolean.FALSE;
        }
        if(p11.getText().isEmpty() || p11.getText().isBlank()){
            return Boolean.FALSE;
        }
        else
            return true;


    }
    public static ObservableList<Bowler> populateBowlingTeam(ObservableList<Batsmen> batsmenList) {
        Bowler b1 = new Bowler(batsmenList.get(0).getName());
        Bowler b2 = new Bowler(batsmenList.get(1).getName());
        Bowler b3 = new Bowler(batsmenList.get(2).getName());
        Bowler b4 = new Bowler(batsmenList.get(3).getName());
        Bowler b5 = new Bowler(batsmenList.get(4).getName());
        Bowler b6 = new Bowler(batsmenList.get(5).getName());
        Bowler b7 = new Bowler(batsmenList.get(6).getName());
        Bowler b8 = new Bowler(batsmenList.get(7).getName());
        Bowler b9 = new Bowler(batsmenList.get(8).getName());
        Bowler b10 = new Bowler(batsmenList.get(9).getName());
        Bowler b11 = new Bowler(batsmenList.get(10).getName());

        bowlingTeam = new Teams(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11);

        return bowlingTeam.getBowlerTeam();
    }
    public static ObservableList<Bowler> getPlayers() {
       return bowlingTeam.getBowlerTeam();
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     @FXML //This Method is used to switch from one window to another
    private void switchToSelection(Button btn, String title, String fxml) throws IOException {
        Stage window = (Stage) btn.getScene().getWindow();  //Makes a new stage, and sets it equal to the current stage
        window.close(); ///The current window closes
    }

    public static String getTeamName() {
        return teamName;
    }

    public static void setTeamName(String newTeamName) {
        teamName = newTeamName;
    }
    
    public static Teams getBowlingTeam() {
        return bowlingTeam;
    }

    public static void setBowlingTeam(Teams newBowlingTeam) {
        bowlingTeam = newBowlingTeam;
    }
    
}



 