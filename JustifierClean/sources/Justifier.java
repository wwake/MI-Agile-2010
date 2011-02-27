import java.util.Vector;


public abstract class Justifier {

	private TextBlock result;
	private Vector<TextSection> lines;

	public Justifier(Vector<TextSection> theLines)
	{
		lines = theLines;
	}

	public abstract void addSection(TextSection section);
	
	public void addToResult(TextLine line, int offset) {
		this.workingBlock().add(line, offset);
	}
	
	public TextBlock newResult()
	{
		this.resetResultBlock();
		for (TextSection text : lines) {
			this.addSection(text);
		}

		return result;
	}
	
	private void resetResultBlock()
	{
		result = new TextBlock();
	}
	
	protected TextBlock workingBlock()
	{
		if (result == null)
			this.resetResultBlock();
		
		return result;
	}
}
