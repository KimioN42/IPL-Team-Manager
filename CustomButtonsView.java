
import java.io.File;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CustomButtonsView {
    /**
     * Class responsible for validating textFields as NumberOnlyTextFields,
     * that is, it will not accept any character that is not a number.
     * Huge thanks to Bukhard! (https://stackoverflow.com/a/18959399/5033494)
     * 
     * @author Bukhard
     * @modified by Kimio Nishino
     */
    public static class NumberTextField extends TextField {
        @Override
        public void replaceText(int start, int end, String text) {
            if (validate(text)) {
                super.replaceText(start, end, text);
            }
        }

        @Override
        public void replaceSelection(String text) {
            if (validate(text)) {
                super.replaceSelection(text);
            }
        }

        private boolean validate(String text) {
            return text.matches("[0-9]*");
        }
    }

    /**
     * Wrapper class to be able to get the String value when running the action
     * inside
     * a lambda function. Used on combobox for batting position selector and
     * when choosing a file.
     * Huge thanks to luca.vercelli! (https://stackoverflow.com/a/55758047)
     * 
     * @author luca.vercelli
     * @modified by Kimio Nishino
     */
    public static class Wrapper<T> {
        public T obj;

        public Wrapper(T obj) {
            this.obj = obj;
        }
    }

    protected static void openAddWindow() {
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
        form.add(firstNameField, 4, 1);

        Label lastName = new Label("Last name:");
        form.add(lastName, 0, 2);
        TextField lastNameField = new TextField();
        form.add(lastNameField, 4, 2);

        Label age = new Label("Age: ");
        form.add(age, 0, 3);
        NumberTextField ageField = new NumberTextField();
        form.add(ageField, 4, 3);

        Label height = new Label("Height (cm): ");
        form.add(height, 0, 4);
        NumberTextField heightField = new NumberTextField();
        form.add(heightField, 4, 4);

        Label weight = new Label("Weight (kg): ");
        form.add(weight, 0, 5);
        NumberTextField weighField = new NumberTextField();
        form.add(weighField, 4, 5);

        Label jerseyNo = new Label("Jersey Number: ");
        form.add(jerseyNo, 0, 6);
        NumberTextField jNumberTextField = new NumberTextField();
        form.add(jNumberTextField, 4, 6);

        Label totalGamesPlayed = new Label("Total Games Played: ");
        form.add(totalGamesPlayed, 0, 7);
        NumberTextField tgpField = new NumberTextField();
        form.add(tgpField, 4, 7);

        Label battingLineUpLabel = new Label("Batting Line up: ");
        form.add(battingLineUpLabel, 0, 8);
        NumberTextField bluField = new NumberTextField();
        form.add(bluField, 4, 8);

        Label currentRunRateLabel = new Label("Current Run Rate: ");
        form.add(currentRunRateLabel, 0, 9);
        NumberTextField crrField = new NumberTextField();
        form.add(crrField, 4, 9);

        Label totalRunsLabel = new Label("Total Runs: ");
        form.add(totalRunsLabel, 0, 10);
        NumberTextField trField = new NumberTextField();
        form.add(trField, 4, 10);

        String battingPos[] = { "Right Hand", "Left Hand" };
        Label battingPosLabel = new Label("Batting position: ");
        form.add(battingPosLabel, 0, 11);
        ComboBox battingPosSelector = new ComboBox<>(FXCollections.observableArrayList(battingPos));
        battingPosSelector.getSelectionModel().select(0);
        battingPosSelector.autosize();
        form.add(battingPosSelector, 4, 11);

        Label teamNameLabel = new Label("Team Name: ");
        form.add(teamNameLabel, 0, 12);
        TextField tnField = new TextField();
        form.add(tnField, 4, 12);

        Label imgLabel = new Label("Select the player picture: ");
        form.add(imgLabel, 0, 13);
        FileChooser fileChooser = new FileChooser();
        Button imgSelectButton = new Button("Select file");
        Wrapper<String> stringWrapper = new Wrapper<>(null);
        Label imgURLLabel = new Label();
        imgSelectButton.setOnAction(e -> {
            Stage fileSelectorStage = new Stage();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            File selectedFile = fileChooser.showOpenDialog(fileSelectorStage);
            ;
            stringWrapper.obj = selectedFile.getPath();
            System.out.println("stringwrapper in action: " + stringWrapper.obj);
            imgURLLabel.setText(stringWrapper.obj);
        });
        form.add(imgSelectButton, 4, 13);
        form.add(imgURLLabel, 0, 14, 5, 1);

        HBox buttonsBox = new HBox(10);
        Button saveBtn = new Button("Save");
        Button closeBtn = new Button("Close");

        closeBtn.setOnAction(actionEvent -> {
            System.out.println("StringWrapper: " + stringWrapper.obj);
            stage.close();
        });

        buttonsBox.getChildren().addAll(saveBtn, closeBtn);
        buttonsBox.setAlignment(Pos.BOTTOM_CENTER);
        form.add(buttonsBox, 3, 16);

        Scene scene = new Scene(form, 500, 800);

        stage.setTitle("Add new player");
        stage.setScene(scene);
        stage.show();

    }

}
