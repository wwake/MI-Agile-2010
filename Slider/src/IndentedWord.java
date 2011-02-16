public class IndentedWord extends Cluster {
	public final static char UNOCCUPIED = '.';
	
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
			return UNOCCUPIED;
		if (i < indent + word.length())
			return word.charAt(i - indent);
		return UNOCCUPIED;
	}

	@Override
	public String toString() {
		return repeat(UNOCCUPIED, indent) + word;
	}

	@Override
	public boolean equals(Object obj) {
		Cluster that = (Cluster) obj;
		
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

	public Cluster flipped() {
		return this;
	}

	@Override
	public boolean contains(String string) {
		return word.equals(string);
	}
}

/*
- Great example of why I don't like method parameters named the same as instance vars:
  from construtor taking an IndentedWord->  this.word = word.word;
  OK, which which is which?  :-)   
- Why the "finals" on the instance vars?  Just curious.
- Other than that, another really good class.
*/