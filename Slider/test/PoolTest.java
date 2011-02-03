import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class PoolTest {
	private Pool poolWider;
	
	private IndentedWord word1;
	private IndentedWord word2;
	private IndentedWord word3;
	private IndentedWord word4;

	@Before
	public void setUp() {
		poolWider = new Pool();
		word1 = new IndentedWord("w");
		word2 = new IndentedWord("wo");
		word3 = new IndentedWord("wor");
		word4 = new IndentedWord("word");

		poolWider.add(word2);
		poolWider.add(word4);
		poolWider.add(word3);
		poolWider.add(word1);
	}
	
	@Test
	public void poolAddsItems() {
		assertEquals(4, poolWider.size());
		assertEquals(word2, poolWider.get(0));
		assertEquals(word1, poolWider.get(3));
	}

	@Test 
	public void bestMatches() {
		Pool pool = new Pool();
		pool.add(new Pair("a", "b", 0));
		pool.add(new Pair("c", "d", 0));
		pool.add(new Pair("e", "f", 0));
		
		Set<Piece> candidates = pool.candidates();

		int numberOfPossiblePairs = 3;
		int waysToArrangeAPair = 4;
		
		assertEquals(numberOfPossiblePairs * waysToArrangeAPair, candidates.size());
	}
	
	@Test
	public void toStringHasContents() {
		assertEquals("wo\nword\nwor\nw\n", poolWider.toString());
	}
	
	@Test
	public void allCombosContains4BaseCombinations() {
		Pool poolCloser = new Pool();
		Pair pair1 = new Pair("a", "c", 0);
		Pair pair2 = new Pair("d", "g", 0);
		
		Set<Piece> candidates = new HashSet<Piece>();
		poolCloser.allCombos(candidates, pair1, pair2);
		
		assertTrue(candidates.contains(new Pair(pair1, pair2, 0)));							// ac-dg
		assertTrue(candidates.contains(new Pair(pair1, pair2.flipped(), 0)));				// ac-gd
		assertTrue(candidates.contains(new Pair(pair1.flipped(), pair2, 0)));				// ca-dg
		assertTrue(candidates.contains(new Pair(pair1.flipped(), pair2.flipped(), 0)));		// ca-gd
	}
	
	@Test
	public void allSlidePositions() {
		Pool poolCloser = new Pool();
		Piece piece1 = new IndentedWord("sol", 0);
		Piece piece2 = new IndentedWord("do", 0);

		Set<Piece> allSlides = new HashSet<Piece>();
		poolCloser.allSlidePositions(piece1, piece2, allSlides);
		
		assertEquals(4, allSlides.size());
		assertTrue(allSlides.contains(new Pair("sol", "do", 0)));
		assertTrue(allSlides.contains(new Pair("sol", "do", 1)));
		assertTrue(allSlides.contains(new Pair("sol", "do", 2)));
		assertTrue(allSlides.contains(new Pair(new IndentedWord("sol", 1), new IndentedWord("do", 0), 0)));
	}	
}
