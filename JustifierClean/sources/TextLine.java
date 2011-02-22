
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
}
