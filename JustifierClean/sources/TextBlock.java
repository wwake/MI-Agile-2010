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
	
	public void setName(SectionName newName) {
		name = newName;
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
		for(BlockEntry entry : this.entries()) {
			minPosition = Math.min(minPosition, entry.offset());
			maxPosition = Math.max(maxPosition, entry.width());
		}
		return maxPosition - minPosition;
	}

	public void adjustAllOffsetsBy(int amount) {
		for(BlockEntry entry : this.entries()) {
			entry.changeOffset(entry.offset() + amount);
		}
	}
}
