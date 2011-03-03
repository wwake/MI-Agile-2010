
public class Builder {
	private Pool pool;
	final PieceScorer scorer;

	public Builder(String[] strings, PieceScorer scorer) {
		this.scorer = scorer;
		pool = new Pool();
		for (String string : strings)
			pool.add(new IndentedWord(string));
	}

	public void build() {
		pool.build(scorer);
	}
	
	public String result() {
		return pool.toString();
	}
	
	public Piece finalPiece() {
		return pool.get(0);
	}
}
