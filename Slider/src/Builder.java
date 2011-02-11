import java.util.Set;

public class Builder {
	private Pool pool;
	private final Scorer scorer;

	public Builder(String[] strings, Scorer scorer) {
		this.scorer = scorer;
		pool = new Pool();
		for (String string : strings)
			pool.add(new IndentedWord(string));
	}

	public String row(int row) {
		return "bobb";
	}

	public void build() {
		while (pool.size() > 1) {
			Set<Cluster> possibilities = pool.candidates();
			Pair best = (Pair) scorer.bestIn(possibilities);
			pool.remove(best.part1().get(0).word());
			pool.remove(best.part2().get(0).word());
			pool.add(best);
		}
	}
	
	public String result() {
		return pool.toString();
	}
}
