import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DatFileHandler is the class that can open and handle the .dat files.
 * It contains the methods necessary to save the player objects from .dat file;
 * @author Kimio Nishino and Saniya Farishta
 */
public class DatFileHandler {

    private File file;    

    public DatFileHandler() {
    }

    /**
     * This method is responsible for opening the file located at the 
     * param 'filepath' and saving every
     * @author Kimio Nishino and Saniya Farishta
     * @param filepath - String containing directory location for .dat file to be opened.
     * @return players - ArrayList of Player object containing every player found in .dat file.
     */
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
            System.out.println("Players added: \n" + player.getPlayerString());
        }
        return players;
    }
}
