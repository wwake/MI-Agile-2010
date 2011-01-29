import static org.junit.Assert.*;

import org.junit.Test;


public class TextSectionTest {

	@Test
	public void testConstructorInitialization() {
		
		TextSection section = new FakeSection();
		assertEquals("Name field should be empty", "", section.getName());
		assertEquals("Offset should be 0", 0, section.offset());
	}
	
	@Test
	public void testSetOffset() {

		TextSection section = new FakeSection();
		section.setOffset(3234);
		assertEquals(3234, section.offset());
	}

	@Test
	public void testSetName() {
		
		TextSection section = new FakeSection();
		section.setName("Fred");
		assertEquals("Fred", section.getName());
	}

	@Test
	public void testIsLineIsAlwaysFalse() {
		
		assertFalse(new FakeSection().isLine());
	}

	@Test
	public void testIsBlockIsAlwaysFalse() {

		assertFalse(new FakeSection().isBlock());
	}

	public class FakeSection extends TextSection
	{
		@Override
		int width() {
			return 20;
		}
		
		
	}
}
