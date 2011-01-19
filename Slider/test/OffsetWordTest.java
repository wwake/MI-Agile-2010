import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class OffsetWordTest {
	@Test
	public void wordKnowsCharacters() {
		OffsetWord word = new OffsetWord("fob", 1);
		assertEquals(4, word.length());
		assertEquals('.', word.at(0));
		assertEquals('f', word.at(1));
		assertEquals('o', word.at(2));
		assertEquals('b', word.at(3));
		assertEquals('.', word.at(4));
	}
	
	@Test
	public void valueObject() {
		OffsetWord word1a = new OffsetWord("bar", 3);
		OffsetWord word1b = new OffsetWord("bar", 3);
		OffsetWord word2 = new OffsetWord("bar", 4);
		assertEquals("...bar", word1a.toString());
		assertEquals(word1a, word1b);
		assertEquals(word1a.hashCode(), word1b.hashCode());
		assertFalse(word1a.equals(word2));
	}
		
	@Test
	public void singleWordCantEqualNonOffsetWord() {
		assertFalse(new OffsetWord("skip", 2).equals(new Object()));
	}
	
	@Test public void wordCanShiftRight() {
		OffsetWord word = new OffsetWord("CAT", 1);
		assertEquals(new OffsetWord("CAT", 4), word.shiftRight(3));
	}
	
	@Test public void negativeShiftIsOKIfOffsetStaysAtLeast0() {
		assertEquals(new OffsetWord("CAT", 1), new OffsetWord("CAT", 3).shiftRight(-2));
		assertEquals(new OffsetWord("CAT"), new OffsetWord("CAT", 3).shiftRight(-3));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void negativeShiftThrowsExceptionIfOffsetGoesBelow0() {
		new OffsetWord("CAT", 1).shiftRight(-2);
	}
	
	@Test 
	public void buildWordFromWordAddsOffset() {
		assertEquals(new OffsetWord("CAT", 5), new OffsetWord(new OffsetWord("CAT", 2), 3));
	}
	
	@Test 
	public void wordIsItsOwnFirstAndLastWord() {
		assertEquals(new OffsetWord("foo", 2), new OffsetWord("foo", 2).first());
		assertEquals(new OffsetWord("foo", 2), new OffsetWord("foo", 2).last());
	}
	
	@Test
	public void minIndexIs0() {
		assertEquals(0, new OffsetWord("foo").minIndex());
	}
	
	@Test
	public void widthIsWordWidth() {
		assertEquals(3, new OffsetWord("bar").width());
	}
	
	@Test
	public void columnBuildsStringForPosition() {
		assertEquals("f", new OffsetWord("foo").column(0));
	}
	
	@Test
	public void columnHandlesOffsets() {
		OffsetWord word = new OffsetWord("bx", 1);
		assertEquals(".", word.column(0));
		assertEquals("b", word.column(1));
		assertEquals("x", word.column(2));
		assertEquals(".", word.column(3));
	}
	
	@Test
	public void heightIs1() {
		assertEquals(1, new OffsetWord("foo", 3).height());
	}
	
	@Test
	public void getGetsTheOnlyWord() {
		OffsetWord baz = new OffsetWord("baz", 2);
		assertEquals(baz, baz.get(0));
	}
	
	@Test
	public void wordReversedIsItself() {
		OffsetWord word = new OffsetWord("blink", 3);
		assertEquals(word, word.reversed());
	}
}
