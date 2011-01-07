public class OffsetWord implements Piece {
	private final String word;
	private final int offset;

	public OffsetWord(String word) {
		this(word, 0);
	}

	public OffsetWord(String word, int offset) {
		this.word = word;
		this.offset = offset;
	}
	
	public OffsetWord(OffsetWord word, int moreOffset) {
		this.word = word.word;
		this.offset = word.offset + moreOffset;
	}

	public int length() {
		return offset + word.length();
	}

	public char at(int i) {
		if (i < offset) return '.';
		if (i < offset + word.length()) return word.charAt(i - offset);
		return '.';
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < offset; i++)
			result.append('.');
		result.append(word);
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		OffsetWord that = (OffsetWord) obj;
		return this.word.equals(that.word) && this.offset == that.offset;
	}

	@Override
	public int hashCode() {
		return word.hashCode() ^ offset;
	}

	public OffsetWord shiftRight(int distance) {
		if (offset + distance < 0)							//TODO Do we care?
			throw new UnsupportedOperationException("Can't shift Word left of offset 0");  
		return new OffsetWord(word, offset + distance);
	}

	public OffsetWord first() {
		return this;
	}

	public OffsetWord last() {
		return this;
	}

	public int minIndex() {
		return 0;
	}

	public int width() {
		return word.length();
	}
	
	public int maxIndex() {
		return offset + word.length() - 1;
	}

	public String column(int c) {
		return String.valueOf(this.at(c));
	}

	public Piece get(int i) {
		return this;
	}

	public int height() {
		return 1;
	}
}
