
import java.io.File;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Class that generates the GUI and methods for buttons when clicked.
 * 
 * @author Kimio Nishino
 */
public class CustomButtonsView extends LeftSideView {

    protected static Stage stage = new Stage();

    /**
     * Class responsible for validating textFields as NumberOnlyTextFields,
     * that is, it will not accept any character that is not a number.
     * Huge thanks to Bukhard! (https://stackoverflow.com/a/18959399/5033494)
     * 
     * @author Bukhard
     * @modified by Kimio Nishino
     */
    public static class IntegerTextField extends TextField {
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
     * inside a lambda function. Used on combobox for batting position selector and
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
        firstNameField.setId("TextField for first name");
        form.add(firstNameField, 4, 1);

        Label lastName = new Label("Last name:");
        form.add(lastName, 0, 2);
        TextField lastNameField = new TextField();
        lastNameField.setId("TextField for last name");
        form.add(lastNameField, 4, 2);

        Label age = new Label("Age: ");
        form.add(age, 0, 3);
        IntegerTextField ageField = new IntegerTextField();
        ageField.setId("TextField for Age");
        form.add(ageField, 4, 3);

        Label height = new Label("Height (cm): ");
        form.add(height, 0, 4);
        IntegerTextField heightField = new IntegerTextField();
        heightField.setId("TextField for height");
        form.add(heightField, 4, 4);

        Label weight = new Label("Weight (kg): ");
        form.add(weight, 0, 5);
        IntegerTextField weightField = new IntegerTextField();
        weightField.setId("TextField for weight");
        form.add(weightField, 4, 5);

        Label jerseyNo = new Label("Jersey Number: ");
        form.add(jerseyNo, 0, 6);
        IntegerTextField jNumberTextField = new IntegerTextField();
        jNumberTextField.setId("TextField for jersey number");
        form.add(jNumberTextField, 4, 6);

        Label totalGamesPlayed = new Label("Total Games Played: ");
        form.add(totalGamesPlayed, 0, 7);
        IntegerTextField tgpField = new IntegerTextField();
        tgpField.setId("TextField for total games played");
        form.add(tgpField, 4, 7);

        Label battingLineUpLabel = new Label("Batting Line up: ");
        form.add(battingLineUpLabel, 0, 8);
        IntegerTextField bluField = new IntegerTextField();
        bluField.setId("TextField for batting line up");
        form.add(bluField, 4, 8);

        Label currentRunRateLabel = new Label("Current Run Rate: ");
        form.add(currentRunRateLabel, 0, 9);
        TextField crrField = new TextField();
        crrField.setId("TextField for current run rate");
        form.add(crrField, 4, 9);

        Label totalRunsLabel = new Label("Total Runs: ");
        form.add(totalRunsLabel, 0, 10);
        IntegerTextField trField = new IntegerTextField();
        trField.setId("TextField for total runs");
        form.add(trField, 4, 10);

        String battingPos[] = { "Right Hand", "Left Hand" };
        Label battingPosLabel = new Label("Batting position: ");
        form.add(battingPosLabel, 0, 11);
        ComboBox battingPosSelector = new ComboBox<>(FXCollections.observableArrayList(battingPos));
        battingPosSelector.getSelectionModel().select(0);
        battingPosSelector.autosize();
        form.add(battingPosSelector, 4, 11);

        String posArray[] = { "Batsman", "Wicket Keeper", "Bowler", "All Rounder" };
        Label positionLabel = new Label("Position");
        ComboBox posSelector = new ComboBox<>(FXCollections.observableArrayList(posArray));
        posSelector.getSelectionModel().select(0);
        posSelector.autosize();
        form.add(positionLabel, 0, 12);
        form.add(posSelector, 4, 12);

        Label teamNameLabel = new Label("Team Name: ");
        form.add(teamNameLabel, 0, 13);
        TextField tnField = new TextField();
        tnField.setId("TextField for team name");
        form.add(tnField, 4, 13);

