import java.util.ArrayList;
import java.util.List;

public class Pool {
	private final PieceScorer scorer;

	List<Piece> pieces = new ArrayList<Piece>();
	
	public Pool(PieceScorer scorer) {
		this.scorer = scorer;
	}

	public void add(Piece piece) {
		pieces.add(piece);
	}

	public Piece best() {
		int bestScore = -1;
		Piece bestResult = get(0);
		
		for (int i = 0; i < pieces.size(); i++) 
			for (int j = i+1; j < pieces.size(); j++) {
				Pair bestCombo = bestCombo(this.get(i), this.get(j));
				if (scorer.score(bestCombo) > bestScore) {
					bestScore = scorer.score(bestCombo);
					bestResult = bestCombo;
				}
			}
		
		return bestResult;
	}

	private Pair bestCombo(Piece piece1, Piece piece2) {
		return new Pair(piece1, piece2, 0);
	}

	public int size() {
		return pieces.size();
	}

	public Piece get(int i) {
		return pieces.get(i);
	}
	
	public String toString() {		
		Object[] array = pieces.toArray();
		StringBuffer result = new StringBuffer();
		for (Object obj : array) {
			result.append(obj.toString());
			result.append('\n');
		}
		return result.toString();
	}
}
