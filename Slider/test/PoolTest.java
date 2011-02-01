import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class PoolTest {
	PieceScorer widerIsBetterScorer = new PieceScorer() {
		public int score(Piece piece) {
			Pair pair = (Pair) piece;
			return pair.part1().width() + pair.part2().width();
		}
	};
	
	PieceScorer fewerGapsIsBetterScorer = new PieceScorer() {
		public int score(Piece piece) {
			int sumOfFirstLetterGaps = 0;
			Piece previous = piece.first();
			for (int i = 1; i < piece.height(); i++) {
				Piece current = piece.get(i);
				sumOfFirstLetterGaps += Math.abs(previous.toString().charAt(0) - current.toString().charAt(0));
				previous = current;
			}
			return 10000 - sumOfFirstLetterGaps;
		}
	};
	
	private Pool poolWider;
	
	private IndentedWord word1;
	private IndentedWord word2;
	private IndentedWord word3;
	private IndentedWord word4;

	@Before
	public void setUp() {
		poolWider = new Pool(widerIsBetterScorer);
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
	public void bestPair() {
		Piece piece = poolWider.best();
		assertEquals(7, widerIsBetterScorer.score(piece));
	}
	
	@Test
	public void toStringHasContents() {
		assertEquals("wo\nword\nwor\nw\n", poolWider.toString());
	}
	
	@Test 
	public void bestCombo_WhenAThenB() {
		Pool poolCloser = new Pool(fewerGapsIsBetterScorer);
		Piece pair1 = new Pair("a", "c", 0);
		Piece pair2 = new Pair("d", "g", 0);

		Set<Piece> possibilities = new HashSet<Piece>();
		poolCloser.addCombo(pair1, pair2, possibilities);
		
		assertEquals(1, possibilities.size());
		assertEquals(new Pair(pair1, pair2, 0), possibilities.toArray()[0]);
	}

	@Test
	public void allCombosContains4BaseCombinations() {
		Pool poolCloser = new Pool(fewerGapsIsBetterScorer);
		Pair pair1 = new Pair("a", "c", 0);
		Pair pair2 = new Pair("d", "g", 0);
		Set<Piece> allCombos = poolCloser.allCombos(pair1, pair2);
		assertTrue(allCombos.contains(new Pair(pair1, pair2, 0)));							// ac-dg
		assertTrue(allCombos.contains(new Pair(pair1, pair2.flipped(), 0)));				// ac-gd
		assertTrue(allCombos.contains(new Pair(pair1.flipped(), pair2, 0)));				// ca-dg
		assertTrue(allCombos.contains(new Pair(pair1.flipped(), pair2.flipped(), 0)));	// ca-gd
	}
	
	@Test
	public void allSlidePositions() {
		Pool poolCloser = new Pool(fewerGapsIsBetterScorer);
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


	@Test
	public void bestInSet() {
		Pool poolCloser = new Pool(fewerGapsIsBetterScorer);
		Pair pair1 = new Pair("a", "c", 0);
		Pair pair2 = new Pair("d", "g", 0);
		Set<Piece> allCombos = poolCloser.allCombos(pair1, pair2);
		assertEquals(new Pair(pair1, pair2, 0), poolCloser.bestIn(allCombos));
	}
	
}
