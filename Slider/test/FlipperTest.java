import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FlipperTest {
	Pair pair = new Pair(new IndentedWord("fish"), new IndentedWord("sticks", 3));
	Piece reversed = pair.flipped();

	@Test
	public void reversedPairResemblesOriginal() {
		assertEquals(pair.width(), reversed.width());
		assertEquals(pair.height(), reversed.height());
	}

	@Test
	public void reversedPairIsSameAsOriginal() {
		assertEquals(pair, reversed.flipped());
	}

	@Test
	public void firstAndLastSwapPlaces() {
		assertEquals(pair.last(), reversed.first());
		assertEquals(pair.first(), reversed.last());
	}

	@Test
	public void columnIsReversedFromBase() {
		assertEquals("hs", pair.column(3));
		assertEquals("sh", reversed.column(3));
	}

	@Test
	public void toStringGetsReversedByWord() {
		Pair original = new Pair(
				new Pair(new IndentedWord("ab"), new IndentedWord("cd", 1)), 
				new RightShifter(
						new Pair(new IndentedWord("efef"), new IndentedWord("ghgh", 2)),
						1));
		assertEquals("...ghgh\n.efef\n.cd\nab", original.flipped().toString());
	}

	@Test
	public void equalsForEqualThings() {
		Pair original = new Pair(
				new Pair(new IndentedWord("ab"), new IndentedWord("cd", 1)), 
				new RightShifter(new Pair(new IndentedWord("efef"), new IndentedWord("ghgh", 2)), 1));
		Pair expected = new Pair(
				new IndentedWord("ghgh", 3), 
				new Pair(
						new IndentedWord("efef", 1),
						new Pair(new IndentedWord("cd", 1), new IndentedWord("ab"))));
		assertEquals(original.flipped(), expected);
		assertEquals(expected, original.flipped());
		assertEquals(expected.hashCode(), original.flipped().hashCode());
	}

	@Test
	public void contains() {
		Pair pair = new Pair(new IndentedWord("foo"), new IndentedWord("gef", 2));
		Flipper flipper = new Flipper(pair);
		assertTrue(flipper.contains("foo"));
		assertFalse(flipper.contains("fo"));
	}
}