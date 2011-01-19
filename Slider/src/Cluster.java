import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Cluster implements Iterable<OffsetWord> {
	List<OffsetWord> cluster = new ArrayList<OffsetWord>();

	public int size() {
		return cluster.size();
	}

	public void add(OffsetWord word) {
		cluster.add(word);
	}

	public OffsetWord get(int index) {
		return cluster.get(index);
	}

	public Iterator<OffsetWord> iterator() {
		return cluster.iterator();
	}

	public OffsetWord last() {
		return cluster.get(cluster.size() - 1);
	}

	public OffsetWord first() {
		return cluster.get(0);
	}

	public Cluster flip() {
		Cluster result = new Cluster();
		result.cluster.addAll(this.cluster);
		Collections.reverse(result.cluster);
		return result;
	}

	public void shiftRight(int distance) {
		List<OffsetWord> newCluster = new ArrayList<OffsetWord>();

		for (OffsetWord word : cluster) 
			newCluster.add(word.shiftRight(distance));

		cluster = newCluster;
	}
}
