import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WrappingClusterTest {
	@Test
	public void wrappedClusterActsLikeOriginal() {
		Cluster cluster = new Pair(new IndentedWord("what"), new IndentedWord("ever"), 1);
		WrappingCluster wrappedCluster = new WrappingCluster(cluster);

		assertEquals(cluster, wrappedCluster);
		assertEquals(cluster.first(), wrappedCluster.first());
		assertEquals(cluster.last(), wrappedCluster.last());
		assertEquals(cluster.width(), wrappedCluster.width());
		assertEquals(cluster.height(), wrappedCluster.height());
		assertEquals(cluster.column(1), wrappedCluster.column(1));
		assertEquals(cluster.wordAt(1), wrappedCluster.wordAt(1));
		assertEquals(cluster.inverted(), wrappedCluster.inverted());
		assertEquals(cluster.contains("ever"), wrappedCluster.contains("ever"));
		assertEquals(cluster.contains("wh"), wrappedCluster.contains("wh"));
	}
}
