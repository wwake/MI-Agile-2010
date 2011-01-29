import java.util.Vector;


//Note: This corresponds to Seq. Contig

public class TextBlock extends TextSection {

	private TextLine.CreationType howCreated;
	private Settings settings;
	private Vector<TextSection> textSections;
	
	public TextBlock() 
	{
		super();
		settings = new Settings();
		textSections = new Vector<TextSection>();
		howCreated = TextLine.CreationType.AppCreated;
	}
	
	public void setHowCreated(TextLine.CreationType creationMethod)
	{
		howCreated = creationMethod;
	}
	
	public TextLine.CreationType creationType(){
		
		return howCreated;
	}
	
	public Boolean isNameUserCreated()
	{
		return howCreated == TextLine.CreationType.UserEntered;
	}
	
	public Settings settings()
	{
		return settings;
	}
	
	public void add(TextLine text, int offset)
	{
		textSections.add(text);
		text.setOffset(offset);
	}
	
	public void remove(TextSection text)
	{
		if (textSections.contains(text))
			textSections.remove(textSections);
	}
	
	public Vector<TextSection> sections()
	{
		return textSections;
	}
	
	//TODO: Change to isMultiLine and return result based on content (instead of type)
	public Boolean isBlock()
	{
		return true;
	}

	@Override
	int width() {
		if (textSections.size() == 0)
			return 0;
		
		int minPosition = Integer.MAX_VALUE;
		int maxPosition = Integer.MIN_VALUE;
		for (TextSection current : textSections)
		{
			minPosition = Math.min(minPosition, current.offset());
			maxPosition = Math.max(maxPosition, current.offset() + current.width());
		}
		return maxPosition - minPosition;
	}
}
