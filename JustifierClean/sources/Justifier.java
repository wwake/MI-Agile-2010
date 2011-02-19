import java.util.Vector;


public abstract class Justifier {

	private TextBlock result;
	private Vector<TextSection> lines;

	public Justifier(Vector<TextSection> theLines)
	{
		lines = theLines;
	}

	public abstract void add(TextLine aLine);
	public abstract void addLinesFrom(TextBlock aBlock);
	
	public TextBlock newResult()
	{
		this.resetResultBlock();
		for (TextSection text : lines)
		{
			if (text.getClass() == TextLine.class)
			{
				this.add((TextLine)text);
			}
			else if (text.getClass() == TextBlock.class)
			{
				this.addLinesFrom((TextBlock)text);
			}
		}

		return result;
	}
	
	private void resetResultBlock()
	{
		result = new BlockBuilder().newBlockNamedFrom(lines);
	}
	
	protected TextBlock resultBlock()
	{
		if (result == null)
			this.resetResultBlock();
		
		return result;
	}
}
