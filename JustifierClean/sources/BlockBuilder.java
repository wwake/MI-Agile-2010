
public class BlockBuilder {

	private Settings nameGenerator;
	
	public BlockBuilder(Settings theSettings)
	{
		nameGenerator = theSettings;
	}
	
	public TextBlock newBlock()
	{
		return new TextBlock();
		
	}
}
