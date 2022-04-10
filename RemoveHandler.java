import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RemoveHandler extends CustomButtonsView implements EventHandler<ActionEvent> {

    private Player player;

    public RemoveHandler(Player p) {
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
        System.out.println("Player you want to delete: " + player.getName());
        int indexRemove = lookForPlayer();
        System.out.println("Index to be removed in observable list: " + indexRemove);
        olPlayers.remove(indexRemove);
        datFileReader.removePlayer(player.getNum());
        stage.close();
    }
}
