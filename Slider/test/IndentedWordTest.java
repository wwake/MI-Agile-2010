import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IndentedWordTest {
	@Test
	public void wordKnowsCharacters() {
		IndentedWord word = new IndentedWord("fob", 1);
		assertEquals("fob", word.word());
		assertEquals(4, word.width());
		assertEquals('.', word.at(0));
		assertEquals('f', word.at(1));
		assertEquals('o', word.at(2));
		assertEquals('b', word.at(3));
		assertEquals('.', word.at(4));
	}
	
	@Test
	public void valueObject() {
		IndentedWord word1a = new IndentedWord("bar", 3);
		IndentedWord word1b = new IndentedWord("bar", 3);
		IndentedWord word2 = new IndentedWord("bar", 4);
		assertEquals("...bar", word1a.toString());
		assertEquals(word1a, word1b);
		assertEquals(word1a.hashCode(), word1b.hashCode());
		assertFalse(word1a.equals(word2));
	}
		
	@Test
	public void singleWordCantEqualNonOffsetWord() {
		assertFalse(new IndentedWord("skip", 2).equals(new Object()));
	}
	
	@Test public void wordCanShiftRight() {
		IndentedWord word = new IndentedWord("CAT", 1);
		assertEquals(new IndentedWord("CAT", 4), word.shiftRight(3));
	}
	
	@Test public void negativeShiftIsOKIfOffsetStaysAtLeast0() {
		assertEquals(new IndentedWord("CAT", 1), new IndentedWord("CAT", 3).shiftRight(-2));
		assertEquals(new IndentedWord("CAT"), new IndentedWord("CAT", 3).shiftRight(-3));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void negativeShiftThrowsExceptionIfOffsetGoesBelow0() {
		new IndentedWord("CAT", 1).shiftRight(-2);
	}
	
	@Test 
	public void buildWordFromWordAddsOffset() {
		assertEquals(new IndentedWord("CAT", 5), new IndentedWord(new IndentedWord("CAT", 2), 3));
	}
	
	@Test 
	public void wordIsItsOwnFirstAndLastWord() {
		assertEquals(new IndentedWord("foo", 2), new IndentedWord("foo", 2).first());
		assertEquals(new IndentedWord("foo", 2), new IndentedWord("foo", 2).last());
	}
	
	@Test
	public void widthIsIndentPlusWordWidth() {
		assertEquals(5, new IndentedWord("bar", 2).width());
	}
	
	@Test
	public void columnBuildsStringForPosition() {
		assertEquals("f", new IndentedWord("foo").column(0));
	}
	
	@Test
	public void columnHandlesOffsets() {
		IndentedWord word = new IndentedWord("bx", 1);
		assertEquals(".", word.column(0));
		assertEquals("b", word.column(1));
		assertEquals("x", word.column(2));
		assertEquals(".", word.column(3));
	}
	
	@Test
	public void heightIs1() {
		assertEquals(1, new IndentedWord("foo", 3).height());
	}
	
	@Test
	public void getGetsTheOnlyWord() {
		IndentedWord baz = new IndentedWord("baz", 2);
		assertEquals(baz, baz.get(0));
	}
	
	@Test
	public void wordReversedIsItself() {
		IndentedWord word = new IndentedWord("blink", 3);
		assertEquals(word, word.flipped());
	}
	
	@Test
	public void contains() {
		IndentedWord word = new IndentedWord("biff", 5);
		assertTrue(word.contains("biff"));
		assertFalse(word.contains("if"));
	}
}
