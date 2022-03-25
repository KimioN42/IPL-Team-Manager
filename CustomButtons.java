
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class CustomButtons {
    public static Button getAddBtn() {
        Button addBtn = new Button("Add new player");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                GridPane form = new GridPane();
                Stage stage = new Stage();

                form.setAlignment(Pos.CENTER);
                form.setHgap(10);
                form.setVgap(10);
                form.setPadding(new Insets(25, 25, 25, 25));

                Text formTitle = new Text("Add new player");
                formTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 18));

                form.add(formTitle, 0, 0, 2, 1);
                // Setting up labels and textfields for each player stat or info
                Label firstName = new Label("First name:");
                form.add(firstName, 0, 1);
                TextField firstNameField = new TextField();
                form.add(firstNameField, 1, 1);

                Label lastName = new Label("Last name:");
                form.add(lastName, 0, 2);
                TextField lastNameField = new TextField();
                form.add(lastNameField, 1, 2);

                Label age = new Label("Age: ");
                form.add(age, 0, 3);
                TextField ageField = new TextField();
                form.add(ageField, 1, 3);

                HBox buttonsBox = new HBox(10);
                Button saveBtn = new Button("Save");
                Button closeBtn = new Button("Close");

                closeBtn.setOnAction(actionEvent -> {
                    stage.close();
                });

                buttonsBox.getChildren().addAll(saveBtn, closeBtn);
                buttonsBox.setAlignment(Pos.BOTTOM_CENTER);
                form.add(buttonsBox, 1, 4);

                Scene scene = new Scene(form, 500, 800);

                stage.setTitle("Add new player");
                stage.setScene(scene);
                stage.show();
            }
        });

        return addBtn;
    }
}
