import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class BuilderTest {
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
	
	@Test
	public void bestInSet() {
		Pool poolCloser = new Pool();
		Pair pair1 = new Pair("a", "c", 0);
		Pair pair2 = new Pair("d", "g", 0);
		Set<Piece> allCombos = poolCloser.allCombos(pair1, pair2);
		assertEquals(new Pair(pair1, pair2, 0), fewerGapsIsBetterScorer.bestIn(allCombos));
	}
}
