import java.util.Vector;

public class Justifier {

	public static enum Arrangement
	{
		AllLeft,
		AllRight,
		EndToEnd,
		None
	};
	
	private Library library;
	private Document result;
	private Vector<TextBlock> lines;
	private Arrangement arrangement = Arrangement.None;
	
	public Justifier(Library theLibrary, Vector<TextBlock> theLines, Arrangement howToJoin)
	{
		lines = theLines;
		arrangement = howToJoin;
		this.resetResultDocument();
	}
	
	public void resetResultDocument()
	{
		result = new Document();
		result.setHowCreated(TextLine.CreationType.AppCreated);
	}
	
	public Vector<TextBlock> selectedLines()
	{
		return lines;
	}
	
	public Library library()
	{
		return library;
	}
	
	public Document document()
	{
		return result;
	}
	
	public void execute()
	{
	    this.joinAllFrom(this.selectedLines(), this.document().settings().nextName());
	    this.library().input(this.document());
	}

	public void joinAllFrom(Vector<TextBlock> textToAlign, String defaultName)
	{
		this.nameResultDocumentUsing(textToAlign, defaultName);
	
		// Remove the Selected lines from the document if they are already in there
		for (TextBlock currentBlock : textToAlign)
			this.document().remove(currentBlock);
	
		for (TextBlock textBlock : textToAlign)
		{
			if (textBlock.IsLine())
			{
				this.addLine((TextLine)textBlock);
			}
			else if (textBlock.IsDocument())
			{
				addDocument((Document)textBlock);
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
	
	public void addDocument(Document bunchOfLines)
	{
		if (arrangement == Arrangement.AllLeft)
			this.leftJustify(bunchOfLines);
		else if (arrangement == Arrangement.AllRight)
			this.rightJustify(bunchOfLines);
		else if (arrangement == Arrangement.EndToEnd)
			this.spread(bunchOfLines);		
	}
	
	public void nameResultDocumentUsing(Vector<TextBlock> linesToAlign, String defaultName)
	{
		Vector<String> userGeneratedNames = new Vector<String>();
		Vector<String> appGeneratedNames = new Vector<String>();
		
		for (TextBlock textBlock : linesToAlign)
		{
			if (textBlock.IsDocument())
			{
				Document inputDoc = (Document) textBlock;
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
		for(TextBlock block : result.blocks())
		{
			block.setOffset(block.offset() + offsetAdjustment);
		}
	}

	void leftJustify(Document sourceDocument)
	{
	    this.joinTextBlocksFrom(sourceDocument, 0);
	}
	
	void rightJustify(Document sourceDocument)
	{
		int currentResultLength = result.width();
	 	int sourceLength = sourceDocument.width();
	
		int sourceAdjustmentForFinalLength = 0;
		if (currentResultLength < sourceLength)
	    {
		    adjustResultSequencesBy(sourceLength - currentResultLength);
	    }
		else
		{
			sourceAdjustmentForFinalLength = currentResultLength - sourceLength;
		}
	    joinTextBlocksFrom(sourceDocument, sourceAdjustmentForFinalLength);
	}

	void spread(Document sourceDocument)
	{
	    int originalLength = result.width();
	    this.joinTextBlocksFrom(sourceDocument, originalLength);
	}
	
	void joinTextBlocksFrom(Document sourceDocument, int offsetAdjustment)
	{
		for (TextBlock sourceBlock : sourceDocument.blocks())
		{
			sourceDocument.remove(sourceBlock);
			TextLine line = (TextLine) sourceBlock;
			result.add(line, offsetAdjustment + line.offset());
		}
	}
}

