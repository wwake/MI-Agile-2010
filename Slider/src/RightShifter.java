import java.util.Arrays;

public class RightShifter extends Piece {
	private final Piece base;
	private final int amountToShiftRight;

	public RightShifter(Piece piece, int amountToShiftRight) {
		this.base = piece;
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
		return base.width();
	}

	@Override
	public int maxIndex() {
		return amountToShiftRight + base.maxIndex();
	}

	@Override
	public String column(int c) {
		if (c < amountToShiftRight) return repeat('.', base.height());
		return base.column(c - amountToShiftRight);
	}

	private String repeat(char ch, int count) {
		char[] chars = new char[count];
		Arrays.fill(chars, ch);
		return String.valueOf(chars);
	}

	@Override
	public IndentedWord get(int i) {
		return new IndentedWord(base.get(i), amountToShiftRight);
	}

	@Override
	public int height() {
		return base.height();
	}

	@Override
	public Piece flipped() {
		return new RightShifter(base.flipped(), amountToShiftRight);
	}

	@Override
	public boolean contains(String string) {
		return base.contains(string);
	}
}
