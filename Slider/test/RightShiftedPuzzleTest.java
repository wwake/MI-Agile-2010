import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RightShiftedPuzzleTest {
	@Test
	public void shiftsFirstWordRight() {
		RightShiftedPuzzle shifter = new RightShiftedPuzzle(new IndentedWord("foo"), 2);
		assertEquals("..foo", shifter.first().toString());
	}
	
	@Test
	public void shiftsLastWordRight() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bard"));
		RightShiftedPuzzle shifter = new RightShiftedPuzzle(pair, 1);
		assertEquals(".bard", shifter.last().toString());	
		assertEquals(5, shifter.width());
		assertEquals(".foo", shifter.wordAt(0).toString());
	}
	
	@Test
	public void shiftedTextHasRightColumnsAndHeight() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("wish"), new IndentedWord("fishes"));
		RightShiftedPuzzle shifter = new RightShiftedPuzzle(pair, 3);
		assertEquals("..", shifter.column(0));
		assertEquals("wf", shifter.column(3));
		assertEquals(2, shifter.height());
	}
	
	@Test
	public void flippedShiftedTextIsShiftOfFlip() {
		JoinedPuzzle pair = new JoinedPuzzle(new IndentedWord("wish"), new IndentedWord("fishes"));
		RightShiftedPuzzle shifter = new RightShiftedPuzzle(pair, 3);
		assertEquals(
				new JoinedPuzzle(
						new IndentedWord("fishes", 3),
						new IndentedWord("wish", 3)),
				shifter.inverted());
	}
	
	@Test
	public void contains() {
		IndentedWord word = new IndentedWord("trash", 3);
		RightShiftedPuzzle shifter = new RightShiftedPuzzle(word, 2);
		assertTrue(shifter.contains("trash"));
		assertFalse(shifter.contains("truck"));
	}
}
