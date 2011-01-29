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
		arrangement = howToJoin;
		this.resetResultDocument();
	}
	
	public void changeArrangementTo(Arrangement newJoinMethod)
	{
		arrangement = newJoinMethod;
	}
	
	public void resetResultDocument()
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
		this.nameResultDocumentUsing(textToAlign, defaultName);
	
		// Remove the Selected lines from the document if they are already in there
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
	
	public void addBlock(TextBlock bunchOfLines)
	{
		if (arrangement == Arrangement.AllLeft)
			this.leftJustify(bunchOfLines);
		else if (arrangement == Arrangement.AllRight)
			this.rightJustify(bunchOfLines);
		else if (arrangement == Arrangement.EndToEnd)
			this.spread(bunchOfLines);		
	}
	
	public void nameResultDocumentUsing(Vector<TextSection> linesToAlign, String defaultName)
	{
		Vector<String> userGeneratedNames = new Vector<String>();
		Vector<String> appGeneratedNames = new Vector<String>();
		
		for (TextSection textBlock : linesToAlign)
		{
			if (textBlock.isBlock())
			{
				TextBlock inputDoc = (TextBlock) textBlock;
				if (inputDoc.isNameUserCreated())
				{
					userGeneratedNames.add(inputDoc.getName());
				}
				else
				{
					appGeneratedNames.add(inputDoc.getName());
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

	void adjustResultSequencesBy(int offsetAdjustment)
	{
		for(TextSection block : result.sections())
		{
			block.setOffset(block.offset() + offsetAdjustment);
		}
	}

	void leftJustify(TextBlock sourceBlock)
	{
	    this.joinContentsOf(sourceBlock, 0);
	}
	
	void rightJustify(TextBlock sourceBlock)
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

	void spread(TextBlock sourceBlock)
	{
	    int currentWidth = result.width();
	    this.joinContentsOf(sourceBlock, currentWidth);
	}
	
	void joinContentsOf(TextBlock sourceBlock, int offsetAdjustment)
	{
		for (TextSection sourceItem : sourceBlock.sections())
		{
			TextLine line = (TextLine) sourceItem;
			result.add(line, offsetAdjustment + line.offset());
		}
	}
}

