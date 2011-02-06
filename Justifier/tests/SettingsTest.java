import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SettingsTest {

	public static class ResettableSettings extends Settings
	{
		public static void resetInstance()
		{
			Settings.resetInstance();
		}
	}
		
	@Before
	public void SetUp()
	{
		ResettableSettings.resetInstance();  // for the first time in
	}
	
	@After
	public void TearDown()
	{
		ResettableSettings.resetInstance();  // clean up for last time out
	}
	
	@Test
	public void testGetInstanceShouldAlwaysReturnTheSameInstance() 
	{
		Settings testSettings = Settings.getInstance();
		assertSame(testSettings, Settings.getInstance());
	}

	@Test
	public void testNewInstanceOfSettingsStartsNameSuffixAtOne() 
	{
		assertTrue(Settings.getInstance().nextName().endsWith("1"));
	}
	
	@Test
	public void testNextNamePrefix()
	{
		assertTrue(Settings.getInstance().nextName().startsWith("block-"));
	}

	@Test
	public void testNextNameIncrementsSuffixForEveryCall()
	{
		assertTrue(Settings.getInstance().nextName().endsWith("1"));
		assertTrue(Settings.getInstance().nextName().endsWith("2"));
		assertTrue(Settings.getInstance().nextName().endsWith("3"));

	}
}
