import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handler for saving the player.
 * It implements javafx's eventhandler class, and extends to CustomButtonsView.
 * This method is used for saving a player in the database and observableList.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class SaveHandler extends CustomButtonsView implements EventHandler<ActionEvent> {

    private Player player;

    /**
     * Constructor to get the player from another class;
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @param p - Player object to be removed;
     */
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
        System.out.println("Image path from player: " + player.getPath());
        datFileReader.addPlayerToFile(player.saveToString());
        // this updates the observableList during runtime as well. Again, the list is
        // updated next time the program refreshes, as long as the player was properly
        // saved to the .dat file
        olPlayers.add(player);
        stage.close();
    }

    // for some reason I can't understand, this method is not working right now
    // I'll fix it later, but just ignore it for the project scope, we don't use it.
    private void copyImage(String imgURL) {
        File origin = new File(imgURL);
        File destination = new File(System.getProperty("user.dir") + "\\imgs");

        System.out.println("origin to uri: " + origin.toURI());
        System.out.println("destination to uri + origin name: " + destination.toURI() + origin.getName());

        try {
            Files.copy(origin.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error copying file to imgs folder");
            e.printStackTrace();
        }
    }
}
