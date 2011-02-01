
public class Builder {

	private Pool pool;

	public Builder(String[] strings, PieceScorer scorer) {
		pool = new Pool(scorer);
		for (String string : strings)
			pool.add(new IndentedWord(string));
	}

	public String row(int row) {
		return "bobb";
	}

	public void build() {
		while (pool.size() > 1) {
			Pair best = (Pair) pool.best();
			pool.remove(best.part1());
			pool.remove(best.part2());
			pool.add(best);
		}
		// run through pool
		// pick best 2
		// remove individuals, replace with combo
		// repeat until 1 item left
	}
	
	public String result() {
		return pool.toString();
	}

}
