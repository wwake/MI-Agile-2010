import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Vector;


public class NameChooserTest {

	@Test
	public void deriveBestNameFromShouldBeDefaultSystemNameIfListContainsNoBlocks()  {
		Vector<TextSection> data = new Vector<TextSection>();
		NameChooser builder = new NameChooser();
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
	public void deriveBestNameFromShouldBeNameFromFirstBlockWithUserGeneratedName() {
		Vector<TextSection> data = new Vector<TextSection>();
		TextBlock block = new TextBlock(SectionName.importedNameFrom("Imported Block"));
		data.add(block);
		block = new TextBlock(SectionName.defaultSystemName());
		data.add(block);
		block = new TextBlock(SectionName.userSuppliedNameFrom("User Block"));
		data.add(block);
		NameChooser builder = new NameChooser();
		SectionName bestName = builder.deriveBestNameFrom(data);
		assertEquals("User Block", bestName.toString());
		assertTrue(bestName.isFromUser());
	}
	
	@Test
	public void deriveBestNameFromShouldBeNameFromFirstBlockWithImportedNameIfNoUserGeneratedBlocks() {
		Vector<TextSection> data = new Vector<TextSection>();
		TextBlock block = new TextBlock(SectionName.defaultSystemName());
		data.add(block);
		block = new TextBlock(SectionName.importedNameFrom("Imported Block"));
		data.add(block);
		block = new TextBlock();
		data.add(block);
		NameChooser builder = new NameChooser();
		SectionName bestName = builder.deriveBestNameFrom(data);
		assertEquals("Imported Block", bestName.toString());
		assertTrue(bestName.isFromImport());
	}
	
	@Test
	public void deriveBestNameFromShouldBeNewSystemNameIfOnlyAppGeneratedBlocksInTheList() {
		Vector<TextSection> data = new Vector<TextSection>();
		SectionName existingBlockName = SectionName.defaultSystemName();
		TextBlock block = new TextBlock(existingBlockName);
		data.add(block);
		NameChooser builder = new NameChooser();
		SectionName bestName = builder.deriveBestNameFrom(data);
		assertTrue(bestName.isApplicationGenerated());
		assertFalse(existingBlockName.toString().equals(bestName.toString()));
	}
}
