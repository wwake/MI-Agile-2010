import java.util.Vector;


public class RightJustifier extends Justifier {

	public RightJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void add(TextLine aLine) {
		
		if (this.resultBlock().width() >= aLine.width())
		{
			this.addShorterLine(aLine, this.resultBlock().width() - aLine.width());
		}
		else
		{
			int offsetIncrease = aLine.width() - this.resultBlock().width();
			this.addLongerLine(aLine, 0, offsetIncrease);
		}
	}

	@Override
	public void addLinesFrom(TextBlock aBlock) {
		
		for (BlockEntry entry : aBlock.entries())
		{
			if (this.resultBlock().width() >= aBlock.width())
			{
				int currentOffset = entry.offset();
				this.addShorterLine(entry.line(), currentOffset + (this.resultBlock().width() - aBlock.width()));
			}
			else
			{
				int offsetIncrease = aBlock.width() - this.resultBlock().width();
				this.addLongerLine(entry.line(), entry.offset(), offsetIncrease);
			}
		}
	}
	
	private void addShorterLine(TextLine line, int lengthDifference)
	{
		this.resultBlock().add(line, lengthDifference);
	}

	private void addLongerLine(TextLine line, int initialOffset, int amountToGrowResult)
	{
		for(BlockEntry resultEntry : this.resultBlock().entries())
		{
			resultEntry.changeOffset(resultEntry.offset() + amountToGrowResult);
		}
		this.resultBlock().add(line, initialOffset);
	}
}

/* This implementation totally surprised me. I thought it would be "run through everything to find max width, 
 * run through it again to slide things to the right." With this approach, won't you be readjusting 
 * lines multiple times?
 * 
 * Seems like the if & else clauses are similar enough there might be a way to fold them together.
 */ 
