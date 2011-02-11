public class Flipper extends Cluster {
	private final Cluster base;

	public Flipper(Cluster base) {
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

	public String column(int c) {
		return new StringBuffer(base.column(c)).reverse().toString();
	}

	public IndentedWord get(int row) {
		return base.get(base.height() - 1 - row);
	}

	public int height() {
		return base.height();
	}

	public Cluster flipped() {
		return base;
	}
	
	@Override
	public boolean contains(String string) {
		return base.contains(string);
	}
}
