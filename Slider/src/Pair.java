public class Pair extends Piece {
	private Piece part1;
	private Piece part2;

	public Pair(Piece part1, Piece part2) {
		this.part1 = part1;
		this.part2 = part2;
	}

	public IndentedWord first() {
		return part1.first();
	}

	public IndentedWord last() {
		return part2.last();
	}

	public int width() {
		return Math.max(part1.width(), part2.width());
	}

	public String column(int c) {
		return part1.column(c).concat(part2.column(c));
	}

	public Piece part1() {
		return part1;
	}

	public Piece part2() {
		return part2;
	}

	public IndentedWord get(int row) {
		if (row < part1.height())
			return part1.get(row);
		return part2.get(row - part1.height());
	}

	public int height() {
		return part1.height() + part2.height();
	}

	public Piece flipped() {
		return new Flipper(this);
	}

	@Override
	public boolean contains(String string) {
		return part1.contains(string) || part2.contains(string);
	}
}
