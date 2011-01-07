import static org.junit.Assert.*;

import org.junit.Test;


public class _TestPieceScorer {

	private PieceScorer scorer = new PieceScorer();

	@Test 
	public void scoreOfEmptyString_IsZero() {
		assertEquals(0, scorer.score(""));
	}
	
	@Test
	public void scoreOfRun_IsSquareOfLength() {
		assertEquals(9, scorer.score("AAA"));
	}
	
	@Test
	public void scoreOfEachSingleChar_Is1() {
		assertEquals(2, scorer.score("AB"));
	}
	
	@Test 
	public void scoreOfMixedRuns_IsCorrect() {
		assertEquals(9 + 1 + 4, scorer.score("AAABCC"));
	}
	
	@Test 
	public void dotsBreakRunsButDontScore() {
		assertEquals(1 + 1 + 4, scorer.score("A..ABB"));
	}
	
	@Test
	public void scoreOfPiece_IsSumOfColumns() {
		Piece piece = new Pair("ABC", "BDA", 1);
		assertEquals(1 + 4 + 2 + 1, scorer.score(piece));
	}
}
