public class InvertedPuzzle extends WrappingPuzzle {
	public InvertedPuzzle(Puzzle puzzleToWrap) {
		super(puzzleToWrap);
	}

	@Override
	public IndentedWord first() {
		return originalPuzzle.last();
	}

	@Override
	public IndentedWord last() {
		return originalPuzzle.first();
	}

	@Override
	public String column(int c) {
		return new StringBuffer(originalPuzzle.column(c)).reverse().toString(); 
	}

	@Override
	public IndentedWord wordAt(int row) {
		return originalPuzzle.wordAt(originalPuzzle.height() - 1 - row);
	}

	@Override
	public Puzzle inverted() {
		return originalPuzzle;
	}	
}