import java.util.Vector;


public class Runner {

	public static void main(String[] ignoredArgs)
	{
//		Runner.runJustifierWith(Runner.singleLinesData());
//		Runner.runJustifierWith(Runner.twoTextBlocks());
		Runner.runJustifierWith(Runner.mixOfLinesAndBlocks());
	}
	
	public static void runJustifierWith(Vector<TextSection> textData)
	{
		Justifier justifier = new Justifier(textData, Justifier.Arrangement.AllRight);
		justifier.execute();
		showOutput(justifier.resultBlock());
		
		justifier.resetResultDocument();
		justifier.changeArrangementTo(Justifier.Arrangement.AllLeft);
		justifier.execute();
		showOutput(justifier.resultBlock());
		
		justifier.resetResultDocument();
		justifier.changeArrangementTo(Justifier.Arrangement.EndToEnd);
		justifier.execute();
		showOutput(justifier.resultBlock());
	}
	
	public static void showOutput(TextBlock block)
	{
		for (TextSection line : block.sections())
		{
			for (int indent = 0; indent < line.offset(); indent++)
				System.out.print(' ');
			System.out.println(line.toString());
		}
		System.out.println();
	}

	public static Vector<TextSection> singleLinesData() {
		Vector<TextSection> lines = new Vector<TextSection>();
		lines.add(new TextLine("ABCDEFG"));
		lines.add(new TextLine("A line longer than the others"));
		lines.add(new TextLine("Don't know what to say"));
		return lines;
	}

	public static Vector<TextSection> twoTextBlocks() {
		Vector<TextSection> lines = new Vector<TextSection>();
		TextBlock block = new TextBlock();
		block.add(new TextLine("A line indented 4"), 4);
		block.add(new TextLine("Even more indented"), 7);
		block.add(new TextLine("This is not indented at all"), 0);
		lines.add(block);
		block = new TextBlock();
		block.add(new TextLine("12345"), 2);
		block.add(new TextLine("LMNOPQRST"), 0);
		lines.add(block);
		return lines;
	}
	
	public static Vector<TextSection> mixOfLinesAndBlocks()
	{
		Vector<TextSection> results = Runner.singleLinesData();
		Vector<TextSection> blockVector = Runner.twoTextBlocks();
		results.add(blockVector.firstElement());
		results.add(new TextLine("Just another line to mix things up"));
		results.add(blockVector.lastElement());
		return results;
	}
}
