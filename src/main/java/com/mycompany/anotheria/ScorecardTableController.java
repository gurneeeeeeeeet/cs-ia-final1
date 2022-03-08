/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anotheria;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
/**
 * FXML Controller class
 *
 * @author sandeepsingh
 */
public class ScorecardTableController implements Initializable {
    @FXML
    private TableView <Batsmen> firstBattingTeamScorecard = new TableView<Batsmen>();
    
    @FXML
    private TableView <Bowler> firstBowlingTeamScorecard = new TableView<Bowler>();

    @FXML
    private TextField totalScore;
    
    @FXML
    private Button backtoSlideTwo;

    @FXML
    private Button slideFour, export;
    
    @FXML 
    private Label allExtrasCount;
    
    private int inningCount = 1;
    
    
    
    private ObservableList<Batsmen> batters;
    private ObservableList<Bowler> bowlers;

    private String projectedScore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstBattingTeamScorecard.setEditable(true);
        firstBowlingTeamScorecard.setEditable(true);
        bowlers = GameRecorderController.getBowlers();

        int sumOfExtras = BowlingTeamController.getBowlingTeam().getExtrasByes() +
                BowlingTeamController.getBowlingTeam().getExtrasLegByes() + 
                BowlingTeamController.getBowlingTeam().getExtrasNoBalls() + 
                BowlingTeamController.getBowlingTeam().getExtrasWides();
        
        allExtrasCount.setText("Extras: " + sumOfExtras + " (Byes: " + 
                BowlingTeamController.getBowlingTeam().getExtrasByes() + ", Leg Byes: " + 
                BowlingTeamController.getBowlingTeam().getExtrasLegByes() +  ", Wides: " + 
                BowlingTeamController.getBowlingTeam().getExtrasWides() + ", No Balls: " + 
                BowlingTeamController.getBowlingTeam().getExtrasNoBalls() + ")");
        
        batters = GameRecorderController.getBatters();
        //calculating projected score
        System.out.println("BattingTeamController.getTeam().getRuns()" +BattingTeamController.getTeam().getRuns());

        this.projectedScore = BattingTeamController.getTeam().getRuns()+" Runs and "
                + BattingTeamController.getTeam().getWickets()+" Wickets "
                + BattingTeamController.getTeam().getOversDone()+" Overs";

        totalScore.setText(""+projectedScore);
        System.out.println("projectedScore : "+projectedScore);

