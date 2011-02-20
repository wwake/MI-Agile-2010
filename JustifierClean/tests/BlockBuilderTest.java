import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

public class BlockBuilderTest {

	@Test
	public void testDefaultSystemBlockHasApplicationGeneratedName()
	{
		BlockBuilder builder = new BlockBuilder();
		TextBlock block = builder.defaultSystemBlock();
		assertTrue(block.name().isApplicationGenerated());
	}

	@Test
	public void testDeriveBestNameFromReturnsDefaultSystemNameIfListContainsNoBlocks() 
	{
		Vector<TextSection> theLines = new Vector<TextSection>();
		BlockBuilder builder = new BlockBuilder();
		SectionName bestName = builder.deriveBestNameFrom(theLines);
		assertTrue(bestName.toString().startsWith(SectionName.DefaultNamePrefix));
		assertTrue(bestName.isApplicationGenerated());
		
		TextLine aLine = new TextLine("lkjfdhkdhfsdlkjah");
		theLines.add(aLine);
		bestName = builder.deriveBestNameFrom(theLines);
		assertTrue(bestName.toString().startsWith(SectionName.DefaultNamePrefix));
		assertTrue(bestName.isApplicationGenerated());
	}
	
	@Test
	public void testDeriveBestNameFromReturnsNameFromFirstBlockWithUserGeneratedName()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextBlock block = new TextBlock(SectionName.importedNameFrom("Imported Block"));
		data.add(block);
		block = new TextBlock(SectionName.defaultSystemName());
		data.add(block);
		block = new TextBlock(SectionName.userSuppliedNameFrom("User Block"));
		data.add(block);
		BlockBuilder builder = new BlockBuilder();
		SectionName bestName = builder.deriveBestNameFrom(data);
		assertEquals("User Block", bestName.toString());
		assertTrue(bestName.isFromUser());
	}
	
	@Test
	public void testDeriveBestNameFromReturnsNameFromFirstBlockWithImportedNameIfNoUserGeneratedBlocks()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextBlock block = new TextBlock(SectionName.defaultSystemName());
		data.add(block);
		block = new TextBlock(SectionName.importedNameFrom("Imported Block"));
		data.add(block);
		block = new TextBlock();
		data.add(block);
		BlockBuilder builder = new BlockBuilder();
		SectionName bestName = builder.deriveBestNameFrom(data);
		assertEquals("Imported Block", bestName.toString());
		assertTrue(bestName.isFromImport());
	}
	
	@Test
	public void testDeriveBestNameFromReturnsNewSystemNameIfOnlyAppGeneratedBlocksInTheList()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		SectionName existingBlockName = SectionName.defaultSystemName();
		TextBlock block = new TextBlock(existingBlockName);
		data.add(block);
		BlockBuilder builder = new BlockBuilder();
		SectionName bestName = builder.deriveBestNameFrom(data);
		assertTrue(bestName.isApplicationGenerated());
		assertFalse(existingBlockName.toString().equals(bestName.toString()));
	}
	
	@Test
	public void testNewBlockNamedFromUsesDerivedName()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextBlock block = new TextBlock(SectionName.importedNameFrom("Imported Block"));
		data.add(block);
		block = new TextBlock(SectionName.userSuppliedNameFrom("User Block"));
		data.add(block);
		BlockBuilder builder = new BlockBuilder();
		TextBlock newBlock = builder.newBlockNamedFrom(data);
		assertEquals("User Block", newBlock.name().toString());
	}
}

/* I'd say to pull out common @Before stuff. 
 * You're using "theLines" in one test.
 * 2d test looks like it could be split into two distinct tests.
 * I'm surprised most of the tests seem to be about names. I thought BlockBuilder would be building blocks. Is it a NameBuilder?
 */
