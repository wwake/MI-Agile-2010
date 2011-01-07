import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class _TestPool {
	PieceScorer scorer = new PieceScorer() {
		public int score(Piece piece) {
			Pair pair = (Pair) piece;
			return pair.part1().width() + pair.part2().width();
		}
	};
	
	private Pool pool;
	
	private OffsetWord word1;
	private OffsetWord word2;
	private OffsetWord word3;
	private OffsetWord word4;

	@Before
	public void setUp() {
		pool = new Pool(scorer);
		word1 = new OffsetWord("w");
		word2 = new OffsetWord("wo");
		word3 = new OffsetWord("wor");
		word4 = new OffsetWord("word");

		pool.add(word2);
		pool.add(word4);
		pool.add(word3);
		pool.add(word1);
	}
	
	@Test
	public void poolAddsItems() {
		assertEquals(4, pool.size());
		assertEquals(word2, pool.get(0));
		assertEquals(word1, pool.get(3));
	}

	@Test 
	public void bestPair() {
		Piece piece = pool.best();
		assertEquals(7, scorer.score(piece));
	}
	
	@Test
	public void toStringHasContents() {
		assertEquals("wo\nword\nwor\nw\n", pool.toString());
	}
	
}
