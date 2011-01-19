import static org.junit.Assert.*;

import org.junit.Test;

public class _TestPairReverser {
	Pair pair = new Pair("fish", "sticks", 3);
	Piece reversed = pair.reversed();

	@Test
	public void reversedPairResemblesOriginal() {
		assertEquals(pair.width(), reversed.width());
		assertEquals(pair.minIndex(), reversed.minIndex());
		assertEquals(pair.maxIndex(), reversed.maxIndex());
		assertEquals(pair.height(), reversed.height());
	}
	
	@Test
	public void reversedPairIsSameAsOriginal() {
		assertEquals(pair, reversed.reversed());
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
		Pair original = new Pair(new Pair("ab", "cd", 1), new Pair("efef", "ghgh", 2), 1);
		assertEquals("...ghgh\n.efef\n.cd\nab", original.reversed().toString());
	}
	
	@Test
	public void equalsForEqualThings() {
		Pair original = new Pair(new Pair("ab", "cd", 1), new Pair("efef", "ghgh", 2), 1);
		Pair expected = 
				new Pair(
						new OffsetWord("ghgh", 3), 
						new Pair(
								new OffsetWord("efef", 1), 
								new Pair(new OffsetWord("cd", 1), new OffsetWord("ab"), 0), 
								0), 
						0);
		assertEquals(original.reversed(), expected);
		assertEquals(expected, original.reversed());
		assertEquals(expected.hashCode(), original.reversed().hashCode());
	}
}
