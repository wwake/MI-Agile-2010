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
	
	protected TextBlock workingBlock()
	{
		if (result == null)
			this.resetResultBlock();
		
		return result;
	}
}

/** 
 * I'm surprised to see the add vs. addLinesFrom stuff and the class checks.
 * Not sure why you need the mechanism around null checks etc. - really need two result returns? 
 **/