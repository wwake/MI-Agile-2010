
public class GreedyPuzzleMaker {
	public Puzzle make(Pool pool, Scorer scorer) {
		while (pool.size() > 1) {
			Pool possibilities = pool.candidates();
			Puzzle best = possibilities.bestIn(scorer);
			pool.remove(best.first().word());
			pool.remove(best.last().word());
			pool.add(best);
		}
		
		return pool.any();
	}
}