import java.util.Set;

public class Scorer {

	public int scoreColumn(String string) {
		if (string.length() == 0) return 0;

		int result = 0;
		char runStart = '\0';
		int runLength = 0;

		for (int i = 0; i < string.length(); i++) {
			char current = string.charAt(i);
			if (current == '.') {
				runStart = '\0';
				runLength = 0;
				continue;
			}
			if (current != runStart) {
				runStart = current;
				runLength = 1;
				result += 1;
			} else {
				result -= square(runLength);
				runLength++;
				result += square(runLength);
			}
		}
		return result;
	}

	private int square(int arg) {
		return arg * arg;
	}

	public int score(Cluster cluster) {
		int result = 0;
		
		for (int i = 0; i < cluster.width(); i++) 
			result += this.scoreColumn(cluster.column(i));

		return result;
	}

	public Cluster bestIn(Set<Cluster> clusters) {
		int bestScore = -1;
		Cluster bestResult = null;
		
		for (Cluster cluster : clusters) {
			int score = score(cluster);
			if (score > bestScore) {
				bestResult = cluster;
				bestScore = score;
			}
		}
		
		return bestResult;
	}

	public Cluster bestIn(Pool clusters) {
		int bestScore = -1;
		Cluster bestResult = null;
		
		for (Cluster cluster : clusters) {
			int score = score(cluster);
			if (score > bestScore) {
				bestResult = cluster;
				bestScore = score;
			}
		}
		
		return bestResult;
	}
}
