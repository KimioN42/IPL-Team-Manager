
import javafx.scene.control.Button;

/**
 * Class that generates the necessary buttons for the project
 * It is extended to the CustomButtonsView class, which generates the GUI
 * and runs the methods when the buttons in this class are clicked/interacted.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class CustomButtons extends CustomButtonsView {

    /**
     * Method used to create an add button and set its onAction methods.
     * 
     * @author Kimio Nishino and Saniya Farishta
     */
    public static Button getAddBtn() {
        Button addBtn = new Button("Add new player");
        addBtn.setOnAction(e -> openAddWindow());
        return addBtn;
    }

    /**
     * Method used to create and delete button and set its onAction methods.
     * 
     * @author Kimio Nishino and Saniya Farishta
     */
    public static Button getDelBtn() {
        Button deleteBtn = new Button("Delete player");
        deleteBtn.setOnAction(e -> openDelWindow());
        return deleteBtn;
    }

    /**
     * Method used to create an edit button and set its onAction methods.
     * 
     * @author Kimio Nishino and Saniya Farishta
     */
    public static Button getEditBtn() {
        Button editBtn = new Button("Edit");
        editBtn.setOnAction(e -> openEditWindow());
        return editBtn;
    }

}
