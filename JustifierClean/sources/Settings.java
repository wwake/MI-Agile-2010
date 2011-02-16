
public class Settings {

	public static String DefaultNamePrefix = "block-";

	public static enum NameSource {
		APPLICATION,
		USER,
		IMPORTED
	}

	private int lastUsedNumber;

	public Settings()
	{
		lastUsedNumber = 0;
	}
	
	private String nextName()
	{
		++lastUsedNumber;
		return Settings.DefaultNamePrefix + lastUsedNumber;
	}
	
	public SectionName defaultSystemName()
	{
		return new SectionName(this.nextName(), NameSource.APPLICATION);
	}

	public SectionName importedNameFrom(String importName)
	{
		String newName = (importName.isEmpty()) ? this.nextName() : importName;
		return new SectionName(newName, NameSource.IMPORTED);
	}
	
	public SectionName userSuppliedNameFrom(String fromUser)
	{
		return new SectionName(fromUser, NameSource.USER);
	}
}
