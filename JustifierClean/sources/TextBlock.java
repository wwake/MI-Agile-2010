import java.util.Vector;


public class TextBlock implements TextSection {

	private SectionName name;
	private Vector<BlockEntry> textSections;
	
	public TextBlock()  {
		this(SectionName.defaultSystemName());
	}
	
	public TextBlock(SectionName blockName) {
		textSections = new Vector<BlockEntry>();
		name = blockName;
	}
	
	public SectionName name() {
		return name;
	}
	
	public boolean isEmpty() {
		return this.entries().isEmpty();
	}
	
	public void add(TextLine text, int offset) {
		BlockEntry entry = new BlockEntry(text, offset);
		textSections.add(entry);
	}
	
	public Vector<BlockEntry> entries() {
		return textSections;
	}

	@Override
	public int width()  {
		if (this.isEmpty())
			return 0;
		
		int minPosition = 0;  // if any offset goes negative use the neg as minimum, otherwise 0
		int maxPosition = Integer.MIN_VALUE;
		for(BlockEntry entry : this.entries())
		{
			minPosition = Math.min(minPosition, entry.offset());
			maxPosition = Math.max(maxPosition, entry.width());
		}
		return maxPosition - minPosition;
	}
}

/* 
 * 
 * This width() stuff is a bit confusing. Maybe the word "entry" isn't helping. 
 * (I still don't understand BlockEntry vs. TextBlock vs anything else.)
 * 
 * I'm not clear on why you're setting minPosition=0 rather than say Integer.MAX_VALUE.
 * (Could you have a text block with an positive offset?)
 */
