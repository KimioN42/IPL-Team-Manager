import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DatFileHandler {
    File file;    
    public DatFileHandler() {
    }

    public ArrayList<Player> saveFileAsPlayers(String filepath) {
        file = new File(filepath);
        ArrayList<Player> players = new ArrayList<>();
        try{
            System.out.println("Looking for file in " + filepath);
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                players.add(Player.valueOf(scan.nextLine()));
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error opening the file");
        }   
        for (Player player : players) {
            System.out.println("Players added: " + player.getName().toString());
        }
        return players;
    }
}
