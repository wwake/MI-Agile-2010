import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FormatterTest {
	Formatter formatter = new Formatter();

	@Test
	public void formatsOneWordCluster() {
		assertEquals("word\n", formatter.format(new IndentedWord("word")));
	}

	@Test
	public void formatsTwoWords() {
		assertEquals("foo\nbar\n", formatter.format(new Pair(new IndentedWord("foo"), new IndentedWord("bar"), 0)));
	}

	@Test
	public void formatsIndentedWord() {
		assertEquals("..foo\n", formatter.format(new IndentedWord("foo", 2)));
	}

	@Test
	public void firstAndLastOfRunAreMarked() {
		Pair pair = new Pair(
				new IndentedWord("f"),
				new IndentedWord("f"));
			
		assertEquals("/\n\\\n", formatter.format(pair));
	}
	
	@Test
	public void middleOfRunIsMarked() {
		IndentedWord wordF = new IndentedWord("f");
		Pair words = new Pair(wordF, new Pair(wordF, wordF));
			
		assertEquals("/\n|\n\\\n", formatter.format(words));
	}
	
	@Test
	public void runsMarkedInEachColumn() {
		Pair pair = new Pair(
			new IndentedWord("foo"),
			new IndentedWord("fob"));
		
		assertEquals("//o\n" + "\\\\b\n", formatter.format(pair));
	}

	@Test
	public void runsTakeOffsetsIntoAccount() {
		Pair pair = new Pair(
			new IndentedWord("face", 0),
			new IndentedWord("ace", 1));
		assertEquals("f///\n" + ".\\\\\\\n", formatter.format(pair));
	}
}
