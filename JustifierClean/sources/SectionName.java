

public class SectionName {

	private static int lastUsedNumber = 0;
	public static String DefaultNamePrefix = "block-";
	
	private static enum NameSource {
		APPLICATION,
		USER,
		IMPORTED
	}
	
	public static SectionName defaultSystemName()
	{
		return new SectionName(SectionName.nextName(), NameSource.APPLICATION);
	}

	public static SectionName importedNameFrom(String importName)
	{
		String newName = (importName.isEmpty()) ? SectionName.nextName() : importName;
		return new SectionName(newName, NameSource.IMPORTED);
	}
	
	public static SectionName userSuppliedNameFrom(String fromUser)
	{
		return new SectionName(fromUser, NameSource.USER);
	}

	private static String nextName()
	{
		++lastUsedNumber;
		return SectionName.DefaultNamePrefix + lastUsedNumber;
	}
	
	
	private String name;
	private NameSource type;
	
	
	private SectionName(String nameValue, NameSource howCreated)
	{
		name = nameValue;
		type = howCreated;
	}
	
	public Boolean isFromUser()
	{
		return type == NameSource.USER;
	}
	
	public Boolean isFromImport()
	{
		return type == NameSource.IMPORTED;
	}	
	
	public Boolean isApplicationGenerated()
	{
		return ! this.isFromImport() && ! this.isFromUser();
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
/* isFromXyz feels a bit clunky. Seems like whoever is calling these methods should be trusting some other object to do its job.
I'm not terribly fond of the multiple isXyz() methods anyway. Note how isApplicationGenerated() is different - seems like it should 
parallel the others. (Notice how nobody ever looks at NameSource.APPLICATION.)
*/