/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anotheria;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author sandeepsingh
 */
public class InformationRecorderController implements Initializable {
    @FXML
    private Button battingTeamSelection;
    
    @FXML
    private Button bowlingTeamSelection;

    @FXML
    private TextField noOfOvers;
    
    @FXML
    private Button submit;

    @FXML
    private TextField homeTeam;

    @FXML
    private TextField awayTeam;

    private static int numberOfOvers;
    private static String homeTeamName;
    private static String awayTeamName;
    private static String numOvers;

    public static String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public static String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getNumOvers() {
        return numOvers;
    }

    public static int getNumberOfOvers() {
        return numberOfOvers;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        homeTeam.setText(homeTeamName);
        awayTeam.setText(awayTeamName);
        noOfOvers.setText(String.valueOf(numberOfOvers));
    }    
    @FXML
    private void switchToBattingSelection() throws IOException {
        homeTeamName = homeTeam.getText();
        if(homeTeamName == null || homeTeamName.isBlank()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please Enter Home Team Name");
            a.show();
        }
        else {
            String overs = noOfOvers.getText();
            numberOfOvers = Integer.parseInt(overs);
            awayTeamName = awayTeam.getText();
            System.out.println("Batting team name : " + homeTeamName);
            switchToSelection(battingTeamSelection, " Batting Team Selection", "battingTeam");
        }
    }
          
    @FXML //This Method is used to switch from one window to another
    private void switchToSelection(Button btn, String title, String fxml) throws IOException {
        Stage window = (Stage) btn.getScene().getWindow();//Makes a new stage, and sets it equal to the current stage
        if (fxml == "battingTeam" || fxml == "bowlingTeam") { //we do not want to close the current window if we have to enter the details of the players
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml")); // loads the fxml file for entering the players
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } else {
            window.close(); ///The current window closes
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            Scene scene = new Scene (root); ///Makes a new scene with the FXML file selected
            window.setTitle(title);  ///sets the title of the window
            window.setScene(scene); ///We go to the next slide
            window.setResizable(false); //Sets the new window to not be resizeable
            window.show(); //Shows the next window
        }
    }
    
    @FXML
    private void switchToBowlingSelection() throws IOException {
        awayTeamName = awayTeam.getText();
        if(awayTeamName == null || awayTeamName.isBlank() || awayTeamName.length() < 1){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please Enter Away Team Name");
            a.show();
        }
        else {

            System.out.println("Bowling team name : " + awayTeamName);
            switchToSelection(bowlingTeamSelection, "Bowling Team Selection", "bowlingTeam");
        }
    }  
    
    @FXML
    private void switchToGameRecorder() throws IOException {
        String overs = noOfOvers.getText();
        numberOfOvers = Integer.parseInt(overs);
        if(numberOfOvers < 1){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Number of Overs cannot be ZERO");
            a.show();
        } else if (awayTeamName == null || awayTeamName.isBlank() || awayTeamName.length() < 1 || homeTeamName == null || homeTeamName.isBlank() || homeTeamName.length() < 1) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Make sure you have entered the HOME TEAM and AWAY TEAM!");
            a.show();
        }
        else {
            numberOfOvers = Integer.parseInt(overs);
            System.out.println("overs:" + overs);
            switchToSelection(submit, "Game Recorder", "gameRecorder");
        }
    }
    
}
