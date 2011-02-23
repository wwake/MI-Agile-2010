import java.util.Vector;


public class RightJustifier extends Justifier {

	public RightJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {
		if (this.workingBlock().width() >= aLine.width())
		{
			this.addShorterLine(aLine, this.workingBlock().width() - aLine.width());
		}
		else
		{
			int offsetIncrease = aLine.width() - this.workingBlock().width();
			this.workingBlock().adjustAllOffsetsBy(offsetIncrease);
			this.workingBlock().add(aLine, 0);
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
				this.addShorterLine(entry.line(), entry.offset() + difference);
			}
			else {
				this.workingBlock().add(entry.line(), entry.offset());
			}
		}
	}
	
	private void addShorterLine(TextLine line, int lengthDifference) {
		this.workingBlock().add(line, lengthDifference);
	}
}

/* This implementation totally surprised me. I thought it would be "run through everything to find max width, 
 * run through it again to slide things to the right." With this approach, won't you be readjusting 
 * lines multiple times?
 * 
 * Seems like the if & else clauses are similar enough there might be a way to fold them together.
 */ 
