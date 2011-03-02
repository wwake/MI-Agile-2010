import static org.junit.Assert.*;

import org.junit.Test;

public class FormatterTest {
	Cluster cluster = new Cluster();
	Formatter formatter = new Formatter();

	@Test
	public void formatsEmptyCluster() {
		assertEquals("", formatter.format(cluster));
	}

	@Test
	public void formatsOneWordCluster() {
		cluster.add(new IndentedWord("word"));

		assertEquals("word\n", formatter.format(cluster));
	}

	@Test
	public void formatsTwoWords() {
		cluster.add(new IndentedWord("foo"));
		cluster.add(new IndentedWord("bar"));
		assertEquals("foo\nbar\n", formatter.format(cluster));
	}

	@Test
	public void formatsIndentedWord() {
		cluster.add(new IndentedWord("foo", 2));
		assertEquals("..foo\n", formatter.format(cluster));
	}

	@Test
	public void overlappingLettersPrintDash() {
		cluster.add(new IndentedWord("foo"));
		cluster.add(new IndentedWord("fob"));
		assertEquals("--o\n" + "--b\n", formatter.format(cluster));
	}

	@Test
	public void overlappingLettersPrintDashTakingOffsetsIntoAccount() {
		cluster.add(new IndentedWord("face", 0));
		cluster.add(new IndentedWord("ace", 1));
		assertEquals("f---\n" + ".---\n", formatter.format(cluster));
	}
}
