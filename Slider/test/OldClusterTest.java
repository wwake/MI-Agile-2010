import static org.junit.Assert.*;

import org.junit.Test;


public class OldClusterTest {
	OldCluster cluster = new OldCluster();
	IndentedWord foo = new IndentedWord("FOO");
	IndentedWord bar = new IndentedWord("BAR");

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
		OldCluster flipped = cluster.flip();
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
