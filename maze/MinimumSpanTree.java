package maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumSpanTree implements Serializable {
    int[][] matrix;
    int[][] minSpanTree;
    Set<Integer> foundNodes = new HashSet<Integer>();

    public MinimumSpanTree(int[][] matrix) {
        this.matrix = matrix;
        minSpanTree = new int[matrix.length][matrix.length];
    }

    public int[][] findMinimumSpanTree() {          //using Prim's algorithm
        foundNodes.add(0);
        while (foundNodes.size() < matrix.length) {
            int minimumEdge = 999;
            int nextNode = 0;
            int currentNode = 0;
            for (int node : foundNodes) {
                for (int i = 0; i < matrix.length; i++) {
                    if (matrix[node][i] != 0 && matrix[node][i] < minimumEdge && !foundNodes.contains(i)) {
                        nextNode = i;
                        minimumEdge = matrix[node][i];
                        currentNode = node;
                    }
                }
            }
            foundNodes.add(nextNode);
            minSpanTree[currentNode][nextNode] = 1;   //since the minSpanTree matrix is symmetric, the same value is added to its symmetric counterpart
            minSpanTree[nextNode][currentNode] = 1;
        }
        return minSpanTree;
    }

}

