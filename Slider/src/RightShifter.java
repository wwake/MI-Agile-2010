
public class RightShifter extends Cluster {
	private final Cluster base;
	private final int amountToShiftRight;

	public RightShifter(Cluster cluster, int amountToShiftRight) {
		this.base = cluster;
		this.amountToShiftRight = amountToShiftRight;
	}

	@Override
	public IndentedWord first() {
		return new IndentedWord(base.first(), amountToShiftRight);
	}

	@Override
	public IndentedWord last() {
		return new IndentedWord(base.last(), amountToShiftRight);
	}

	@Override
	public int width() {
		return amountToShiftRight + base.width();
	}

	@Override
	public String column(int c) {
		if (c < amountToShiftRight) return repeat('.', base.height());
		return base.column(c - amountToShiftRight);
	}

	@Override
	public IndentedWord wordAt(int i) {
		return new IndentedWord(base.wordAt(i), amountToShiftRight);
	}

	@Override
	public int height() {
		return base.height();
	}

	@Override
	public Cluster flipped() {
		return new RightShifter(base.flipped(), amountToShiftRight);
	}

	@Override
	public boolean contains(String string) {
		return base.contains(string);
	}
}

/*
- Again with "base" as a variable name.  Confusing (for someone doing C# for so long).
- Did you think about a common superclass like IndentedCluster that both this and IndentedWord 
  would inherit from (actually, I kinda like that name better for this class even if there isn't
  a common base.  Same as Flipper where this feels more like a noun than a verb)?  I didn't spend 
  a lot of time thinking about it, but it feels at first glance there's a lot of shared behavior 
  (and duplicated logic).  
- <personal preference> really don't like "return" on same line as "if" in column() method
*/
