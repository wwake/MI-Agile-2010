import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PairTest {
	@Test
	public void pairRemembersItsContents(){
		Pair pair = new Pair(new IndentedWord("fbo"), new IndentedWord("over", 2));
		assertEquals(new IndentedWord("fbo", 0), pair.first());
		assertEquals(new IndentedWord("over", 2), pair.last());
		assertEquals("fbo\n..over", pair.toString());
	}
	
	@Test 
	public void whenFirstTermIsLongest_WidthIsFirstWordLength() {
		Pair pair = new Pair(new IndentedWord("verylong"), new IndentedWord("short", 1));
		assertEquals(8, pair.width());
	}
	
	@Test 
	public void whenSecondTermIsLongest_WidthIsSecondWordLength() {
		Pair pair = new Pair(new IndentedWord("short", 1), new IndentedWord("verylong"));
		assertEquals(8, pair.width());
	}
	
	@Test
	public void pairGetsColumn() {
		Pair pair = new Pair(new IndentedWord("of"), new IndentedWord("po"));
		assertEquals("op", pair.column(0));
	}
	
	@Test
	public void columnHandlesSecondPartWithOffset() {
		Pair pair = new Pair(new IndentedWord("ba"), new IndentedWord("at", 1));
		assertEquals("b.", pair.column(0));
		assertEquals("aa", pair.column(1));
		assertEquals(".t", pair.column(2));
	}
	
	@Test
	public void pairGetsPositionBeforeAndAfterBreak() {
		Pair pair1 = new Pair(new IndentedWord("ba"), new IndentedWord("bo"));
		Pair pair2 = new Pair(pair1, new IndentedWord("foo"));
		
		assertEquals(new IndentedWord("ba"), pair2.wordAt(0));
		assertEquals(new IndentedWord("bo"), pair2.wordAt(1));
		assertEquals(new IndentedWord("foo"), pair2.wordAt(2));
	}
	
	@Test
	public void getHandlesOffsets() {
		Pair pair = new Pair(new IndentedWord("a"), new IndentedWord("z", 3));
		assertEquals(new IndentedWord("a", 0), pair.wordAt(0));
		assertEquals(new IndentedWord("z", 3), pair.wordAt(1));
	}
	
	@Test
	public void equalsAndHashCodeDependOnWordsAndOffsets() {
		Pair pair1a = new Pair(new IndentedWord("foo"), new IndentedWord("bar", 3));
		Pair pair2a = new Pair(new IndentedWord("gab"), new IndentedWord("job", 4));
		Pair pairBothA = new Pair(pair1a, new RightShifter(pair2a, 1));
		
		Pair pair1b = new Pair(new IndentedWord("foo"), new IndentedWord("bar", 3));
		Pair pair2b = new Pair(pair1b, new IndentedWord("gab", 1));
		Pair pairBothB = new Pair(pair2b, new IndentedWord("job", 5));
		
		assertEquals(pairBothA, pairBothB);
		assertEquals(pairBothA.hashCode(), pairBothB.hashCode());
	}

	@Test
	public void pairsWithDifferentContentsArentEqual() {
		Pair pair1a = new Pair(new IndentedWord("foo"), new IndentedWord("bar", 3));
		Pair pair2 = new Pair(new IndentedWord("foo"), new IndentedWord("bar", 4));
		assertFalse(pair1a.equals(pair2));
	}

	@Test
	public void pairsCanBeFlipped() {
		Pair pair1 = new Pair(
				new Pair(new IndentedWord("fizz"), new IndentedWord("soda", 1)), 
				new Pair(new IndentedWord("bang"), new IndentedWord("gar", 3)), 
				2);
		
		assertEquals(
				new Pair(
						new Pair(new IndentedWord("gar", 5), new IndentedWord("bang", 2)), 
						new Pair(new IndentedWord("soda", 1), new IndentedWord("fizz"))), 
				pair1.flipped());
	}
	
	@Test
	public void contains() {
		Pair pair = new Pair(
				new Pair(new IndentedWord("foo"), new IndentedWord("bar", 1)), 
				new Pair(new IndentedWord("if"), new IndentedWord("flat", 1)), 
				2);
		
		assertTrue(pair.contains("bar"));
		assertTrue(pair.contains("flat"));
		assertFalse(pair.contains("oo"));
	}
}