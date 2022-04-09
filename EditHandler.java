import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EditHandler extends CustomButtonsView implements EventHandler<ActionEvent> {

    private Player player;

    public EditHandler(Player p) {
        this.player = p;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Gonna try to edit the player now");
        datFileReader.editPlayer(player.saveToString());
        stage.close();
    }
}
