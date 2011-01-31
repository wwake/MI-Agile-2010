
public class Flipper extends Piece {
	private final Piece base;

	public Flipper(Piece base) {
		this.base = base;
	}

	public OffsetWord first() {
		return base.last();
	}

	public OffsetWord last() {
		return base.first();
	}

	public int width() {
		return base.width();
	}

	public int maxIndex() {
		return base.maxIndex();
	}

	public String column(int c) {
		return new StringBuffer(base.column(c)).reverse().toString();
	}

	public OffsetWord get(int row) {
		return base.get(base.height() - 1 - row);
	}

	public int height() {
		return base.height();
	}

	public Piece flipped() {
		return base;
	}

	@Override
	public String toString() {
		if (height() == 0) return "";
		StringBuffer result = new StringBuffer();
		result.append(get(0));
		for (int i = 1; i < height(); i++) {
			result.append('\n');
			result.append(get(i));
		}
		return result.toString();
	}
}
