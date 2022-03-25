import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * SideViewRight class for the project.
 * This project contains the elements necessary to render the right side
 * of the application, including player stats and info.
 * @author Kimio Nishino and Saniya Farishta
 */
public class SideViewRight {

    public SideViewRight() {
    }

    public static VBox getSideViewRight() {
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
        

        return sideViewRight;
    }

}
