import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handler for removing the player.
 * It implements javafx's eventhandler class, and extends to CustomButtonsView.
 * This method is used to remove a player from the database and observable list.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class RemoveHandler extends CustomButtonsView implements EventHandler<ActionEvent> {

    private Player player;

    /**
     * Constructor to get the player from another class;
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @param p - Player object to be removed;
     */
    public RemoveHandler(Player p) {
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
        System.out.println("Player you want to delete: " + player.getName());
        int indexRemove = lookForPlayer();
        System.out.println("Index to be removed in observable list: " + indexRemove);
        olPlayers.remove(indexRemove);
        datFileReader.removePlayer(player.getNum());
        stage.close();
    }
}
