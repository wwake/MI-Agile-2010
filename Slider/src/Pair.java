public class Pair extends Cluster {
	private Cluster part1;
	private Cluster part2;

	public Pair(Cluster part1, Cluster part2) {
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

	public Cluster part1() {
		return part1;
	}

	public Cluster part2() {
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

	public Cluster flipped() {
		return new Flipper(this);
	}

	@Override
	public boolean contains(String string) {
		return part1.contains(string) || part2.contains(string);
	}
}
