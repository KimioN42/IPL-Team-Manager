
public class Statistics {

    // data members for player`s statistics
    private int totalRuns;
    private String battingPos;
    private String teamName;
    private double currentRunRate;
    private int battingLineupNumber;
    private int totalGamesPlayed;

    // default constructor
    public Statistics() {
    }

    /**
     * constructor with all player's data members
     *
     * @param Totalruns           total number of runs
     * @param Battingpos          batting position of player
     * @param Teamname            name of the team
     * @param Currentrunrate      current run rate of player
     * @param Battinglineupnumber on which number position batsman will come for
     *                            batting
     */
    public Statistics(int Totalruns, String Battingpos, String Teamname,
            double Currentrunrate, int Battinglineupnumber, int totalGamesPlayed) {
        this.totalRuns = Totalruns;
        this.battingPos = Battingpos;
        this.teamName = Teamname;
        this.currentRunRate = Currentrunrate;
        this.battingLineupNumber = Battinglineupnumber;
        this.totalGamesPlayed = totalGamesPlayed;
    }

    // Getters and setters below
    public int getTotalRuns() {
        return this.totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public String getBattingPos() {
        return this.battingPos;
    }

    public void setBattingPos(String battingPos) {
        this.battingPos = battingPos;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getCurrentRunRate() {
        return this.currentRunRate;
    }

    public void setCurrentRunRate(double currentRunRate) {
        this.currentRunRate = currentRunRate;
    }

    public void setCurrentRunRateString(String currentRunRate) throws Exception {
        boolean parsable = true;
        try {
            this.currentRunRate = Double.parseDouble(currentRunRate);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        if (!parsable)
            throw new Exception("Make sure the text format for current run rate is a double! (eg.: 2.4 etc)");
    }

    public int getBattingLineupNumber() {
        return this.battingLineupNumber;
    }

    public void setBattingLineupNumber(int battingLineupNumber) {
        this.battingLineupNumber = battingLineupNumber;
    }

    public int getTotalGamesPlayed() {
        return this.totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    @Override
    public String toString() {
        String temp = "======STATS=====\n" +
                "Total Runs: " + totalRuns +
                "\nBatting Positon: " + battingPos +
                "\nCurrent Run Rate: " + currentRunRate +
                "\nBatting Lineup Number: " + battingLineupNumber +
                "\nTeam name: " + teamName;
        return temp;
    }

}
