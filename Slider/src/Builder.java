
public class Builder {
	private Pool clusters;
	private final Scorer scorer;

	public Builder(String[] strings, Scorer scorer) {
		this.scorer = scorer;
		clusters = new Pool();
		for (String string : strings)
			clusters.add(new IndentedWord(string));
	}

	public String build() {
		while (clusters.size() > 1) {
			Pool possibilities = clusters.candidates();			
			Cluster best = scorer.bestIn(possibilities);
			clusters.remove(best.first().word());
			clusters.remove(best.last().word());
			clusters.add(best);
		}
		
		return clusters.any().toString();
	}
}
/*
- Like the Scorer being passed in.  Nice touch.
- Did you think about a Pool having a method like solveWith(Scorer) that encapsulated the loop
  from the build() method above?  Pool seems to be referenced a lot in that method and if it was
  on Pool, then those would all become "this".  The Pool could be renamed to Puzzle.  I like this 
  tiny class, though, so I'm torn a bit on this.
*/