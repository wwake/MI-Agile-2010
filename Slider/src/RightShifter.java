
public class RightShifter extends Cluster {
	private final Cluster base;
	private final int amountToShiftRight;

	public RightShifter(Cluster cluster, int amountToShiftRight) {
		this.base = cluster;
		this.amountToShiftRight = amountToShiftRight;
	}

	@Override
	public IndentedWord first() {
		return new IndentedWord(base.first(), amountToShiftRight);
	}

	@Override
	public IndentedWord last() {
		return new IndentedWord(base.last(), amountToShiftRight);
	}

	@Override
	public int width() {
		return amountToShiftRight + base.width();
	}

	@Override
	public String column(int c) {
		if (c < amountToShiftRight) return repeat('.', base.height());
		return base.column(c - amountToShiftRight);
	}

	@Override
	public IndentedWord get(int i) {
		return new IndentedWord(base.get(i), amountToShiftRight);
	}

	@Override
	public int height() {
		return base.height();
	}

	@Override
	public Cluster flipped() {
		return new RightShifter(base.flipped(), amountToShiftRight);
	}

	@Override
	public boolean contains(String string) {
		return base.contains(string);
	}
}
