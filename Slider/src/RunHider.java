
public class RunHider extends Cluster {

	private final Cluster base;

	public RunHider(Cluster base) {
		this.base = base;
	}

	@Override
	public IndentedWord first() {
		return base.first();
	}

	@Override
	public IndentedWord last() {
		return base.last();
	}

	@Override
	public int width() {
		return base.width();
	}

	@Override
	public String column(int c) {
		return base.column(c);
	}

	@Override
	public IndentedWord wordAt(int i) {
		IndentedWord word = base.wordAt(i);
		return word;
	}

	@Override
	public int height() {
		return base.height();
	}

	@Override
	public Cluster flipped() {
		return base.flipped();
	}

	@Override
	public boolean contains(String string) {
		return base.contains(string);
	}
}
