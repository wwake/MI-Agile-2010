
public abstract class Piece {
	public abstract IndentedWord first();
	public abstract IndentedWord last();
	public abstract int width();
	public abstract int maxIndex();
	public abstract String column(int c);
	public abstract IndentedWord get(int i);
	public abstract int height();
	public abstract Piece flipped();
	
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
