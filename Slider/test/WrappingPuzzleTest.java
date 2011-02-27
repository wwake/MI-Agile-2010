import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WrappingPuzzleTest {
	@Test
	public void wrappedPuzzleActsLikeOriginal() {
		Puzzle puzzle = new Pair(new IndentedWord("what"), new IndentedWord("ever"), 1);
		WrappingPuzzle wrappedPuzzle = new WrappingPuzzle(puzzle);

		assertEquals(puzzle, wrappedPuzzle);
		assertEquals(puzzle.first(), wrappedPuzzle.first());
		assertEquals(puzzle.last(), wrappedPuzzle.last());
		assertEquals(puzzle.width(), wrappedPuzzle.width());
		assertEquals(puzzle.height(), wrappedPuzzle.height());
		assertEquals(puzzle.column(1), wrappedPuzzle.column(1));
		assertEquals(puzzle.wordAt(1), wrappedPuzzle.wordAt(1));
		assertEquals(puzzle.inverted(), wrappedPuzzle.inverted());
		assertEquals(puzzle.contains("ever"), wrappedPuzzle.contains("ever"));
		assertEquals(puzzle.contains("wh"), wrappedPuzzle.contains("wh"));
	}
}