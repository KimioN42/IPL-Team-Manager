import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handler for editing the player.
 * It implements javafx's eventhandler class, and extends to CustomButtonsView.
 * This method is used for editing a player in the database and observableList.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class EditHandler extends CustomButtonsView implements EventHandler<ActionEvent> {

    private Player player;

    /**
     * Constructor to get the player from another class;
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @param p - Player object to be removed;
     */
    public EditHandler(Player p) {
        this.player = p;
    }

    /**
     * Method used to get the index of player to be removed from observableList;
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @return index of player that you want to delete in the ObservableList of
     *         players;
     */
    private int lookForPlayer() {
        for (int i = 0; i < olPlayers.size(); i++) {
            if (players.get(i).getNum() == player.getNum())
                return i;
        }
        return -1;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Editing player " + player.getName());
        datFileReader.editPlayer(player.saveToString());
        int indexToEdit = lookForPlayer();
        olPlayers.set(indexToEdit, player);
        stage.close();
    }
}
