import static org.junit.Assert.*;
import org.junit.Test;

public class TextBlockTest {

	@Test
	public void testDefaultConstructorInitializesValues() {
		
		TextBlock block = new TextBlock();
		assertTrue("Should initialize an empty sections list", block.isEmpty());
		assertTrue(
				"name should be default system name", 
				block.name().toString().startsWith(SectionName.DefaultNamePrefix));
		assertTrue("name should be app generated", block.name().isApplicationGenerated());
	}
	
	@Test
	public void testConstructorWithNameSetsGivenName() {
		
		SectionName blockName = SectionName.importedNameFrom("import");
		TextBlock block = new TextBlock(blockName);
		assertTrue("Should initialize an empty sections list", block.isEmpty());
		assertSame(blockName, block.name());
	}
	
	@Test
	public void testIsEmptyIsTrueIfItContainsNoLines()
	{
		assertTrue(new TextBlock().isEmpty());
	}
	
	@Test
	public void testIsEmptyIsFalseIfItContainsAnyLines()
	{
		TextBlock block = new TextBlock();
		block.add(new TextLine(""), 0); // there doesn't need to be anything in the line
		assertFalse(block.isEmpty());
	}

	@Test
	public void testAdd() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		block.add(aLine, 0);
		assertEquals(1, block.sections().size());
		assertEquals(
				"line value should be the same", 
				aLine.toString(), 
				block.sections().firstElement().line().toString());

		block.add(new TextLine("other"), 0);
		assertEquals(2, block.sections().size());
	}
	
	@Test
	public void testAddUpdatesOffsetOnAddedLine() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		block.add(aLine, 33);
		assertEquals(1, block.sections().size());
		assertEquals(33, block.sections().firstElement().offset());
	}

	@Test
	public void testWidthIsZeroIfBlockIsEmpty() {
		
		assertEquals(0, new TextBlock().width());
	}
	
	@Test
	public void testWidthEqualsTheWidthOfContainedTextIfOnlySingleLine()
	{
		TextLine line = new TextLine("12345");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		assertEquals(5, block.width());
	}
	
	@Test
	public void testWidthIncorporatesOffsetsInDeterminingValue()
	{
		TextLine line = new TextLine("12345");
		TextLine lineOffsetRight = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		block.add(lineOffsetRight, 100);
		assertEquals(103, block.width());
	}
	
	@Test
	public void testWidthAccountsForNegativeOffsets()
	{
		TextLine line = new TextLine("1");
		TextLine lineOffsetNegative = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		block.add(lineOffsetNegative, -3);
		assertEquals(4, block.width());
	}
	
	@Test
	public void testWidthStartsAtZeroIfAllContainedLinesHavePositiveOffsets()
	{
		TextLine line1 = new TextLine("1");
		TextLine line2 = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line1, 5);
		block.add(line2, 4);
		assertEquals(7, block.width());
	}
}
