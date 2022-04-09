/**
 * Enum with players positions.
 * 
 * @author Kimio Nishino and Saniya Farishta
 */
public enum Position {
    BATSMAN("Batsman"),
    WICKETKEEPER("Wicket Keeper"),
    BOWLER("Bowler"),
    ALL_ROUNDER("All Rounder"),
    ANY_POSITION("Any Position");

    public final String label;

    private Position(String label) {
        this.label = label;
    }

    public String getPositionString(Position p) {
        switch (p) {
            case BATSMAN:
                return "Batsman";
            case WICKETKEEPER:
                return "WicketKeeper";
            case BOWLER:
                return "Bowler";
            case ALL_ROUNDER:
                return "All rounder";
            default:
                return "Invalid Position";
        }
    }

    public static Position getPositionFromInt(int p) {
        switch (p) {
            case 0:
                return Position.BATSMAN;
            case 1:
                return Position.WICKETKEEPER;
            case 2:
                return Position.ALL_ROUNDER;
            case 3:
                return Position.BOWLER;
            default:
                return Position.ANY_POSITION;
        }
    }

    public static int getIntFromPosition(Position p) {
        switch (p) {
            case BATSMAN:
                return 0;
            case WICKETKEEPER:
                return 1;
            case BOWLER:
                return 2;
            case ALL_ROUNDER:
                return 3;
            case ANY_POSITION:
                return 4;
            default:
                return 4;
        }
    }
}
