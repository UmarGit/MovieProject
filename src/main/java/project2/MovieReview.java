package main.java.project2;
/**
  CS3354 Spring 2020 MovieReview Class Implementation
    @author metsis
    @author tesic
 */
public class MovieReview {

    /**
     * Constructor.
     * @param id
     * @param filePath
     * @param text
     * @param realScore 
     */
    public MovieReview(int id, String filePath, String text, 
            ReviewScore realScore, ReviewScore predictedScore) {
        this.id = id;
        this.filePath = filePath;
        this.text = text;
        this.realScore = realScore;
        this.predictedScore = predictedScore; // Set a default value. To be changed later.
    }

    /**
     * Getter method for accessing the id of a review.
     * @return Review id field
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method for accessing the file path of a review.
     * @return The filePath
     */
    public String getFilePath() {
        return filePath;
    }


    /**
     * Getter method for accessing the text of a review.
     * @return Review text field
     */
    public String getText() {
        return text;
    }

    /**
     * Getter method for accessing the predictedScore of a review.
     * @return predictedScore field
     */
    public ReviewScore getPredictedScore() {
        return predictedScore;
    }

    /**
     * Setter method for setting the predictedScore of a review.
     * @param predictedScore.
     */
    public void setPredictedScore(ReviewScore predictedScore) {
        this.predictedScore = predictedScore;
    }

    /**
     * Getter method for accessing the realScore of a review.
     * @return realScore
     */
    public ReviewScore getRealScore() {
        return realScore;
    }

    
    /**
     * The id of the review (e.g. 2087).
     */
    private final int id;
    
    /**
     * The path to text file containing the movie review. 
     */
    private final String filePath;
    
    /**
     *  The text of the review.
     */
    private final String text;
    
    /**
     * The predicted score of the review (NEGATIVE, POSITIVE).
     */
    private ReviewScore predictedScore;
    
    /**
     * The ground truth score of the review (NEGATIVE, POSITIVE, UNKNOWN).
     */
    private final ReviewScore realScore;

}
