
public class Statistics {

    // data members for player`s statistics
    private int Totalruns;
    private int Battingpos;
    private int Teamname;
    private int Jerseynumber;
    private int Currentrunrate;
    private int Battinglineupnumber;
    

    // default constructor   
    public Statistics() {
    }

    /**
     * constructor with all player`s data members
     *
     * @param Totalruns total number of runs
     * @param Battingpos batting position of player
     * @param Teamname name of the team
     * @param Jerseynumber jersey number of player
     * @param Currentrunrate current run rate of player
     * @param Battinglineupnumber on which number position batsman will come for batting
     */
    public Statistics(int Totalruns, int Battingpos , int Teamname, int Jerseynumber,
            int Currentrunrate, int Battinglineupnumber)
        {
        this.Totalruns = Totalruns;
        this.Battingpos = Battingpos;
        this.Teamname = Teamname;
        this.Jerseynumber = Jerseynumber;
        this.Currentrunrate = Currentrunrate;
        this.Battinglineupnumber = Battinglineupnumber;
        
    }

    // getters for player`s statistics
    public int getTotalruns() {
        return Totalruns;
    }

    public int getBattingpos() {
        return Battingpos;
    }

    public int getTeamname() {
        return Teamname;
    }

    public int getJerseynumber() {
        return Jerseynumber;
    }

    public int getCurrentrunrate() {
        return Currentrunrate;
    }

    public int getBattinglineupnumber() {
        return Battinglineupnumber;
    }

    

    // setters for player`s statistics
    public void setTotalruns(int Totalruns) {
        this.Totalruns = Totalruns;
    }

    public void setBattingpos(int Battingpos) {
        this.Battingpos = Battingpos;
    }

    public void setTeamname(int Teamname) {
        this.Teamname = Teamname;
    }

    public void setJerseynumber(int Jerseynumber) {
        this.Jerseynumber = Jerseynumber;
    }

    public void setCurrentrunrate(int Currentrunrate) {
        this.Currentrunrate = Currentrunrate;
    }

    public void setBattinglineupnumber(int Battinglineupnumber) {
        this.Battinglineupnumber = Battinglineupnumber;
    }

   
}
