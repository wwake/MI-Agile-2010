
public class RightShiftingPuzzle extends WrappingPuzzle {
	private final int amountToShift;

	public RightShiftingPuzzle(Puzzle clusterToWrap, int theAmountToShiftRight) {
		super(clusterToWrap);
		this.amountToShift = theAmountToShiftRight;
	}

	@Override
	public IndentedWord first() {
		return new IndentedWord(originalCluster.first(), amountToShift);
	}

	@Override
	public IndentedWord last() {
		return new IndentedWord(originalCluster.last(), amountToShift);
	}

	@Override
	public int width() {
		return amountToShift + originalCluster.width();
	}

	@Override
	public String column(int c) {
		if (c < amountToShift) 
			return StringUtil.repeat('.', originalCluster.height());
		
		return originalCluster.column(c - amountToShift);
	}

	@Override
	public IndentedWord wordAt(int i) {
		return new IndentedWord(originalCluster.wordAt(i), amountToShift);
	}

	@Override
	public Puzzle inverted() {
		return new RightShiftingPuzzle(originalCluster.inverted(), amountToShift);
	}
}