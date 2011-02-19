import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;


public class TextBlock implements TextSection {

	//TODO: Move out to application level object?
	public static enum NameSource {
		APPLICATION,
		USER,
		IMPORTED
	}
	
	//TODO: Look at creating a PositionedItem class that implements TextSection and Offsettable
	//		Then we can just use an array instead of the map...
	
	private SectionName name;
	private LinkedHashMap<TextSection, Integer> textSections;
	
	public TextBlock() 
	{
		this(SectionName.defaultSystemName());
	}
	
	public TextBlock(SectionName blockName)
	{
		textSections = new LinkedHashMap<TextSection, Integer>();
		name = blockName;
	}
	
	public SectionName name()
	{
		return name;
	}
	
	public Boolean isEmpty()
	{
		return this.sections().isEmpty();
	}
	
	public void add(TextLine text, int offset)
	{
		textSections.put(text, offset);
	}
	
	public void remove(TextSection text)
	{
		if (textSections.containsKey(text))
			textSections.remove(text);
	}
	
	public Set<TextSection> sections()
	{
		return textSections.keySet();
	}

	@Override
	public int width() {
		if (textSections.size() == 0)
			return 0;
		
		int minPosition = 0;
		int maxPosition = Integer.MIN_VALUE;
		Iterator<Entry<TextSection, Integer>> sectionIterator = textSections.entrySet().iterator();
		while (sectionIterator.hasNext())
		{
			Entry<TextSection, Integer> positionedSection = sectionIterator.next();
			minPosition = Math.min(minPosition, positionedSection.getValue());
			maxPosition = Math.max(
					maxPosition, 
					positionedSection.getValue() + positionedSection.getKey().width());
		}
		return maxPosition - minPosition;
	}
}
