import java.util.Vector;


public class EndToEndJustifier extends Justifier {

	public EndToEndJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {
		this.addToResult(aLine, this.workingBlock().width());
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {
		int currentWidth = this.workingBlock().width();
		for (BlockEntry entry : aBlock.entries())
		{
			this.addToResult(entry.line(), entry.offset() + currentWidth);
		}
	}

}
