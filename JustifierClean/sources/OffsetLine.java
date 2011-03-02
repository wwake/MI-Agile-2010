import java.util.Vector;


public class OffsetLine implements TextSection {

	private TextLine line;
	private int offset;
	
	public OffsetLine(TextLine aLine) {
		this(aLine, 0);
	}
	
	public OffsetLine(TextLine aLine, int blockOffset) {
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

	@Override
	public Vector<OffsetLine> entries() {
		Vector<OffsetLine> entries = new Vector<OffsetLine>();
		entries.add(this);
		return entries;
	}
}
