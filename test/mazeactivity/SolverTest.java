package mazeactivity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolverTest {
    private QueueMazeSolver solver;
    private Maze maze;

    @BeforeEach
    public void makeSolver(){
        maze = new Maze("maze-1");
        solver = new QueueMazeSolver(maze);
    }

    @Test
    public void testInitialization(){
        List<Square> workQueue = solver.getWorkQueueAsList();
        assertEquals(1, workQueue.size());
        assertEquals(maze.getStart(), workQueue.get(0));
    }

    @Test
    public void testStep(){
        solver.step();
        
        assertTrue(maze.getSquares()[0][0].isExplored());
        assertFalse(maze.getSquares()[0][1].isExplored());
        assertFalse(maze.getSquares()[1][0].isExplored());
       
        assertNotNull(maze.getSquares()[0][1].getPrevious());
        assertNotNull(maze.getSquares()[1][0].getPrevious());
        
        List<Square> workQueue = solver.getWorkQueueAsList();
        assertEquals("[0,1][1,0]", workQueue.stream().map(Square::showCoordinates).collect(Collectors.joining()));
        
        solver.step();
        workQueue = solver.getWorkQueueAsList();
        assertEquals("[1,0][0,2][1,1]", workQueue.stream().map(Square::showCoordinates).collect(Collectors.joining()));
    }

    @Test
    public void testFinalPath()
    {
        int stepsTaken = 0;
        boolean isDone = false;
        while (!isDone){
            isDone = solver.step();
            stepsTaken++;
        }
        assertEquals(9, stepsTaken);
        
        assertEquals("[2,2][1,2][0,2][0,1]", solver.markAndGetFinalPath());

        List<String> finalPathList = List.of("[2,2]", "[1,2]", "[0,2]", "[0,1]");
        Square[][] mazeArray = maze.getSquares();
        for(int r = 0; r < mazeArray.length; r++){
            for(int c = 0; c < mazeArray[0].length; c++){
                String coords = mazeArray[r][c].showCoordinates();
                if (finalPathList.contains(coords)){
                    assertTrue(mazeArray[r][c].isOnFinalPath());
                }
                else{
                    assertFalse(mazeArray[r][c].isOnFinalPath());
                }
            }
        }
    }
}
