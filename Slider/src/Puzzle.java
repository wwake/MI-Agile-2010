
public abstract class Puzzle {
	public abstract IndentedWord first();
	public abstract IndentedWord last();
	public abstract int width();
	public abstract String column(int c);
	public abstract IndentedWord wordAt(int i);
	public abstract int height();
	public abstract Puzzle inverted();
	public abstract boolean contains(String string);
		
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Puzzle)) return false;
		
		Puzzle that = (Puzzle) obj;
		
		if (this.height() != that.height()) return false;
		for (int i = 0; i < height(); i++) {
			if (!this.wordAt(i).equals(that.wordAt(i)))
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
		result.append(wordAt(0));
		for (int i = 1; i < height(); i++) {
			result.append('\n');
			result.append(wordAt(i));
		}
		return result.toString();
	}
}