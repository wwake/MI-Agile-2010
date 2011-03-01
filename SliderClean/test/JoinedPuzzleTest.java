import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JoinedPuzzleTest {
	@Test
	public void joinedPuzzleRemembersItsContents(){
		JoinedPuzzle puzzle = new JoinedPuzzle(new IndentedWord("fbo"), new IndentedWord("over", 2));
		assertEquals(new IndentedWord("fbo", 0), puzzle.first());
		assertEquals(new IndentedWord("over", 2), puzzle.last());
		assertEquals("fbo\n..over", puzzle.toString());
	}
	
	@Test 
	public void whenFirstTermIsLongest_WidthIsFirstWordLength() {
		JoinedPuzzle puzzle = new JoinedPuzzle(new IndentedWord("verylong"), new IndentedWord("short", 1));
		assertEquals(8, puzzle.width());
	}
	
	@Test 
	public void whenSecondTermIsLongest_WidthIsSecondWordLength() {
		JoinedPuzzle puzzle = new JoinedPuzzle(new IndentedWord("short", 1), new IndentedWord("verylong"));
		assertEquals(8, puzzle.width());
	}
	
	@Test
	public void joinedPuzzleGetsColumn() {
		JoinedPuzzle puzzle = new JoinedPuzzle(new IndentedWord("of"), new IndentedWord("po"));
		assertEquals("op", puzzle.column(0));
	}
	
	@Test
	public void columnHandlesSecondPartWithOffset() {
		JoinedPuzzle puzzle = new JoinedPuzzle(new IndentedWord("ba"), new IndentedWord("at", 1));
		assertEquals("b.", puzzle.column(0));
		assertEquals("aa", puzzle.column(1));
		assertEquals(".t", puzzle.column(2));
	}
	
	@Test
	public void joinedPuzzleGetsPositionBeforeAndAfterBreak() {
		JoinedPuzzle puzzle1 = new JoinedPuzzle(new IndentedWord("ba"), new IndentedWord("bo"));
		JoinedPuzzle puzzle2 = new JoinedPuzzle(puzzle1, new IndentedWord("foo"));
		
		assertEquals(new IndentedWord("ba"), puzzle2.wordAt(0));
		assertEquals(new IndentedWord("bo"), puzzle2.wordAt(1));
		assertEquals(new IndentedWord("foo"), puzzle2.wordAt(2));
	}
	
	@Test
	public void getHandlesOffsets() {
		JoinedPuzzle puzzle = new JoinedPuzzle(new IndentedWord("a"), new IndentedWord("z", 3));
		assertEquals(new IndentedWord("a", 0), puzzle.wordAt(0));
		assertEquals(new IndentedWord("z", 3), puzzle.wordAt(1));
	}
	
	@Test
	public void equalsAndHashCodeDependOnWordsAndOffsets() {
		JoinedPuzzle puzzle1a = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 3));
		JoinedPuzzle puzzle2a = new JoinedPuzzle(new IndentedWord("gab"), new IndentedWord("job", 4));
		JoinedPuzzle puzzleBothA = new JoinedPuzzle(puzzle1a, new RightShiftedPuzzle(puzzle2a, 1));
		
		JoinedPuzzle puzzle1b = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 3));
		JoinedPuzzle puzzle2b = new JoinedPuzzle(puzzle1b, new IndentedWord("gab", 1));
		JoinedPuzzle puzzleBothB = new JoinedPuzzle(puzzle2b, new IndentedWord("job", 5));
		
		assertEquals(puzzleBothA, puzzleBothB);
		assertEquals(puzzleBothA.hashCode(), puzzleBothB.hashCode());
	}

	@Test
	public void joinedPuzzlesWithDifferentContentsArentEqual() {
		JoinedPuzzle puzzle1 = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 3));
		JoinedPuzzle puzzle2 = new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 4));
		assertFalse(puzzle1.equals(puzzle2));
	}

	@Test
	public void joinedPuzzlesCanBeFlipped() {
		JoinedPuzzle puzzle = new JoinedPuzzle(
				new JoinedPuzzle(new IndentedWord("fizz"), new IndentedWord("soda", 1)), 
				new JoinedPuzzle(new IndentedWord("bang"), new IndentedWord("gar", 3)), 
				2);
		
		assertEquals(
				new JoinedPuzzle(
						new JoinedPuzzle(new IndentedWord("gar", 5), new IndentedWord("bang", 2)), 
						new JoinedPuzzle(new IndentedWord("soda", 1), new IndentedWord("fizz"))), 
				puzzle.inverted());
	}
	
	@Test
	public void contains() {
		JoinedPuzzle puzzle = new JoinedPuzzle(
				new JoinedPuzzle(new IndentedWord("foo"), new IndentedWord("bar", 1)), 
				new JoinedPuzzle(new IndentedWord("if"), new IndentedWord("flat", 1)), 
				2);
		
		assertTrue(puzzle.contains("bar"));
		assertTrue(puzzle.contains("flat"));
		assertFalse(puzzle.contains("oo"));
	}
}