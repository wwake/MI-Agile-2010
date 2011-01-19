
public interface Piece {
	OffsetWord first();
	OffsetWord last();
	int minIndex();
	int width();
	int maxIndex();
	String column(int c);
	OffsetWord get(int i);
	int height();
	Piece reversed();
}
