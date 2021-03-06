import java.util.Arrays;

public abstract class Piece {
	public abstract IndentedWord first();
	public abstract IndentedWord last();
	public abstract int width();
	public abstract String column(int c);
	public abstract IndentedWord get(int i);
	public abstract int height();
	public abstract Piece flipped();
	public abstract boolean contains(String string);
		
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
		return toString().hashCode();
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
	
	protected String repeat(char ch, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ch);
		return String.valueOf(chars);
	}
}
