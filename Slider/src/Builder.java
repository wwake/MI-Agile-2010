
public class Builder {
	private Pool pool;
	private final Scorer scorer;

	public Builder(String[] strings, Scorer scorer) {
		this.scorer = scorer;
		pool = new Pool();
		for (String string : strings)
			pool.add(new IndentedWord(string));
	}

	public String build() {
		while (pool.size() > 1) {
			Pool possibilities = pool.candidates();			
			Cluster best = scorer.bestIn(possibilities);
			pool.remove(best.first().word());
			pool.remove(best.last().word());
			pool.add(best);
		}
		return pool.any().toString();
	}
}
