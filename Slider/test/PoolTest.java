import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class PoolTest {
	Pool pool;
	
	@Before
	public void setUp() {
		pool = new Pool(new String[]{});
	}
	
	@Test
	public void poolAddsItems() {
		pool.add(new IndentedWord("woo"));
		pool.add(new IndentedWord("able"));
		pool.add(new IndentedWord("back"));

		assertEquals(3, pool.size());
		
		assertTrue(pool.contains(new IndentedWord("woo")));
		assertTrue(pool.contains(new IndentedWord("able")));
		assertTrue(pool.contains(new IndentedWord("back")));
	
		assertTrue(pool.contains(pool.any()));
		assertFalse(pool.contains(new IndentedWord("notPresent")));
	}
	
	@Test (expected=NoSuchElementException.class) public void anyThrowsExceptionIfPoolEmpty() {
		pool.any();
	}

	@Test 
	public void bestMatches() {
		pool.add(new Pair(new IndentedWord("a"), new IndentedWord("b")));
		pool.add(new Pair(new IndentedWord("c"), new IndentedWord("d")));
		pool.add(new Pair(new IndentedWord("e"), new IndentedWord("f")));
		
		Pool candidates = pool.candidates();

		int numberOfPossiblePairs = 3;
		int waysToArrangeAPair = 4;
		
		assertEquals(numberOfPossiblePairs * waysToArrangeAPair, candidates.size());
	}
	
	@Test
	public void allCombosContains4BaseCombinations() {
		Pair pair1 = new Pair(new IndentedWord("a"), new IndentedWord("c"));
		Pair pair2 = new Pair(new IndentedWord("d"), new IndentedWord("g"));
		
		pool.addAllCombos(pair1, pair2);
		
		assertTrue(pool.contains(new Pair(pair1, pair2)));							// ac-dg
		assertTrue(pool.contains(new Pair(pair1, pair2.inverted())));				// ac-gd
		assertTrue(pool.contains(new Pair(pair1.inverted(), pair2)));				// ca-dg
		assertTrue(pool.contains(new Pair(pair1.inverted(), pair2.inverted())));	// ca-gd
	}
	
	@Test
	public void removingAStringTakesPuzzleOutOfPool() {
		Pair pair1 = new Pair(new IndentedWord("foo"), new IndentedWord("bar", 3));
		Pair pair2 = new Pair(new IndentedWord("baz", 2), new IndentedWord("boff"), 1);
		pool.add(pair1);
		pool.add(pair2);
		
		pool.remove("baz");
		
		assertTrue(pool.contains(pair1));
		assertFalse(pool.contains(pair2));
	}
	
	@Test
	public void allSlidePositions() {
		Puzzle puzzle1 = new IndentedWord("sol");
		Puzzle puzzle2 = new IndentedWord("do");

		pool.allSlidePositions(puzzle1, puzzle2);
		
		assertEquals(4, pool.size());
		assertTrue(pool.contains(new Pair(new IndentedWord("sol"), new IndentedWord("do"))));
		assertTrue(pool.contains(new Pair(new IndentedWord("sol"), new IndentedWord("do", 1))));
		assertTrue(pool.contains(new Pair(new IndentedWord("sol"), new IndentedWord("do", 2))));
		assertTrue(pool.contains(new Pair(new IndentedWord("sol", 1), new IndentedWord("do"))));
	}	
	
	@Test
	public void allSlidePositionsHandlesIndentedFirstItem() {
		Puzzle puzzle1 = new IndentedWord("cant", 2);
		Puzzle puzzle2 = new IndentedWord("dot", 1);

		pool.allSlidePositions(puzzle1, puzzle2);
		
		assertEquals(6, pool.size());

		assertTrue(pool.contains(new Pair(new IndentedWord("cant", 2), new IndentedWord("dot", 2))));
		assertTrue(pool.contains(new Pair(new IndentedWord("cant", 2), new IndentedWord("dot", 3))));
		assertTrue(pool.contains(new Pair(new IndentedWord("cant", 2), new IndentedWord("dot", 4))));
		assertTrue(pool.contains(new Pair(new IndentedWord("cant", 2), new IndentedWord("dot", 5))));

		assertTrue(pool.contains(new Pair(new IndentedWord("cant", 2), new IndentedWord("dot", 1))));
		assertTrue(pool.contains(new Pair(new IndentedWord("cant", 3), new IndentedWord("dot", 1))));		
	}
	
	@Test
	public void addAllCombinations() {
		Puzzle puzzle1 = new Pair(new IndentedWord("fish"), new IndentedWord("wishes"));
		Puzzle puzzle2 = new Pair(new IndentedWord("iffy"), new IndentedWord("of"));
		
		pool.addAllCombos(puzzle1, puzzle2);
		
		assertEquals(9 + 7 + 7 + 5, pool.size());
	}
	
	@Test
	public void bestInSet() {
		Scorer fewerGapsIsBetterScorer = new Scorer() {
			public int score(Puzzle puzzle) {
				int sumOfFirstLetterGaps = 0;
				Puzzle previous = puzzle.first();
				for (int i = 1; i < puzzle.height(); i++) {
					Puzzle current = puzzle.wordAt(i);
					sumOfFirstLetterGaps += Math.abs(previous.toString().charAt(0)
							- current.toString().charAt(0));
					previous = current;
				}
				return 10000 - sumOfFirstLetterGaps;
			}
		};


		Pair pair1 = new Pair(new IndentedWord("a"), new IndentedWord("c"));
		Pair pair2 = new Pair(new IndentedWord("d"), new IndentedWord("g"));

		Pool candidates = new Pool(new String[]{});
		candidates.addAllCombos(pair1, pair2);
		
		assertEquals(new Pair(pair1, pair2), candidates.bestIn(fewerGapsIsBetterScorer));
	}
}