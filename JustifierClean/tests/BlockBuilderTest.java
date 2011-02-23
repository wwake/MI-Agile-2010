import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

public class BlockBuilderTest {

	@Test
	public void deriveBestNameFromReturnsDefaultSystemNameIfListContainsNoBlocks()  {
		Vector<TextSection> data = new Vector<TextSection>();
		BlockBuilder builder = new BlockBuilder();
		SectionName bestName = builder.deriveBestNameFrom(data);
		assertTrue(bestName.toString().startsWith(SectionName.DefaultNamePrefix));
		assertTrue(bestName.isApplicationGenerated());
		
		TextLine aLine = new TextLine("lkjfdhkdhfsdlkjah");
		data.add(aLine);
		bestName = builder.deriveBestNameFrom(data);
		assertTrue(bestName.toString().startsWith(SectionName.DefaultNamePrefix));
		assertTrue(bestName.isApplicationGenerated());
	}
	
	@Test
	public void deriveBestNameFromReturnsNameFromFirstBlockWithUserGeneratedName() {
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
	public void deriveBestNameFromReturnsNameFromFirstBlockWithImportedNameIfNoUserGeneratedBlocks() {
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
	public void deriveBestNameFromReturnsNewSystemNameIfOnlyAppGeneratedBlocksInTheList() {
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
	public void newBlockNamedFromUsesDerivedName() {
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

/* 
 * I'm surprised most of the tests seem to be about names. I thought BlockBuilder would be building blocks. Is it a NameBuilder?
 */
