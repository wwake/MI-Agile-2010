import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class OldCluster implements Iterable<IndentedWord> {
	List<IndentedWord> cluster = new ArrayList<IndentedWord>();

	public int size() {
		return cluster.size();
	}

	public void add(IndentedWord word) {
		cluster.add(word);
	}

	public IndentedWord get(int index) {
		return cluster.get(index);
	}

	public Iterator<IndentedWord> iterator() {
		return cluster.iterator();
	}

	public IndentedWord last() {
		return cluster.get(cluster.size() - 1);
	}

	public IndentedWord first() {
		return cluster.get(0);
	}

	public OldCluster flip() {
		OldCluster result = new OldCluster();
		result.cluster.addAll(this.cluster);
		Collections.reverse(result.cluster);
		return result;
	}

	public void shiftRight(int distance) {
		List<IndentedWord> newCluster = new ArrayList<IndentedWord>();

		for (IndentedWord word : cluster) 
			newCluster.add(new IndentedWord(word, distance));

		cluster = newCluster;
	}
}
