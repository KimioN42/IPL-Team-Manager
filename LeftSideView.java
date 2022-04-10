import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LeftSideView extends MainProject {

    /**
     * Variable necessary to add a listener everytime it changes.
     */
    protected static ObjectProperty<Player> selectedPlayer = new SimpleObjectProperty<>();
    protected static ObservableList<Player> olPlayers;

    protected static boolean checkValidJerseyNum(int num) throws Exception {
        for (Player player : olPlayers) {
            if (player.getNum() == num)
                throw new Exception("There is already one player with this jersey number");
        }
        return true;
    }

    /**
     * Method responsible for generating all the correct data from the sideleftview
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @return sideViewLeft - VBox containing all the elements in the sideView of
     *         the application
     */
    public static VBox getSideViewLeft() {
        // Creating the VBox that will contain all the nodes
        VBox sideViewLeft = new VBox(10);

        // Name of the team
        Text teamNameManagement = new Text("RCB Management");
        teamNameManagement.setStyle("-fx-font: 20 Verdana;");

        // Getting logo from imgs folder
        Image logo = new Image("./imgs/RCB_Logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);
        Pane pane = new Pane();
        pane.getChildren().add(logoView);

        TableView<Player> playersTable = new TableView<>();

        // Setting the first column (jersey number)
        TableColumn<Player, String> c1 = new TableColumn<>("No.");
        c1.setCellValueFactory(new PropertyValueFactory<>("num"));
        c1.setResizable(false);
        c1.setReorderable(false);
        c1.prefWidthProperty().bind(playersTable.widthProperty().multiply(0.2));

        // Setting the second column (player name)
        TableColumn<Player, String> c2 = new TableColumn<>("Name");
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setResizable(false);
        c2.setReorderable(false);
        c2.prefWidthProperty().bind(playersTable.widthProperty().multiply(0.8));

        playersTable.getColumns().addAll(c1, c2);
        playersTable.setPrefWidth(sideViewLeft.widthProperty().get());
        playersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Label comboBoxLabel = new Label("Select the Position you want to filter");
        comboBoxLabel.setAlignment(Pos.CENTER);

        // ComboBox with the position properties
        ComboBox<Position> positionSelector = new ComboBox<>();
        positionSelector.getItems().setAll(Position.values());
        positionSelector.getSelectionModel().select(4);
        positionSelector.autosize();

        // Filtering and sorting the players before actually putting them in the
        // tableView
        olPlayers = FXCollections.observableList(players);
        FilteredList<Player> filteredPlayerByPos = new FilteredList<>(olPlayers, p -> true);

        // adding functionality to the combobox
        positionSelector.setOnAction(e -> {
            System.out.println("Position selected: " + positionSelector.getValue().label);
            filteredPlayerByPos.setPredicate(player -> {
                if (positionSelector.getValue() == Position.ANY_POSITION)
                    return true;
                else if (player.getPosition() == positionSelector.getValue())
                    return true;
                return false;
            });

            playersTable.getSelectionModel().select(0);
            selectedPlayer.set(playersTable.getSelectionModel().getSelectedItem());
        });

        SortedList<Player> slPlayer = new SortedList<>(filteredPlayerByPos);
        slPlayer.comparatorProperty().bind(playersTable.comparatorProperty());
        playersTable.setItems(slPlayer);

        playersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        playersTable.getSelectionModel().select(0);
        selectedPlayer.set(playersTable.getSelectionModel().getSelectedItem());

        // Adding a listener to get the selected player everytime user clicks tableview
        ObservableList<Player> selectedItems = playersTable.getSelectionModel().getSelectedItems();
        selectedItems.addListener(new ListChangeListener<Player>() {
            @Override
            public void onChanged(Change<? extends Player> change) {
                try {
                    selectedPlayer.set(selectedItems.get(0));
                    System.out.println("Current selected player is:" + selectedPlayer.get().getName());
                } catch (Exception e) {
                    System.out.println("TableView was filtered, getting new selected player");
                }

            }
        });

        if (playersTable.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Selected player: " + playersTable.getSelectionModel().getSelectedItem().getName());
        } else {
            System.out.println("Selected player is null");
        }

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(CustomButtons.getAddBtn(), CustomButtons.getDelBtn());
        buttonsBox.setAlignment(Pos.CENTER);

        // SideView with team logo, players and search bar
        sideViewLeft.setPadding(new Insets(0, 20, 0, 20));
        sideViewLeft.setAlignment(Pos.CENTER);
        sideViewLeft.getChildren().addAll(logoView, teamNameManagement, comboBoxLabel, positionSelector, playersTable,
                buttonsBox);
        HBox.setMargin(comboBoxLabel, new Insets(10, 0, 10, 0));

        // setting borders for debugging reasons
        sideViewLeft.setStyle("-fx-border-color: black;" +
                "-fx-border-insets: 5; fx-border-width: 2;" +
                "-fx-border-style: solid;");

        return sideViewLeft;
    }

}
