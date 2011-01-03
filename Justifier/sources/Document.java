import java.util.Vector;


//Note: This corresponds to Seq. Contig

public class Document extends TextBlock {

	private TextLine.CreationType howCreated;
	private Settings settings;
	private Vector<TextBlock> textBlocks;
	
	public Document() 
	{
		super();
		settings = new Settings();
	}
	
	public void setHowCreated(TextLine.CreationType creationMethod)
	{
		howCreated = creationMethod;
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
		textBlocks.add(text);
		text.setOffset(offset);
	}
	
	public void remove(TextBlock text)
	{
		for (TextBlock currentBlock : textBlocks)
		{
			if (currentBlock.equals(text))
				textBlocks.remove(currentBlock);
		}
	}
	
	public Vector<TextBlock> blocks()
	{
		return textBlocks;
	}
	
	public Boolean IsDocument()
	{
		return true;
	}

	@Override
	int width() {
		if (textBlocks.size() == 0)
			return 0;
		
		int minPosition = Integer.MAX_VALUE;
		int maxPosition = Integer.MIN_VALUE;
		for (TextBlock currentBlock : textBlocks)
		{
			minPosition = Math.min(minPosition, currentBlock.offset());
			maxPosition = Math.max(maxPosition, currentBlock.offset() + currentBlock.width());
		}
		return maxPosition - minPosition + 1;
	}
}
