/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anotheria;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandeepsingh
 */
public class GameRecorderController implements Initializable {
    ArrayList<Batsmen> outList = new ArrayList<>();

    private static ObservableList<Batsmen> batters = BattingTeamController.getPlayers();
    private static ObservableList<Bowler> bowlers = BowlingTeamController.getPlayers();
    private static ObservableList<Bowler> tempBowlers = FXCollections.observableArrayList(bowlers);

    private static int noOfOvers = InformationRecorderController.getNumberOfOvers();

    private static Inning firstInning = new Inning();
    private static Inning secondInning = new Inning();

    private static int inningCount = 0;

    @FXML
    private ChoiceBox playerA = new ChoiceBox();

    @FXML
    private ChoiceBox playerB = new ChoiceBox();

    @FXML
    private ChoiceBox outOptionsA = new ChoiceBox();

    @FXML
    private ChoiceBox noballA = new ChoiceBox();

    @FXML
    private ChoiceBox outOptionsB = new ChoiceBox();

    @FXML
    private ChoiceBox noballB = new ChoiceBox();

    @FXML
    private ChoiceBox wideBallA = new ChoiceBox();

    @FXML
    private ChoiceBox wideBallB = new ChoiceBox();

    @FXML
    private ChoiceBox legByesA = new ChoiceBox();

    @FXML
    private ChoiceBox legByesB = new ChoiceBox();

    @FXML
    private ChoiceBox byesA = new ChoiceBox();

    @FXML
    private ChoiceBox byesB = new ChoiceBox();

    @FXML
    private ChoiceBox bowler = new ChoiceBox();

    @FXML
    private Button zeroA, zeroB, oneA, oneB, twoA, twoB, threeA, threeB,
            foursA, foursB, fiveA, fiveB, sixesA, sixesB;
    
    @FXML
    private Button btnSwitch;

    @FXML
    private ChoiceBox runOutA = new ChoiceBox();

    @FXML
    private ChoiceBox runOutB = new ChoiceBox();

    @FXML
    private ChoiceBox notFacingBallA = new ChoiceBox();

    @FXML
    private ChoiceBox notFacingBallB = new ChoiceBox();
    
    @FXML
    private TableView <Batsmen> tblViewBatters = new TableView<Batsmen>();
    
    @FXML
    private TableView <Bowler> tblViewBowlers = new TableView<Bowler>();
    
    private static boolean isSecondInning = false;

    public static boolean isIsSecondInning() {
        return isSecondInning;
    }

    public static void setIsSecondInning(boolean isSecondInning) {
        GameRecorderController.isSecondInning = isSecondInning;
    }

    @FXML
    private Button thirdSlide;

    private static String Pa, Pb, PcB;
    private int totalRuns;

    public static ObservableList<Batsmen> getBatters() {
        return batters;
    }

    public static void setBatters(ObservableList<Batsmen> newBatters) {
        batters = newBatters;
    }

    public static ObservableList<Bowler> getBowlers() {
        return bowlers;
    }