        Label imgLabel = new Label("Select the player picture: ");
        form.add(imgLabel, 0, 14);
        FileChooser fileChooser = new FileChooser();
        Button imgSelectButton = new Button("Select file");
        Wrapper<String> filePathWrapper = new Wrapper<>(null);
        Wrapper<String> imgNameWrapper = new Wrapper<String>(null);
        Label imgURLLabel = new Label();
        imgURLLabel.setWrapText(true);
        GridPane.setHalignment(imgURLLabel, HPos.CENTER);
        imgSelectButton.setOnAction(e -> {
            try {
                Stage fileSelectorStage = new Stage();
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/imgs"));
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg",
                        "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                File selectedFile = fileChooser.showOpenDialog(fileSelectorStage);
                imgURLLabel.setText("Select an image inside the imgs folder of the project!");
                filePathWrapper.obj = selectedFile.getPath();
                imgNameWrapper.obj = selectedFile.getName();
                imgURLLabel.setText("Image selected: " + imgNameWrapper.obj);
            } catch (Exception ex) {
                System.out.println("Error in filechooser");
                System.out.println("Value of the stringwrapper:" + filePathWrapper.obj);
            }

        });
        form.add(imgSelectButton, 4, 14);
        form.add(imgURLLabel, 0, 17, 5, 2);

        Label exceptionLabel = new Label("");
        form.add(exceptionLabel, 0, 15, 5, 1);
        GridPane.setHalignment(exceptionLabel, HPos.CENTER);

        // creating hbox for the 3 buttons
        HBox buttonsBox = new HBox(10);
        // 3 buttons
        Button checkForm = new Button("Check player");
        Button saveBtn = new Button("Save");
        Button closeBtn = new Button("Close");

        // setting initial state of the saveBtn
        saveBtn.setDisable(true);

        // player p will be the player created from the form
        Player p = new Player();

        // setting actions for each button

        closeBtn.setOnAction(actionEvent -> stage.close());
        checkForm.setOnAction(e -> {
            // first I'm gonna make sure the user has filled up every text-field
            // and has also got a valid image for player picture.

            // looking up all text fields in the form nodes
            Set<Node> nodes = form.lookupAll(".text-field");
            boolean emptyTextFields = false;
            for (Node node : nodes) {
                TextField tfNode = (TextField) node;
                if (tfNode.getText() == "") {
                    emptyTextFields = true;
                }
            }
            if (filePathWrapper.obj == null) {
                System.out.println("User hasn't selected a valid image yet!");
                exceptionLabel.setText("Please select a valid image!!!");
            } else if (emptyTextFields) {
                System.out.println("At least one text-field is empty");
                exceptionLabel.setText("First fill up every text label");
            } else {
                try {
                    Statistics s = new Statistics();
                    p.setName(firstNameField.getText() + " " + lastNameField.getText());
                    p.setAge(Integer.parseInt(ageField.getText()));
                    p.setHeight(Integer.parseInt(heightField.getText()));
                    p.setWeight(Integer.parseInt(weightField.getText()));
                    if (checkValidJerseyNum(Integer.parseInt(jNumberTextField.getText())))
                        p.setNum(Integer.parseInt(jNumberTextField.getText()));
                    p.setPosition(Position.getPositionFromInt(posSelector.getSelectionModel().getSelectedIndex()));
                    p.setPath(imgNameWrapper.obj);
                    s.setTotalGamesPlayed(Integer.parseInt(tgpField.getText()));
                    s.setBattingLineupNumber(Integer.parseInt(bluField.getText()));
                    s.setTotalRuns(Integer.parseInt(trField.getText()));
                    s.setBattingPos(battingPosSelector.getValue().toString());
                    s.setTeamName(tnField.getText());
                    s.setCurrentRunRate(Double.parseDouble(crrField.getText()));
                    saveBtn.setDisable(false);
                    File imgFile = new File(filePathWrapper.obj);
                    Image pImage = new Image(imgFile.toURI().toString());
                    p.setImage(pImage);
                    p.setStatistics(s);
                    exceptionLabel.setText("You can save this player! (Or close to discard)");

                    // Making every text-input and combobox disabled so user can't change
                    // after checking the form
                    for (Node node : nodes) {
                        TextField tfNode = (TextField) node;
                        tfNode.setEditable(false);
                    }
                    posSelector.setDisable(true);
                    battingPosSelector.setDisable(true);
                    imgSelectButton.setDisable(true);
                    checkForm.setDisable(true);

                } catch (Exception ex) {
                    exceptionLabel.setText(ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }

        });
        saveBtn.setOnAction(new SaveHandler(p));

        buttonsBox.getChildren().addAll(checkForm, saveBtn, closeBtn);
        buttonsBox.setAlignment(Pos.BOTTOM_CENTER);
        form.add(buttonsBox, 0, 16, 5, 1);

        Scene scene = new Scene(form, 500, 800);

        stage.setTitle("Add new player");
        stage.setScene(scene);
        stage.show();
    }

    protected static void openEditWindow() {
        GridPane form = new GridPane();

        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(25, 25, 25, 25));
        Text formTitle = new Text("Edit player");
        formTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 18));
        form.add(formTitle, 0, 0, 2, 1);
        // Setting up labels and textfields for each player stat or info
        Label firstName = new Label("First name:");
        form.add(firstName, 0, 1);
        TextField firstNameField = new TextField();
        firstNameField.setId("TextField for first name");
        firstNameField.setText(selectedPlayer.get().getFirstName());
        form.add(firstNameField, 4, 1);

