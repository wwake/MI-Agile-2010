import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class SectionNameTest {

	@Test
	public void defaultNamePrefixHasntChanged() {
		assertEquals("block-", SectionName.DefaultNamePrefix);
	}
	
	@Test
	public void defaultSystemNameUsesDefaultPrefix() {
		SectionName systemName = SectionName.defaultSystemName();
		assertTrue(systemName.toString().startsWith(SectionName.DefaultNamePrefix));
		assertFalse(systemName.isFromImport() || systemName.isFromUser());
	}

	@Test
	public void defaultSystemNameIncrementsSuffixForEveryCall() {
		SectionName firstName = SectionName.defaultSystemName();
		int dashIndex = firstName.toString().indexOf('-');
		int firstNumber = Integer.parseInt(firstName.toString().substring(dashIndex+1));
		assertTrue(SectionName.defaultSystemName().toString().endsWith(new Integer(firstNumber+1).toString()));
		assertTrue(SectionName.defaultSystemName().toString().endsWith(new Integer(firstNumber+2).toString()));
	}
	
	@Test
	public void importedNameFromSetsNameGivenAndSetsCorrectType() {
		SectionName importName = SectionName.importedNameFrom("loadEmUp");
		assertEquals("loadEmUp", importName.toString());
		assertTrue(importName.isFromImport());
	}
	
	@Test
	public void importedNameFromUsesSystemNameIfGivenNameIsEmpty() {
		SectionName importName = SectionName.importedNameFrom("");
		assertTrue(importName.toString().startsWith("block-"));
		assertTrue(importName.isFromImport());
	}
	
	@Test
	public void userSuppliedNameFromSetsNameGivenAndSetsCorrectType() {
		SectionName fromUser = SectionName.userSuppliedNameFrom("PEBKAC");
		assertEquals("PEBKAC", fromUser.toString());
		assertTrue(fromUser.isFromUser());
	}
	
	@Test
	public void userSuppliedNameFromUsesAllowsSettingOfEmptyName() {
		SectionName fromUser = SectionName.userSuppliedNameFrom("");
		assertEquals("", fromUser.toString());
		assertTrue(fromUser.isFromUser());
	}

	@Test
	public void toStringIsJustTheName() {
		SectionName sectionName = SectionName.userSuppliedNameFrom("hi");
		assertEquals("hi", sectionName.toString());
	}

	@Test
	public void isFromUserIsTrueIfTypeIsUser() {
		SectionName userSupplied = SectionName.userSuppliedNameFrom("block-1");
		assertTrue(userSupplied.isFromUser());
		assertFalse("User supplied name should not be from application", userSupplied.isApplicationGenerated());
		assertFalse("User supplied name should not be from import", userSupplied.isFromImport());
	}

	@Test
	public void isFromImportIsTrueIfTypeIsImport() {
		SectionName importSupplied = SectionName.importedNameFrom("block-1");
		assertTrue(importSupplied.isFromImport());
		assertFalse("Import supplied name should not be from application", importSupplied.isApplicationGenerated());
		assertFalse("Import supplied name should not be from user", importSupplied.isFromUser());
	}

	@Test
	public void isApplicationGeneratedIsTrueIfTypeIsApplication() {
		SectionName appName = SectionName.defaultSystemName();
		assertTrue(appName.isApplicationGenerated());
		assertFalse("App generated name should not be from import", appName.isFromImport());
		assertFalse("App generated name should not be from user", appName.isFromUser());
	}
}
