import static org.junit.Assert.*;

import org.junit.Test;


public class RightShifterTest {
	@Test
	public void shiftsFirstWordRight() {
		RightShifter shifter = new RightShifter(new OffsetWord("foo", 0), 2);
		assertEquals("..foo", shifter.first().toString());
		assertEquals(0, shifter.minIndex());
	}
	
	@Test
	public void shiftsLastWordRight() {
		Pair pair = new Pair("foo", "bard", 0);
		RightShifter shifter = new RightShifter(pair, 1);
		assertEquals(".bard", shifter.last().toString());	
		assertEquals(pair.width(), shifter.width());
		assertEquals(4, shifter.maxIndex());
		assertEquals(".foo", shifter.get(0).toString());
	}
	
	@Test
	public void shiftedTextHasRightColumnsAndHeight() {
		Pair pair = new Pair("wish", "fishes", 0);
		RightShifter shifter = new RightShifter(pair, 3);
		assertEquals("..", shifter.column(0));
		assertEquals("wf", shifter.column(3));
		assertEquals(2, shifter.height());
	}
	
	@Test
	public void flippedShiftedTextIsShiftOfFlip() {
		Pair pair = new Pair("wish", "fishes", 0);
		RightShifter shifter = new RightShifter(pair, 3);
		assertEquals(
				new Pair(
						new OffsetWord("fishes", 3),
						new OffsetWord("wish", 3),
						0),
				shifter.flipped());
	}
}
