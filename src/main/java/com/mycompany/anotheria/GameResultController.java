package com.mycompany.anotheria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameResultController implements Initializable {
    @FXML
    private TableView<GameResult> gameResultTable = new TableView<>();

    @FXML
    private Button backtoSlideOne;

    private static ObservableList<GameResult> gameResults;

    private static ObservableList<GameResult> getGameResults() {
        return gameResults;
    }

    private static void setGameResults(ObservableList<GameResult> gameResults) {
        GameResultController.gameResults = gameResults;
    }

    public static void populateGameResult(String teamA,String teamB,String result) {
        gameResults = FXCollections.observableArrayList();
        GameResult gameResult = new GameResult();
        gameResult.setHomeTeam(teamA);
        gameResult.setAwayTeam(teamB);
        gameResult.setResult(result);
        gameResults.add(gameResult);
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn teamA = new TableColumn("Home Team");
        teamA.setMinWidth(200);
        teamA.setResizable(true);
        teamA.setCellValueFactory(new PropertyValueFactory<GameResult, String>("homeTeam"));

        TableColumn teamB = new TableColumn("Away Team");
        teamB.setMinWidth(200);
        teamB.setResizable(true);
        teamB.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("awayTeam"));

        TableColumn result = new TableColumn("Result");
        result.setMinWidth(300);
        result.setResizable(true);
        result.setCellValueFactory(new PropertyValueFactory<Batsmen, String>("Result"));

        gameResultTable.setItems(gameResults);
        gameResultTable.getColumns().addAll(teamA, teamB, result);

    }

    @FXML
    private void switchToSelection(Button btn, String title, String fxml) throws IOException {
        Stage window = (Stage) btn.getScene().getWindow();
        window.close();
        Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
        Scene scene = new Scene(root);
        window.setTitle(title);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    @FXML
    private void switchToSlideOne() throws IOException {
        switchToSelection(backtoSlideOne, "InformationRecorder", "informationRecorder");
    }

}
