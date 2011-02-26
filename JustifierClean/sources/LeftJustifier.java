import java.util.Vector;


public class LeftJustifier extends Justifier {

	public LeftJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {
		this.addToResult(aLine, 0);
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {		
		for (BlockEntry entry : aBlock.entries())
		{
			this.addToResult(entry.line(), entry.offset());
		}
	}

}
