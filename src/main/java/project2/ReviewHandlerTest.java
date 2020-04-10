/**
 * 
 */
package main.java.project2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

/**
 * @Date : Apr 10-2020
 * @author iqbal
 * @version Java SE 1.8
 * @version JDK 1.8
 * JUnit 4
 * 100% results checked all 8/8 methods on JUnit 4 and JUnit 5
 */


public class ReviewHandlerTest {
	
	/**
	 * path	: The path to the Data folder where these 
	 * 		  files and folders exists { positive-words.txt , negative-words.txt , pos(folder) , neg(folder) }
	 * 
	 * Above File Path will be change/Modify According to personal Computer
	 */
	private String path = "/Users/Power/eclipse-workspace/NEW/Data/"; 
	
	/**
	 * @throws java.lang.Exception
	 */
	
	
	/**
	*JUnit 4 : 
	*		@BeforeClass
	*		@AfterClass
	*JUnit 5 :
	*		@BeforeAll
	*		@AfterAll
	* @BeforeClass / @BeforeAll Method Execute one time before All Test Cases
	* @AfterClass / @AfterAll Method Execute one time After All Test Cases 
	*/
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ReviewHandlerTest rht=new ReviewHandlerTest();
		rht.memoryClear();
	}
	
	@AfterClass
	public static void TestingEnd(){
		System.out.println("\nAll Test Cases Testing Completed Successfully Done ...");
	}
	private void memoryClear() {
		ReviewHandlerTest rht=new ReviewHandlerTest();
		System.out.println("\nClear Memory\n");
		
	}
	/**
	*JUnit 4 : 
	*		@Before
	*		@After
	*JUnit 5 :
	*		@BeforeEach
	*		@AfterEach
	* @Before / @BeforeEach Method Execute Before Each Test Case
	* @After / @AfterEach Method Execute After Each Test Case
	*/
	@Before
	public void beforeTest(){
		System.out.println("\nCase Test : ");
	}
	
	@After
	public void afterTest(){
		System.out.println("Case Tested Complete\n");
	}

	

	
	/**
	 * Test method for {@link main.java.project2.ReviewHandler#loadReviews(java.lang.String, int)}.
	 * 
	 * 
	 * Ppth is Your Positive-Words File Path to the Data folder !
	 * this path must be modify according to the personal Computer
	 *
	 */
		
	/**
	 * @param Ppath+"positive-words.txt" file path of the positive words
	 * @param 0,1,2 The real class of the review (0 = Negative, 1 = Positive 2 = Unknown).
	 */
	@Test
	public void testLoadReviews() {
		ReviewHandler object1 = new ReviewHandler();
		object1.loadReviews(path+"positive-words.txt", 0);
		
		ReviewHandler object2 = new ReviewHandler();
		object2.loadReviews(path+"positive-words.txt", 1);
		
		ReviewHandler object3 = new ReviewHandler();
		object3.loadReviews(path+"positive-words.txt", 2);
		
		ReviewHandler object4 = new ReviewHandler();
		object4.loadReviews(path+"negative-words.txt", 0);
		
		ReviewHandler object5 = new ReviewHandler();
		object5.loadReviews(path+"negative-words.txt", 1);
		
		ReviewHandler object6 = new ReviewHandler();
		object6.loadReviews(path+"negative-words.txt", 2);
		
		ReviewHandler object7 = new ReviewHandler();
		object7.loadReviews(path+"Movie-reviews/neg", 0);
		
		ReviewHandler object8 = new ReviewHandler();
		object8.loadReviews(path+"Movie-reviews/neg", 1);
		
		ReviewHandler object9 = new ReviewHandler();
		object9.loadReviews(path+"Movie-reviews/neg", 2);
		
		ReviewHandler object10 = new ReviewHandler();
		object10.loadReviews(path+"Movie-reviews/pos", 0);
		
		ReviewHandler object11 = new ReviewHandler();
		object11.loadReviews(path+"Movie-reviews/pos", 1);
		
		ReviewHandler object12 = new ReviewHandler();
		object12.loadReviews(path+"Movie-reviews/pos", 2);
	}

	/**
	 * Test method for {@link main.java.project2.ReviewHandler#readReview(java.lang.String, int)}.
	 * 
	 * Reads a single review file and returns it as a MovieReview object.
	 * 
     * @param reviewFilePath 0_9 path to 0_9 .txt file containing a review.
     * @param realClass The real class entered by the user.
     * 
	 */
	
	@Test
	public void testReadReview() {
		ReviewHandler object1 = new ReviewHandler();
		try {
			object1.readReview(path+"positive-words.txt", 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReviewHandler object2 = new ReviewHandler();
		try {
			object2.readReview(path+"positive-words.txt", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReviewHandler object3 = new ReviewHandler();
		try {
			object3.readReview(path+"positive-words.txt", 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReviewHandler object4 = new ReviewHandler();
		try {
			object4.readReview(path+"negative-words.txt", 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReviewHandler object5 = new ReviewHandler();
		try {
			object5.readReview(path+"negative-words.txt", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReviewHandler object6 = new ReviewHandler();
		try {
			object6.readReview(path+"negative-words.txt", 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link main.java.project2.ReviewHandler#classifyReview(main.java.project2.MovieReview)}.
	 * 
	 * pos_word And neg_word string are the words from the data file.
	 */
	@Test
	public void testClassifyReview() {
		String pos_word = "achievement";
		String neg_word = "addicted";
		
		MovieReview object101 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.NEGATIVE, ReviewScore.NEGATIVE);
		MovieReview object111 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.NEGATIVE, ReviewScore.POSITIVE);
		MovieReview object121 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.NEGATIVE, ReviewScore.UNKNOWN);
		
		MovieReview object202 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.POSITIVE, ReviewScore.NEGATIVE);
		MovieReview object212 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.POSITIVE, ReviewScore.POSITIVE);
		MovieReview object222 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.POSITIVE, ReviewScore.UNKNOWN);
		
		MovieReview object303 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.UNKNOWN, ReviewScore.NEGATIVE);
		MovieReview object313 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.UNKNOWN, ReviewScore.POSITIVE);
		MovieReview object323 = new MovieReview(0, path+"positive-words.txt", pos_word, ReviewScore.UNKNOWN, ReviewScore.UNKNOWN);
		
		
		MovieReview object404 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.NEGATIVE, ReviewScore.NEGATIVE);
		MovieReview object414 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.NEGATIVE, ReviewScore.POSITIVE);
		MovieReview object424 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.NEGATIVE, ReviewScore.UNKNOWN);
		
		MovieReview object505 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.POSITIVE, ReviewScore.NEGATIVE);
		MovieReview object515 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.POSITIVE, ReviewScore.POSITIVE);
		MovieReview object525 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.POSITIVE, ReviewScore.UNKNOWN);
		
		MovieReview object606 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.UNKNOWN, ReviewScore.NEGATIVE);
		MovieReview object616 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.UNKNOWN, ReviewScore.POSITIVE);
		MovieReview object626 = new MovieReview(0, path+"negative-words.txt", neg_word, ReviewScore.UNKNOWN, ReviewScore.UNKNOWN);
		
		ReviewHandler object1 = new ReviewHandler();
		object1.classifyReview(object101);
		
		ReviewHandler object2 = new ReviewHandler();
		object2.classifyReview(object111);
		
		ReviewHandler object3 = new ReviewHandler();
		object3.classifyReview(object121);
		
		ReviewHandler object4 = new ReviewHandler();
		object4.classifyReview(object202);
		
		ReviewHandler object5 = new ReviewHandler();
		object5.classifyReview(object212);
		
		ReviewHandler object6 = new ReviewHandler();
		object6.classifyReview(object222);
		
		ReviewHandler object7 = new ReviewHandler();
		object7.classifyReview(object303);
		
		ReviewHandler object8 = new ReviewHandler();
		object8.classifyReview(object313);
		
		ReviewHandler object9 = new ReviewHandler();
		object9.classifyReview(object323);
		
		ReviewHandler object10 = new ReviewHandler();
		object10.classifyReview(object404);
		
		ReviewHandler object11 = new ReviewHandler();
		object11.classifyReview(object414);
		
		ReviewHandler object12 = new ReviewHandler();
		object12.classifyReview(object424);
		
		ReviewHandler object13 = new ReviewHandler();
		object13.classifyReview(object505);
		
		ReviewHandler object14 = new ReviewHandler();
		object14.classifyReview(object515);
		
		ReviewHandler object15 = new ReviewHandler();
		object15.classifyReview(object525);
		
		ReviewHandler object16 = new ReviewHandler();
		object16.classifyReview(object606);
		
		ReviewHandler object17 = new ReviewHandler();
		object17.classifyReview(object616);
		
		ReviewHandler object18 = new ReviewHandler();
		object18.classifyReview(object626);
	}

	/**
	 * Test method for {@link main.java.project2.ReviewHandler#deleteReview(int)}.
	 */
	@Test
	public void testDeleteReview() {
		
		int review_id = 0 ;
		
		ReviewHandler object1 = new ReviewHandler();
		
		object1.deleteReview(review_id);
		
	}

	/**
	 * Test method for {@link main.java.project2.ReviewHandler#saveDB()}.
	 */
	@Test
	public void testSaveDB() {
		
		ReviewHandler object1 = new ReviewHandler();
		
		
		try {
			object1.saveDB();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Test method for {@link main.java.project2.ReviewHandler#loadDB()}.
	 */
	@Test
	public void testLoadDB() {
		ReviewHandler object1 = new ReviewHandler();
		try {
			object1.loadDB();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link main.java.project2.ReviewHandler#searchById(int)}.
	 */
	@Test
	public void testSearchById() {
		int review_id = 0 ;
		
		ReviewHandler object1 = new ReviewHandler();
		
		object1.searchById(review_id);
		
    
	}

	/**
	 * Test method for {@link main.java.project2.ReviewHandler#searchBySubstring(java.lang.String)}.
	 * 
	 * search_review are the 
	 * 
	 * 	
	 * search_review are positive review and negative reviews
	 */
	@Test
	public void testSearchBySubstring() {
		
		String search_review1 = "Fair drama/love story movie that focuses on the lives of blue collar people finding new life thru new love";//positive review
		String search_review2 = "Story of a man who has unnatural feelings for a pig";//negative review
		String search_review3 = "Although this was obviously a low-budget production, the performances and the songs in this movie are worth seeing";//positive review
		String search_review4 = "Weak plot, predictable violence ";//negative review
		
		ReviewHandler object1 = new ReviewHandler();
		object1.searchBySubstring(search_review1);
		
		ReviewHandler object2 = new ReviewHandler();
		object2.searchBySubstring(search_review2);
		
		ReviewHandler object3 = new ReviewHandler();
		object3.searchBySubstring(search_review3);
		
		ReviewHandler object4 = new ReviewHandler();
		object4.searchBySubstring(search_review4);
		
	}
	
	

}
