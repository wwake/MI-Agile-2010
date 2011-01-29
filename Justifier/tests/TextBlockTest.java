import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TextBlockTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testConstructorInitializesValues() {
		
		TextBlock block = new TextBlock();
		assertTrue("Should initialize an empty sections list", block.sections().isEmpty());
		assertEquals("Default creations type to Application", TextLine.CreationType.AppCreated, block.creationType());
		assertNotNull("Should create default settings", block.settings());
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
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
	public void testIsBlockIsAlwaysTrue() {
		assertTrue(new TextBlock().isBlock());
	}

	@Test
	public void testIsNameUserCreatedIsTrueOnlyForUserEnteredCreationType() {
		TextBlock block = new TextBlock();
		block.setHowCreated(TextLine.CreationType.Imported);
		assertFalse(block.isNameUserCreated());
		
		block.setHowCreated(TextLine.CreationType.AppCreated);
		assertFalse(block.isNameUserCreated());
		
		block.setHowCreated(TextLine.CreationType.UserEntered);
		assertTrue(block.isNameUserCreated());
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}
}
