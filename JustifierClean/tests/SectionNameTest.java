import static org.junit.Assert.*;

import org.junit.Test;


public class SectionNameTest {

	@Test
	public void testToStringIsJustTheName() {
		
		SectionName sectionName = new SectionName("hi", Settings.NameSource.APPLICATION);
		assertEquals("hi", sectionName.toString());
	}

	@Test
	public void testIsFromUserIsTrueIfTypeIsUser() {
		
		assertTrue(new SectionName("block-1", Settings.NameSource.USER).isFromUser());
	}

	@Test
	public void testIsFromUserIsFalseIfTypeIsApplication() {
		
		assertFalse(new SectionName("blah", Settings.NameSource.APPLICATION).isFromUser());
	}

	@Test
	public void testIsFromUserIsFalseIfTypeIsImported() {
		
		assertFalse(new SectionName("another", Settings.NameSource.IMPORTED).isFromUser());
	}

	@Test
	public void testIsFromImportIsTrueIfTypeIsImported() {
		
		assertTrue(new SectionName("jhfkdj", Settings.NameSource.IMPORTED).isFromImport());
	}

	@Test
	public void testIsFromImportIsFalseIfTypeIsApplication() {
		
		assertFalse(new SectionName("blah", Settings.NameSource.APPLICATION).isFromImport());
	}

	@Test
	public void testIsFromImportIsFalseIfTypeIsUser() {
		
		assertFalse(new SectionName("another", Settings.NameSource.USER).isFromImport());
	}
}
