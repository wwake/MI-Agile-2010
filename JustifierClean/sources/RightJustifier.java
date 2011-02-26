import java.util.Vector;


public class RightJustifier extends Justifier {

	public RightJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {
		if (this.workingBlock().width() >= aLine.width())
		{
			this.addToResult(aLine, (this.workingBlock().width() - aLine.width()));
		}
		else
		{
			int offsetIncrease = aLine.width() - this.workingBlock().width();
			this.workingBlock().adjustAllOffsetsBy(offsetIncrease);
			this.addToResult(aLine, 0);
		}
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {
		int difference = this.workingBlock().width() - aBlock.width();
		boolean existingIsWider = (difference >= 0);
		
		if (!existingIsWider)
			this.workingBlock().adjustAllOffsetsBy(-difference);
		
		for (BlockEntry entry : aBlock.entries()) {
			if (existingIsWider) {
				this.addToResult(entry.line(), (entry.offset() + difference));
			}
			else {
				this.addToResult(entry.line(), entry.offset());
			}
		}
	}
}

/*  
 * Seems like the if & else clauses are similar enough there might be a way to fold them together.
 */ 
