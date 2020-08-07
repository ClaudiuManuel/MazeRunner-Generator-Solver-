# MazeRunner-Generator-Solver-


**Matrix drawn to showcase the logic behind the development of AdjacencyMatrix class.**
        
        Class is created to translate the input(dimensions of the board) into a graph of nodes.It will connect each node with it's upper and right neighbours and assign each edge</br>         a random value.The matrix created in this class will help me spawn a minimum Spanning Tree in the minSpanTree class(using Prim's algorithm)  which I will use to</br>                   generate the random Maze.
        
        The total number of nodes are half the height ( minus 2 on each dimension for the wall, rounded up) times
        half the width ( minus 2 on each dimension for the wall, rounded up)

         for a matrix of w = 11  and h = 7:

         w  w   w   w   w   w   w   w   w   w   w
         w  0       1       2       3       4   w
         w      w        w      w       w       w
         w  5       6       7       8       9   w
         w      w        w      w       w       w
         w  10      11      12      13      14  w
         w  w   w   w   w   w   w   w   w   w   w

         the white spaces are just the edges of those nodes
         
        
