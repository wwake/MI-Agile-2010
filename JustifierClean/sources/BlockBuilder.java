import java.util.Vector;


public class BlockBuilder {

	
	public BlockBuilder()
	{
	}
	
	public TextBlock defaultSystemBlock()
	{
		return new TextBlock();
	}
	
	public SectionName deriveBestNameFrom(Vector<TextSection> textData)
	{
		SectionName importedName = null;
		
		for (TextSection currentSection : textData)
		{
			if (currentSection.getClass() == TextBlock.class)
			{
				TextBlock block = (TextBlock) currentSection;
				if (block.name().isFromUser())
				{
					return block.name();
				}
				else if (block.name().isFromImport() && (importedName == null))
				{
					importedName = block.name();
				}
			}
		}
		
		return (importedName != null) ? importedName : SectionName.defaultSystemName();
	}
	
	public TextBlock newBlockNamedFrom(Vector<TextSection> textData)
	{
		return new TextBlock(this.deriveBestNameFrom(textData));
	}
}
