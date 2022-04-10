import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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

            // getting the player's jersey number to edit
            String temp[] = playeString.split(",");
            int playerNum = Integer.parseInt(temp[0]);

            for (String next, line = brFile.readLine(); line != null; line = next) {
                next = brFile.readLine();
                String splitLine[] = line.split(",");
                // when it finds the player with the same jerseyNumber it's trying to edit
                // it will append the new playerString
                if (Integer.parseInt(splitLine[0]) == playerNum)
                    inputBuffer.append(playeString);
                else
                    inputBuffer.append(line);
                if (next != null)
                    inputBuffer.append(System.lineSeparator());
            }
            brFile.close();
            String inputStr = inputBuffer.toString();
            // System.out.println("Debugging: " + inputStr);

            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println();
        }

    }

    public void removePlayer(int playerNum) {
        System.out.println("Deleting line of player number " + playerNum);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer newFileContent = new StringBuffer();
            boolean isNextTarget = false;
            boolean isPreviousTarget = false;
            boolean firstLine = true;
            for (String next, line = reader.readLine(); line != null; line = next) {
                next = reader.readLine();
                // when our target string is not in the last line, we create a linebreak
                if (isNextTarget && next != null) {
                    newFileContent.append(System.lineSeparator());
                    isNextTarget = false;
                    isPreviousTarget = true;
                }
                // edge case: player to be deleted is last line in file
                else if (isNextTarget && next == null) {
                    System.out.println("Super duper edge case");
                } else if (!firstLine && !isPreviousTarget) {
                    newFileContent.append(System.lineSeparator());
                }
                String temp[] = line.split(",");
                // if the current line is not the line we're looking for
                if (Integer.parseInt(temp[0]) != playerNum) {
                    newFileContent.append(line);
                    // and if the next line is not null
                    if (next != null) {
                        String tempNext[] = next.split(",");
                        // check if the next line is the one we're looking for
                        if (Integer.parseInt(tempNext[0]) == playerNum)
                            isNextTarget = true;
                    }
                    firstLine = false;
                    isPreviousTarget = false;
                }
            }
            reader.close();
            System.out.println("String to be saved to file: " +
                    newFileContent.toString());

            FileWriter writer = new FileWriter(file);
            BufferedWriter bfWriter = new BufferedWriter(writer);
            PrintWriter replace = new PrintWriter(bfWriter);
            replace.write(newFileContent.toString());
            replace.close();
        } catch (Exception e) {
            System.out.println("Exception ocurred while removing player from .dat file");
            e.printStackTrace();
        }
    }
}
