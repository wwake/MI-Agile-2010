import static org.junit.Assert.*;

import org.junit.Test;


public class _TestPair {
	@Test
	public void pairRemembersItsContents(){
		Pair pair = new Pair("fbo", "over", 2);
		assertEquals(new OffsetWord("fbo", 0), pair.first());
		assertEquals(new OffsetWord("over", 2), pair.last());
	}
	
	@Test
	public void whenOffsetIsPositive_MinIndexIs0() {
		Pair pair = new Pair("fxo", "over", 2);
		assertEquals(0, pair.minIndex());
	}
	
	@Test 
	public void whenOffsetIsNeg_MinIndexIsNeg() {
		Pair pair = new Pair("roo", "over", -3);
		assertEquals(-3, pair.minIndex());	
	}
	
	@Test 
	public void minIndexRecursesOnFirstPart() {
		Pair pair1 = new Pair("rno", "over", -2);
		Pair pair2 = new Pair(pair1, new OffsetWord("other"), 3);
		assertEquals(-2, pair2.minIndex());		
	}

	@Test 
	public void minIndexRecursesOnSecondPart() {
		Pair pair1 = new Pair("rno", "over", 2);
		Pair pair2 = new Pair(pair1, new OffsetWord("other"), -1);
		assertEquals(-1, pair2.minIndex());		
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
}