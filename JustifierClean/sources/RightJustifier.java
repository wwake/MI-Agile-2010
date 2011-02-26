import java.util.Vector;


public class RightJustifier extends Justifier {

	public RightJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void addSection(TextSection section) {
		int difference = this.workingBlock().width() - section.width();
		boolean existingIsWider = (difference >= 0);
		
		if ( ! existingIsWider)
			this.workingBlock().adjustAllOffsetsBy(-difference);
		
		for (BlockEntry entry : section.entries()) {
			if (existingIsWider) {
				this.addToResult(entry.line(), (entry.offset() + difference));
			}
			else {
				this.addToResult(entry.line(), entry.offset());
			}
		}
	}
}
