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
			this.addLongerLine(aLine, 0, offsetIncrease);
		}
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {
		for (BlockEntry entry : aBlock.entries())
		{
			if (this.workingBlock().width() >= aBlock.width())
			{
				int currentOffset = entry.offset();
				this.addShorterLine(entry.line(), currentOffset + (this.workingBlock().width() - aBlock.width()));
			}
			else
			{
				int offsetIncrease = aBlock.width() - this.workingBlock().width();
				this.addLongerLine(entry.line(), entry.offset(), offsetIncrease);
			}
		}
	}
	
	private void addShorterLine(TextLine line, int lengthDifference) {
		this.workingBlock().add(line, lengthDifference);
	}

	private void addLongerLine(TextLine line, int initialOffset, int amountToGrowResult) {
		for(BlockEntry resultEntry : this.workingBlock().entries())
		{
			resultEntry.changeOffset(resultEntry.offset() + amountToGrowResult);
		}
		this.workingBlock().add(line, initialOffset);
	}
}

/* This implementation totally surprised me. I thought it would be "run through everything to find max width, 
 * run through it again to slide things to the right." With this approach, won't you be readjusting 
 * lines multiple times?
 * 
 * Seems like the if & else clauses are similar enough there might be a way to fold them together.
 */ 
