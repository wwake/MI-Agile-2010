import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RightShifterTest {
	@Test
	public void shiftsFirstWordRight() {
		RightShifter shifter = new RightShifter(new IndentedWord("foo"), 2);
		assertEquals("..foo", shifter.first().toString());
	}
	
	@Test
	public void shiftsLastWordRight() {
		Pair pair = new Pair(new IndentedWord("foo"), new IndentedWord("bard"));
		RightShifter shifter = new RightShifter(pair, 1);
		assertEquals(".bard", shifter.last().toString());	
		assertEquals(5, shifter.width());
		assertEquals(".foo", shifter.get(0).toString());
	}
	
	@Test
	public void shiftedTextHasRightColumnsAndHeight() {
		Pair pair = new Pair(new IndentedWord("wish"), new IndentedWord("fishes"));
		RightShifter shifter = new RightShifter(pair, 3);
		assertEquals("..", shifter.column(0));
		assertEquals("wf", shifter.column(3));
		assertEquals(2, shifter.height());
	}
	
	@Test
	public void flippedShiftedTextIsShiftOfFlip() {
		Pair pair = new Pair(new IndentedWord("wish"), new IndentedWord("fishes"));
		RightShifter shifter = new RightShifter(pair, 3);
		assertEquals(
				new Pair(
						new IndentedWord("fishes", 3),
						new IndentedWord("wish", 3)),
				shifter.flipped());
	}
	
	@Test
	public void contains() {
		IndentedWord word = new IndentedWord("trash", 3);
		RightShifter shifter = new RightShifter(word, 2);
		assertTrue(shifter.contains("trash"));
		assertFalse(shifter.contains("truck"));
	}
}
