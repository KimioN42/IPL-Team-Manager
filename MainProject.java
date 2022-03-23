import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dpetla
 */
public class MainProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FMXLMainProject.fxml"));
        Scene scene = new Scene(root);
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
