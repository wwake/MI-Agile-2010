import java.util.Vector;


public class LeftJustifier extends Justifier {

	public LeftJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {

		this.resultBlock().add(aLine, 0);
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {
		
		for (BlockEntry entry : aBlock.entries())
		{
			this.resultBlock().add(entry.line(), 0);
		}
	}

}

/* Got extra blank lines that should probably just be deleted.
Not consistently formatted with the other files. 
*/