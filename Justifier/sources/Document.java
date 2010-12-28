import java.util.Vector;


//Note: This corresponds to Seq. Contig

public class Document extends TextBlock {

	private TextLine.CreationType howCreated;
	private Settings settings;
	private Vector<TextLine> textLines;
	
	public Document() 
	{
		super();
		settings = new Settings();
	}
	
	public void setHowCreated(TextLine.CreationType theType)
	{
		howCreated = theType;
	}
	
	public Settings settings()
	{
		return settings;
	}
	
	public void remove(TextLine aLine)
	{
		for (TextLine line : textLines)
		{
			if (line.equals(aLine))
				textLines.remove(line);
		}
	}
	
	public Boolean IsDocument()
	{
		return true;
	}
}
