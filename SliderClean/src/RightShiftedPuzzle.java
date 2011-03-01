
public class RightShiftedPuzzle extends WrappingPuzzle {
	private final int amountToShift;

	public RightShiftedPuzzle(Puzzle puzzleToWrap, int theAmountToShiftRight) {
		super(puzzleToWrap);
		this.amountToShift = theAmountToShiftRight;
	}

	@Override
	public IndentedWord first() {
		return new IndentedWord(originalPuzzle.first(), amountToShift);
	}

	@Override
	public IndentedWord last() {
		return new IndentedWord(originalPuzzle.last(), amountToShift);
	}

	@Override
	public int width() {
		return amountToShift + originalPuzzle.width();
	}

	@Override
	public String column(int c) {
		if (c < amountToShift) 
			return StringUtil.repeat('.', originalPuzzle.height());
		
		return originalPuzzle.column(c - amountToShift);
	}

	@Override
	public IndentedWord wordAt(int i) {
		return new IndentedWord(originalPuzzle.wordAt(i), amountToShift);
	}

	@Override
	public Puzzle inverted() {
		return new RightShiftedPuzzle(originalPuzzle.inverted(), amountToShift);
	}
}