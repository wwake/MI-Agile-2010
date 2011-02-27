import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InvertedPuzzleTest {
	JoinedPuzzle puzzle = new JoinedPuzzle(new IndentedWord("fish"), new IndentedWord("sticks", 3));
	Puzzle reversed = puzzle.inverted();

	@Test
	public void reversedPuzzleResemblesOriginal() {
		assertEquals(puzzle.width(), reversed.width());
		assertEquals(puzzle.height(), reversed.height());
	}

	@Test
	public void doublyReversedPuzzleIsSameAsOriginal() {
		assertEquals(puzzle, reversed.inverted());
	}

	@Test
	public void firstAndLastSwapPlaces() {
		assertEquals(puzzle.last(), reversed.first());
		assertEquals(puzzle.first(), reversed.last());
	}

	@Test
	public void columnIsReversedFromBase() {
		assertEquals("hs", puzzle.column(3));
		assertEquals("sh", reversed.column(3));
	}

	@Test
	public void toStringGetsReversedByWord() {
		JoinedPuzzle original = new JoinedPuzzle(
				new JoinedPuzzle(new IndentedWord("ab"), new IndentedWord("cd", 1)), 
				new JoinedPuzzle(new IndentedWord("efef"), new IndentedWord("ghgh", 2)),
				1);
		assertEquals("...ghgh\n.efef\n.cd\nab", original.inverted().toString());
	}

	@Test
	public void equalsForEqualThings() {
		JoinedPuzzle original = new JoinedPuzzle(
				new JoinedPuzzle(new IndentedWord("ab"), new IndentedWord("cd", 1)), 
				new JoinedPuzzle(new IndentedWord("efef"), new IndentedWord("ghgh", 2)), 
				1);
		JoinedPuzzle expected = new JoinedPuzzle(
				new IndentedWord("ghgh", 3), 
				new JoinedPuzzle(
						new IndentedWord("efef", 1),
						new JoinedPuzzle(new IndentedWord("cd", 1), new IndentedWord("ab"))));
		assertEquals(original.inverted(), expected);
		assertEquals(expected, original.inverted());
		assertEquals(expected.hashCode(), original.inverted().hashCode());
	}

	@Test
	public void contains() {
		JoinedPuzzle puzzleFromParts = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("gef", 2));
		InvertedPuzzle flipper = new InvertedPuzzle(puzzleFromParts);
		assertTrue(flipper.contains("foo"));
		assertFalse(flipper.contains("fo"));
	}
}