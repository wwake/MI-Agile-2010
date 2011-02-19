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
		
		for (BlockEntry entry : aBlock.sections())
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
		for(BlockEntry resultEntry : this.resultBlock().sections())
		{
			resultEntry.changeOffset(resultEntry.offset() + amountToGrowResult);
		}
		this.resultBlock().add(line, initialOffset);
	}
}
