import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {
	@Test
	public void pairRemembersItsContents(){
		Pair pair = new Pair("fbo", "over", 2);
		assertEquals(new OffsetWord("fbo", 0), pair.first());
		assertEquals(new OffsetWord("over", 2), pair.last());
		assertEquals("fbo\n..over", pair.toString());
	}
	
	@Test 
	public void whenFirstTermIsLongest_WidthIsFirstWordLength() {
		Pair pair = new Pair("verylong", "short", 1);
		assertEquals(8, pair.width());
	}
	
	@Test
	public void whenOffsetIs0_MaxIndexIsMaxWordLength() {
		Pair pair = new Pair("fox", "rabbit", 0);
		assertEquals(5, pair.maxIndex());
	}
	
	@Test
	public void whenWordHasOffset_MaxIndexAccountsForIt() {
		Pair pair = new Pair(new OffsetWord("bunny", 0), new OffsetWord("dog", 3), 0);
		assertEquals(5, pair.maxIndex());
	}
	
	@Test
	public void whenPairHasOffset_MaxIndexAccountsForIt() {
		Pair pair = new Pair("beetle", "fox", 4);
		assertEquals(6, pair.maxIndex());
	}
	
	@Test
	public void pairGetsColumn() {
		Pair pair = new Pair("of", "op", 0);
		assertEquals("oo", pair.column(0));
	}
	
	@Test
	public void columnHandlesSecondPartWithOffset() {
		Pair pair = new Pair("ba", "at", 1);
		assertEquals("b.", pair.column(0));
		assertEquals("aa", pair.column(1));
		assertEquals(".t", pair.column(2));
	}
	
	@Test
	public void pairGetsPositionBeforeAndAfterBreak() {
		Pair pair1 = new Pair("ba", "bo", 0);
		Pair pair2 = new Pair(pair1, new OffsetWord("foo"), 0);
		
		assertEquals(new OffsetWord("ba"), pair2.get(0));
		assertEquals(new OffsetWord("bo"), pair2.get(1));
		assertEquals(new OffsetWord("foo"), pair2.get(2));
	}
	
	@Test
	public void getHandlesOffsets() {
		Pair pair = new Pair("a", "z", 3);
		assertEquals(new OffsetWord("a", 0), pair.get(0));
		assertEquals(new OffsetWord("z", 3), pair.get(1));
	}
	
	@Test
	public void equalsAndHashCodeDependOnWordsAndOffsets() {
		Pair pair1a = new Pair("foo", "bar", 3);
		Pair pair2a = new Pair("gab", "job", 4);
		Pair pairBothA = new Pair(pair1a, pair2a, 1);
		
		Pair pair1b = new Pair("foo", "bar", 3);
		Pair pair2b = new Pair(pair1b, new OffsetWord("gab", 1), 0);
		Pair pairBothB = new Pair(pair2b, new OffsetWord("job", 3), 2);
		
		assertEquals(pairBothA, pairBothB);
		assertEquals(pairBothA.hashCode(), pairBothB.hashCode());
	}

	@Test
	public void pairsWithDifferentContentsArentEqual() {
		Pair pair1a = new Pair("foo", "bar", 3);
		Pair pair2 = new Pair("foo", "bar", 4);
		assertFalse(pair1a.equals(pair2));
	}

	@Test
	public void pairsCanBeFlipped() {
		Pair pair1 = new Pair(new Pair("fizz", "soda", 1), new Pair("bang", "gar", 3), 2);
		
		assertEquals(
				new Pair(
						new Pair(new OffsetWord("gar", 5), new OffsetWord("bang", 2), 0), 
						new Pair(new OffsetWord("soda", 1), new OffsetWord("fizz", 0), 0), 
						0), 
				pair1.flipped());
	}
	
	@Test
	public void canOffsetLeftOrRight() {
		Pair pair = new Pair("tou", "can", -2);
		assertEquals("..tou", pair.get(0).toString());
		assertEquals("can", pair.get(1).toString());
	}
}