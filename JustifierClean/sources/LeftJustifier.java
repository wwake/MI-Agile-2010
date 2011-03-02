import java.util.Vector;


public class LeftJustifier extends Justifier {

	public LeftJustifier(Vector<TextSection> theLines) {
		super(theLines);
	}

	@Override
	public void addSection(TextSection section) {
		for (OffsetLine entry : section.entries()) {
			this.addToResult(entry.line(), entry.offset());
		}
	}

}
