
//Note: this corresponds to Sequence

public class TextLine extends TextSection {

	public static enum CreationType {
		AppCreated,
		UserEntered,
		Imported
	}
	
	private String text;
	
	public TextLine(String theText)
	{
		text = theText;
	}
	
	public Boolean isLine()
	{
		return true;
	}

	@Override
	public int width() {
		return text.length();
	}
	
	@Override
	public String toString()
	{
		return text;
	}
}
