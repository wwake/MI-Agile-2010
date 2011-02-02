import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pool {
	List<Piece> pieces = new ArrayList<Piece>();
	
	public void add(Piece piece) {
		pieces.add(piece);
	}

	public Set<Piece> best() {
		Set<Piece> possibilities = new HashSet<Piece>();
		
		for (int i = 0; i < pieces.size(); i++) 
			for (int j = i+1; j < pieces.size(); j++) 
				addCombo(possibilities, this.get(i), this.get(j));
		
		return possibilities;
	}

	public void addCombo(Set<Piece> possibilities, Piece piece1, Piece piece2) {
		Pair pair = new Pair(piece1, piece2, 0);
		possibilities.add(pair);
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
		result.add(new Pair(pair1, pair2.flipped(), 0));
		result.add(new Pair(pair1.flipped(), pair2, 0));
		result.add(new Pair(pair1.flipped(), pair2.flipped(), 0));
		return result;
	}

	public void allSlidePositions(Piece piece1, Piece piece2, Set<Piece> result) {
		IndentedWord lastFromPiece1 = piece1.last();
		int offset1 = lastFromPiece1.indent();

		IndentedWord firstFromPiece2 = piece2.first();
		int offset2 = firstFromPiece2.indent();
		
		for (int i = 0; i < lastFromPiece1.width(); i++) {
			Pair pair = new Pair(piece1, piece2, offset1 + i);
			result.add(pair);
		}
		
		for (int i = 1; i < firstFromPiece2.width(); i++) {
			Pair pair = new Pair(piece1, piece2, offset2 - i);
			result.add(pair);
		}
	}

}
