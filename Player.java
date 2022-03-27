import java.io.File;

import javafx.scene.image.Image;

/**
 * Player class is the object that contains all both player info and player stats.
 * It also contains the the proper classes to display player name;
 * @author Kimio Nishino and Saniya Farishta
 */
public class Player implements Comparable<Player> {

    private Name name;
    //players jersey number
    private int num;
    private int weight;
    private int height;
    private int age;
    private Image image;

    // path to the image file
    private String path;
    private Position pos;
    private Statistics stats;

    /**
     * default constructor
     */
    public Player() { }

    /**
     * Full Player constructor.
     *
     * @param name player`s name
     * @param n jersey number
     * @param w weight
     * @param h height
     * @param a age
     * @param img profile picture
     * @param pos position
     * @param s statistics
     */
    public Player(Name name, int n, int w, int h, int a, Image img, String path,
            Position pos, Statistics s) {

        this.name = name;
        num = n;
        weight = w;
        height = h;
        age = a;
        image = img;
        this.path = path;
        this.pos = pos;
        this.stats = s;
    }

    

    public void setName(String n) {
        name.setName(n);
    }

    public void setNum(int n) {
        if (n > 0 && n < 100)
            num = n;
    }

    public void setWeight(int w) {
        if (w > 0 && w < 250)
            weight = w;
    }

    public void setAge(int a) {
        if (a > 0 && a < 120)
            age = a;
    }

    public void setHeight(int h) {
        if (h > 0 && h < 250)
            height = h;
    }

    public String getName() {
        return this.name.toString();
    }

    public String getFirstName() {
        return this.name.getFirstName();
    }

    public String getLastName() {
        return this.name.getLastName();
    }

    public int getWeight() {
        return weight;
    }

    public int getNum() {
        return num;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public Image getImage() {
        return image;
    }

    public Position getPosition() {
        return pos;
    }

    public Statistics getStats() {
        return stats;
    }

    public String getPath() {
        return path;
    }

    /**
     * user needs to choose between ALL_ROUNDER,
     * BATSMAN, WICKETKEEPER and BOWLER
     *
     * @param p player position
     */
    public void setPosition(Position p) {
        
        // need to figure out a way to check if option is valid
        if (true)
            pos = p;
    }

    /**
     *
     * @param s
     * @return
     */
    public static Player saveFromString(String s) {
        System.out.println("Getting the player from this string: " + s);

        // temporary array to hold strings from input
        String[] temp;

        // varibles to recive input from after parsing each string
        String firstName = null, lastName = null;
        
        int num = 0, weight = 0, height = 0, age = 0;
        int totalRuns = 0, battingLineupNumber = 0;
        int totalGamesPlayed = 0;
        double currentRunRate = 0;
        String teamName = null, battingPos = null;
        
        String pathName = null;
        File file;
        Image image = null;
        Position pos = null;

        try {
            //store string each piece of the string in a temporary array
            temp = s.split(",");

            // read number, name
            num = Integer.parseInt(temp[0]);
            firstName = temp[1];
            lastName = temp[2];

            // get the image file
            pathName = temp[3];
            file = new File("./Java2-Project-Team-Manager/imgs/" + pathName);
            image = new Image(file.toURI().toString());

            // get position and convert to enum
            pos = Position.getPositionFromInt(Integer.parseInt(temp[4]));
            

            // getting the weight, height and age
            age = Integer.parseInt(temp[5]);
            weight = Integer.parseInt(temp[6]);
            height = Integer.parseInt(temp[7]);
            
            // getting all the other stats
            totalRuns = Integer.parseInt(temp[8]);
            battingPos = temp[9];
            currentRunRate = Double.parseDouble(temp[10]);
            battingLineupNumber = Integer.parseInt(temp[11]);
            totalGamesPlayed = Integer.parseInt(temp[12]);
            teamName = temp[13];

        } catch (Exception ex) {
            System.out.println("Data is not in the correct format or some data is missing");
        }

        // create name instance
        Name fn = new Name(firstName, lastName);

        // create statistics instance
        Statistics st = new Statistics(totalRuns, battingPos, teamName, currentRunRate, battingLineupNumber, totalGamesPlayed);
        //create a player instance
        Player pl = new Player(fn, num, weight, height, age, image, pathName,
                pos, st);
        // System.out.println("Player saved from string: \n" + pl.getPlayerString());
        return pl;
    }


    public String getPlayerString() {
        String temp = "====PLAYER INFO=====\n" + 
        "Player : " + name.toString() +
        "\nJerseyNo: " + num + "\nHeight (cm): " + height +
        "\nWeight (kg): " + weight + "\nAge: " + age + 
        "\nImg URL: " + image.getUrl() + "\nPosition: " + pos.toString()
        + "\n" + stats.toString();
        return temp;
    }

    /**
     * Edited toString method to return the jersey number + player name
     * to show it nicely in the listView.
     * Edit: deprecated, we are now using tableView in the main screen.
     * @return
     */
    @Override
    public String toString() {
        return num + " " + name.toString();
    }

    /**
     * Compare players alphabetically.
     * 
     * @param other
     * @return
     */
    @Override
    public int compareTo(Player other) {

        //check if object is null
        if (other == null) throw new NullPointerException();

        //check if both object s being compared are the same
        if (this == other) return 0;

        //compare by name
        return this.getName().compareTo(other.getName());
    }
}
