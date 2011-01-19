import static org.junit.Assert.*;

import org.junit.Test;



public class BuilderTest {

	PieceScorer scorer = new PieceScorer() {
		public int score(Piece piece) {
			Pair pair = (Pair) piece;
			return pair.part1().width() + pair.part2().width();
		}
	};
	
	@Test public void poolWithOneItemIsResult() {
		Builder builder = new Builder(new String[]{"only"}, new PieceScorer());
		builder.build();
		assertEquals("only\n", builder.result());
	}

	@Test
	public void foo() {
		String[] strings = new String[] {"wo", "wor", "w", "word"};
		Builder builder = new Builder(strings, new PieceScorer());
		builder.build();
		assertTrue(builder.row(0).startsWith("b") || builder.row(0).startsWith("f"));
	}
	
}
