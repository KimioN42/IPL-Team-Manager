import java.io.File;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
                sideViewRight.setGridLinesVisible(true);
                sideViewRight.setAlignment(Pos.CENTER);
                sideViewRight.setPadding(new Insets(15, 15, 15, 15));
                sideViewRight.setStyle("-fx-border-color: blue;" +
                                "-fx-border-insets: 5; fx-border-width: 2;" +
                                "-fx-border-style: dashed;");

                
                //Here I'm running into the same problem as the file opening..
                //Windows requires the full path as if the file is being run from one directory
                //outside of the project directory, while MacOS is being run from within 
                //the project directory... need to find a way to automatically manage this.
                File file = new File("./Java2-Project-Team-Manager/imgs/dk.JPG");
                Image image = new Image(file.toURI().toString());
                ImageView imgView = new ImageView(image);
                imgView.setFitHeight(300);
                imgView.setPreserveRatio(true);

                Label imgViewLabel = new Label("imgViewLabel");

                Label testLabel = new Label("This is just a test");
                sideViewRight.add(testLabel, 3, 0);
                sideViewRight.add(imgView, 0, 0);
                sideViewRight.add(imgViewLabel, 0, 1);
                GridPane.setHalignment(imgViewLabel, HPos.CENTER);

                return sideViewRight;
        }

}
