
public class Pair extends Piece {

	private Piece part1;
	private Piece part2;
	int offset;

	public Pair(String string1, String string2, int blanksToLeftOfString2) {
		this(new OffsetWord(string1, 0), new OffsetWord(string2, 0), blanksToLeftOfString2);
	}

	public Pair(Piece part1, Piece part2, int charsToLeft) {
		this.part1 = part1;
		this.part2 = part2;
		offset = charsToLeft;
		if (charsToLeft > 0)
			this.part2 = new RightShifter(part2, charsToLeft);
		else if (charsToLeft < 0)
			this.part1 = new RightShifter(part1, -charsToLeft);
		offset = 0;
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
		StringBuffer result = new StringBuffer();
		result.append(get(0));
		
		for (int i = 1; i < height(); i++) {
			result.append('\n');
			result.append(get(i));
		}
		return result.toString();
	}
	
	public Piece flipped() {
		return new PieceFlipper(this);
	}
}
