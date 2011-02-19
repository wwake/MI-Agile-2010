import java.util.Vector;


public class EndToEndJustifier extends Justifier {

	public EndToEndJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {

		this.resultBlock().add(aLine, this.resultBlock().width());
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {
		
		int currentWidth = this.resultBlock().width();
		for (BlockEntry entry : aBlock.sections())
		{
			this.resultBlock().add(entry.line(), entry.offset() + currentWidth);
		}
	}

}
