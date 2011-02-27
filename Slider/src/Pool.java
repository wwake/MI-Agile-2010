import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Pool implements Iterable<Puzzle> {
	Set<Puzzle> clusters = new HashSet<Puzzle>();

	public Pool(String[] words) {
		for (String string : words)
			clusters.add(new IndentedWord(string));
	}
	
	public void add(Puzzle cluster) {
		clusters.add(cluster);
	}

	public int size() {
		return clusters.size();
	}

	public Puzzle any() {
		for (Puzzle cluster : clusters)
			return cluster;
		throw new NoSuchElementException();
	}

	public boolean contains(Puzzle cluster) {
		return clusters.contains(cluster);
	}

	public void remove(String string) {
		for (Puzzle cluster : clusters) {
			if (cluster.contains(string)) {
				clusters.remove(cluster);
				return;
			}
		}
	}

	public Iterator<Puzzle> iterator() {
		return clusters.iterator();
	}

	public Pool candidates() {
		List<Puzzle> clusterList = new ArrayList<Puzzle>(clusters);
		Pool result = new Pool(new String[]{});
		
		for (int i = 0; i < clusterList.size(); i++)
			for (int j = i + 1; j < clusterList.size(); j++)
				result.addAllCombos(clusterList.get(i), clusterList.get(j));

		return result;
	}

	public void addAllCombos(Puzzle cluster1, Puzzle cluster2) {
		this.allSlidePositions(cluster1, cluster2);
		this.allSlidePositions(cluster1, cluster2.inverted());
		this.allSlidePositions(cluster1.inverted(), cluster2);
		this.allSlidePositions(cluster1.inverted(), cluster2.inverted());
	}

	public void allSlidePositions(Puzzle cluster1, Puzzle cluster2) {
		IndentedWord lastWordFromCluster1 = cluster1.last();
		IndentedWord firstWordFromCluster2 = cluster2.first();

		int distanceToAlignFirstLetters = lastWordFromCluster1.indent()
				- firstWordFromCluster2.indent();

		for (int i = 0; i < lastWordFromCluster1.word().length(); i++)
			this.add(new Pair(cluster1, cluster2, distanceToAlignFirstLetters + i));

		for (int i = 1; i < firstWordFromCluster2.word().length(); i++)
			this.add(new Pair(cluster1, cluster2, distanceToAlignFirstLetters - i));
	}

	public Puzzle bestIn(Scorer scorer) {
		int bestScore = -1;
		Puzzle bestResult = null;
		
		for (Puzzle cluster : this) {
			int score = scorer.score(cluster);
			if (score > bestScore) {
				bestResult = cluster;
				bestScore = score;
			}
		}
		
		return bestResult;
	}
}