    public static void setBowlers(ObservableList<Bowler> newBowlers) {
        bowlers = newBowlers;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { // loads up all the data for the choice boxes and tables when the window opens up
        totalRuns = 0;
        inningCount++;
        System.out.println("numberofovers : " + noOfOvers);
        BattingTeamController.getTeam().setTotalOversAvailable(noOfOvers);
        //Adds bowlers to the list for when out
        //caughtBowlersA.setItems(bowlers);
        //caughtBowlersB.setItems(bowlers);


        //Choosing New Bowler's Choice Box

       // adds the bowler list to the choiceboxes
        bowler.setItems(tempBowlers);
        //Adds the batter list to the choice boxes
        playerA.setItems(BattingTeamController.getPlayers());
        playerB.setItems(BattingTeamController.getPlayers());


        //Adds Options to Out choice box
        outOptions(outOptionsA, "Out:", "Stumping", "Runout", "Catch", "Bowled");
        outOptions(outOptionsB, "Out:", "Stumping", "Runout", "Catch", "Bowled");

        // adds a listener to the choice boxes for the batters and shows errors if the user performs invalid actions
        playerA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            if (outList.contains((Batsmen)newValue)) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("This player is already out!");
                a.show();
                playerA.getSelectionModel().clearSelection();
            } else if (playerB.getValue() == playerA.getValue() && playerB.getValue() != null && playerA.getValue() != null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("This player is already batting!");
                a.show();
                playerA.getSelectionModel().clearSelection();
            }
        });

        playerB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            if (outList.contains((Batsmen)newValue)) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("This player is already out");
                a.show();
                playerB.getSelectionModel().clearSelection();
            } else if (playerB.getValue() == playerA.getValue() && playerB.getValue() != null && playerA.getValue() != null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("This player is already batting!");
                a.show();
                playerB.getSelectionModel().clearSelection();
            }
        });


        //Adds options to no-ball's choice box
        noball(noballA, "Noball:", "Noball", "Noball+1", "Noball+2", "Noball+3", "Noball+4",
                "Noball+5", "Noball+6");
        noball(noballB, "Noball:", "Noball", "Noball+1", "Noball+2", "Noball+3", "Noball+4",
                "Noball+5", "Noball+6");

        //Adds options to Wide-ball's choice box
        wideball(wideBallA, "Wide ball:", "Wide-ball", "Wide-ball+1", "Wide-ball+2",
                "Wide-ball+3", "Wide-ball+4");
        wideball(wideBallB, "Wide ball:", "Wide-ball", "Wide-ball+1", "Wide-ball+2",
                "Wide-ball+3", "Wide-ball+4");

        //Adds options to Legbyes's choice box
        legByes(legByesA, "LegByes:", "1 LegByes", "2 LegByes", "3 LegByes", "4 LegByes");
        legByes(legByesB, "LegByes:", "1 LegByes", "2 LegByes", "3 LegByes", "4 LegByes");


        //Adds options to Byes' choice box
        Byes(byesA, "Byes:", "1 Byes", "2 Byes", "3 Byes", "4 Byes");
        Byes(byesB, "Byes:", "1 Byes", "2 Byes", "3 Byes", "4 Byes");

        //Adds options to Runout choice box
        runOut(runOutA, "Run-Out:", "Runout+0", "Runout+1", "Runout+2", "Runout+3",
                "Runout+4", "Runout+5");
        runOut(runOutB, "Run-Out:", "Runout+0", "Runout+1", "Runout+2", "Runout+3",
                "Runout+4", "Runout+5");

        notFacingBallRunOut(notFacingBallA, "Run-out ball not faced: ", "Runout+0", "Runout+1", "Runout+2",
                "Runout+3", "Runout+4", "Runout+5");
        notFacingBallRunOut(notFacingBallB, "Run-out ball not faced: ", "Runout+0", "Runout+1", "Runout+2",
                "Runout+3", "Runout+4", "Runout+5");


        // Adds a listener to the runout and notFacingBall choice boxes and calls handleRunOut() with different arguments based on what the user selected
        runOutA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            //String p = playerA.getValue().toString();
            String p = filterPlayerName(playerA.getValue().toString());
            String b = bowler.getValue().toString();

            if (newValue.equals("Runout+0")) {
                handleRunOut(0, p, b);
                runOutA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+1")) {
                handleRunOut(1, p, b);
                runOutA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+2")) {
                handleRunOut(2, p, b);
                runOutA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+3")) {
                handleRunOut(3, p, b);
                runOutA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+4")) {
                handleRunOut(4, p, b);
                runOutA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+5")) {
                handleRunOut(5, p, b);
                runOutA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            }
            outOptionsA.setValue("Out:"); // reset the value for the chociebox after the action has been performed
            runOutA.setVisible(false); //hid the runout choicebox as not required for the time being
            notFacingBallA.setVisible(false); // hid the noFacingBall chociebox as not required for the time being
        });

        runOutB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
           // String p = playerB.getValue().toString();
            String p = filterPlayerName(playerB.getValue().toString());
            String b = bowler.getValue().toString();


            if (newValue.equals("Runout+0")) {
                handleRunOut(0, p, b);
                runOutB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+1")) {
                handleRunOut(1, p, b);
                runOutB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+2")) {
                handleRunOut(2, p, b);
                runOutB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+3")) {
                handleRunOut(3, p, b);
                runOutB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+4")) {
                handleRunOut(4, p, b);
                runOutB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+5")) {
                handleRunOut(5, p, b);
                runOutB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            }
            outOptionsB.setValue("Out:");
            runOutB.setVisible(false);
            notFacingBallB.setVisible(false);
        });

        notFacingBallA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            //String p = playerB.getValue().toString();
            String p = filterPlayerName(playerB.getValue().toString());
            String b = bowler.getValue().toString();

            if (newValue.equals("Runout+0")) {
                handleRunOut(0, p, b);
                notFacingBallA.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+1")) {
                handleRunOut(1, p, b);
                notFacingBallA.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+2")) {
                handleRunOut(2, p, b);
                notFacingBallA.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+3")) {
                handleRunOut(3, p, b);
                notFacingBallA.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+4")) {
                handleRunOut(4, p, b);
                notFacingBallA.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+5")) {
                handleRunOut(5, p, b);
                notFacingBallA.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
            }
            outOptionsA.setValue("Out:");
            runOutA.setVisible(false);
            notFacingBallA.setVisible(false);
        });

        notFacingBallB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            //String p = playerA.getValue().toString();
            String p = filterPlayerName(playerA.getValue().toString());
            String b = bowler.getValue().toString();

            if (newValue.equals("Runout+0")) {
                handleRunOut(0, p, b);
                notFacingBallB.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+1")) {
                handleRunOut(1, p, b);
                notFacingBallB.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+2")) {
                handleRunOut(2, p, b);
                notFacingBallB.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+3")) {
                handleRunOut(3, p, b);
                notFacingBallB.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+4")) {
                handleRunOut(4, p, b);
                notFacingBallB.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            } else if (newValue.equals("Runout+5")) {
                handleRunOut(5, p, b);
                notFacingBallB.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
            }
            outOptionsB.setValue("Out:");
            runOutB.setVisible(false);
            notFacingBallB.setVisible(false);
        });

        // Adds a listener to the noball choiceboxes and calls handleNoBalls() with arguments based on what the user selected
        noballA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
           // String p = playerA.getValue().toString();
            String p = filterPlayerName(playerA.getValue().toString());
            String b = bowler.getValue().toString();

            if (newValue.equals("Noball")) {
                handleNoBalls(0, p, b);
                noballA.setValue(oldValue);
            } else if (newValue.equals("Noball+1")) {
                handleNoBalls(1, p, b);
                noballA.setValue(oldValue);
            } else if (newValue.equals("Noball+2")) {
                handleNoBalls(2, p, b);
                noballA.setValue(oldValue);
            } else if (newValue.equals("Noball+3")) {
                handleNoBalls(3, p, b);
                noballA.setValue(oldValue);
            } else if (newValue.equals("Noball+4")) {
                handleNoBalls(4, p, b);
                noballA.setValue(oldValue);
            } else if (newValue.equals("Noball+5")) {
                handleNoBalls(5, p, b);
                noballA.setValue(oldValue);
            } else if (newValue.equals("Noball+6")) {
                handleNoBalls(6, p, b);
                noballA.setValue(oldValue);
            }


        });

        noballB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            //String pb = playerB.getValue().toString();
            String pb = filterPlayerName(playerB.getValue().toString());
            String b = bowler.getValue().toString();

            if (newValue.equals("Noball")) {
                handleNoBalls(0, pb, b);
                noballB.setValue(oldValue);
            } else if (newValue.equals("Noball+1")) {
                handleNoBalls(1, pb, b);
                noballB.setValue(oldValue);
            } else if (newValue.equals("Noball+2")) {
                handleNoBalls(2, pb, b);
                noballB.setValue(oldValue);
            } else if (newValue.equals("Noball+3")) {
                handleNoBalls(3, pb, b);
                noballB.setValue(oldValue);
            } else if (newValue.equals("Noball+4")) {
                handleNoBalls(4, pb, b);
                noballB.setValue(oldValue);
            } else if (newValue.equals("Noball+5")) {
                handleNoBalls(5, pb, b);
                noballB.setValue(oldValue);
            } else if (newValue.equals("Noball+6")) {
                handleNoBalls(6, pb, b);
                noballB.setValue(oldValue);
            }

        });

        // same thing as before but with wideBall chocieboxes and calls handler method for wide balls
        wideBallA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            String pb = filterPlayerName(playerA.getValue().toString());
            //String pb = playerA.getValue().toString();
            String b = bowler.getValue().toString();

            if (newValue.equals("Wide-ball")) {
                handleWideBalls(1, pb, b);
                wideBallA.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+1")) {
                handleWideBalls(2, pb, b);
                wideBallA.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+2")) {
                handleWideBalls(3, pb, b);
                wideBallA.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+3")) {
                handleWideBalls(4, pb, b);
                wideBallA.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+4")) {
                handleWideBalls(5, pb, b);
                wideBallA.setValue(oldValue);
            }
        });

        wideBallB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            String p = filterPlayerName(playerB.getValue().toString());
            //String p = playerB.getValue().toString();
            String b = bowler.getValue().toString();

            if (newValue.equals("Wide-ball")) {
                handleWideBalls(1, p, b);
                wideBallB.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+1")) {
                handleWideBalls(2, p, b);
                wideBallB.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+2")) {
                handleWideBalls(3, p, b);
                wideBallB.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+3")) {
                handleWideBalls(4, p, b);
                wideBallB.setValue(oldValue);
            } else if (newValue.equals("Wide-ball+4")) {
                handleWideBalls(5, p, b);
                wideBallB.setValue(oldValue);
            }
        });


        legByesA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            String p = filterPlayerName(playerA.getValue().toString());
           // String p = playerA.getValue().toString();
            String b = bowler.getValue().toString();

            if (newValue.equals("1 LegByes")) {
                handleLegByes(1, p, b);
                legByesA.setValue(oldValue);
            } else if (newValue.equals("2 LegByes")) {
                handleLegByes(2, p, b);
                legByesA.setValue(oldValue);
            } else if (newValue.equals("3 LegByes")) {
                handleLegByes(3, p, b);
                legByesA.setValue(oldValue);
            } else if (newValue.equals("4 LegByes")) {
                handleLegByes(4, p, b);
                legByesA.setValue(oldValue);
            }
        });

        legByesB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            String pb = filterPlayerName(playerB.getValue().toString());
           // String pb = playerB.getValue().toString();
            String b = bowler.getValue().toString();

            if (newValue.equals("1 LegByes")) {
                handleLegByes(1, pb, b);
                legByesB.setValue(oldValue);
            } else if (newValue.equals("2 LegByes")) {
                handleLegByes(2, pb, b);
                legByesB.setValue(oldValue);
            } else if (newValue.equals("3 LegByes")) {
                handleLegByes(3, pb, b);
                legByesB.setValue(oldValue);
            } else if (newValue.equals("4 LegByes")) {
                handleLegByes(4, pb, b);
                legByesB.setValue(oldValue);
            }
        });

        byesA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
           // String p = playerA.getValue().toString();
            String p = filterPlayerName(playerA.getValue().toString());
            String b = bowler.getValue().toString();

            if (newValue.equals("1 Byes")) {
                handleByes(1, p, b);
                byesA.setValue(oldValue);
            } else if (newValue.equals("2 Byes")) {
                handleByes(2, p, b);
                byesA.setValue(oldValue);
            } else if (newValue.equals("3 Byes")) {
                handleByes(3, p, b);
                byesA.setValue(oldValue);
            } else if (newValue.equals("4 Byes")) {
                handleByes(4, p, b);
                byesA.setValue(oldValue);
            }
        });

        byesB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            String pb = filterPlayerName(playerB.getValue().toString());
            //String pb = playerB.getValue().toString();
            String b = bowler.getValue().toString();

            if (newValue.equals("1 Byes")) {
                handleByes(1, pb, b);
                byesB.setValue(oldValue);
            } else if (newValue.equals("2 Byes")) {
                handleByes(2, pb, b);
                byesB.setValue(oldValue);
            } else if (newValue.equals("3 Byes")) {
                handleByes(3, pb, b);
                byesB.setValue(oldValue);
            } else if (newValue.equals("4 Byes")) {
                handleByes(4, pb, b);
                byesB.setValue(oldValue);
            }
        });


        outOptionsA.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            String pb = filterPlayerName(playerA.getValue().toString());
           // String pb = playerA.getValue().toString();
            String b = bowler.getValue().toString();

            if (newValue.equals("Stumping")) {
                outButton(0, pb, b);
                outOptionsA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection(); // clears the selection of playerA choicebox
                runOutA.setVisible(false);
                notFacingBallA.setVisible(false);
                for (Batsmen player : batters) { // looks for that player through the batters list
                    if (pb.equals(player.getName())) {
                        player.howOut("Stumped"); // sets the value for howOut attribute for the Batter object
                    }
                }
                
            } else if (newValue.equals("Catch")) {
                outButton(0, pb, b);
                outOptionsA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
                runOutA.setVisible(false);
                notFacingBallA.setVisible(false);
                for (Batsmen player : batters) {
                    if (pb.equals(player.getName())) {
                        player.howOut("Caught");
                    }
                }
            }

            else if (newValue.equals("Runout")) {
            runOutA.setVisible(true); // makes the cb visible to the user
            notFacingBallA.setVisible(true);
            }

            else if (newValue.equals("Bowled")) {
                outButton(0, pb, b);
                outOptionsA.setValue(oldValue);
                playerA.getSelectionModel().clearSelection();
                runOutA.setVisible(false);
                notFacingBallA.setVisible(false);
                for (Batsmen player : batters) {
                    if (pb.equals(player.getName())) {
                        player.howOut("bowled");
                    }
                }
            }
            outOptionsA.setValue("Out:"); // resets the value

        });

        outOptionsB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            String pb = filterPlayerName(playerB.getValue().toString());
           // String pb = playerB.getValue().toString();
            String b = bowler.getValue().toString();

            if (newValue.equals("Stumping")) {
                outButton(0, pb, b);
                outOptionsB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
                runOutB.setVisible(false);
                notFacingBallB.setVisible(false);
                for (Batsmen player : batters) {
                    if (pb.equals(player.getName())) {
                        player.howOut("Stumped");
                    }
                }
            } else if (newValue.equals("Catch")) {
                outButton(0, pb, b);
                outOptionsB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
                runOutB.setVisible(false);
                notFacingBallB.setVisible(false);
                for (Batsmen player : batters) {
                    if (pb.equals(player.getName())) {
                        player.howOut("Caught");
                    }
                }
            }

            else if (newValue.equals("Runout")) {
            runOutB.setVisible(true);
            notFacingBallB.setVisible(true);
            }

            else if (newValue.equals("Bowled")) {
                outButton(0, pb, b);
                outOptionsB.setValue(oldValue);
                playerB.getSelectionModel().clearSelection();
                runOutB.setVisible(false);
                notFacingBallB.setVisible(false);
                for (Batsmen player : batters) {
                    if (pb.equals(player.getName())) {
                        player.howOut("bowled");
                    }
                }
            }
            outOptionsB.setValue("Out:");
        });
        
        //--- ADDING DATA TO THE BATTER AND BOWLER TABLES ---\\
        
        TableColumn playersName = new TableColumn("Player's Name"); // makes a new column
        playersName.setMinWidth(100); // sets the minimum width to 100px wide
        playersName.setResizable(false); // changes the resizable property of the column to false
        playersName.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("name")); // tells the column where to fetch the data from and the data type (FROM Batsmen class fetches the variable naem which is a String)
       
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



        tblViewBatters.setItems(batters); // feeds the observableList batters to tableView

        tblViewBatters.getColumns().addAll(playersName, howBatsmenOut, ballsFaced, runsScored, 
                foursScored, sixesScored, batsmenStrikeRate); // adds all the columns to the tableView
        

        //Bowling Team Deatails
        
        TableColumn bowlersplayersName = new TableColumn("Player's Name");
        bowlersplayersName.setMinWidth(100);
        bowlersplayersName.setResizable(false);
        bowlersplayersName.setCellValueFactory(new PropertyValueFactory<Bowler, String>("name"));
        
        TableColumn runsConceded = new TableColumn("Runs");
        runsConceded.setMinWidth(75);
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
        
        
        tblViewBowlers.setItems(bowlers);
        tblViewBowlers.getColumns().addAll(bowlersplayersName, playerBallsBowled, playerOversBowled, 
                runsConceded, dotBallsBowled, wicketsTaken, noBallsBowled, widesBowled, byesBowled, 
                legByesBowled, playerEconomy);
        
        
        tblViewBatters.autosize();
        tblViewBowlers.autosize();
    }

    // handles the out choicbox operations
    public void outButton(int runs, String p, String b) {
        for (Batsmen player : batters) {
            if (p.equals(player.getName())) {
                player.setRuns(runs);
                player.increaseBalls();
                outList.add(player); // adds the player's name to outList so we can check against it when the user selects a new batsmen and not let the user select a player thats already out
                BattingTeamController.getTeam().increaseWickets();
                BattingTeamController.getTeam().increaseBalls();
                System.out.println("Wickets: " + BattingTeamController.getTeam().getWickets());
                System.out.println("Player OUT: " + player.getName());
            }
        }

        for (Bowler player : bowlers) {
            if (b.equals(player.getName())) {
                player.setRuns(runs);
                player.calculateEconomy();
                player.setEconomy(formatDoubleValue(player.getEconomy()));
                player.increaseWickets();
                player.increaseDotPointBalls();
                System.out.println("Bowlers Wickets: " + player.getWickets());
            }
        }
        
        tblViewBatters.refresh(); // refreshes the data of the tableView
        tblViewBowlers.refresh();
    }

    // handles the runOut choicbox operations
    public void handleRunOut(int runs, String p, String b) {
        for (Batsmen player : batters) {
            if (p.equals(player.getName())) {
                player.setRuns(runs);
                BattingTeamController.getTeam().increaseRuns(runs);
                BattingTeamController.getTeam().increaseBalls();
                outList.add(player);
                System.out.println("Batsmen Runs Scored: " + player.getRuns());
                tblViewBatters.refresh();
                tblViewBowlers.refresh();
                
                    if (p.equals(player.getName())) {
                        player.howOut("Runout");
                    }
                
                
            }
        }

        for (Bowler player : bowlers) {
            if (b.equals(player.getName())) {
                player.setRuns(runs);
                player.calculateEconomy();
                player.setEconomy(formatDoubleValue(player.getEconomy()));

                player.increaseDotPointBalls();
                System.out.println("Bowler Runs Conceded: " + player.getRuns());
                tblViewBatters.refresh();
                tblViewBowlers.refresh();
            }
        }
        tblViewBatters.refresh();
        tblViewBowlers.refresh();
    }


    // handles the byes choicbox operations
    public void handleByes(int runs, String p, String b) {
        for (Batsmen player : batters) {
            if (p.equals(player.getName())) {
                player.setRuns(0);
                System.out.println("Batsmen Runs Scored: " + player.getRuns());
                BattingTeamController.getTeam().increaseRuns(runs);
                BattingTeamController.getTeam().increaseExtrasByes(runs);
                player.increaseDotBalls();
                tblViewBatters.refresh();
                tblViewBowlers.refresh();

            }
            tblViewBatters.refresh();
            tblViewBowlers.refresh();
        }

        for (Bowler player : bowlers) {
            if (b.equals(player.getName())) {
                player.setRuns(0);
                player.calculateEconomy();
                player.setEconomy(formatDoubleValue(player.getEconomy()));

                player.increaseExtrasByes(runs);
                player.increaseDotPointBalls();
                BowlingTeamController.getBowlingTeam().increaseExtrasByes(runs);
                System.out.println("Bowler Runs Conceded: " + player.getRuns());
                System.out.println("Bowlers Byes Conceded:" + player.getExtrasByes());
                player.increaseDotBalls();
                tblViewBatters.refresh();
                tblViewBowlers.refresh();
            }
        }
        tblViewBatters.refresh();
        tblViewBowlers.refresh();
    }

    // handles the legByes choicbox operations
    public void handleLegByes(int runs, String p, String b) {
        for (Batsmen player : batters) {
            if (p.equals(player.getName())) {
                player.setRuns(0);
                System.out.println("Batsmen Runs Scored: " + player.getRuns());
                BattingTeamController.getTeam().increaseRuns(runs);
                BattingTeamController.getTeam().increaseExtrasLegByes(runs);
                player.increaseDotBalls();
                tblViewBatters.refresh();
                tblViewBowlers.refresh();
            }
            tblViewBatters.refresh();
            tblViewBowlers.refresh();
        }

        for (Bowler player : bowlers) {
            if (b.equals(player.getName())) {
                player.setRuns(0);
                player.calculateEconomy();
                player.setEconomy(formatDoubleValue(player.getEconomy()));

                player.increaseExtrasLegByes(runs);
                player.increaseDotPointBalls();
                BowlingTeamController.getBowlingTeam().increaseExtrasLegByes(runs);
                System.out.println("Bowler Runs Conceded: " + player.getRuns());
                System.out.println("Bowlers Leg Byes Conceded:" + player.getExtrasLegByes());
                player.increaseDotBalls();
                tblViewBatters.refresh();
                tblViewBowlers.refresh();
            }
        }
    }

    // handles the noBalls choicbox operations
    public void handleNoBalls(int runs, String p, String b) {
        for (Batsmen player : batters) {
            if (p.equals(player.getName())) {
                player.setRuns(runs);
                System.out.println("Batsmen Runs Scored: " + player.getRuns());
                BattingTeamController.getTeam().increaseRuns(runs + 1);
                BattingTeamController.getTeam().increaseExtrasNoBalls();
                tblViewBatters.refresh();
                tblViewBowlers.refresh();
            }
        }

        for (Bowler player : bowlers) {
            if (b.equals(player.getName())) {
                player.increaseRuns(runs + 1);
                player.calculateEconomy();
                player.setEconomy(formatDoubleValue(player.getEconomy()));

                player.increaseExtrasNoBalls();
                BowlingTeamController.getBowlingTeam().increaseExtrasNoBalls();
                System.out.println("Bowler Runs Conceded: " + player.getRuns());
                System.out.println("Bowlers No Balls Conceded:" + player.getExtrasNoBalls());
                tblViewBatters.refresh();
                tblViewBowlers.refresh();
            }
        }
    }

    // handles the wideBalls choicbox operations
    public void handleWideBalls(int runs, String p, String b) {
        for (Batsmen player : batters) {
            if (p.equals(player.getName())) {
                System.out.println("Batsmen Runs Scored: " + player.getRuns());
                BattingTeamController.getTeam().increaseRuns(runs);
                BattingTeamController.getTeam().increaseExtrasWides(runs);
            }
        }

        for (Bowler player : bowlers) {
            if (b.equals(player.getName())) {
                player.increaseRuns(runs);
                player.calculateEconomy();
                player.setEconomy(formatDoubleValue(player.getEconomy()));

                player.increaseExtrasWides(runs);
                BowlingTeamController.getBowlingTeam().increaseExtrasWides(runs);
                System.out.println("Bowler Runs Conceded: " + player.getRuns());
                System.out.println("Bowlers Wide Balls:" + player.getExtrasWides());

            }
        }
        tblViewBatters.refresh();
        tblViewBowlers.refresh();
    }


    @FXML // handles the operations of all the runButtons
    private void handleRunButtonAction(MouseEvent event) {
        
        if (event.getSource() == zeroA || event.getSource() == zeroB)
            runButton(0, event);

        else if (event.getSource() == oneA || event.getSource() == oneB)
            runButton(1, event);

        else if (event.getSource() == twoA || event.getSource() == twoB)
            runButton(2, event);

        else if (event.getSource() == threeA || event.getSource() == threeB)
            runButton(3, event);

        else if (event.getSource() == foursA || event.getSource() == foursB)
            runButton(4, event);

        else if (event.getSource() == fiveA || event.getSource() == fiveB)
            runButton(5, event);

        else if (event.getSource() == sixesA || event.getSource() == sixesB)
            runButton(6, event);
        }

    // used to filter out just the players name
    public String filterPlayerName(String name) {

        String[] parts = name.split("\\:");
        String playerName = parts[0];

        return playerName;
    }



    // returns true if the runs scored by the batting team in the second inning are greater than the batting team in the first inning
    private Boolean runsDone() {

        if (BattingTeamController.getTeam().getRuns() >= firstInning.getTotalRuns()) {
            return Boolean.TRUE;
        }
        else
            return Boolean.FALSE;
    }

    // generates the result for the game and declares the winner of the match
    public String generateResult() {

        String message = "";
        System.out.println("FirstInning team Name:"+firstInning.getTeamName()+" Runs: "+firstInning.getTotalRuns()
        +"Wickets: "+firstInning.getTotalWickets());
        System.out.println("SecondInning team Name:"+secondInning.getTeamName()+" Runs: "+secondInning.getTotalRuns()
                +"Wickets: "+secondInning.getTotalWickets());

        if (firstInning.getTotalRuns() > secondInning.getTotalRuns()) {
            int diff = firstInning.getTotalRuns() - secondInning.getTotalRuns();
            message = "Team " + firstInning.getTeamName() + " won by " + diff + " runs";

        } else if (secondInning.getTotalRuns() > firstInning.getTotalRuns()) {
            int diff = firstInning.getTotalWickets() - secondInning.getTotalWickets();
            message = "Team " + secondInning.getTeamName() + " won by " + diff + " wickets";
        }
        else message = "Match is draw";

        return message;
    }

    //Adds runs to the batter object selected in the choicebox according to the runs button pressed by the user
    @FXML
    private void runButton(int runs, MouseEvent event) {

        if (BattingTeamController.getTeam().totalOversDone()) {

            System.out.println("inningCount : " + inningCount);
            if (inningCount >= 2) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Match is over. View Scorecard");
                Optional<ButtonType> result = a.showAndWait(); // waits for the user to press OK
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    secondInning.setBatters(BattingTeamController.getPlayers());
                    secondInning.setBowlers(BowlingTeamController.getPlayers());

                    secondInning.setTotalRuns(BattingTeamController.getTeam().getRuns());
                    secondInning.setTeamName(BattingTeamController.getTeamName());
                    secondInning.setTotalWickets(BattingTeamController.getTeam().getWickets());

                    GameResultController.populateGameResult(BowlingTeamController.getTeamName(), BattingTeamController.getTeamName(), generateResult());

                try {
                    isSecondInning = true;
                    thirdSlide();
                } catch (IOException e) {
                    }
                }
                
                
                
            } else {
                //display score card of FirstInning
                try {
                    System.out.println("CHANGING SLIDE");
                    thirdSlide();
                    changeInning();
                } catch (IOException e) {
                }

            }
        } 
        else if (checkCB(playerA) || checkCB(playerB)){ // checks if all the required players are selected
                System.out.println("ALL PLAYERS NOT SELECTED");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please select the 2 batters");
                a.show();
        } else if (checkCB(bowler)) {
            System.out.println("ALL PLAYERS NOT SELECTED");
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select the bowler");
            a.show();
        }
        else {
            String p = "";
            if (event.getSource() == zeroA || event.getSource() == oneA || event.getSource() == twoA
                    || event.getSource() == threeA || event.getSource() == foursA || event.getSource() == fiveA ||
                    event.getSource() == sixesA) {
                // p = playerA.getValue().toString();
                p = filterPlayerName(playerA.getValue().toString());
            } else {
                //p = playerB.getValue().toString();
                p = filterPlayerName(playerB.getValue().toString());
            }
            String b = bowler.getValue().toString();
            
            int batsmanIndex = 0;
            for (Batsmen player : batters) {
                if (p.equals(player.getName())) {
                    batsmanIndex = batters.indexOf(player);
                    System.out.println("batsmanIndex :" + batsmanIndex);
                    player.howOut("Not Out");
                    player.setRuns(runs);

                    if (event.getSource() == foursA || event.getSource() == foursB) {
                        player.incrementFours();
                    } else if (event.getSource() == sixesA || event.getSource() == sixesB) {
                        player.incrementSixes();
                    } else if (event.getSource() == zeroA || event.getSource() == zeroB) {
                        player.increaseDotBalls();
                    }
                    player.increaseBalls();
                    System.out.println("Batter Runs: " + player.getRuns());

                    System.out.println("Batter Balls: " + player.getBalls());
                    double number = player.calculateStrikeRate();
                    player.setTotalRuns(player.getRuns());
                    System.out.println("Batter Total Runs: " + player.getTotalRuns());

                    player.setBatsmenStrikeRate(formatDoubleValue(number));
                    player.setStrikeRate(formatDoubleValue(number));

                }
            }

            BattingTeamController.getTeam().increaseBalls();
            System.out.println("Team Total Balls: " + BattingTeamController.getTeam().getBalls());

            BattingTeamController.getTeam().increaseRuns(runs);
            System.out.println("Team Total Runs: " + BattingTeamController.getTeam().getRuns());
            double tempOvers = (BattingTeamController.getTeam().getBalls()+1 )/ 6;
            BattingTeamController.getTeam().setOversDone((tempOvers));
            System.out.println("overs done:"+ BattingTeamController.getTeam().getOversDone());
            for (Bowler player : bowlers) {

                if (b.equals(player.getName())) {
                    player.setRuns(runs);
                    player.calculateEconomy();
                    player.setEconomy(formatDoubleValue(player.getEconomy()));

                    player.incrementOvers();
                    player.increaseDotPointBalls();
                    System.out.println("Bowlers Runs:" + player.getRuns());
                    System.out.println("Bowler Balls: " + player.getBalls());

                    if (event.getSource() == zeroA || event.getSource() == zeroB) {
                        player.increaseDotBalls();
                    }
                    if(player.getBalls() == 6){
                        bowler.getSelectionModel().clearSelection();
                        tempBowlers.remove(player);
                    }
                }
            }
        }
        
        tblViewBatters.refresh();
        tblViewBowlers.refresh();
    }

    // limits the strikeRate to two decimal places
    public static double formatDoubleValue(double strikeRate) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        double formattedValue = Double.parseDouble(numberFormat.format(strikeRate));
        return formattedValue;
    }

    // initializes attributes of the Inning object - firstInning
    public void changeInning() {

        firstInning.setBatters(batters);
        firstInning.setBowlers(bowlers);

        firstInning.setTeamName(BattingTeamController.getTeamName());
        firstInning.setTotalWickets(BattingTeamController.getTeam().getWickets());
        totalRuns = BattingTeamController.getTeam().getRuns();
        System.out.println("Team Total Runs: " + BattingTeamController.getTeam().getRuns());
        firstInning.setTotalRuns(totalRuns);

        System.out.println("Number of Runs Required : " + totalRuns);
        System.out.println("*****************************************");

        shufflePlayerList();

        inningCount++;
        System.out.println("inningCount : " + inningCount);

    }

    // swaps the the contents of batters and bowlers observableList(s)
    public void shufflePlayerList() {

        ObservableList<Bowler> tempList = BowlingTeamController.getPlayers();
        BowlingTeamController.populateBowlingTeam(BattingTeamController.getPlayers());
        BattingTeamController.populateBattingTeam(tempList);

        String tempName = BowlingTeamController.getTeamName();
        BowlingTeamController.setTeamName(BattingTeamController.getTeamName());
        BattingTeamController.setTeamName(tempName);
        //Choosing New Bowler's Choice Box
        //tempBowlers = FXCollections.observableArrayList(bowlers);
        bowlers = BowlingTeamController.getPlayers();
        batters = BattingTeamController.getPlayers();
        tempBowlers = FXCollections.observableArrayList(bowlers);
        bowler.setItems(tempBowlers);

        //Adds the player list to the choice box
        playerA.setItems(BattingTeamController.getPlayers());
        playerB.setItems(BattingTeamController.getPlayers());

        
    }

    
    ///--- METHODS TO ADD DATA TO THE CHOICBOXES ---\\\
    public void outOptions(ChoiceBox fxid, String defaultname, String opt1, String opt2, String opt3, String opt4) {
        fxid.setValue(defaultname);
        fxid.setItems(FXCollections.observableArrayList(defaultname, opt1, opt2, opt3, opt4));
    }

    public void noball(ChoiceBox fxid, String defaultname, String opt1, String opt2, String opt3,
                       String opt4, String opt5, String opt6, String opt7) {
        fxid.setValue(defaultname);
        fxid.setItems(FXCollections.observableArrayList(defaultname, opt1, opt2, opt3, opt4, opt5, opt6, opt7));
    }

    public void wideball(ChoiceBox fxid, String defaultname, String opt1, String opt2, String opt3, String opt4,
                         String opt5) {
        fxid.setValue(defaultname);
        fxid.setItems(FXCollections.observableArrayList(defaultname, opt1, opt2, opt3, opt4, opt5));
    }

    public void legByes(ChoiceBox fxid, String defaultname, String opt1, String opt2, String opt3, String opt4) {
        fxid.setValue(defaultname);
        fxid.setItems(FXCollections.observableArrayList(defaultname, opt1, opt2, opt3, opt4));
    }

    public void Byes(ChoiceBox fxid, String defaultname, String opt1, String opt2, String opt3, String opt4) {
        fxid.setValue(defaultname);
        fxid.setItems(FXCollections.observableArrayList(defaultname, opt1, opt2, opt3, opt4));
    }

    public void runOut(ChoiceBox fxid, String defaultname, String opt1, String opt2, String opt3, String opt4,
                       String opt5, String opt6) {
        fxid.setValue(defaultname);
        fxid.setItems(FXCollections.observableArrayList(defaultname, opt1, opt2, opt3, opt4, opt5, opt6));
    }

    public void notFacingBallRunOut(ChoiceBox fxid, String defaultname, String opt1, String opt2, String opt3, String opt4,
                                    String opt5, String opt6) {
        fxid.setValue(defaultname);
        fxid.setItems(FXCollections.observableArrayList(defaultname, opt1, opt2, opt3, opt4, opt5, opt6));
    }

    @FXML // method to go to scorecard
    public void thirdSlide() throws IOException {
        switchToSelection(zeroA, "Scorecard", "scorecardTable");
    }

    @FXML // general method to switch scenes
    private void switchToSelection(Button btn, String title, String fxml) throws IOException {
        System.out.println("switch to : " + fxml);
        Stage window = (Stage) btn.getScene().getWindow();
        window.close();
        Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
        Scene scene = new Scene(root);
        window.setTitle(title);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }
    
    // returns true if the value in the choicebox is null
    private boolean checkCB(ChoiceBox cb) {
        if(cb.getValue() == null) {
            return true;
        } else{
        return false;
        }
    }
    
}


