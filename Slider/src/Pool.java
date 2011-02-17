import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Pool implements Iterable<Cluster> {
	Set<Cluster> clusters = new HashSet<Cluster>();

	public void add(Cluster cluster) {
		clusters.add(cluster);
	}

	public int size() {
		return clusters.size();
	}

	public Cluster any() {
		for (Cluster cluster : clusters)
			return cluster;
		throw new NoSuchElementException();
	}

	public boolean contains(Cluster cluster) {
		return clusters.contains(cluster);
	}

	public void remove(String string) {
		for (Cluster cluster : clusters) {
			if (cluster.contains(string)) {
				clusters.remove(cluster);
				return;
			}
		}
	}

	public Iterator<Cluster> iterator() {
		return clusters.iterator();
	}

	public Pool candidates() {
		List<Cluster> clusterList = new ArrayList<Cluster>(clusters);
		Pool result = new Pool();
		
		for (int i = 0; i < clusterList.size(); i++)
			for (int j = i + 1; j < clusterList.size(); j++)
				result.addAllCombos(clusterList.get(i), clusterList.get(j));

		return result;
	}

	public void addAllCombos(Cluster cluster1, Cluster cluster2) {
		this.allSlidePositions(cluster1, cluster2);
		this.allSlidePositions(cluster1, cluster2.inverted());
		this.allSlidePositions(cluster1.inverted(), cluster2);
		this.allSlidePositions(cluster1.inverted(), cluster2.inverted());
	}

	public void allSlidePositions(Cluster cluster1, Cluster cluster2) {
		IndentedWord lastWordFromCluster1 = cluster1.last();
		IndentedWord firstWordFromCluster2 = cluster2.first();

		int distanceToAlignFirstLetters = lastWordFromCluster1.indent()
				- firstWordFromCluster2.indent();

		for (int i = 0; i < lastWordFromCluster1.word().length(); i++)
			this.add(new Pair(cluster1, cluster2, distanceToAlignFirstLetters + i));

		for (int i = 1; i < firstWordFromCluster2.word().length(); i++)
			this.add(new Pair(cluster1, cluster2, distanceToAlignFirstLetters - i));
	}
}