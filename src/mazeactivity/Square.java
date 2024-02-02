package mazeactivity;

public class Square {

    public static enum SquareType { SPACE, WALL, START, EXIT }

    private SquareType type;
    private int rowCoordinate;
    private int colCoordinate;

    private Square previous; // neighboring square the caused this one to be added to the work queue
    private boolean explored; // have all of this square's neighbors been added to the work queue?
    private boolean onFinalPath; // This square is on the final solution path between start and exit.

    // Makes a Square with contents n with the specified coordinates
    public Square(int i, int j, SquareType type) {
        this.type = type;
        this.rowCoordinate = i;
        this.colCoordinate = j;

        this.previous = null;
    }

    // Returns the contents of the square
    public SquareType getType() {
        return this.type;
    }

    // Sets the contents of the square
    public void setType(SquareType type) {
        this.type = type;
    }

    public int getRow() {
        return rowCoordinate;
    }

    public int getCol() {
        return colCoordinate;
    }

    public Square getPrevious() {
        return this.previous;
    }

    /**
     * @param sq: neighboring square that was explored when this square was added to the work queue
     */
    public void setPrevious(Square sq) {
        this.previous = sq;
    }

    /**
     * @return the explored
     */
    public boolean isExplored() {
        return explored;
    }

    /**
     * @param explored the explored to set
     */
    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    /**
     * @return the onFinalPath
     */
    public boolean isOnFinalPath() {
        return onFinalPath;
    }

    /**
     * @param onFinalPath the onFinalPath to set
     */
    public void setOnFinalPath(boolean onFinalPath) {
        this.onFinalPath = onFinalPath;
    }

    public String showCoordinates() {
        return "[" + rowCoordinate + "," + colCoordinate + "]";
    }

    public String toString() {
        String s = "";
        if (this.type == SquareType.SPACE) {
            s = "_";
        }
        if (this.type == SquareType.WALL) {
            s = "#";
        }
        if (this.type == SquareType.START) {
            s = "S";
        }
        if (this.type == SquareType.EXIT) {
            s = "E";
        }
        if (this.previous != null) {
            s = "o";
        }
        if (this.explored) {
            s = ".";
        }
        if (this.onFinalPath) {
            s = "x";
        }
        return s;
    }

}

