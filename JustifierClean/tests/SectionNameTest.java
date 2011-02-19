import static org.junit.Assert.*;

import org.junit.Test;


public class SectionNameTest {

	@Test
	public void testDefaultNamePrefixHasntChanged()
	{
		assertEquals("block-", SectionName.DefaultNamePrefix);
	}
	
	@Test
	public void testDefaultSystemNamePrefix()
	{
		assertTrue(SectionName.defaultSystemName().toString().startsWith("block-"));
	}
	
	@Test
	public void testDefaultSystemNameUsesDefaultPrefix()
	{
		SectionName systemName = SectionName.defaultSystemName();
		assertTrue(systemName.toString().startsWith(SectionName.DefaultNamePrefix));
		assertFalse(systemName.isFromImport() || systemName.isFromUser());
	}

	@Test
	public void testDefaultSystemNameIncrementsSuffixForEveryCall()
	{
		SectionName firstName = SectionName.defaultSystemName();
		int dashIndex = firstName.toString().indexOf('-');
		int firstNumber = Integer.parseInt(firstName.toString().substring(dashIndex+1));
		assertTrue(SectionName.defaultSystemName().toString().endsWith(new Integer(firstNumber+1).toString()));
		assertTrue(SectionName.defaultSystemName().toString().endsWith(new Integer(firstNumber+2).toString()));
	}
	
	@Test
	public void testImportedNameFromSetsNameGivenAndSetsCorrectType()
	{
		SectionName importName = SectionName.importedNameFrom("loadEmUp");
		assertEquals("loadEmUp", importName.toString());
		assertTrue(importName.isFromImport());
	}
	
	@Test
	public void testImportedNameFromUsesSystemNameIfGivenNameIsEmpty()
	{
		SectionName importName = SectionName.importedNameFrom("");
		assertTrue(importName.toString().startsWith("block-"));
		assertTrue(importName.isFromImport());
	}
	
	@Test
	public void testUserSuppliedNameFromSetsNameGivenAndSetsCorrectType()
	{
		SectionName fromUser = SectionName.userSuppliedNameFrom("PEBKAC");
		assertEquals("PEBKAC", fromUser.toString());
		assertTrue(fromUser.isFromUser());
	}
	
	@Test
	public void testUserSuppliedNameFromUsesAllowsSettingOfEmptyName()
	{
		SectionName fromUser = SectionName.userSuppliedNameFrom("");
		assertEquals("", fromUser.toString());
		assertTrue(fromUser.isFromUser());
	}

	@Test
	public void testToStringIsJustTheName() {
		
		SectionName sectionName = SectionName.userSuppliedNameFrom("hi");
		assertEquals("hi", sectionName.toString());
	}

	@Test
	public void testIsFromUserIsTrueIfTypeIsUser() {
		
		SectionName userSupplied = SectionName.userSuppliedNameFrom("block-1");
		assertTrue(userSupplied.isFromUser());
		assertFalse("User supplied name should not be from application", userSupplied.isApplicationGenerated());
		assertFalse("User supplied name should not be from import", userSupplied.isFromImport());
	}

	@Test
	public void testIsFromImportIsTrueIfTypeIsImport() {
		
		SectionName importSupplied = SectionName.importedNameFrom("block-1");
		assertTrue(importSupplied.isFromImport());
		assertFalse("Import supplied name should not be from application", importSupplied.isApplicationGenerated());
		assertFalse("Import supplied name should not be from user", importSupplied.isFromUser());
	}

	@Test
	public void testIsApplicationGeneratedIsTrueIfTypeIsApplication() {
		
		SectionName appName = SectionName.defaultSystemName();
		assertTrue(appName.isApplicationGenerated());
		assertFalse("App generated name should not be from import", appName.isFromImport());
		assertFalse("App generated name should not be from user", appName.isFromUser());
	}
}
