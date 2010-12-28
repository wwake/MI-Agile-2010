
//Note: this corresponds to Sequence

public class TextLine extends TextBlock {

	public static enum CreationType {
		AppCreated,
		UserEntered,
		Imported
	}
	
	public TextLine()
	{}
	
	public Boolean IsLine()
	{
		return true;
	}
}
