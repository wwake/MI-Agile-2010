import static org.junit.Assert.*;

import org.junit.Test;


public class SettingsTest {

	@Test
	public void testDefaultNamePrefixHasntChanged()
	{
		assertEquals("block-", Settings.DefaultNamePrefix);
	}
	
	@Test
	public void testDefaultSystemNamePrefix()
	{
		Settings settings = new Settings();
		assertTrue(settings.defaultSystemName().toString().startsWith("block-"));
	}
	
	@Test
	public void testDefaultSystemNameUsesDefaultNameAndIndexStartsAtOne()
	{
		SectionName systemName = new Settings().defaultSystemName();
		assertEquals("block-1", systemName.toString());
		assertFalse(systemName.isFromImport() || systemName.isFromUser());
	}

	@Test
	public void testDefaultSystemNameIncrementsSuffixForEveryCall()
	{
		Settings settings = new Settings();
		assertTrue(settings.defaultSystemName().toString().endsWith("1"));
		assertTrue(settings.defaultSystemName().toString().endsWith("2"));
		assertTrue(settings.defaultSystemName().toString().endsWith("3"));
	}
	
	@Test
	public void testImportedNameFromSetsNameGivenAndSetsCorrectType()
	{
		Settings settings = new Settings();
		SectionName importName = settings.importedNameFrom("loadEmUp");
		assertEquals("loadEmUp", importName.toString());
		assertTrue(importName.isFromImport());
	}
	
	@Test
	public void testImportedNameFromUsesSystemNameIfGivenNameIsEmpty()
	{
		Settings settings = new Settings();
		SectionName importName = settings.importedNameFrom("");
		assertEquals("block-1", importName.toString());
		assertTrue(importName.isFromImport());
	}
	
	@Test
	public void testUserNameFromSetsNameGivenAndSetsCorrectType()
	{
		Settings settings = new Settings();
		SectionName fromUser = settings.userSuppliedNameFrom("PEBKAC");
		assertEquals("PEBKAC", fromUser.toString());
		assertTrue(fromUser.isFromUser());
	}
	
	@Test
	public void testUserNameFromUsesAllowsSettingOfEmptyName()
	{
		Settings settings = new Settings();
		SectionName fromUser = settings.userSuppliedNameFrom("");
		assertEquals("", fromUser.toString());
		assertTrue(fromUser.isFromUser());
	}
}
