public class IndentedWord extends Piece {
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

	public String word() {
		return word;
	}
	
	public int indent() {
		return indent;
	}

	public char at(int i) {
		if (i < indent) return '.';
		if (i < indent + word.length()) return word.charAt(i - indent);
		return '.';
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < indent; i++)
			result.append('.');
		result.append(word);
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof IndentedWord)) return false;
		
		IndentedWord that = (IndentedWord) obj;
		
		return this.word.equals(that.word) && this.indent == that.indent;
	}

	@Override
	public int hashCode() {
		return word.hashCode() ^ indent;
	}

	public IndentedWord shiftRight(int distance) {
		if (indent + distance < 0)							//TODO Do we care?
			throw new UnsupportedOperationException("Can't shift OffsetWord left of offset 0");  
		return new IndentedWord(word, indent + distance);
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
	
	public int maxIndex() {
		return indent + word.length() - 1;
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

	public Piece flipped() {
		return this; 
	}
}
