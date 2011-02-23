import java.util.Vector;


public class BlockBuilder {

	public SectionName deriveBestNameFrom(Vector<TextSection> textData) {
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
	
	public TextBlock newBlockNamedFrom(Vector<TextSection> textData) {
		return new TextBlock(this.deriveBestNameFrom(textData));
	}
}

/** 
 * I'm not clear why you want the loop to keep going once you've found an import name.
 * Is the logic "if any block has a name, use it as the best name. Otherwise, use an import name if it exists, otherwise the default name.
 * Again, the getClass stuff. Might be in the original but feels out of place here. 
 * 
 * I commented more on SectionName. Feels funny to be querying block about its name; could it just know it?
 * Makes me wonder when the blcok name gets assigned. 
 */