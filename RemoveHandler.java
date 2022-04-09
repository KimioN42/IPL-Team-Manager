import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RemoveHandler extends CustomButtonsView implements EventHandler<ActionEvent> {

    private Player player;

    public RemoveHandler(Player p) {
        this.player = p;
    }

    private int lookForPlayer() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getNum() == player.getNum())
                System.out.println("Index you're looking for is: " + i);
        }
        return 0;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Player you want to delete: " + player.getName());
        lookForPlayer();
        // olPlayers.remove(olPlayers.indexOf(player));
        // players.remove(players.indexOf(player));
        System.out.println("current players in observable list:");
        for (Player player : olPlayers) {
            System.out.println(player.getName());
        }
    }
}
