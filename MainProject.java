import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        players = fileReader.saveFileAsPlayers("database.dat");
        for (Player player : players) {
            player.toString();
        }

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
        ImageView view = new ImageView(logo);
        view.setFitWidth(100);
        view.setPreserveRatio(true);
        Pane pane = new Pane();
        pane.getChildren().add(view);


        //SideView with team logo, players and search bar
        VBox sideViewLeft = new VBox();
        sideViewLeft.getChildren().addAll(view, teamNameManagement);
        VBox.setMargin(view, new Insets(10, 20, 20, 60));
        VBox.setMargin(teamNameManagement, new Insets(0, 20, 20, 20));
        
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
