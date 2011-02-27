import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ScorerTest {
	private Scorer scorer = new Scorer();

	@Test 
	public void scoreOfEmptyString_IsZero() {
		assertEquals(0, scorer.scoreColumn(""));
	}
	
	@Test
	public void scoreOfRun_IsSquareOfLength() {
		assertEquals(9, scorer.scoreColumn("AAA"));
	}
	
	@Test
	public void scoreOfEachSingleChar_Is1() {
		assertEquals(1+1, scorer.scoreColumn("AB"));
	}
	
	@Test 
	public void scoreOfMixedRuns_IsCorrect() {
		assertEquals(9 + 1 + 4, scorer.scoreColumn("AAABCC"));
	}
	
	@Test 
	public void dotsBreakRunsButDontScore() {
		assertEquals(1 + 1 + 4, scorer.scoreColumn("A..ABB"));
	}
	
	@Test
	public void scoreIsSumOfColumns() {
		Puzzle puzzle = new Pair(new IndentedWord("ABC"), new IndentedWord("BDA", 1));
		assertEquals(1 + 4 + 2 + 1, scorer.score(puzzle));
	}
}
