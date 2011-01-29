import java.util.Vector;


public class Runner {

	public static void main(String[] ignoredArgs)
	{
		Vector<TextSection> lines = singleLinesData();
		Justifier justifier = new Justifier(lines, Justifier.Arrangement.AllLeft);
		justifier.execute();
		showOutput(justifier.resultBlock());
		
		justifier.resetResultDocument();
		justifier.changeArrangementTo(Justifier.Arrangement.AllRight);
		justifier.execute();
		showOutput(justifier.resultBlock());
		
		justifier.resetResultDocument();
		justifier.changeArrangementTo(Justifier.Arrangement.EndToEnd);
		justifier.execute();
		showOutput(justifier.resultBlock());
	}

	public static Vector<TextSection> singleLinesData() {
		Vector<TextSection> lines = new Vector<TextSection>();
		lines.add(new TextLine("ABCDEFG"));
		lines.add(new TextLine("Every Good Boy Does Fine"));
		lines.add(new TextLine("Don't know what to say"));
		return lines;
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
		System.out.println();
	}
}
