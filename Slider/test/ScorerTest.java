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
		assertEquals(2, scorer.scoreColumn("AB"));
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
		Cluster cluster = new Pair(new IndentedWord("ABC"), new IndentedWord("BDA", 1));
		assertEquals(1 + 4 + 2 + 1, scorer.score(cluster));
	}
	
	@Test
	public void bestInSet() {
		Scorer fewerGapsIsBetterScorer = new Scorer() {
			public int score(Cluster cluster) {
				int sumOfFirstLetterGaps = 0;
				Cluster previous = cluster.first();
				for (int i = 1; i < cluster.height(); i++) {
					Cluster current = cluster.wordAt(i);
					sumOfFirstLetterGaps += Math.abs(previous.toString().charAt(0)
							- current.toString().charAt(0));
					previous = current;
				}
				return 10000 - sumOfFirstLetterGaps;
			}
		};


		Pair pair1 = new Pair(new IndentedWord("a"), new IndentedWord("c"));
		Pair pair2 = new Pair(new IndentedWord("d"), new IndentedWord("g"));

		Pool candidates = new Pool();
		candidates.addAllCombos(pair1, pair2);
		
		assertEquals(new Pair(pair1, pair2), fewerGapsIsBetterScorer.bestIn(candidates));
	}
}
