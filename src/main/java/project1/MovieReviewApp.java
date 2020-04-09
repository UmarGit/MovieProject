package main.java.project1;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class. Provides functionality for interacting with the user.
    @author metsis
    @author tesic
    @author wen
 */
public class MovieReviewApp {
    
    // Used to read from System's standard input
    static final Scanner CONSOLE_INPUT = new Scanner(System.in);

    public static void main(String [] args) {
        
        // Check if the correct number of arguments was provided
        if (args.length < 2) {
            System.err.println("Please provide command liner arguments: <posFilePath> and <negFilePath>");
            return;
        }
        
        String pathToPosWords = args[0];
        String pathToNegWords = args[1];
        
        // Create ReviewHandler object
        ReviewHandler rh = new ReviewHandler();
        
        try {
            // Load the positive and negative words
            rh.loadPosNegWords(pathToPosWords, pathToNegWords);
        } catch (IOException ex) {
            System.err.println("Could not load positive and negative words. "
                    + "Please check that the file paths are correct and try again.");
            return;
        }
        
        try {
            // Load database if it exists.
            rh.loadDB();
        } catch (IOException ex) {
            System.err.println("Error accessing the database file.");
            return;
        }

        String welcomeMessage = "\nChoose one of the following functions:\n\n"
                + "\t 0. Exit program.\n"
                + "\t 1. Load new movie review collection (given a folder or a file path).\n"
                + "\t 2. Delete movie review from database (given its id).\n"
                + "\t 3. Search movie reviews in database by id or by matching a substring.\n";

        //Output welcome message
        System.out.println("\nDatabase size: " + rh.getDatabase().size());
        System.out.println(welcomeMessage);
        String selection = CONSOLE_INPUT.nextLine();

        while (!selection.equals("0")) {
            switch (selection) {
                case "1":
                    // 1. Load new movie review collection (given a folder or a file path).
                    System.out.println("Please input the path of file or folder.");
                    // ./Data/Movie-reviews/neg
                    String path = CONSOLE_INPUT.nextLine();
                    System.out.println("Please input real class (0, 1, 2).");
                    System.out.println("0 = negative, 1 = positive, 2 = unknown.");
                    int realClass = CONSOLE_INPUT.nextInt();
                    CONSOLE_INPUT.nextLine(); //Clear new line character from input
                    
                    if (realClass >= 0 && realClass <= 2) {
                        rh.loadReviews(path, realClass);
                    } else {
                        System.out.println("Illegal input.");
                    }

                    System.out.println("Database size: " + rh.getDatabase().size());
                    break;
                case "2":
                    // 2. Delete movie review from database (given its id).
                    System.out.println("Please input review ID.");
                    String idStr = CONSOLE_INPUT.nextLine();

                    if (!idStr.matches("-?(0|[1-9]\\d*)")) {
                        // Input is not an integer
                        System.out.println("Illegal input.");
                    } else {
                        int id = Integer.parseInt(idStr);
                        rh.deleteReview(id);
                    }

                    break;

                case "3":
                    // 3. Search movie reviews in database by id or by matching a substring.
                    System.out.println("Please input your command (1, 2).");
                    System.out.println("1 = search by ID.");
                    System.out.println("2 = search by substring");
                    String command = CONSOLE_INPUT.nextLine();
                    if (command.equals("1")) {
                        System.out.println("Please input review ID.");
                        idStr = CONSOLE_INPUT.nextLine();
                        if (!idStr.matches("-?(0|[1-9]\\d*)")) {
                            // Input is not an integer
                            System.out.println("Illegal input.");
                        } else {
                            int id = Integer.parseInt(idStr);
                            MovieReview mr = rh.searchById(id);
                            if (mr != null) {
                                printTableHead();
                                printTableContent(mr);
                            } else {
                                System.out.println("Review not found.");
                            }
                        }

                    } else if (command.equals("2")) {
                        System.out.println("Please input substring.");
                        String substring = CONSOLE_INPUT.nextLine();
                        List<MovieReview> reviewList = rh.searchBySubstring(substring);
                        if (reviewList != null) {
                            printTableHead();
                            for (MovieReview mr : reviewList) {
                                printTableContent(mr);
                            }
                            System.out.println(reviewList.size() + " reviews found.");

                        } else {
                            System.out.println("Review not found.");
                        }
                    } else {
                        System.out.println("Illegal input.");
                    }
                    break;
                case "h":
                    System.out.println("\nDatabase size: " + rh.getDatabase().size());
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 'h' to list the commands.");
                    break;
            }

            System.out.println("Please enter another command or 'h' to list the commands.\n");
            selection = CONSOLE_INPUT.nextLine();
        }

        try {
            // Save the database before exiting.
            rh.saveDB();
        } catch (IOException ex) {
            System.err.println("Error: The database file could not be saved.");
        }
        System.out.println("See you!");
    }

    public static void printTableHead() {
        String line = "------------------------------------------------------------------------------------------";
        String information = "| ";
        information = information + String.format("%4s", "ID") + " | ";
        information = information + String.format("%53s", "Text") + " | ";
        information = information + String.format("%10s", "Predicted") + " | ";
        information = information + String.format("%10s", "Real") + " |";

        System.out.println(line);
        System.out.println(information);
        System.out.println(line);
    }

    public static void printTableContent(MovieReview mr) {
        // Add your code here for printing the table contents
    }

}
