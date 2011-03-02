import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pool {
	List<Piece> pieces = new ArrayList<Piece>();

	public void add(Piece piece) {
		pieces.add(piece);
	}

	public Set<Piece> candidates() {
		Set<Piece> candidates = new HashSet<Piece>();

		for (int i = 0; i < pieces.size(); i++)
			for (int j = i + 1; j < pieces.size(); j++)
				addCombo(candidates, this.get(i), this.get(j));

		return candidates;
	}

	public void addCombo(Set<Piece> candidates, Piece piece1, Piece piece2) {
		allCombos(candidates, piece1, piece2);
	}

	public void allCombos(Set<Piece> candidates, Piece piece1, Piece piece2) {
		this.allSlidePositions(candidates, piece1, piece2);
		this.allSlidePositions(candidates, piece1, piece2.flipped());
		this.allSlidePositions(candidates, piece1.flipped(), piece2);
		this.allSlidePositions(candidates, piece1.flipped(), piece2.flipped());
	}

	public void allSlidePositions(Set<Piece> result, Piece piece1, Piece piece2) {
		IndentedWord lastFromPiece1 = piece1.last();
		int offset1 = lastFromPiece1.indent();

		IndentedWord firstFromPiece2 = piece2.first();
		int offset2 = firstFromPiece2.indent();

		for (int i = 0; i < lastFromPiece1.width(); i++) {
			Pair pair = new Pair(piece1, new RightShifter(piece2, offset1 + i));
			result.add(pair);
		}

		for (int i = 1; i < firstFromPiece2.width(); i++) {
			int offset = offset2 - i;
			Pair pair = offset > 0 ? new Pair(piece1, new RightShifter(piece2, offset)) : new Pair(new RightShifter(piece1, -offset), piece2);
			result.add(pair);
		}
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
			result.append("\n");
		}
		return result.toString();
	}

	public void remove(Piece piece) {
		pieces.remove(piece);
	}

	public void remove(String string) {
		for (Piece piece : pieces) {
			if (piece.contains(string)) {
				remove(piece);
				return;
			}
		}
	}
}
