public class IndentedWord extends Puzzle {
	public final static char UNOCCUPIED = '.';
	
	private final String word;
	private final int indent;

	public IndentedWord(String aWord) {
		this(aWord, 0);
	}

	public IndentedWord(String aWord, int anOffset) {
		this.word = aWord;
		this.indent = anOffset;
	}

	public IndentedWord(IndentedWord aWord, int moreIndent) {
		this.word = aWord.word;
		this.indent = aWord.indent + moreIndent;
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
			return UNOCCUPIED;
		if (i < indent + word.length())
			return word.charAt(i - indent);
		return UNOCCUPIED;
	}

	@Override
	public String toString() {
		return StringUtil.repeat(UNOCCUPIED, indent) + word;
	}

	@Override
	public boolean equals(Object obj) {
		Puzzle that = (Puzzle) obj;
		
		if (that.height() != 1) return false;
		
		IndentedWord thatWord = that.wordAt(0);
		return this.word.equals(thatWord.word) && this.indent == thatWord.indent;
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

	public IndentedWord wordAt(int i) {
		return this;
	}

	public int height() {
		return 1;
	}

	public Puzzle inverted() {
		return this;
	}

	@Override
	public boolean contains(String string) {
		return word.equals(string);
	}
}