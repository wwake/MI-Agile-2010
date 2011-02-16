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

	public IndentedWord wordAt(int row) {
		return base.wordAt(base.height() - 1 - row);
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
/*
- "base" is a confusing name for the instance variable (especially with syntax highlighting)
  I couldn't figure out why you kept calling the "base" class' methods when they were abstract.
  How about originalCluster?
- Maybe a better name for the class would be InvertedCluster?  Flipper sounds like an action to me
  where it feels more like a thing from its behavior.
- (personal preference) never pass parameter with same name as variable.
- if you decide to change the class name then maybe change flipped() to inverted()
*/