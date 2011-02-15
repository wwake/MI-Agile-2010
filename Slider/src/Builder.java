
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
