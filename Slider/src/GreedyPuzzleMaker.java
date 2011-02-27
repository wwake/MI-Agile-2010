
public class GreedyPuzzleMaker {
	public Cluster make(Pool pool, Scorer scorer) {
		while (pool.size() > 1) {
			Pool possibilities = pool.candidates();
			Cluster best = possibilities.bestIn(scorer);
			pool.remove(best.first().word());
			pool.remove(best.last().word());
			pool.add(best);
		}
		
		return pool.any();
	}
}