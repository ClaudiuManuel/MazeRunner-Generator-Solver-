package maze;

import java.io.Serializable;
import java.util.Random;

public class AdjacencyMatrix implements Serializable {

    public  int[][] generateAdjacencyMatrix(int h,int w){
        int nodesPerRow = (int) Math.ceil(((double) w - 2) / 2);    //refer to matrix drawn in the read me file
        int nodesPerColumn = (int) Math.ceil(((double) h - 2) / 2);

        int totalNodes = nodesPerRow * nodesPerColumn;

        int[][] adjacencyMatrix = new int[totalNodes][totalNodes];

        Random rand = new Random();

        int node=0;
        for(int i=0;i<nodesPerColumn;i++){
            for(int j=0;j<nodesPerRow;j++){
                if(j<nodesPerRow-1){
                    int edgeRight=rand.nextInt(w * h) + 1;
                    adjacencyMatrix[node][node+1]=edgeRight;
                    adjacencyMatrix[node+1][node]=edgeRight;
                }
                if (i > 0) {
                    //it it's not the first row, add an edge to its upper neighbor
                    // since the adjacencyMatrix is symmetric, the same value is added to its symmetric counterpart

                    //random between 1 .. width * height + 1, empirically defined (and != 0)
                    int edgeTop = rand.nextInt(w * h) + 1;
                    int topNeighbor = node - nodesPerRow; //refer to the matrix from read me file

                    adjacencyMatrix[node][topNeighbor] = edgeTop;
                    adjacencyMatrix[topNeighbor][node] = edgeTop;
                }


                if (node == totalNodes - 1) {
                    break;
                } else {
                    node++;
                }
            }
        }

        return adjacencyMatrix;
    }
}
