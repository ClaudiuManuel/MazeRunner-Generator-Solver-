package maze;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MazeSolver {
    int[][] maze;
    Spot start;
    Spot end;
    Spot[][] grid;

    public MazeSolver(int[][] maze) {
        this.maze = maze;
        grid = new Spot[maze.length][maze.length];
    }

    public void findStartAndEnd() {
        for (int i = 1; i < maze.length - 1; i++) {
            if (maze[i][0] == 0)
                start = grid[i][0];
            if (maze[i][maze.length - 1] == 0)
                end = grid[i][maze.length - 1];
        }
    }

    public void generateGridOfSpots() {
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == 0)
                    grid[i][j] = new Spot(i, j, true, Integer.MAX_VALUE);    //initialize all distances for max value so  ↓
                else                                                          //                                                ↓
                    grid[i][j] = new Spot(i, j, false, Integer.MAX_VALUE);   // I can apply Dijkstra’s shortest path algorithm
            }
    }

    public void Dijkstra() {
        this.generateGridOfSpots();
        this.findStartAndEnd();

        start.setDistance(0);
        Set<Spot> unsettled = new HashSet<Spot>();
        Set<Spot> settled = new HashSet<Spot>();


        unsettled.add(start);
        while (!unsettled.isEmpty()) {
            Spot current = findMinimum(unsettled);    //always checking the option with the lowest distance from the unsettled list of Spots
            Spot helper = null;
            //ckecking all of the current Spot's neighbours to see if I can find a shorter path to each one of them through the current Spot.

            // checking top Spot
            if (current.getI() - 1 >= 0) {
                helper = grid[current.getI() - 1][current.getJ()];
                if (!settled.contains(helper) && helper.findIfPass() && helper.getDistance() > current.getDistance() + 1) {
                    helper.setDistance(current.getDistance() + 1);
                    helper.setParent(current);        //keeping track of the previous Spot so I can develop a path after the algorithm has finished its job
                    unsettled.add(helper);
                }
            }

            //checking left Spot
            if (current.getJ() - 1 >= 0) {
                helper = grid[current.getI()][current.getJ() - 1];
                if (!settled.contains(helper) && helper.findIfPass() && helper.getDistance() > current.getDistance() + 1) {
                    helper.setDistance(current.getDistance() + 1);
                    helper.setParent(current);
                    unsettled.add(helper);
                }
            }

            //checking right Spot
            if (current.getJ() + 1 < maze.length) {
                helper = grid[current.getI()][current.getJ() + 1];
                if (!settled.contains(helper) && helper.findIfPass() && helper.getDistance() > current.getDistance() + 1) {
                    helper.setDistance(current.getDistance() + 1);
                    helper.setParent(current);
                    unsettled.add(helper);
                }
            }

            //checking  lower Spot
            if (current.getI() - 1 < maze.length) {
                helper = grid[current.getI()+1][current.getJ()];
                if (!settled.contains(helper) && helper.findIfPass() && helper.getDistance() > current.getDistance() + 1) {
                    helper.setDistance(current.getDistance() + 1);
                    helper.setParent(current);
                    unsettled.add(helper);
                }
            }
            unsettled.remove(current);
            settled.add(current);
        }

        //backtrack the path and make every element that is part of it equal to 2
        Spot current = end;
        while (current != start) {
            maze[current.getI()][current.getJ()] = 2;
            current = current.getParent();
        }

        maze[start.getI()][start.getJ()] = 2;

        //printing the solution and undoing the changes(element=2) so the maze stays the same
        String wallChar = "\u2588\u2588";
        String passChar = "  ";
        String pathChar = "//";

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == 0)
                    System.out.print(passChar);
                else if (maze[i][j] == 1)
                    System.out.print(wallChar);
                else {
                    System.out.print(pathChar);
                    maze[i][j] = 0;
                }
            }
            System.out.print("\n");
        }
    }

    public Spot findMinimum(Set<Spot> spots) {
        int minimumDist = Integer.MAX_VALUE;      //finding the Spot with the lowest distance attached to it from the unsettled list
        Spot helper = null;
        for (Spot s : spots) {
            if (s.getDistance() < minimumDist) {
                helper = s;
                minimumDist = s.getDistance();
            }
        }
        return helper;
    }
}