        Label lastName = new Label("Last name:");
        form.add(lastName, 0, 2);
        TextField lastNameField = new TextField();
        lastNameField.setId("TextField for last name");
        lastNameField.setText(selectedPlayer.get().getLastName());
        form.add(lastNameField, 4, 2);

        Label age = new Label("Age: ");
        form.add(age, 0, 3);
        IntegerTextField ageField = new IntegerTextField();
        ageField.setId("TextField for Age");
        ageField.setText("" + selectedPlayer.get().getAge());
        form.add(ageField, 4, 3);

        Label height = new Label("Height (cm): ");
        form.add(height, 0, 4);
        IntegerTextField heightField = new IntegerTextField();
        heightField.setId("TextField for height");
        heightField.setText("" + selectedPlayer.get().getHeight());
        form.add(heightField, 4, 4);

        Label weight = new Label("Weight (kg): ");
        form.add(weight, 0, 5);
        IntegerTextField weightField = new IntegerTextField();
        weightField.setId("TextField for weight");
        weightField.setText("" + selectedPlayer.get().getWeight());
        form.add(weightField, 4, 5);

        Label jerseyNo = new Label("Jersey Number: ");
        form.add(jerseyNo, 0, 6);
        IntegerTextField jNumberTextField = new IntegerTextField();
        jNumberTextField.setId("TextField for jersey number");
        jNumberTextField.setText("" + selectedPlayer.get().getNum());
        jNumberTextField.setDisable(true);
        form.add(jNumberTextField, 4, 6);

        Label totalGamesPlayed = new Label("Total Games Played: ");
        form.add(totalGamesPlayed, 0, 7);
        IntegerTextField tgpField = new IntegerTextField();
        tgpField.setId("TextField for total games played");
        tgpField.setText("" + selectedPlayer.get().getStats().getTotalGamesPlayed());
        form.add(tgpField, 4, 7);

        Label battingLineUpLabel = new Label("Batting Line up: ");
        form.add(battingLineUpLabel, 0, 8);
        IntegerTextField bluField = new IntegerTextField();
        bluField.setId("TextField for batting line up");
        bluField.setText("" + selectedPlayer.get().getStats().getBattingLineupNumber());
        form.add(bluField, 4, 8);

