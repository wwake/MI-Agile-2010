import java.util.Vector;


public class Runner {

	public static void main(String[] ignoredArgs)
	{
//		Runner.runJustifierWith(Runner.singleLinesData());
//		Runner.runJustifierWith(Runner.twoTextBlocks());
		Runner.runJustifierWith(Runner.mixOfLinesAndBlocks());
	}
	
	public static void runJustifierWith(Vector<TextSection> textData){
		Justifier justifier = new RightJustifier(textData);
		showOutput(justifier.newResult());
		
		justifier = new LeftJustifier(textData);
		showOutput(justifier.newResult());
		
		justifier = new EndToEndJustifier(textData);
		showOutput(justifier.newResult());
	}
	
	public static void showOutput(TextBlock block)
	{
		for (BlockEntry entry : block.entries())
		{
			for (int indent = 0; indent < entry.offset(); indent++)
				System.out.print(' ');
			System.out.println(entry.line().toString());
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
