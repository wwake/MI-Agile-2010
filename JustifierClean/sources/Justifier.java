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
		result = new BlockBuilder().newBlockNamedFrom(lines);
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
	
	protected TextBlock resultBlock()
	{
		return result;
	}
}
