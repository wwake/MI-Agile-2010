import static org.junit.Assert.*;

import org.junit.Test;


public class ClusterTest {
	Cluster cluster = new Cluster();
	OffsetWord foo = new OffsetWord("FOO");
	OffsetWord bar = new OffsetWord("BAR");

	@Test
	public void clusterStartsEmpty() {
		assertEquals(0, cluster.size());
	}
	
	@Test
	public void addWordIncreasesSize() {
		cluster.add(foo);
		assertEquals(1, cluster.size());
		assertEquals(foo, cluster.get(0));
	}
	
	@Test
	public void addedWordsAreAllTracked() {
		cluster.add(foo);
		cluster.add(bar);
		assertEquals(2, cluster.size());
		assertEquals(foo, cluster.get(0));
		assertEquals(bar, cluster.get(1));
	}
	
	@Test
	public void flip() {
		cluster.add(foo);
		cluster.add(bar);
		Cluster flipped = cluster.flip();
		assertEquals(2, flipped.size());
		assertEquals(bar, flipped.get(0));
		assertEquals(foo, flipped.get(1));	
	}
	
	@Test
	public void shiftRight() {
		cluster.add(foo);
		cluster.shiftRight(2);
		assertEquals("..FOO", cluster.get(0).toString());
	}
}
