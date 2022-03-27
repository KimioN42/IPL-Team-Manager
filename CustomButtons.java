
import javafx.scene.control.Button;

/**
 * Class that generates the necessary buttons for the project
 * It is extended to the CustomButtonsView class, which generates the UI
 * and also generates the GUI or runs the methods when the buttons
 * in this class are clicked/interacted.
 * 
 * @author Kimio Nishino
 */
public class CustomButtons extends CustomButtonsView {

    public static Button getAddBtn() {
        Button addBtn = new Button("Add new player");
        addBtn.setOnAction(e -> openAddWindow());
        return addBtn;
    }

    public static Button getDelBtn() {
        Button deleteBtn = new Button("Delete player");
        return deleteBtn;
    }

    public static Button getEditBtn() {
        Button editBtn = new Button("Edit");
        return editBtn;
    }

}
