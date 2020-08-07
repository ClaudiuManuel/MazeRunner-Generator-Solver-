package maze;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        int choice = -1;
        MazeBoard maze = null;
        Scanner reader=new Scanner(System.in);

        while (choice != 0) {

            if (maze == null) {
                printShortMenu();
                try {
                    choice = Integer.parseInt(reader.nextLine());
                }catch(NumberFormatException  e){
                    e.printStackTrace();
                }
                switch (choice){
                    case 1:
                        System.out.println("Please enter the size of the new maze");
                        int size = 0;
                        try {
                            size = Integer.parseInt(reader.nextLine());
                        }catch(NumberFormatException  e){
                            e.printStackTrace();
                        }
                        maze=new MazeBoard(size,size);
                        maze.generateBoard();
                        maze.printMaze();
                        break;
                    case 2:
                        maze=loadMaze(reader);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Incorrect option. Please try again");
                        break;
                }
            }else {
                printFullMenu();
                try {
                    choice = Integer.parseInt(reader.nextLine());
                }catch(NumberFormatException  e){
                    e.printStackTrace();
                }
                switch (choice) {
                    case 1:
                        System.out.println("Enter the size of the new maze");
                        int size=0;
                        try {
                            size = Integer.parseInt(reader.nextLine());
                        }catch(NumberFormatException  e){
                            e.printStackTrace();
                        }
                        maze = new MazeBoard(size, size);
                        maze.generateBoard();
                        maze.printMaze();
                        break;
                    case 2:
                        maze=loadMaze(reader);
                        break;
                    case 3:
                        saveMaze(maze,reader);
                        break;
                    case 4:
                        maze.printMaze();
                        break;
                    case 5:
                        MazeSolver solver=new MazeSolver(maze.getBoard());
                        solver.Dijkstra();
                    case 0:
                        break;
                    default:
                        System.out.println("Incorrect option. Please try again");
                        break;
                }
            }
        }
        reader.close();

    }

    public static void printFullMenu() {
        System.out.print("=== Menu ===\n"+
                "1.Generate a new maze.\n" +
                "2.Load a maze.\n" +
                "3.Save the maze.\n" +
                "4.Display the maze.\n" +
                "5.Find the escape.\n" +
                "0.Exit.\n");
    }

    public static void printShortMenu() {
        System.out.print("=== Menu ===\n"+
                "1.Generate a new maze.\n" +
                "2.Load a maze.\n" +
                "0.Exit.\n");
    }

    public static MazeBoard loadMaze(Scanner reader){
        try {
            System.out.println("Enter the file name");
            String path= reader.nextLine();
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in=new ObjectInputStream(fileIn);
            MazeBoard mazeBoard=(MazeBoard)in.readObject();
            in.close();
            fileIn.close();
            return mazeBoard;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("The file ... does not exist");
            e.printStackTrace();
            return null;
        }
    }

    public static void saveMaze(MazeBoard maze,Scanner reader){
        try {
            System.out.println("Enter the file name");
            String path= reader.nextLine();
            FileOutputStream fileOut=new FileOutputStream(path);
            ObjectOutputStream out=new ObjectOutputStream(fileOut);
            out.writeObject(maze);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
