package main.java.project2;

import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * CS3354 Spring 2020 Review Handler Abstract Class specification
    @author metsis
    @author tesic
 */
public abstract class AbstractReviewHandler {

    /**
     * Default constructor.
     */
    public AbstractReviewHandler() {
        database = new HashMap<>();
        posWords = new HashSet<String>();
        negWords = new HashSet<String>();
    }

    /**
     * Getter method for accessing reviewIdCounter.
     * @return reviewIdCounter
     */
    public static int getReviewIdCounter() {
        return reviewIdCounter;
    }

    /**
     * Setter method for setting reviewIdCounter.
     * @param reviewIdCounter 
     */
    public static void setReviewIdCounter(int reviewIdCounter) {
        AbstractReviewHandler.reviewIdCounter = reviewIdCounter;
    }

    /**
     * Getter method for accessing the database.
     * @return database
     */
    public Map<Integer, MovieReview> getDatabase() {
        return database;
    }

    /**
     * Getter method for accessing the positive words HashSet.
     * @return 
     */
    public HashSet<String> getPosWords() {
        return posWords;
    }

    /**
     * Getter method for accessing the negative words HashSet.
     * @return 
     */
    public HashSet<String> getNegWords() {
        return negWords;
    }
    
    
    /**
     * Read positive/negative words in to the dictionary Set passed as a parameter. 
     *
     * @param fileName name of file containing positive or negative words
     * @param dictionary word dictionary
     * @throws IOException
     */
    private void readInWords(String fileName, Set<String> dictionary) throws IOException {
        Scanner inFile = new Scanner(new FileReader(fileName));
        String text = "";
        while (inFile.hasNextLine()) {
            text = inFile.nextLine();
            if (!text.isEmpty() && !text.startsWith(";") && !text.equals("")) {
                dictionary.add(text);
            }
        }
        
        close(inFile);

        if(dictionary.size() == 0){
            throw new IOException("File " + fileName + " is empty");
        }
        System.out.println("Hash set is of size " + dictionary.size());
    }
    

    /**
     * Loads reviews from a given path and stores them into the database. 
     * If the given path is a .txt file, then a single review is loaded. 
     * Otherwise, if the path is a folder, all reviews in it are loaded.
     * This method calls the method classifyReview to classify each review.
     * @param filePath The path to the file (or folder) containing the review.
     * @param realClass The real class of the review (0 = Negative, 1 = Positive
     * 2 = Unknown).
     */
    public abstract void loadReviews(String filePath, int realClass);
    
    /**
     * Reads a single review file and returns it as a MovieReview object. 
     * @param reviewFilePath A path to a .txt file containing a review.
     * @param realClass The real class entered by the user.
     * @return a MovieReview object.
     * @throws IOException if specified file cannot be opened.
     */
    public abstract MovieReview readReview(String reviewFilePath, int realClass) 
            throws IOException;


    /**
     * Classifies a review as negative, or positive by using the text of the review.
     * It updates the predictedPolarity value of the review object and it also
     * returns the predicted polarity.
     * Note: the classification is achieved by counting positive and negative words
     * in the review text.
     * @param review A review object.
     * @return 0 = negative, 1 = positive.
     */
    public abstract ReviewScore classifyReview(MovieReview review);
    

    /**
     * Deletes a review from the database, given its id.
     * @param id The id value of the review.
     */
    public abstract void deleteReview(int id);
    
    
    /**
     * Auxiliary convenience method used to close a file and handle possible
     * exceptions that may occur.
     *
     * @param c The file to be closed
     */
    public void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            ex.printStackTrace();
        }
    }
    
    /**
     * Methods that loads the positive and negative words into the HashSets
     * @param posFilePath
     * @param negFilePath
     * @throws IOException 
     */
    public void loadPosNegWords(String posFilePath, String negFilePath) throws IOException {
        readInWords(posFilePath, posWords);
        readInWords(negFilePath, negWords);
    }
    

    /**
     * Saves the database in the working directory as a text file (database.txt)
     * @throws java.io.IOException
     */
    public abstract void saveDB() throws IOException;
    

    /**
     * Loads review database from a file into the HashMap.
     * @throws java.io.IOException
     */
    public abstract void loadDB() throws IOException;
    

    /**
     * Searches the review database by id.
     * @param id The id to search for.
     * @return The review that matches the given id or null if the id does not 
     * exist in the database.
     */
    public abstract MovieReview searchById(int id);
    

    /**
     * Searches the review database for reviews matching a given substring.
     * @param substring The substring to search for.
     * @return A list of review objects matching the search criterion.
     */
    public abstract List<MovieReview> searchBySubstring(String substring);


    /**
     * An integer counter to be used as the review ID. This counter increases
     * by 1 every time a new review is loaded.
     */
    private static int reviewIdCounter = 0;
    
    /**
     * A map of <id, review> pairs.
     */
    private Map<Integer, MovieReview> database;
    
    private HashSet<String> posWords;
    private HashSet<String> negWords;
    
    /**
     * The file name of where the database is going to be saved.
     */
    protected static final String DATA_FILE_NAME = "database.txt";
}
