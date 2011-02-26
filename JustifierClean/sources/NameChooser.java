import java.util.Vector;


public class NameChooser {

	public SectionName deriveBestNameFrom(Vector<TextSection> textData) {
		SectionName importedName = null;
		
		for (TextSection currentSection : textData) {
			if (currentSection.getClass() != TextBlock.class)
				continue;
	
			TextBlock block = (TextBlock) currentSection;
			if (block.name().isFromUser()) {
				return block.name();
			}
			else if (block.name().isFromImport() && (importedName == null)) {
				importedName = block.name();
			}
		}
		
		return (importedName != null) ? importedName : SectionName.defaultSystemName();
	}
}
