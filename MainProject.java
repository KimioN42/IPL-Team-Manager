import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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

    // protected datafields that are available to the subclasses of MainProject
    static protected DatFileHandler datFileReader = new DatFileHandler();
    static protected ArrayList<Player> players = new ArrayList<>();
    static protected HBox root = new HBox();

    /**
     * Start method for the Team Manager project.
     * The start method puts together all views and nodes to render
     * the application, as well as opens the database file to get the player info.
     * 
     * @author Kimio Nishino and Saniya Farishta
     */
    @Override
    public void start(Stage stage) throws Exception {

        // On MacOS the filepath needs to be ./datfiles/...
        // And on windows, the pathfile should start in the project folder, like this:
        // ./Java2-Project-Team-Manager/datfiles/...
        players = datFileReader.saveFileAsPlayers("./Java2-Project-Team-Manager/datfiles/test.dat");
        


        // Setting up both side views (left and right)
        VBox svl = LeftSideView.getSideViewLeft();
        GridPane svr = RightSideView.getSideViewRight();

        // borders for debugging
        root.setStyle("-fx-border-color: red;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: dashed;");

        root.getChildren().addAll(svl, svr);
        // HBox.setHgrow(svr, Priority.ALWAYS);

        Scene scene = new Scene(root, 900, 600);
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
