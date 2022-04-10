import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EditHandler extends CustomButtonsView implements EventHandler<ActionEvent> {

    private Player player;

    public EditHandler(Player p) {
        this.player = p;
    }

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
