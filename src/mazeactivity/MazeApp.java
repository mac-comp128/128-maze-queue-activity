package mazeactivity;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.TextField;
import edu.macalester.graphics.ui.Button;

/**
 * Comp 128 Maze solver application
 */
public class MazeApp {
    private CanvasWindow canvas;
    private TextField filenameField;
    private GraphicsText mazeDisplay;

    private Maze maze = null;
    private QueueMazeSolver solver;

    public MazeApp(){
        canvas = new CanvasWindow("Queue Maze Solver", 600, 600);
        setupUI();
    }

    /**
     * Create the user interface
     */
    private void setupUI(){
        mazeDisplay = new GraphicsText();
        mazeDisplay.setFont("monospaced, Courier New", FontStyle.PLAIN, 14);
        canvas.add(mazeDisplay, 50, 50);

        GraphicsGroup uiGroup = new GraphicsGroup();

        filenameField = new TextField();
        filenameField.setText("maze-2");

        Button loadButton = new Button("Load Maze");
        loadButton.onClick( () -> loadMaze() );

        Button stepButton = new Button("Step");
        stepButton.onClick(() -> runOneStep());

        Point center = canvas.getCenter();
        double fieldWidthWithMargin = filenameField.getSize().getX() + 5;
        double totalWidth = fieldWidthWithMargin + loadButton.getSize().getX();


        uiGroup.add(filenameField, center.getX() - totalWidth/2.0, 0);
        uiGroup.add(loadButton, filenameField.getPosition().getX() + fieldWidthWithMargin, 0);
        uiGroup.add(stepButton, center.getX() - stepButton.getWidth()/2.0, Math.max(loadButton.getHeight(), filenameField.getHeight())+ 10);
        canvas.add(uiGroup, 0, canvas.getHeight() - uiGroup.getHeight());
    }

    /**
     * Loads a maze from the resources folder and displays it.
     */
    private void loadMaze(){
        String fname = filenameField.getText();
        if (fname.isEmpty()){
            System.err.println("A filename must be specified before a maze can be loaded (e.g. 'maze-2')");
            return;
        }

        try {
            
            maze = new Maze(fname);
            mazeDisplay.setText(maze.toString());
            solver = new QueueMazeSolver(maze);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs one step of the solving algorithm and displays the results
     */
    private void runOneStep(){
        boolean isDone = solver.step();
        mazeDisplay.setText(maze.toString());
    }


    public static void main(String[] args) {
        new MazeApp();
    }
}
