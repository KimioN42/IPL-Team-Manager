import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * SideView class for the project.
 * This project contains the elements necessary to render the left side
 * of the application, including player list, selection and sorting by position.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class SideView {

    /**
     * Method responsible for generating all the correct data from the sideleftview
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @param players - ArrayList of player objects
     * @return sideViewLeft - VBox containing all the elements in the sideView of
     *         the application
     */
    public static VBox getSideView(ArrayList<Player> players) {
        // Creating the VBox that will contain all the nodes
        VBox sideViewLeft = new VBox();

        // Name of the team
        Text teamNameManagement = new Text("RCB Management");
        teamNameManagement.setStyle("-fx-font: 20 Verdana;");

        // Getting logo from imgs folder
        Image logo = new Image("./imgs/RCB_Logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);
        Pane pane = new Pane();
        pane.getChildren().add(logoView);

        TableView playersTable = new TableView<>();

        // Setting the first column (jersey number)
        TableColumn<String, Player> c1 = new TableColumn<>("No.");
        c1.setCellValueFactory(new PropertyValueFactory<>("num"));
        c1.setResizable(false);
        c1.prefWidthProperty().bind(playersTable.widthProperty().multiply(0.2));

        // Setting the second column (player name)
        TableColumn<String, Player> c2 = new TableColumn<>("Name");
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setResizable(false);
        c2.prefWidthProperty().bind(playersTable.widthProperty().multiply(0.8));

        playersTable.getColumns().addAll(c1, c2);
        playersTable.setPrefWidth(sideViewLeft.widthProperty().get());
        playersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Label comboBoxLabel = new Label("Select the Position you want to filter");
        comboBoxLabel.setAlignment(Pos.CENTER);

        // ComboBox with the position properties
        ComboBox<Position> positionSelector = new ComboBox<>();
        positionSelector.getItems().setAll(Position.values());
        positionSelector.getSelectionModel().select(4);
        positionSelector.autosize();

        ObservableList<Player> olPlayers = FXCollections.observableList(players);
        FilteredList<Player> filteredPlayerByPos = new FilteredList<>(olPlayers, p -> true);

        // adding functionality to the combobox
        positionSelector.setOnAction((Event) -> {
            System.out.println("Position selected: " + positionSelector.getValue().label);
            filteredPlayerByPos.setPredicate(player -> {
                if (positionSelector.getValue() == Position.ANY_POSITION)
                    return true;
                else if (player.getPosition() == positionSelector.getValue())
                    return true;
                return false;
            });
        });

        SortedList<Player> slPlayer = new SortedList<>(filteredPlayerByPos);

        slPlayer.comparatorProperty().bind(playersTable.comparatorProperty());
        playersTable.setItems(slPlayer);

        // just for testing
        Button deleteBtn = new Button("Delete player");

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(CustomButtons.getAddBtn(), deleteBtn);
        buttonsBox.setAlignment(Pos.CENTER);

        // SideView with team logo, players and search bar
        sideViewLeft.setPadding(new Insets(0, 20, 0, 20));
        sideViewLeft.setAlignment(Pos.CENTER);
        sideViewLeft.getChildren().addAll(logoView, teamNameManagement, comboBoxLabel, positionSelector, playersTable,
                buttonsBox);
        HBox.setMargin(comboBoxLabel, new Insets(10, 0, 10, 0));

        // setting borders for debugging reasons
        sideViewLeft.setStyle("-fx-border-color: black;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: dashed;");

        return sideViewLeft;
    }

    /**
     * Method responsible for generating all the correct data from the sideleftright
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @return sideviewright - vbox containing elements from sideview
     */
    public static VBox getSideViewRight() {
        VBox sideViewRight = new VBox();
        sideViewRight.setStyle("-fx-border-color: green;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: dashed;");

        // Setting the view for player info
        Pane playerView = new Pane();
        playerView.setStyle("-fx-border-color: orange;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: dashed;");
        Text playerName = new Text("Player Name here");
        playerView.getChildren().addAll(playerName);

        // Setting the view for player stats
        Pane playerStatsView = new Pane();
        playerStatsView.setStyle("-fx-border-color: blue;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: dashed;");
        Text playerStats = new Text("Player Stats here");
        playerStatsView.getChildren().addAll(playerStats);

        sideViewRight.setAlignment(Pos.CENTER);
        sideViewRight.getChildren().addAll(playerView, playerStats);

        VBox.setVgrow(playerStatsView, Priority.ALWAYS);
        VBox.setVgrow(playerView, Priority.ALWAYS);

        return sideViewRight;
    }

}
