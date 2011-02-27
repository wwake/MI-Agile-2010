public class InvertingPuzzle extends WrappingPuzzle {
	public InvertingPuzzle(Puzzle clusterToWrap) {
		super(clusterToWrap);
	}

	@Override
	public IndentedWord first() {
		return originalCluster.last();
	}

	@Override
	public IndentedWord last() {
		return originalCluster.first();
	}

	@Override
	public String column(int c) {
		return new StringBuffer(originalCluster.column(c)).reverse().toString(); 
	}

	@Override
	public IndentedWord wordAt(int row) {
		return originalCluster.wordAt(originalCluster.height() - 1 - row);
	}

	@Override
	public Puzzle inverted() {
		return originalCluster;
	}	
}