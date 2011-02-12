import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pool {
	List<Cluster> clusters = new ArrayList<Cluster>();

	public void add(Cluster cluster) {
		clusters.add(cluster);
	}

	public int size() {
		return clusters.size();
	}

	public Cluster get(int i) {
		return clusters.get(i);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Cluster cluster : clusters) {
			result.append(cluster.toString());
			result.append("\n");
		}
		return result.toString();
	}

	public void remove(String string) {
		for (Cluster cluster : clusters) {
			if (cluster.contains(string)) {
				clusters.remove(cluster);
				return;
			}
		}
	}
	
	public Set<Cluster> candidates() {
		Set<Cluster> candidates = new HashSet<Cluster>();

		for (int i = 0; i < clusters.size(); i++)
			for (int j = i + 1; j < clusters.size(); j++)
				addAllCombos(candidates, this.get(i), this.get(j));

		return candidates;
	}

	public void addAllCombos(Set<Cluster> candidates, Cluster cluster1, Cluster cluster2) {
		this.allSlidePositions(candidates, cluster1, cluster2);
		this.allSlidePositions(candidates, cluster1, cluster2.flipped());
		this.allSlidePositions(candidates, cluster1.flipped(), cluster2);
		this.allSlidePositions(candidates, cluster1.flipped(), cluster2.flipped());
	}

	public void allSlidePositions(Set<Cluster> result, Cluster cluster1, Cluster cluster2) {
		IndentedWord lastFromCluster1 = cluster1.last();
		int offset1 = lastFromCluster1.indent();

													// TODO - suspicious - what about ..XYUZ vs. ....AYUR
		
		for (int i = 0; i < lastFromCluster1.width(); i++) {
			Pair pair = new Pair(cluster1, new RightShifter(cluster2, offset1 + i));
			result.add(pair);
		}

		IndentedWord firstFromCluster2 = cluster2.first();
		int offset2 = firstFromCluster2.indent();

		for (int i = 1; i < firstFromCluster2.width(); i++) {
			int offset = offset2 - i;
			Pair pair = offset > 0 ? new Pair(cluster1, new RightShifter(cluster2, offset)) : new Pair(new RightShifter(cluster1, -offset), cluster2);
			result.add(pair);
		}
	}	
}