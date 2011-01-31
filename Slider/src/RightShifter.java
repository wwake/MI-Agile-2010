import java.util.Arrays;

public class RightShifter extends Piece {

	private final Piece piece;
	private final int amountToShiftRight;

	public RightShifter(Piece piece, int amountToShiftRight) {
		this.piece = piece;
		this.amountToShiftRight = amountToShiftRight;
	}

	@Override
	public OffsetWord first() {
		return new OffsetWord(piece.first(), amountToShiftRight);
	}

	@Override
	public OffsetWord last() {
		return new OffsetWord(piece.last(), amountToShiftRight);
	}

	@Override
	public int minIndex() {
		return 0;
	}

	@Override
	public int width() {
		return piece.width();
	}

	@Override
	public int maxIndex() {
		return amountToShiftRight + piece.maxIndex();
	}

	@Override
	public String column(int c) {
		if (c < amountToShiftRight) return repeat('.', piece.height());
		return piece.column(c - amountToShiftRight);
	}

	private String repeat(char ch, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ch);
		return String.valueOf(chars);
	}

	@Override
	public OffsetWord get(int i) {
		return new OffsetWord(piece.get(i), amountToShiftRight);
	}

	@Override
	public int height() {
		return piece.height();
	}

	@Override
	public Piece flipped() {
		return new RightShifter(piece.flipped(), amountToShiftRight);
	}
}
