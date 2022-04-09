import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * SideView class for the project.
 * This project contains the elements necessary to render the left side
 * of the application, including player list, selection and sorting by position.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class RightSideView extends LeftSideView {

    /**
     * Method responsible for generating all the correct data from the siderightview
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @return sideviewright - vbox containing elements from sideview
     */
    public static GridPane getSideViewRight() {
        GridPane sideViewRight = new GridPane();
        sideViewRight.setHgap(10);
        sideViewRight.setVgap(10);
        sideViewRight.setGridLinesVisible(false);
        sideViewRight.setAlignment(Pos.CENTER);
        sideViewRight.setPadding(new Insets(15, 15, 15, 15));
        sideViewRight.setStyle("-fx-border-color: black;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: solid;");

        System.out.println("Test player for right side view: \n" + selectedPlayer.get().getName());

        ImageView imgView = new ImageView(selectedPlayer.get().getImage());
        imgView.setFitWidth(300);
        imgView.setPreserveRatio(true);

        Text playerName = new Text("Name: " + selectedPlayer.get().getName());
        playerName.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerAge = new Text("Age: " + selectedPlayer.get().getAge());
        playerAge.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerHeight = new Text("Height (cm): " + selectedPlayer.get().getHeight());
        playerHeight.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerWeight = new Text("Weight (kg): " + selectedPlayer.get().getWeight());
        playerWeight.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerPosition = new Text("Position: " + selectedPlayer.get().getPosition().label);
        playerPosition.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerBattingPosition = new Text(
                "Batting Position: " + selectedPlayer.get().getStats().getBattingPos().toUpperCase());
        playerBattingPosition.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerTotalRuns = new Text("Total Runs: " + selectedPlayer.get().getStats().getTotalRuns());
        playerTotalRuns.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerTotalGames = new Text(
                "Total Games Played: " + selectedPlayer.get().getStats().getTotalGamesPlayed());
        playerTotalGames.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerCurrentRunRate = new Text(
                "Current Run Rate: " + selectedPlayer.get().getStats().getCurrentRunRate());
        playerCurrentRunRate.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerBattingLineUpNumber = new Text(
                "Batting Lineup Number: " + selectedPlayer.get().getStats().getBattingLineupNumber());
        playerBattingLineUpNumber.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        Text playerTeam = new Text("Team: " + selectedPlayer.get().getStats().getTeamName());
        playerTeam.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 15));

        selectedPlayer.addListener(new ChangeListener<Player>() {
            @Override
            public void changed(ObservableValue<? extends Player> observable, Player oldValue,
                    Player newValue) {
                imgView.setImage(selectedPlayer.get().getImage());
                playerName.setText("Name: " + selectedPlayer.get().getName());
                playerAge.setText("Age: " + selectedPlayer.get().getAge());
                playerHeight.setText("Height (cm): " + selectedPlayer.get().getHeight());
                playerWeight.setText("Weight (kg): " + selectedPlayer.get().getWeight());
                playerPosition.setText("Position: " + selectedPlayer.get().getPosition().label);
                playerBattingPosition.setText(
                        "Batting Position: " + selectedPlayer.get().getStats().getBattingPos()
                                .toUpperCase());
                playerTotalRuns.setText(
                        "Total Runs: " + selectedPlayer.get().getStats().getTotalRuns());
                playerTotalGames.setText("Total Games Played: "
                        + selectedPlayer.get().getStats().getTotalGamesPlayed());
                playerCurrentRunRate.setText("Current Run Rate: "
                        + selectedPlayer.get().getStats().getCurrentRunRate());
                playerBattingLineUpNumber.setText("Batting Lineup Number: "
                        + selectedPlayer.get().getStats().getBattingLineupNumber());
                playerTeam.setText("Team: " + selectedPlayer.get().getStats().getTeamName());
            }
        });

        Button editBtn = CustomButtons.getEditBtn();

        // Adding everything to the GridPane
        sideViewRight.add(imgView, 0, 0, 1, 13);
        sideViewRight.add(playerName, 1, 0);
        sideViewRight.add(playerAge, 1, 1);
        sideViewRight.add(playerHeight, 1, 2);
        sideViewRight.add(playerWeight, 1, 3);
        sideViewRight.add(playerPosition, 1, 4);
        sideViewRight.add(playerTotalGames, 1, 5);
        sideViewRight.add(playerBattingPosition, 1, 6);
        sideViewRight.add(playerCurrentRunRate, 1, 7);
        sideViewRight.add(playerTotalRuns, 1, 8);
        sideViewRight.add(playerBattingLineUpNumber, 1, 9);
        sideViewRight.add(playerTeam, 1, 10);
        sideViewRight.add(editBtn, 0, 15, 5, 1);
        GridPane.setHalignment(editBtn, HPos.CENTER);

        return sideViewRight;
    }

}
