
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
/*
- Like the Scorer being passed in.  Nice touch.
- Did you think about a Pool having a method like solveWith(Scorer) that encapsulated the loop
  from the build() method above?  Pool seems to be referenced a lot in that method and if it was
  on Pool, then those would all become "this".  The Pool could be renamed to Puzzle.  I like this 
  tiny class, though, so I'm torn a bit on this.
*/