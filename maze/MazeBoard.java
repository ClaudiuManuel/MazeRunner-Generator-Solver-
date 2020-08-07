package maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MazeBoard implements Serializable {
    int[][] maze;
    int[][] spanningTree;
    int[][] adjacency;
    int h, w;
    int nodesPerRow;
    int nodesPerColumn;

    public int getSize(){
        return h;
    }

    public int[][] getBoard(){
        return maze;
    }

    public MazeBoard(int h, int w) {
        this.h = h;
        this.w = w;
        maze = new int[h][w];
        adjacency = new AdjacencyMatrix().generateAdjacencyMatrix(h, w);
        spanningTree = new MinimumSpanTree(adjacency).findMinimumSpanTree();
        nodesPerRow = (int) Math.ceil(((double) w - 2) / 2);                    //refer to matrix drawn in the read me file
        nodesPerColumn = (int) Math.ceil(((double) h - 2) / 2);                 // same as above

    }

    public void generateBoard() {
        for (int i = 0; i < h; i++) {       //fill the board with walls
            for (int j = 0; j < w; j++) {
                maze[i][j] = 1;
            }
        }
        int nodCurent = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i % 2 != 0 && j % 2 != 0 && i != h - 1 && j != w - 1) {
                    maze[i][j] = 0;             //every vertex is part of the path.Now I need to connect them with edges that are also part of the path

                    for (int helper = 0; helper < spanningTree.length; helper++) {
                        if (spanningTree[nodCurent][helper] == 1) {

                            int nodeHeight = this.GetNodeHeight(helper);
                            int edgeHeight = (int) Math.floor(((double) nodeHeight + i) / 2);
                            int nodeWidth = this.GetNodeWidth(helper);
                            int edgeWidth = (int) Math.floor(((double) nodeWidth + j) / 2);

                            maze[edgeHeight][edgeWidth] = 0;          //developing the path
                        }
                    }
                    nodCurent++;
                }
            }
        }
        if (h % 2 == 0) {                     //if height is an even number there will be 2 rows of walls(last 2 rows) so I have to deal with that
            for (int i = 0; i < w; i++) {
                if (maze[h - 3][i] == 0 && i % 2 != 0)
                    maze[h - 2][i] = 0;
            }
        }

        if (w % 2 == 0) {                     //if width is an even number there will be 2 columns of walls(last 2 columns) so I have to deal with that
            for (int i = 0; i < h; i++) {
                if (maze[i][w-3] == 0 && i % 2 != 0)
                    maze[i][w-2] = 0;
            }
        }

        Random rand=new Random();
        //place entrance
        int randomNodeHeight=rand.nextInt((nodesPerRow*nodesPerColumn)/nodesPerRow ) +1;
        int entranceHeight=randomNodeHeight+randomNodeHeight-1;
        maze[entranceHeight][0]=0;
        //place exit
        Random rand2=new Random();
        int randomNodeHeight2=rand2.nextInt((nodesPerRow*nodesPerColumn)/nodesPerRow ) +1;
        int exitHeight=randomNodeHeight2+randomNodeHeight2-1;
        maze[exitHeight][w-1]=0;
    }

    public void printMaze() {
        String wallChar = "\u2588\u2588";
        String passChar = "  ";

        for (int i = 0; i < this.h; i++) {
            for (int j = 0; j < this.w; j++) {
                System.out.print(maze[i][j] == 1 ? wallChar : passChar);
            }
            System.out.print("\n");
        }
    }

    public int GetNodeHeight(int node) {
        //obtained empirically
        return (int) Math.floor((node / (double) this.nodesPerRow)) * 2 + 1;

    }

    public int GetNodeWidth(int node) {
        //obtained empirically
        return 1 + 2 * (node - this.nodesPerRow * (int) Math.floor((node / (double) this.nodesPerRow)));

    }

}
