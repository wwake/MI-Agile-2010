import java.util.Set;

public class PieceScorer {

	public int score(String string) {
		if (string.length() == 0) return 0;

		int result = 0;
		char runStart = '\0';
		int runLength = 0;

		for (int i = 0; i < string.length(); i++) {
			char current = string.charAt(i);
			if (current == '.') {
				runStart = '\0';
				runLength = 0;
				continue;
			}
			if (current != runStart) {
				runStart = current;
				runLength = 1;
				result += 1;
			} else {
				result -= square(runLength);
				runLength++;
				result += square(runLength);
			}
		}
		return result;
	}

	private int square(int arg) {
		return arg * arg;
	}

	public int score(Piece piece) {
		int result = 0;
		
		for (int i = 0; i < piece.width(); i++) 
			result += this.score(piece.column(i));

		return result;
	}

	public Piece bestIn(Set<Piece> pieces) {
		int bestScore = -1;
		Piece bestResult = null;
		
		for (Piece piece : pieces) {
			int score = score(piece);
			if (score > bestScore) {
				bestResult = piece;
				bestScore = score;
			}
		}
		
		return bestResult;
	}
}