        Label currentRunRateLabel = new Label("Current Run Rate: ");
        form.add(currentRunRateLabel, 0, 9);
        TextField crrField = new TextField();
        crrField.setId("TextField for current run rate");
        crrField.setText("" + selectedPlayer.get().getStats().getCurrentRunRate());
        form.add(crrField, 4, 9);

        Label totalRunsLabel = new Label("Total Runs: ");
        form.add(totalRunsLabel, 0, 10);
        IntegerTextField trField = new IntegerTextField();
        trField.setId("TextField for total runs");
        trField.setText("" + selectedPlayer.get().getStats().getTotalRuns());
        form.add(trField, 4, 10);

        String battingPos[] = { "Right Hand", "Left Hand" };
        Label battingPosLabel = new Label("Batting position: ");
        form.add(battingPosLabel, 0, 11);
        ComboBox battingPosSelector = new ComboBox<>(FXCollections.observableArrayList(battingPos));
        battingPosSelector.getSelectionModel().select(0);
        battingPosSelector.autosize();
        form.add(battingPosSelector, 4, 11);

        String posArray[] = { "Batsman", "Wicket Keeper", "Bowler", "All Rounder" };
        Label positionLabel = new Label("Position");
        ComboBox posSelector = new ComboBox<>(FXCollections.observableArrayList(posArray));
        posSelector.getSelectionModel().select(Position.getIntFromPosition(selectedPlayer.get().getPosition()));
        posSelector.autosize();
        form.add(positionLabel, 0, 12);
        form.add(posSelector, 4, 12);

        Label teamNameLabel = new Label("Team Name: ");
        form.add(teamNameLabel, 0, 13);
        TextField tnField = new TextField();
        tnField.setId("TextField for team name");
        tnField.setText(selectedPlayer.get().getStats().getTeamName());
        form.add(tnField, 4, 13);

