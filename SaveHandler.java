import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveHandler implements EventHandler<ActionEvent> {

    private Player player;

    public SaveHandler(Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(player.getPlayerString());

    }
}
