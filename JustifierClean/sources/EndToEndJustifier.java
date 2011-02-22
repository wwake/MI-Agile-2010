import java.util.Vector;


public class EndToEndJustifier extends Justifier {

	public EndToEndJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {
		this.workingBlock().add(aLine, this.workingBlock().width());
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {
		int currentWidth = this.workingBlock().width();
		for (BlockEntry entry : aBlock.entries())
		{
			this.workingBlock().add(entry.line(), entry.offset() + currentWidth);
		}
	}

}

/* 
 * You've got a little chaining there with this.resultBlock().add(...). Makes me wonder if you could have a 
 * version of this.add() that takes care of digging through the resultBlock. 
 * */