        Label imgLabel = new Label("Select the player picture: ");
        form.add(imgLabel, 0, 14);
        FileChooser fileChooser = new FileChooser();
        Button imgSelectButton = new Button("Select file");
        Wrapper<String> filePathWrapper = new Wrapper<>(selectedPlayer.get().getPath());
        Wrapper<String> imgNameWrapper = new Wrapper<String>(null);
        Label imgURLLabel = new Label(selectedPlayer.get().getPath());
        imgURLLabel.setWrapText(true);
        GridPane.setHalignment(imgURLLabel, HPos.CENTER);
        imgSelectButton.setOnAction(e -> {
            try {
                Stage fileSelectorStage = new Stage();
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/imgs"));
                FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg",
                        "*.png");
                fileChooser.getExtensionFilters().add(imageFilter);
                File selectedFile = fileChooser.showOpenDialog(fileSelectorStage);
                imgURLLabel.setText("Select an image inside the imgs folder of the project!");
                filePathWrapper.obj = selectedFile.getPath();
                imgNameWrapper.obj = selectedFile.getName();
                imgURLLabel.setText("Image selected: " + imgNameWrapper.obj);
            } catch (Exception ex) {
                System.out.println("Error in filechooser");
                System.out.println("Value of the stringwrapper:" + filePathWrapper.obj);
            }

        });
        form.add(imgSelectButton, 4, 14);
        form.add(imgURLLabel, 0, 17, 5, 2);

        Label exceptionLabel = new Label("");
        form.add(exceptionLabel, 0, 15, 5, 1);
        GridPane.setHalignment(exceptionLabel, HPos.CENTER);

        // creating hbox for the 3 buttons
        HBox buttonsBox = new HBox(10);
        // 3 buttons
        Button checkForm = new Button("Check player");
        Button saveBtn = new Button("Save");
        Button closeBtn = new Button("Close");

        // setting initial state of the saveBtn
        saveBtn.setDisable(true);

        // player p will be the player created from the form
        Player p = new Player();

        // setting actions for each button

        closeBtn.setOnAction(actionEvent -> stage.close());
        checkForm.setOnAction(e -> {
            // first I'm gonna make sure the user has filled up every text-field
            // and has also got a valid image for player picture.

            // looking up all text fields in the form nodes
            Set<Node> nodes = form.lookupAll(".text-field");
            boolean emptyTextFields = false;
            for (Node node : nodes) {
                TextField tfNode = (TextField) node;
                if (tfNode.getText() == "") {
                    emptyTextFields = true;
                }
            }
            if (filePathWrapper.obj == null) {
                System.out.println("User hasn't selected a valid image yet!");
                exceptionLabel.setText("Please select a valid image!!!");
            } else if (emptyTextFields) {
                System.out.println("At least one text-field is empty");
                exceptionLabel.setText("First fill up every text label");
            } else {
                try {
                    Statistics s = new Statistics();
                    p.setName(firstNameField.getText() + " " + lastNameField.getText());
                    p.setAge(Integer.parseInt(ageField.getText()));
                    p.setHeight(Integer.parseInt(heightField.getText()));
                    p.setWeight(Integer.parseInt(weightField.getText()));
                    p.setNum(Integer.parseInt(jNumberTextField.getText()));
                    p.setPosition(Position.getPositionFromInt(posSelector.getSelectionModel().getSelectedIndex()));
                    p.setPath(imgNameWrapper.obj);
                    s.setTotalGamesPlayed(Integer.parseInt(tgpField.getText()));
                    s.setBattingLineupNumber(Integer.parseInt(bluField.getText()));
                    s.setTotalRuns(Integer.parseInt(trField.getText()));
                    s.setBattingPos(battingPosSelector.getValue().toString());
                    s.setTeamName(tnField.getText());
                    s.setCurrentRunRate(Double.parseDouble(crrField.getText()));
                    saveBtn.setDisable(false);
                    File imgFile = new File(filePathWrapper.obj);
                    Image pImage = new Image(imgFile.toURI().toString());
                    p.setImage(pImage);
                    p.setStatistics(s);
                    exceptionLabel.setText("You can save this player! (Or close to discard)");

                    // Making every text-input and combobox disabled so user can't change
                    // after checking the form
                    for (Node node : nodes) {
                        TextField tfNode = (TextField) node;
                        tfNode.setEditable(false);
                    }
                    posSelector.setDisable(true);
                    battingPosSelector.setDisable(true);
                    imgSelectButton.setDisable(true);
                    checkForm.setDisable(true);

                } catch (Exception ex) {
                    exceptionLabel.setText(ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }

        });
        saveBtn.setOnAction(new EditHandler(p));

        buttonsBox.getChildren().addAll(checkForm, saveBtn, closeBtn);
        buttonsBox.setAlignment(Pos.BOTTOM_CENTER);
        form.add(buttonsBox, 0, 16, 5, 1);

        Scene scene = new Scene(form, 500, 800);

        stage.setTitle("Add new player");
        stage.setScene(scene);
        stage.show();
    }

    protected static void openDelWindow() {
        GridPane alert = new GridPane();
        alert.setAlignment(Pos.CENTER);
        alert.setHgap(10);
        alert.setVgap(10);
        alert.setPadding(new Insets(25, 25, 25, 25));
        Label label = new Label("Are you sure you want to delete player "
                + selectedPlayer.get().getName() + "?");
        label.setWrapText(true);
        Button yes = new Button("Yes");
        Button no = new Button("No");
        alert.add(label, 0, 0, 10, 1);
        alert.add(yes, 0, 1, 5, 1);
        alert.add(no, 6, 1, 5, 1);

        yes.setOnAction(new RemoveHandler(selectedPlayer.get()));
        no.setOnAction(e -> stage.close());

        Scene scene = new Scene(alert, 200, 200);
        stage.setTitle("Delete player");
        stage.setScene(scene);
        stage.show();
    }

}
