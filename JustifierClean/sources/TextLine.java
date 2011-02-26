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
	public Vector<BlockEntry> entries() {
		Vector<BlockEntry> entries = new Vector<BlockEntry>(1);
		entries.add(new BlockEntry(this, 0));
		return entries;
	}
}
