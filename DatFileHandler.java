import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DatFileHandler is the class that can open and handle the .dat files.
 * It contains the methods necessary to save the player objects from .dat file;
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public class DatFileHandler {

    private File file;
    private String filepath;

    public DatFileHandler(String filepath) {
        this.file = new File(filepath);
        this.filepath = filepath;
    }

    /**
     * This method is responsible for opening the file located at the
     * param 'filepath' and saving every
     * 
     * @author Kimio Nishino and Saniya Farishta
     * 
     * @return players - ArrayList of Player object containing every player found in
     *         .dat file.
     */
    public ArrayList<Player> saveFileAsPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            System.out.println("Looking for file in " + filepath);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                players.add(Player.saveFromString(scan.nextLine()));
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error opening the file");
        }
        return players;
    }

    /**
     * This method is responsible for opening the file located at the
     * param 'filepath' and saving every
     * 
     * @author Kimio Nishino and Saniya Farishta
     * @param playerString - String containing all the info from the player to be
     *                     added to the file
     */
    public void addPlayerToFile(String playerString) {
        System.out.println("String to save: ");
        System.out.println(playerString);
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(System.lineSeparator());
            fw.write(playerString);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error while appending new player to file");
        }
    }

    public void editPlayer(String playeString) {
        try {
            System.out.println("Player string to be edited: ");
            System.out.println(playeString);
            BufferedReader brFile = new BufferedReader(new FileReader(file));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = brFile.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append(System.lineSeparator());
            }
            brFile.close();
            String inputStr = inputBuffer.toString();
            System.out.println("Debugging: " + inputStr);

            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println();
        }

    }

    public void removePlayer(int indexLine) {

    }
}
