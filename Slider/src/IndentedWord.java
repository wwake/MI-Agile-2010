public class IndentedWord extends Cluster {
	private final String word;
	private final int indent;

	public IndentedWord(String word) {
		this(word, 0);
	}

	public IndentedWord(String word, int offset) {
		this.word = word;
		this.indent = offset;
	}

	public IndentedWord(IndentedWord word, int moreIndent) {
		this.word = word.word;
		this.indent = word.indent + moreIndent;
	}

	public IndentedWord shiftRight(int distance) {
		return new IndentedWord(this, distance);
	}

	public String word() {
		return word;
	}

	public int indent() {
		return indent;
	}

	public char at(int i) {
		if (i < indent)
			return '.';
		if (i < indent + word.length())
			return word.charAt(i - indent);
		return '.';
	}

	@Override
	public String toString() {
		return repeat('.', indent) + word;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IndentedWord))
			return false;

		IndentedWord that = (IndentedWord) obj;

		return this.word.equals(that.word) && this.indent == that.indent;
	}

	@Override
	public int hashCode() {
		return word.hashCode() ^ indent;
	}

	public IndentedWord first() {
		return this;
	}

	public IndentedWord last() {
		return this;
	}

	public int width() {
		return indent + word.length();
	}

	public String column(int c) {
		return String.valueOf(this.at(c));
	}

	public IndentedWord get(int i) {
		return this;
	}

	public int height() {
		return 1;
	}

	public Cluster flipped() {
		return this;
	}

	@Override
	public boolean contains(String string) {
		return word.equals(string);
	}
}