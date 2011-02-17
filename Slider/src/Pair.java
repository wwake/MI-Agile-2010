public class Pair extends Cluster {
	private Cluster top;
	private Cluster bottom;

	public Pair(Cluster theTop, Cluster theBottom) {
		this.top = theTop;
		this.bottom = theBottom;
	}
	
	public Pair(Cluster theTop, Cluster theBottom, int distanceToRightShiftBottom) {
		if (distanceToRightShiftBottom < 0) 
			theTop = new RightShiftingCluster(theTop, -distanceToRightShiftBottom);
		else if (distanceToRightShiftBottom > 0)
			theBottom = new RightShiftingCluster(theBottom, distanceToRightShiftBottom);
		
		this.top = theTop;
		this.bottom = theBottom;
	}

	@Override
	public IndentedWord first() {
		return top.first();
	}

	@Override
	public IndentedWord last() {
		return bottom.last();
	}

	@Override
	public int width() {
		return Math.max(top.width(), bottom.width());
	}

	@Override
	public String column(int c) {
		return top.column(c).concat(bottom.column(c));
	}

	@Override
	public IndentedWord wordAt(int row) {
		if (row < top.height())
			return top.wordAt(row);
		return bottom.wordAt(row - top.height());
	}

	@Override
	public int height() {
		return top.height() + bottom.height();
	}

	@Override
	public Cluster inverted() {
		return new InvertingCluster(this);
	}

	@Override
	public boolean contains(String string) {
		return top.contains(string) || bottom.contains(string);
	}
}