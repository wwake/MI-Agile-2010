import static org.junit.Assert.*;

import org.junit.Test;

public class _TestFormatter {
	Cluster cluster = new Cluster();
	Formatter formatter = new Formatter();

	@Test
	public void formatsEmptyCluster() {
		assertEquals("", formatter.format(cluster));
	}

	@Test
	public void formatsOneWordCluster() {
		cluster.add(new OffsetWord("word"));

		assertEquals("word\n", formatter.format(cluster));
	}

	@Test
	public void formatsTwoWords() {
		cluster.add(new OffsetWord("foo"));
		cluster.add(new OffsetWord("bar"));
		assertEquals("foo\nbar\n", formatter.format(cluster));
	}

	@Test
	public void formatsIndentedWord() {
		cluster.add(new OffsetWord("foo", 2));
		assertEquals("..foo\n", formatter.format(cluster));
	}

	@Test
	public void overlappingLettersPrintDash() {
		cluster.add(new OffsetWord("foo"));
		cluster.add(new OffsetWord("fob"));
		assertEquals("--o\n" + "--b\n", formatter.format(cluster));
	}

	@Test
	public void overlappingLettersPrintDashTakingOffsetsIntoAccount() {
		cluster.add(new OffsetWord("face", 0));
		cluster.add(new OffsetWord("ace", 1));
		assertEquals("f---\n" + ".---\n", formatter.format(cluster));
	}
}
