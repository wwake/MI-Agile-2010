import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pool {
	private final PieceScorer scorer;

	List<Piece> pieces = new ArrayList<Piece>();
	
	public Pool(PieceScorer scorer) {
		this.scorer = scorer;
	}

	public void add(Piece piece) {
		pieces.add(piece);
	}

	public Pair best() {
		int bestScore = -1;
		Pair bestResult = null;
		
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

	public Pair bestCombo(Piece piece1, Piece piece2) {
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

	public void remove(Piece piece) {
		pieces.remove(piece);
	}

	public Set<Piece> allCombos(Pair pair1, Pair pair2) {
		Set<Piece> result = new HashSet<Piece>();
		result.add(new Pair(pair1, pair2, 0));
		result.add(new Pair(pair1, pair2.reversed(), 0));
		result.add(new Pair(pair1.reversed(), pair2, 0));
		result.add(new Pair(pair1.reversed(), pair2.reversed(), 0));
		return result;
	}

	public Piece bestIn(Set<Piece> pieces) {
		int bestScore = -1;
		Piece bestResult = null;
		
		for (Piece piece : pieces) {
			int score = scorer.score(piece);
			if (score > bestScore) {
				bestResult = piece;
				bestScore = score;
			}
		}
		
		return bestResult;
	}
}
