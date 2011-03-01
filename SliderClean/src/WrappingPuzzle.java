public class WrappingPuzzle extends Puzzle {

	protected final Puzzle originalPuzzle;

	public WrappingPuzzle(Puzzle clusterToWrap) {
		originalPuzzle = clusterToWrap;
	}

	@Override
	public IndentedWord first() {
		return originalPuzzle.first();
	}

	@Override
	public IndentedWord last() {
		return originalPuzzle.last();
	}

	@Override
	public int width() {
		return originalPuzzle.width();
	}

	@Override
	public int height() {
		return originalPuzzle.height();
	}

	@Override
	public String column(int c) {
		return originalPuzzle.column(c);
	}

	@Override
	public IndentedWord wordAt(int i) {
		return originalPuzzle.wordAt(i);
	}

	@Override
	public Puzzle inverted() {
		return originalPuzzle.inverted();
	}

	@Override
	public boolean contains(String string) {
		return originalPuzzle.contains(string);
	}
}