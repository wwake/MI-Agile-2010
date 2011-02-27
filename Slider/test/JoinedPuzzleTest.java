import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JoinedPuzzleTest {
	@Test
	public void pairRemembersItsContents(){
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("fbo"), new IndentedWord("over", 2));
		assertEquals(new IndentedWord("fbo", 0), pair.first());
		assertEquals(new IndentedWord("over", 2), pair.last());
		assertEquals("fbo\n..over", pair.toString());
	}
	
	@Test 
	public void whenFirstTermIsLongest_WidthIsFirstWordLength() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("verylong"), new IndentedWord("short", 1));
		assertEquals(8, pair.width());
	}
	
	@Test 
	public void whenSecondTermIsLongest_WidthIsSecondWordLength() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("short", 1), new IndentedWord("verylong"));
		assertEquals(8, pair.width());
	}
	
	@Test
	public void pairGetsColumn() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("of"), new IndentedWord("po"));
		assertEquals("op", pair.column(0));
	}
	
	@Test
	public void columnHandlesSecondPartWithOffset() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("ba"), new IndentedWord("at", 1));
		assertEquals("b.", pair.column(0));
		assertEquals("aa", pair.column(1));
		assertEquals(".t", pair.column(2));
	}
	
	@Test
	public void pairGetsPositionBeforeAndAfterBreak() {
		JoinedPuzzle pair1 = new JoinedPuzzle(new IndentedWord("ba"), new IndentedWord("bo"));
		JoinedPuzzle pair2 = new JoinedPuzzle(pair1, new IndentedWord("foo"));
		
		assertEquals(new IndentedWord("ba"), pair2.wordAt(0));
		assertEquals(new IndentedWord("bo"), pair2.wordAt(1));
		assertEquals(new IndentedWord("foo"), pair2.wordAt(2));
	}
	
	@Test
	public void getHandlesOffsets() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("a"), new IndentedWord("z", 3));
		assertEquals(new IndentedWord("a", 0), pair.wordAt(0));
		assertEquals(new IndentedWord("z", 3), pair.wordAt(1));
	}
	
	@Test
	public void equalsAndHashCodeDependOnWordsAndOffsets() {
		JoinedPuzzle pair1a = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 3));
		JoinedPuzzle pair2a = new JoinedPuzzle(new IndentedWord("gab"), new IndentedWord("job", 4));
		JoinedPuzzle pairBothA = new JoinedPuzzle(pair1a, new RightShiftedPuzzle(pair2a, 1));
		
		JoinedPuzzle pair1b = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 3));
		JoinedPuzzle pair2b = new JoinedPuzzle(pair1b, new IndentedWord("gab", 1));
		JoinedPuzzle pairBothB = new JoinedPuzzle(pair2b, new IndentedWord("job", 5));
		
		assertEquals(pairBothA, pairBothB);
		assertEquals(pairBothA.hashCode(), pairBothB.hashCode());
	}

	@Test
	public void pairsWithDifferentContentsArentEqual() {
		JoinedPuzzle pair1a = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 3));
		JoinedPuzzle pair2 = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 4));
		assertFalse(pair1a.equals(pair2));
	}

	@Test
	public void pairsCanBeFlipped() {
		JoinedPuzzle pair1 = new JoinedPuzzle(
				new JoinedPuzzle(new IndentedWord("fizz"), new IndentedWord("soda", 1)), 
				new JoinedPuzzle(new IndentedWord("bang"), new IndentedWord("gar", 3)), 
				2);
		
		assertEquals(
				new JoinedPuzzle(
						new JoinedPuzzle(new IndentedWord("gar", 5), new IndentedWord("bang", 2)), 
						new JoinedPuzzle(new IndentedWord("soda", 1), new IndentedWord("fizz"))), 
				pair1.inverted());
	}
	
	@Test
	public void contains() {
		JoinedPuzzle pair = new JoinedPuzzle(
				new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 1)), 
				new JoinedPuzzle(new IndentedWord("if"), new IndentedWord("flat", 1)), 
				2);
		
		assertTrue(pair.contains("bar"));
		assertTrue(pair.contains("flat"));
		assertFalse(pair.contains("oo"));
	}
}