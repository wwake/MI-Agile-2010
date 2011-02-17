public class WrappingCluster extends Cluster {

	protected final Cluster originalCluster;

	public WrappingCluster(Cluster clusterToWrap) {
		originalCluster = clusterToWrap;
	}

	@Override
	public IndentedWord first() {
		return originalCluster.first();
	}

	@Override
	public IndentedWord last() {
		return originalCluster.last();
	}

	@Override
	public int width() {
		return originalCluster.width();
	}

	@Override
	public int height() {
		return originalCluster.height();
	}

	@Override
	public String column(int c) {
		return originalCluster.column(c);
	}

	@Override
	public IndentedWord wordAt(int i) {
		return originalCluster.wordAt(i);
	}

	@Override
	public Cluster inverted() {
		return originalCluster.inverted();
	}

	@Override
	public boolean contains(String string) {
		return originalCluster.contains(string);
	}
}