        TableColumn playersName = new TableColumn("Player's Name");
        playersName.setMinWidth(100);
        playersName.setResizable(false);
        playersName.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("name"));
       
        TableColumn ballsFaced = new TableColumn("Balls");
        ballsFaced.setEditable(true);
        ballsFaced.setMinWidth(100);
        ballsFaced.setResizable(false);
        ballsFaced.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("balls"));
        
        //TableColumn dotBallsPlayed = new TableColumn("Dot Balls");
        //dotBallsPlayed.setMinWidth(60);
        //dotBallsPlayed.setResizable(false);
        //dotBallsPlayed.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("dotBalls"));
        
        TableColumn howBatsmenOut = new TableColumn("How Out");
        howBatsmenOut.setMinWidth(125);
        howBatsmenOut.setResizable(false);
        howBatsmenOut.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("howOut"));
       
        TableColumn runsScored = new TableColumn("Runs");
        runsScored.setMinWidth(75);
        runsScored.setResizable(false);
        runsScored.setEditable(true);
        runsScored.setCellFactory(TextFieldTableCell.forTableColumn());
        runsScored.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("runsStr"));

       runsScored.setOnEditCommit(
                new EventHandler<CellEditEvent<Batsmen, String>>() {
                    @Override
                    public void handle(CellEditEvent<Batsmen, String> t) {
                        System.out.println("value changed: "+t.getOldValue()+" new value: "+t.getNewValue());
                        ((Batsmen) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setTotalRuns(Integer.parseInt(t.getNewValue()));

                        System.out.println("updating total runs and strikerate");

                        updateTotalRuns(t.getRowValue(),t.getOldValue(),t.getNewValue());

                    }
                }
        );
       
        TableColumn foursScored = new TableColumn ("4's");
        foursScored.setMinWidth(60);
        foursScored.setResizable(false);
        foursScored.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("fours"));
       
        TableColumn sixesScored = new TableColumn ("6's");
        sixesScored.setMinWidth(60);
        sixesScored.setResizable(false);
        sixesScored.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("sixes"));
       
        TableColumn batsmenStrikeRate = new TableColumn ("Strike Rate");
        batsmenStrikeRate.setMinWidth(75);
        batsmenStrikeRate.setResizable(false);
        batsmenStrikeRate.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("strikeRate"));



        firstBattingTeamScorecard.setItems(batters);

        firstBattingTeamScorecard.getColumns().addAll(playersName, howBatsmenOut, ballsFaced, runsScored, 
                foursScored, sixesScored, batsmenStrikeRate);
        

        //Bowling Team Deatails
        
        TableColumn bowlersplayersName = new TableColumn("Player's Name");
        bowlersplayersName.setMinWidth(100);
        bowlersplayersName.setResizable(false);
        bowlersplayersName.setCellValueFactory(new PropertyValueFactory<Bowler, String>("name"));
        
        TableColumn runsConceded = new TableColumn("Runs");
        runsConceded.setMinWidth(75);
        runsConceded.setEditable(true);
        runsConceded.setResizable(false);
        runsConceded.setCellValueFactory(new PropertyValueFactory<Bowler, String>("runs"));
        
        TableColumn dotBallsBowled = new TableColumn("Dots");
        dotBallsBowled.setMinWidth(75);
        dotBallsBowled.setResizable(false);
        dotBallsBowled.setCellValueFactory(new PropertyValueFactory<Bowler, String>("dotBalls"));
        
        TableColumn legByesBowled = new TableColumn("Leg Byes");
        legByesBowled.setMinWidth(75);
        legByesBowled.setResizable(false);
        legByesBowled.setCellValueFactory(new PropertyValueFactory<Bowler, String>("extrasLegByes"));
        
        TableColumn byesBowled = new TableColumn("Byes");
        byesBowled.setMinWidth(75);
        byesBowled.setResizable(false);
        byesBowled.setCellValueFactory(new PropertyValueFactory<Bowler, String>("extrasByes"));
        
        TableColumn wicketsTaken = new TableColumn("Wickets");
        wicketsTaken.setMinWidth(75);
        wicketsTaken.setResizable(false);
        wicketsTaken.setCellValueFactory(new PropertyValueFactory<Bowler, String>("wickets"));
        
        TableColumn noBallsBowled = new TableColumn("No-Balls");
        noBallsBowled.setMinWidth(75);
        noBallsBowled.setResizable(false);
        noBallsBowled.setCellValueFactory(new PropertyValueFactory<Bowler, String>("extrasNoBalls"));
        
        TableColumn widesBowled = new TableColumn("Wides");
        widesBowled.setMinWidth(75);
        widesBowled.setResizable(false);
        widesBowled.setCellValueFactory(new PropertyValueFactory<Bowler, String>("extrasWides"));
        
        TableColumn playerBallsBowled = new TableColumn("Balls");
        playerBallsBowled.setMinWidth(75);
        playerBallsBowled.setResizable(false);
        playerBallsBowled.setCellValueFactory(new PropertyValueFactory<Bowler, String>("balls"));
        
        TableColumn playerOversBowled = new TableColumn("Overs");
        playerOversBowled.setMinWidth(75);
        playerOversBowled.setResizable(false);
        playerOversBowled.setCellValueFactory(new PropertyValueFactory<Bowler, String>("completeOvers"));
        
        TableColumn playerEconomy = new TableColumn("Economy");
        playerEconomy.setMinWidth(75);
        playerEconomy.setResizable(false);
        playerEconomy.setCellValueFactory(new PropertyValueFactory<Bowler, String>("economy"));
        
        
        firstBowlingTeamScorecard.setItems(bowlers);
        firstBowlingTeamScorecard.getColumns().addAll(bowlersplayersName, playerBallsBowled, playerOversBowled, 
                runsConceded, dotBallsBowled, wicketsTaken, noBallsBowled, widesBowled, byesBowled, 
                legByesBowled, playerEconomy);
    }    

    public void updateTotalRuns(Batsmen b,String oldValue,String newValue){

        int oldRuns = Integer.parseInt(oldValue);
        int newRuns = Integer.parseInt(newValue);

        int tempRuns = BattingTeamController.getTeam().getRuns()- oldRuns;
        tempRuns = tempRuns + newRuns;

        System.out.println("old total runs : "+BattingTeamController.getTeam().getRuns()+" New Runs : "+tempRuns);
        BattingTeamController.getTeam().setRuns(tempRuns);

        System.out.println("old runs : "+b.getTotalRuns()+ " old strikerate:"+b.getStrikeRate());
        b.setTotalRuns(newRuns);
        b.setRuns(newRuns);

        b.setStrikeRate(GameRecorderController.formatDoubleValue(b.calculateStrikeRate()));
        System.out.println("new runs: "+b.getTotalRuns()+" new strikerate:"+b.getStrikeRate());

        this.projectedScore = BattingTeamController.getTeam().getRuns()+" Runs and "
                + BattingTeamController.getTeam().getWickets()+" Wickets "
                + BattingTeamController.getTeam().getOversDone()+" Overs";
    }
    private void switchToSelection(Button btn, String title, String fxml) throws IOException {
        Stage window = (Stage) btn.getScene().getWindow();
        window.close();
        Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
        Scene scene = new Scene (root);
        window.setTitle(title);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
    
    @FXML // passes the tableViews as parameters to export()
    public void export() throws IOException {
        export(firstBattingTeamScorecard, firstBowlingTeamScorecard);
    }    
    
    public void export(TableView<Batsmen> tableViewBatters, TableView<Bowler> tableViewBowlers) throws FileNotFoundException, IOException{

        Workbook workbook = new HSSFWorkbook(); // declares and initializes a new workbook
        Sheet spreadsheet = workbook.createSheet("sample"); // creates a new sheet inside the workbook
        
        CellStyle boldStyle = workbook.createCellStyle(); // creates a new cellStyle
        Font boldFont = workbook.createFont(); // creates a new font so that headingspf the columns and team names can be made bolder
        
        boldFont.setFontName("Arial"); // sets the font to Arial
        boldFont.setFontHeightInPoints((short)10); // sets the font size to 10 points
        boldFont.setBold(true); // makes the font bold
        
        boldStyle.setFont(boldFont);
        
        Row teamNames = spreadsheet.createRow(0); // made a row for the name of the teams
        teamNames.createCell(3).setCellValue(BattingTeamController.getTeamName() + " vs. " + BowlingTeamController.getTeamName());
        
        Row rowBatters = spreadsheet.createRow(2); // made a row for the headings of the columns for batting team's tableView
        
        for (int j = 0; j < tableViewBatters.getColumns().size(); j++) { // adds the headings to the excel sheet
            rowBatters.createCell(j).setCellValue(tableViewBatters.getColumns().get(j).getText());
            spreadsheet.autoSizeColumn(j);
        }
        
        for(int i=0; i<rowBatters.getLastCellNum(); i++) { // changes the font of the headings to the bold font made earlier
            rowBatters.getCell(i).setCellStyle(boldStyle);
        }

        for (int i = 0; i < tableViewBatters.getItems().size(); i++) { // adds the data to the spreadsheet
            rowBatters = spreadsheet.createRow(i + 3);
            for (int j = 0; j < tableViewBatters.getColumns().size(); j++) {
                if(tableViewBatters.getColumns().get(j).getCellData(i) != null) { 
                    rowBatters.createCell(j).setCellValue(tableViewBatters.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    rowBatters.createCell(j).setCellValue("");
                }   
            }
        }
        
        // repeating the same process for bowlers tableView
        int rowNum = 17;
        Row rowBowlers = spreadsheet.createRow(rowNum);
        
        for (int j = 0; j < tableViewBowlers.getColumns().size(); j++) {
            rowBowlers.createCell(j).setCellValue(tableViewBowlers.getColumns().get(j).getText());
            spreadsheet.autoSizeColumn(j);
        }
        
        for(int i=0; i<rowBowlers.getLastCellNum(); i++) {
            rowBowlers.getCell(i).setCellStyle(boldStyle);
        }
        
        for (int i = 0; i < tableViewBowlers.getItems().size(); i++) {
            rowBowlers = spreadsheet.createRow(i + 18);
            for (int j = 0; j < tableViewBowlers.getColumns().size(); j++) {
                if(tableViewBowlers.getColumns().get(j).getCellData(i) != null) { 
                    rowBowlers.createCell(j).setCellValue(tableViewBowlers.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    rowBowlers.createCell(j).setCellValue("");
                }   
            }
        }
        
        teamNames.getCell(3).setCellStyle(boldStyle);
                
        // makes a file chooser so that player can choose the directory where they want to save the data to
        FileChooser fileChooser = new FileChooser();
 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);
        
        fileChooser.setInitialFileName(BattingTeamController.getTeamName() + " vs. " + BowlingTeamController.getTeamName() + ".xls");        
 
        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
 
        if (file != null) {
            saveExcel(workbook, file);
        } else { // shows a message if the user clicked on cancel
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("File not saved!");
            Optional<ButtonType> result = a.showAndWait();
        }        
    }
        
    
    
    @FXML
    private void switchToSlideTwo() throws IOException {
        switchToSelection(backtoSlideTwo, "Game Recorder", "gameRecorder");
    }
    @FXML
    private void switchToSlideFour() throws IOException {
        switchToSelection(slideFour, "Game Result", "gameResult");
    }

    private void saveExcel(Workbook workbook, File file) throws FileNotFoundException, IOException { // saves the excel file to the path selected by the user
        if(GameRecorderController.isIsSecondInning()) {
            FileOutputStream fileOut = new FileOutputStream(file);
            System.out.println("TRUE");
            workbook.write(fileOut);
            fileOut.close();
            this.inningCount = 2;
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Data saved!");
            Optional<ButtonType> result = a.showAndWait();
        } else {
            FileOutputStream fileOut = new FileOutputStream(file);
            GameRecorderController.setIsSecondInning(true);
            System.out.println("FALSE");
            workbook.write(fileOut);
            fileOut.close();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Data saved!");
            Optional<ButtonType> result = a.showAndWait();
        }
    }

}