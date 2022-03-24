import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DatFileReader {
    File file;    


    public DatFileReader() {
    }


    public ArrayList<Player> saveFileAsPlayers(String filepath) {
        file = new File(filepath);
        System.out.println("Looking for file in " + filepath);
        ArrayList<Player> players = new ArrayList<>();
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                players.add(Player.valueOf(scan.nextLine()));
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error opening the file");
        }   
        return players;
    }
}
