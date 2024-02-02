package mazeactivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import mazeactivity.Square.SquareType;

public class Maze {
    private Square[][] mazeArray;

    public Maze(String fname) throws RuntimeException {
        this.loadMaze(fname);
    }

    /**
     * Loads a maze from the resources folder.
     * @param fname
     * @throws RuntimeException
     */
    private void loadMaze(String fname) throws RuntimeException {
        InputStream resourceStream = Maze.class.getResourceAsStream("/" + fname);
        if (resourceStream == null) {
            throw new RuntimeException("Can't find maze named “" + fname
                    + "” (looking for /res/" + fname);
        }

        Scanner input = new Scanner(resourceStream);
        int R = input.nextInt(); // gets the number of rows
        int C = input.nextInt(); // gets the number of columns

        this.mazeArray = new Square[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int n = input.nextInt();
                SquareType type;
                switch (n) {
                    case 0:
                        type = SquareType.SPACE;
                        break;
                    case 1:
                        type = SquareType.WALL;
                        break;
                    case 2:
                        type = SquareType.START;
                        break;
                    case 3:
                        type = SquareType.EXIT;
                        break;
                    default:
                        throw new RuntimeException("square type value must be between [0,3]");
                }
                this.mazeArray[i][j] = new Square(i,j, type);
            }
        }
        input.close();
    }

    /**
     * Returns a list of neighboring squares
     */
    public ArrayList<Square> getNeighbors(Square sq) {
        int row = sq.getRow();
        int col = sq.getCol();
        ArrayList<Square> neighbors = new ArrayList<Square>();
        if (row-1 >= 0) {
            neighbors.add(mazeArray[row-1][col]); // North
        }
        if (col+1 < mazeArray[0].length) {
            neighbors.add(mazeArray[row][col+1]); // East
        }
        if (row+1 < mazeArray.length) {
            neighbors.add(mazeArray[row+1][col]); // South
        }
        if (col-1 >= 0) {
            neighbors.add(mazeArray[row][col-1]); // West
        }
        return neighbors;
    }


    /**
     * @return the starting square of the maze
     */
    public Square getStart() {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                if (mazeArray[i][j].getType() == SquareType.START) {
                    return mazeArray[i][j];
                }
            }
        }
        return null;
    }

    /**
     * @return the end square of the maze
     */
    public Square getExit() {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                if (mazeArray[i][j].getType() == SquareType.EXIT) {
                    return mazeArray[i][j];
                }
            }
        }
        return null;
    }

    public String toString() { // Used for top window in GUI
        String s = "";
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                s = s + (mazeArray[i][j]) + " ";
            }
            s = s + '\n';
        }
        return s;
    }

    public void reset() {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                mazeArray[i][j].setPrevious(null);
                mazeArray[i][j].setExplored(false);
                mazeArray[i][j].setOnFinalPath(false);
            }
        }
    }

    public Square[][] getSquares(){
        return mazeArray;
    }
}

