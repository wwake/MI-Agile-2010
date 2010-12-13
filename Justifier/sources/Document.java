
//Note: This corresponds to Seq. Project

public class Document {

	private TextLine.LineType howCreated;
	private Settings settings;
	
	public Document()
	{
		settings = new Settings();
	}
	
	public void setHowCreated(TextLine.LineType theType)
	{
		howCreated = theType;
	}
	
	public Settings settings()
	{
		return settings;
	}
}
