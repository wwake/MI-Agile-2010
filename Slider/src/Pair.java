
public class Pair implements Piece {

	private Piece part1;
	private Piece part2;
	int offset;

	public Pair(String string1, String string2, int charsToLeft) {
		this(new OffsetWord(string1, 0), new OffsetWord(string2, 0), charsToLeft);
	}

	public Pair(Piece part1, Piece part2, int charsToLeft) {
		this.part1 = part1;
		this.part2 = part2;
		offset = charsToLeft;
	}

	public OffsetWord first() {
		return part1.first();
	}

	public OffsetWord last() {
		return new OffsetWord(part2.last(), offset);
	}

	public int minIndex() {
		return Math.min(part1.minIndex(), offset + part2.minIndex());
	}

	public int width() {
		return part1.width();
	}

	public int maxIndex() {
		return Math.max(part1.maxIndex(), offset + part2.maxIndex());
	}

	public String column(int c) {
		return part1.column(c).concat(part2.column(c - offset));
	}

	public Piece part1() {
		return part1;
	}

	public Piece part2() {
		return part2;
	}

	public OffsetWord get(int row) {
		if (row < part1.height()) return part1.get(row);
		return new OffsetWord(part2.get(row - part1.height()), offset);
	}

	public int height() {
		return part1.height() + part2.height();
	}
	
	@Override
	public String toString() {
		return part1.toString() + "\n" + part2.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Piece)) return false;
		
		Piece that = (Piece) obj;
		
		if (this.height() != that.height()) return false;
		for (int i = 0; i < height(); i++) {
			if (!this.get(i).equals(that.get(i)))
				return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < height(); i++)
			result ^= get(i).hashCode();
		return result;
	}

	public Piece reversed() {
		return new PairReverser(this);
	}
}
