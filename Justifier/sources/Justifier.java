import java.util.Vector;

public class Justifier {

	public static enum Arrangement
	{
		AllLeft,
		AllRight,
		EndToEnd,
		None
	};
	
	private TextBlock result;
	private Vector<TextSection> lines;
	private Arrangement arrangement = Arrangement.None;
	
	public Justifier(Vector<TextSection> theLines, Arrangement howToJoin)
	{
		lines = theLines;
		this.changeArrangementTo(howToJoin);
	}
	
	public void changeArrangementTo(Arrangement newJoinMethod)
	{
		arrangement = newJoinMethod;
		this.resetResult();
	}
	
	public void resetResult()
	{
		result = new TextBlock();
		result.setHowCreated(TextLine.CreationType.AppCreated);
	}
	
	public Vector<TextSection> selectedLines()
	{
		return lines;
	}
		
	public TextBlock resultBlock()
	{
		return result;
	}
	
	public void execute()
	{
	    this.joinAllFrom(this.selectedLines(), this.resultBlock().settings().nextName());
	}

	public void joinAllFrom(Vector<TextSection> textToAlign, String defaultName)
	{
		this.nameResultUsing(textToAlign, defaultName);
	
		// Remove the Selected lines from the result if they are already in there
		for (TextSection currentText : textToAlign)
			this.resultBlock().remove(currentText);
	
		for (TextSection textBlock : textToAlign)
		{
			if (textBlock.isLine())
			{
				this.addLine((TextLine)textBlock);
			}
			else if (textBlock.isBlock())
			{
				addBlock((TextBlock)textBlock);
			}
		}
	}
	
	public void addLine(TextLine aLine)
	{
		int offset = 0;  // arrangement == AllLeft
		int currentResultLength = result.width();
		if (arrangement == Arrangement.EndToEnd)
		{
			offset = currentResultLength;
		}
		else if (arrangement == Arrangement.AllRight)
		{
			if (currentResultLength >= aLine.width())
			{
				offset = currentResultLength - aLine.width();
			}
			else
			{
				adjustResultSequencesBy(aLine.width() - currentResultLength);
			}			
		}
		result.add(aLine, offset);		
	}
	
	public void addBlock(TextBlock aBlock)
	{
		if (arrangement == Arrangement.AllLeft)
			this.leftJustify(aBlock);
		else if (arrangement == Arrangement.AllRight)
			this.rightJustify(aBlock);
		else if (arrangement == Arrangement.EndToEnd)
			this.append(aBlock);		
	}
	
	public void nameResultUsing(Vector<TextSection> stuffToAlign, String defaultName)
	{
		Vector<String> userGeneratedNames = new Vector<String>();
		Vector<String> appGeneratedNames = new Vector<String>();
		
		for (TextSection currentSection : stuffToAlign)
		{
			if (currentSection.isBlock())
			{
				TextBlock block = (TextBlock) currentSection;
				if (block.isNameUserCreated())
				{
					userGeneratedNames.add(block.getName());
				}
				else
				{
					appGeneratedNames.add(block.getName());
				}
			}
		}
		if (userGeneratedNames.size() > 0)
		{
			result.setName(userGeneratedNames.firstElement());
		}
		else if (appGeneratedNames.size() > 0)
		{
			result.setName(appGeneratedNames.firstElement());
		}
		else
		{
			result.setName(defaultName);
		}
	}

	protected void adjustResultSequencesBy(int offsetAdjustment)
	{
		for(TextSection resultLine : result.sections())
		{
			resultLine.setOffset(resultLine.offset() + offsetAdjustment);
		}
	}

	protected void leftJustify(TextBlock sourceBlock)
	{
	    this.joinContentsOf(sourceBlock, 0);
	}
	
	protected void rightJustify(TextBlock sourceBlock)
	{
		int currentResultLength = result.width();
	 	int sourceLength = sourceBlock.width();
	
		int sourceAdjustmentForFinalLength = 0;
		if (currentResultLength < sourceLength)
	    {
		    this.adjustResultSequencesBy(sourceLength - currentResultLength);
	    }
		else
		{
			sourceAdjustmentForFinalLength = currentResultLength - sourceLength;
		}
	    joinContentsOf(sourceBlock, sourceAdjustmentForFinalLength);
	}

	protected void append(TextBlock sourceBlock)
	{
	    int currentWidth = result.width();
	    this.joinContentsOf(sourceBlock, currentWidth);
	}
	
	protected void joinContentsOf(TextBlock sourceBlock, int offsetAdjustment)
	{
		for (TextSection sourceItem : sourceBlock.sections())
		{
			TextLine line = (TextLine) sourceItem;
			result.add(line, offsetAdjustment + line.offset());
		}
	}
}

