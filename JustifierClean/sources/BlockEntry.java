
public class BlockEntry implements TextSection {

	private TextLine line;
	private int offset;
	
	public BlockEntry(TextLine aLine) {
		this(aLine, 0);
	}
	
	public BlockEntry(TextLine aLine, int blockOffset) {
		line = aLine;
		offset = blockOffset;
	}
	
	@Override
	public int width() {
		return offset + line.width();
	}

	public int offset() {
		return offset;
	}
	
	public void changeOffset(int newOffset) {
		offset = newOffset;
	}
	
	public TextLine line() {
		return line;
	}
}
