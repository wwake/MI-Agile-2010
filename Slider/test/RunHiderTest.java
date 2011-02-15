import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class RunHiderTest {
	@Test
	public void mostMethodsPassThrough() {
		Pair pair = new Pair(new IndentedWord("foo"), new IndentedWord("bar"), 2);
		RunHider hider = new RunHider(pair);
			
		assertEquals(new IndentedWord("foo"), hider.first());
		assertEquals(new IndentedWord("bar", 2), hider.last());
		assertEquals("ob", hider.column(2));
		assertEquals(2, hider.height());
		assertEquals(5, hider.width());
		assertTrue(hider.contains("bar"));
		assertEquals(new Pair(new IndentedWord("bar", 2), new IndentedWord("foo"), 0), hider.flipped());
	}
	
	@Test
	public void uniqueCharactersPassThrough() {
		IndentedWord word = new IndentedWord("FAN", 1);
		RunHider hider = new RunHider(word);
		assertEquals(word, hider.get(0));
	}
}
