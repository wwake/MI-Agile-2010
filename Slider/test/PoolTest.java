import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		pool.add(new Pair(new IndentedWord("a"), new IndentedWord("b")));
		pool.add(new Pair(new IndentedWord("c"), new IndentedWord("d")));
		pool.add(new Pair(new IndentedWord("e"), new IndentedWord("f")));
		
		Set<Cluster> candidates = pool.candidates();

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
		Pair pair1 = new Pair(new IndentedWord("a"), new IndentedWord("c"));
		Pair pair2 = new Pair(new IndentedWord("d"), new IndentedWord("g"));
		
		Set<Cluster> candidates = new HashSet<Cluster>();
		poolCloser.allCombos(candidates, pair1, pair2);
		
		assertTrue(candidates.contains(new Pair(pair1, pair2)));							// ac-dg
		assertTrue(candidates.contains(new Pair(pair1, pair2.flipped())));					// ac-gd
		assertTrue(candidates.contains(new Pair(pair1.flipped(), pair2)));					// ca-dg
		assertTrue(candidates.contains(new Pair(pair1.flipped(), pair2.flipped())));		// ca-gd
	}
	
	@Test
	public void allSlidePositions() {
		Pool poolCloser = new Pool();
		Cluster cluster1 = new IndentedWord("sol");
		Cluster cluster2 = new IndentedWord("do");

		Set<Cluster> allSlides = new HashSet<Cluster>();
		poolCloser.allSlidePositions(allSlides, cluster1, cluster2);
		
		assertEquals(4, allSlides.size());
		assertTrue(allSlides.contains(new Pair(new IndentedWord("sol"), new IndentedWord("do"))));
		assertTrue(allSlides.contains(new Pair(new IndentedWord("sol"), new IndentedWord("do", 1))));
		assertTrue(allSlides.contains(new Pair(new IndentedWord("sol"), new IndentedWord("do", 2))));
		assertTrue(allSlides.contains(new Pair(new IndentedWord("sol", 1), new IndentedWord("do"))));
	}	
	
	@Test
	public void removeString() {
		Pool pool = new Pool();
		Pair pair1 = new Pair(new IndentedWord("foo"), new IndentedWord("bar", 3));
		Pair pair2 = new Pair(new IndentedWord("baz", 2), new RightShifter(new IndentedWord("boff"), 1));
		pool.add(pair1);
		pool.add(pair2);
		
		pool.remove("baz");
		
		assertEquals("foo\n...bar\n", pool.toString());
	}
	
	@Test
	public void addAllSlidePositions() {
		Set<Cluster> candidates = new HashSet<Cluster>();
		
		Cluster cluster1 = new Pair(new IndentedWord("fish"), new IndentedWord("wishes"));
		Cluster cluster2 = new Pair(new IndentedWord("iffy"), new IndentedWord("of"));
		
		poolWider.allCombos(candidates, cluster1, cluster2);
		
		assertEquals(9 + 7 + 7 + 5, candidates.size());
	}
}