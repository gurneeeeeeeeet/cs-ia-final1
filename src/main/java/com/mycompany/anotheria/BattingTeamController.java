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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author sandeepsingh
 */
public class BattingTeamController implements Initializable {

    @FXML
    private Button submit;
    
    @FXML
    private TextField p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    
    @FXML
    private TextField j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11;
    
    private static Teams battingTeam;

    private static String teamName;

    @FXML 
    private void submitAndGoBack() throws IOException {
        
        if(!checkEmptyFields()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please enter all the team members");
            a.show();
        }
        else{ //makes Batsmen objects with the data entered in the text fields
            Batsmen b1 = new Batsmen(p1.getText() + ", " + j1.getText(), 0, 0);
            Batsmen b2 = new Batsmen(p2.getText() + ", " + j2.getText(), 0, 0);
            Batsmen b3 = new Batsmen(p3.getText() + ", " + j3.getText(), 0, 0);
            Batsmen b4 = new Batsmen(p4.getText() + ", " + j4.getText(), 0, 0);
            Batsmen b5 = new Batsmen(p5.getText() + ", " + j5.getText(), 0, 0);
            Batsmen b6 = new Batsmen(p6.getText() + ", " + j6.getText(), 0, 0);
            Batsmen b7 = new Batsmen(p7.getText() + ", " + j7.getText(), 0, 0);
            Batsmen b8 = new Batsmen(p8.getText() + ", " + j8.getText(), 0, 0);
            Batsmen b9 = new Batsmen(p9.getText() + ", " + j9.getText(), 0, 0);
            Batsmen b10 = new Batsmen(p10.getText() + ", " + j10.getText(), 0, 0);
            Batsmen b11 = new Batsmen(p11.getText() + ", " + j11.getText(), 0, 0);

            //Makes a Team object out of all the batsmen object
            battingTeam = new Teams(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11);
            teamName = InformationRecorderController.getHomeTeamName();
            System.out.println("Batting team name : " + teamName);

            switchToSelection(submit, "Information Recorder", "informationRecorder");
        }
    }

    public boolean checkEmptyFields(){ //method to check if the user left any of the fields empty
        if(p1.getText().isEmpty() || p1.getText().isBlank()){
            return false;
        }
        if(p2.getText().isEmpty() || p2.getText().isBlank()){
            return false;
        }
        if(p3.getText().isEmpty() || p3.getText().isBlank()){
            return false;
        }
        if(p4.getText().isEmpty() || p4.getText().isBlank()){
            return false;
        }
        if(p5.getText().isEmpty() || p5.getText().isBlank()){
            return false;
        }
        if(p6.getText().isEmpty() || p6.getText().isBlank()){
            return false;
        }
        if(p7.getText().isEmpty() || p7.getText().isBlank()){
            return false;
        }
        if(p8.getText().isEmpty() || p8.getText().isBlank()){
            return false;
        }
        if(p9.getText().isEmpty() || p9.getText().isBlank()){
            return false;
        }
        if(p9.getText().isEmpty() || p9.getText().isBlank()){
            return false;
        }
        if(p10.getText().isEmpty() || p10.getText().isBlank()){
            return false;
        }
        if(p11.getText().isEmpty() || p11.getText().isBlank()){
            return false;
        }
        else
            return true;


    }
    public static ObservableList<Batsmen> getPlayers() {
       return battingTeam.getBatsmenTeam();
    }

    // method used to make a Batter objects and Team objects (used in GameRecorderController)
    public static void populateBattingTeam(ObservableList<Bowler> bowlerList) {

        Batsmen b1 = new Batsmen(bowlerList.get(0).getName(),0,0);
        Batsmen b2 = new Batsmen(bowlerList.get(1).getName(),0,0);
        Batsmen b3 = new Batsmen(bowlerList.get(2).getName(),0,0);
        Batsmen b4 = new Batsmen(bowlerList.get(3).getName(),0,0);
        Batsmen b5 = new Batsmen(bowlerList.get(4).getName(),0,0);
        Batsmen b6 = new Batsmen(bowlerList.get(5).getName(),0,0);
        Batsmen b7 = new Batsmen(bowlerList.get(6).getName(),0,0);
        Batsmen b8 = new Batsmen(bowlerList.get(7).getName(),0,0);
        Batsmen b9 = new Batsmen(bowlerList.get(8).getName(),0,0);
        Batsmen b10 = new Batsmen(bowlerList.get(9).getName(),0,0);
        Batsmen b11 = new Batsmen(bowlerList.get(10).getName(),0,0);

        //Makes a Team object out of all the batsmen object
        battingTeam = new Teams(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11);
        System.out.println("new basman: "+b1.getName());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     @FXML
    private void switchToSelection(Button btn, String title, String fxml) throws IOException {
        Stage window = (Stage) btn.getScene().getWindow();
        window.close();
    }
    
    public static Teams getTeam() {
        return battingTeam;
    }

    public static String getTeamName() {
        return teamName;
    }

    public static void setTeamName(String newTeamName) {
        teamName = newTeamName;
    }
}
