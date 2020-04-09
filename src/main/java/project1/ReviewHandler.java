package main.java.project1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ReviewHandler extends AbstractReviewHandler {

    /**
     * Loads reviews from a given path. If the given path is a .txt file, then
     * a single review is loaded. Otherwise, if the path is a folder, all reviews
     * in it are loaded. 
     * @param filePath The path to the file (or folder) containing the review(sentimentModel).
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     * 2 = Unknown).
     */
    @Override
    public void loadReviews(String filePath, int realClass) {
        File fileOrFolder = new File(filePath);
        try {
            if (fileOrFolder.isFile()) {
                // File
                if (filePath.endsWith(".txt")) {
                    // Import review
                    MovieReview review = readReview(filePath, realClass);
                    
                    // Classify review
                    ReviewScore rs = classifyReview(review);
                    review.setPredictedScore(rs);
                    
                    // Add to getDatabase()
                    getDatabase().put(review.getId(), review);
                    //Output result: single file
                    System.out.println("Review imported.");
                    System.out.println("ID: " + review.getId());
                    System.out.println("Text: " + review.getText());
                    System.out.println("Real Class: " + review.getRealScore());
                    System.out.println("Classification result: " + review.getPredictedScore());
                    if (realClass == 2) {
                        System.out.println("Real class Unknown.");
                    } else if (realClass == 0 && review.getPredictedScore() == ReviewScore.NEGATIVE 
                            || realClass == 1 && review.getPredictedScore() == ReviewScore.POSITIVE) {
                        System.out.println("Correctly classified.");
                    } else {
                        System.out.println("Misclassified.");
                    }
                    System.out.println();

                } else {
                    // Cannot import non-txt files
                    System.out.println("Input file path is neither a txt file nor folder.");
                }
            } else {
                // Folder
                System.out.println("Loading reviews...");
                String[] files = fileOrFolder.list();
                String fileSeparatorChar = System.getProperty("file.separator");
                int counter = 0;
                for (String fileName : files) {
                    if (fileName.endsWith(".txt")) {
                        // Only import txt files
                        // Import review
                        MovieReview review = readReview(filePath + fileSeparatorChar + fileName, realClass);
                        
                        // Classify review
                        ReviewScore rs = classifyReview(review);
                        review.setPredictedScore(rs);
        
                        // Add to getDatabase()
                        getDatabase().put(review.getId(), review);
                        // Count correct classified reviews, only real class is known
                        if (realClass != 2 && review.getRealScore() == review.getPredictedScore()) {
                            counter++;
                        }
                    } else {
                        //Do nothing
                    }
                }
                // Output result: folder
                System.out.println("Folder imported.");
                System.out.println("Number of entries: " + files.length);

                // Only output accuracy if real class is known
                if (realClass != 2) {
                    System.out.println("Correctly classified: " + counter);
                    System.out.println("Misclassified: " + (files.length - counter));
                    System.out.println("Accuracy: " + ((double)counter / (double)files.length * 100) + "%");
                }
            }
        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }

    }

    /**
     * Reads a single review file and returns it as a MovieReview object.
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be opened.
     */
    @Override
    public MovieReview readReview(String reviewFilePath, int realClass) throws IOException {
        // Read file for text
        Scanner inFile = new Scanner(new FileReader(reviewFilePath));
        String text = "";
        while (inFile.hasNextLine()) {
            text += inFile.nextLine();
        }
        // Remove the <br /> occurences in the text and replace them with a space
        text = text.replaceAll("<br />"," ");

        // Create review object, assigning reviewIdCounter and real class
        MovieReview review = new MovieReview(getReviewIdCounter(), reviewFilePath, 
                text, ReviewScore.fromInteger(realClass), ReviewScore.POSITIVE);
        
        // Update reviewIdCounter
        setReviewIdCounter(getReviewIdCounter() + 1);

        return review;
    }
    
    
    /**
     * Classifies a review as negative, or positive by using the text of the review.
     * It updates the predictedPolarity value of the review object and it also
     * returns the predicted polarity.
     * Note: the classification is achieved by counting positive and negative words
     * in the review text.
     * @param review A review object.
     * @return 0 = negative, 1 = positive.
     */
    @Override
    public ReviewScore classifyReview(MovieReview review){
        int positive = 0;
        int negative = 0;
        // Remove the <br /> occurences in the text and replace them with a space
        String text = review.getText().replaceAll("<br />", " ");
        //Remove punctuation marks and replace them with spaces.
        text = text.replaceAll("\\p{Punct}", " ");
        //call function to format text and classify Review
        text = text.toLowerCase();

        // Split the text into tokens using white spaces as the separator character.
        String[] tokens = text.split("\\s+");
        for (String token : tokens) {
            if (getPosWords().contains(token)) {
                positive++;
            }
            if (getNegWords().contains(token)) {
                negative++;
            }
        }
        return ((positive - negative)>0)? ReviewScore.POSITIVE : ReviewScore.NEGATIVE;
    }

    /**
     * Deletes a review from the getDatabase(), given its id.
     * @param id The id value of the review.
     */
    @Override
    public void deleteReview(int id) {

        if (!getDatabase().containsKey(id)) {
            // Review with given reviewIdCounter does not exist
            System.out.println("ID " + id + " does not exist.");
        } else {
            getDatabase().remove(id);
            System.out.println("Review with ID " + id + " deleted.");
        }
    }
    
    
    /**
     * Saves the getDatabase() in the working directory as a text file.
     * @throws java.io.IOException
     */
    @Override
    public void saveDB() throws IOException {
        PrintWriter out = new PrintWriter(DATA_FILE_NAME);
        
        for (MovieReview mr : getDatabase().values()) {
            out.println(mr.getId() + "@" +
                               mr.getFilePath() + "@" +
                               mr.getRealScore() + "@" +
                               mr.getPredictedScore());
        }
        
        close(out);
    }

    /**
     * Loads review getDatabase().
     */
    @Override
    public void loadDB() throws IOException {
        System.out.print("Reading getDatabase()...");
        
        File dataFile = new File(DATA_FILE_NAME);
        if (!dataFile.exists()) {
            System.out.println("No getDatabase().txt file found. A new empty "
                    + "getDatabase() will be created.");
            return;
        }
        
        Scanner inFile = new Scanner(new FileReader(DATA_FILE_NAME));
        
        String line = "";
        while (inFile.hasNextLine()) {
            line = inFile.nextLine();
            String[] lineParts = line.split("@");
            int id = Integer.parseInt(lineParts[0]);
            String filePath = lineParts[1];
            String text = readReview(filePath, 0).getText();
            ReviewScore realScore = ReviewScore.fromString(lineParts[2]);
            ReviewScore predictedScore = ReviewScore.fromString(lineParts[3]);
            
            MovieReview mr = new MovieReview(id, filePath, text, realScore, predictedScore);
            getDatabase().put(id, mr);
        }
        
        // Set the reviewIdCounter to be one greater than the largest id in the database().
        if (getDatabase().keySet().size() > 0)
            setReviewIdCounter(Collections.max(getDatabase().keySet()).intValue() + 1);
        
        System.out.println("Done.");
    }

    /**
     * Searches the review getDatabase() by id.
     * @param id The id to search for.
     * @return The review that matches the given id or null if the id does not
     * exist in the getDatabase().
     */
    @Override
    public MovieReview searchById(int id) {
        if (getDatabase().containsKey(id)) {
            return getDatabase().get(id);
        }
        return null;
    }

    /**
     * Searches the review getDatabase() for reviews matching a given substring.
     * @param substring The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
    @Override
    public List<MovieReview> searchBySubstring(String substring) {
        List<MovieReview> tempList = new ArrayList<>();

        for (Map.Entry<Integer, MovieReview> entry : getDatabase().entrySet()){
            if (entry.getValue().getText().contains(substring)) {
                tempList.add(entry.getValue());
            }
        }

        if (!tempList.isEmpty()) {
            return tempList;
        } else {
            // No review has given substring
            return null;
        }

    }
}
