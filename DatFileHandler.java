import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DatFileHandler {
    File file;    
    public DatFileHandler() {
    }


    public ArrayList<Player> saveFileAsPlayers(String filepath) {
        file = new File(filepath);
        ArrayList<Player> players = new ArrayList<>();
        boolean fileIsNotLocked = file.renameTo(file);
        System.out.println("File is not locked: " + fileIsNotLocked);
        try{
            System.out.println("Looking for file in " + filepath);
            Scanner scan = new Scanner(file);
            System.out.println("It reached this line");
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
