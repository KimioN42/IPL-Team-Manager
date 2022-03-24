import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main class for the Team Manager project.
 * The main method from which the application starts, is located
 * in this class.
 * @author Kimio Nishino and Saniya Farishta
 */
public class MainProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //Opening database file
        DatFileHandler fileReader = new DatFileHandler();
        ArrayList<Player> players = new ArrayList<>();
        players = fileReader.saveFileAsPlayers("./Java2-Project-Team-Manager/datfiles/database.dat");

        //Horizontal Box (root pane)
        HBox root = new HBox();

        //borders for debugging reasons
        root.setStyle("-fx-border-color: red;" +
        "-fx-border-insets: 5; fx-border-width: 2;" +
        "-fx-border-style: dashed;" );        


        //Name of the team
        Text teamNameManagement = new Text("RCB Management"); 
        teamNameManagement.setStyle("-fx-font: 20 Verdana;");

        //Getting logo from imgs folder
        Image logo = new Image("./imgs/RCB_Logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);
        Pane pane = new Pane();
        pane.getChildren().add(logoView);

        Label comboBoxLabel = new Label("Select the Position you want to filter");
        comboBoxLabel.setAlignment(Pos.CENTER);

        //ComboBox with the position properties
        ComboBox<Position> positionSelector = new ComboBox<>();
        positionSelector.getItems().setAll(Position.values());
        positionSelector.autosize();
        // positionSelector.setPlaceholder(comboBoxLabel);
        
        


        //ListView with players' names
        ListView<Player> playersList = new ListView<>();
        for (Player player : players) {
            playersList.getItems().add(player);
        }
        //as soon as the program is run, the focus will go to the listView
        playersList.requestFocus();

        //SideView with team logo, players and search bar
        VBox sideViewLeft = new VBox();
        sideViewLeft.setAlignment(Pos.CENTER);
        sideViewLeft.getChildren().addAll(logoView, teamNameManagement, comboBoxLabel, positionSelector, playersList);
        HBox.setMargin(comboBoxLabel, new Insets(10, 0, 10, 0));
        

        //setting borders for debugging reasons
        sideViewLeft.setStyle("-fx-border-color: black;" +
        "-fx-border-insets: 5; fx-border-width: 2;" +
        "-fx-border-style: dashed;" );

        //Setting the right side view
        VBox sideViewRight = new VBox();
        sideViewRight.setStyle("-fx-border-color: green;" +
        "-fx-border-insets: 5; fx-border-width: 2;" +
        "-fx-border-style: dashed;" );

        //Setting the view for player info
        Pane playerView = new Pane();
        playerView.setStyle("-fx-border-color: orange;" +
        "-fx-border-insets: 5; fx-border-width: 2;" +
        "-fx-border-style: dashed;");
        Text playerName = new Text("Player Name here");
        playerView.getChildren().addAll(playerName);


        //Setting the view for player stats
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

        HBox.setHgrow(sideViewRight, Priority.ALWAYS);
        root.getChildren().addAll(sideViewLeft, sideViewRight);

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Cricket Team Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
