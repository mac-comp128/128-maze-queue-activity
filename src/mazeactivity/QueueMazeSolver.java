package mazeactivity;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import mazeactivity.Square.SquareType;

public class QueueMazeSolver {
    private Maze maze;
    private Queue<Square> workQueue;

    public QueueMazeSolver(Maze maze){
        this.maze = maze;

        //TODO: Initilize the work queue


        //TODO: Add the maze's start square to the work queue

    }

    /**
     * Runs one step of the maze solving algorithm
     * @return true if the maze is solved or unsolvable. False otherwise.
     */
    public boolean step(){
        //TODO: implement one step of the solving algorithm
        



        System.out.println("Queue Contents: ");
        workQueue.stream().map(Square::showCoordinates).forEach(System.out::print);
        System.out.println();

        return false;
    }

    /**
     * Marks all of the squares on the solution path as being on the final path
     * @return a string representing the solution path from the start to end in the form "[r,c][r2,c2][r3,c3]" etc.
     */
    public String markAndGetFinalPath(){
        //TODO: mark all the squares on the solution path and
        // return a string representing the path (use a StringBuilder!)
        
        return ""; // temp
    }

    /**
     * Used for testing
     * @return unmodifiable view of the workQueue
     */
    public List<Square> getWorkQueueAsList(){
        return workQueue.stream().toList(); // immutable
    }
}
