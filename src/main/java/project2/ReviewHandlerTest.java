package main.java.project2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.project2.ReviewScore;

class ReviewHandlerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	// Your File Path to the Data folder !
	private String path = "/Users/Power/eclipse-workspace/NEW/Data/";  
	@Test
	void testLoadReviews() {
		ReviewHandler object1 = new ReviewHandler();
		object1.loadReviews(path+"positive-words.txt", 0);
		object1.loadReviews(path+"positive-words.txt", 1);
		object1.loadReviews(path+"positive-words.txt", 2);
		ReviewHandler object2 = new ReviewHandler();
		object2.loadReviews(path+"negative-words.txt", 0);
		object2.loadReviews(path+"negative-words.txt", 1);
		object2.loadReviews(path+"negative-words.txt", 2);
		ReviewHandler object3 = new ReviewHandler();
		object3.loadReviews(path+"Movie-reviews/neg", 0);
		object3.loadReviews(path+"Movie-reviews/neg", 1);
		object3.loadReviews(path+"Movie-reviews/neg", 2);
		ReviewHandler object4 = new ReviewHandler();
		object4.loadReviews(path+"Movie-reviews/pos", 0);
		object4.loadReviews(path+"Movie-reviews/pos", 1);
		object4.loadReviews(path+"Movie-reviews/pos", 2);
	}

//	@Test
//	void testReadReview() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testClassifyReview() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteReview() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveDB() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testLoadDB() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSearchById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSearchBySubstring() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAbstractReviewHandler() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetReviewIdCounter() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetReviewIdCounter() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetDatabase() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetPosWords() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetNegWords() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testClose() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testLoadPosNegWords() {
//		fail("Not yet implemented");
//	}

}
