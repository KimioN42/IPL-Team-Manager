import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class for the Team Manager project.
 * The main method from which the application starts, is located
 * in this class.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class MainProject extends Application {

    /**
     * Start method for the Team Manager project.
     * The start method puts together all views and nodes to render
     * the application, as well as opens the database file to get the player info.
     * 
     * @author Kimio Nishino and Saniya Farishta
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Opening database file
        DatFileHandler fileReader = new DatFileHandler();
        ArrayList<Player> players = new ArrayList<>();
        // dont know why, but on macos the filepath needs to be ./datfiles/...
        // and on windows, the pathfile should start in the project folder, like this:
        // ./Java2-Project-Team-Manager/datfiles/...
        players = fileReader.saveFileAsPlayers("./datfiles/test.dat");

        // Horizontal Box (root pane)
        HBox root = new HBox();

        // Setting up both side views (left and right)
        VBox svl = SideView.getSideView(players);
        VBox svr = SideView.getSideViewRight();

        // borders for debugging
        root.setStyle("-fx-border-color: red;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: dashed;");

        root.getChildren().addAll(svl);
        HBox.setHgrow(svr, Priority.ALWAYS);

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("RCB Management");
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
