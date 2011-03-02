import java.util.Vector;


public class TextLine implements TextSection {

	private String text;
	
	public TextLine(String theText) {
		text = theText;
	}

	@Override
	public int width() {
		return text.length();
	}
	
	@Override
	public String toString() {
		return text;
	}

	@Override
	public Vector<OffsetLine> entries() {
		Vector<OffsetLine> entries = new Vector<OffsetLine>(1);
		entries.add(new OffsetLine(this, 0));
		return entries;
	}
}
