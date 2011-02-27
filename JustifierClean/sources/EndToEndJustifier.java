import java.util.Vector;

public class EndToEndJustifier extends Justifier {

	public EndToEndJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void addSection(TextSection section) {
		int currentWidth = this.workingBlock().width();
		for (BlockEntry entry : section.entries()) {
			this.addToResult(entry.line(), entry.offset() + currentWidth);
		}
	}

}
