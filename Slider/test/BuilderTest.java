import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BuilderTest {
	Scorer fewerGapsIsBetterScorer = new Scorer() {
		public int score(Cluster cluster) {
			int sumOfFirstLetterGaps = 0;
			Cluster previous = cluster.first();
			for (int i = 1; i < cluster.height(); i++) {
				Cluster current = cluster.get(i);
				sumOfFirstLetterGaps += Math.abs(previous.toString().charAt(0)
						- current.toString().charAt(0));
				previous = current;
			}
			return 10000 - sumOfFirstLetterGaps;
		}
	};

	Scorer scorer = new Scorer() {
		public int score(Cluster cluster) {
			Pair pair = (Pair) cluster;
			return pair.part1().width() + pair.part2().width();
		}
	};

	@Test
	public void poolWithOneItemIsResult() {
		Builder builder = new Builder(new String[] { "only" }, new Scorer());
		builder.build();
		assertEquals("only\n", builder.result());
	}

	@Test
	public void foo() { // TODO
		String[] strings = new String[] { "wo", "wor", "w", "word" };
		Builder builder = new Builder(strings, new Scorer());
		builder.build();
		assertTrue(builder.row(0).startsWith("b") || builder.row(0).startsWith("f"));
	}

	@Test
	public void bestInSet() {
		Pool poolCloser = new Pool();
		Pair pair1 = new Pair(new IndentedWord("a"), new IndentedWord("c"));
		Pair pair2 = new Pair(new IndentedWord("d"), new IndentedWord("g"));

		Set<Cluster> candidates = new HashSet<Cluster>();
		poolCloser.addAllCombos(candidates, pair1, pair2);
		assertEquals(new Pair(pair1, pair2), fewerGapsIsBetterScorer.bestIn(candidates));
	}
}
