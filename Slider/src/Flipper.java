
public class Flipper extends Piece {
	private final Piece base;

	public Flipper(Piece base) {
		this.base = base;
	}

	public IndentedWord first() {
		return base.last();
	}

	public IndentedWord last() {
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

	public IndentedWord get(int row) {
		return base.get(base.height() - 1 - row);
	}

	public int height() {
		return base.height();
	}

	public Piece flipped() {
		return base;
	}
}
