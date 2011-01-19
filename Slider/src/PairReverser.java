
public class PairReverser implements Piece {

	private final Pair base;

	public PairReverser(Pair base) {
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

	public int minIndex() {
		return base.minIndex();
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

	public Piece reversed() {
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
}
