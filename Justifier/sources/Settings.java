
public class Settings {

	private static Settings TheSettings;
	
	public static Settings getInstance()
	{
		if (TheSettings == null)
		{
			TheSettings = new Settings();
		}
		return TheSettings;
	}
	
	protected static void resetInstance()
	{
		TheSettings = null;
	}
	
	private int lastUsedNumber;

	protected Settings()
	{
		lastUsedNumber = 0;
	}
	
	public String nextName()
	{
		++lastUsedNumber;
		return "block-" + lastUsedNumber;
	}
}
