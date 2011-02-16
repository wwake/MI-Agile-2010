
public class SectionName {

	private String name;
	private Settings.NameSource type;
	
	
	public SectionName(String nameValue, Settings.NameSource howCreated)
	{
		name = nameValue;
		type = howCreated;
	}
	
	public Boolean isFromUser()
	{
		return type == Settings.NameSource.USER;
	}
	
	public Boolean isFromImport()
	{
		return type == Settings.NameSource.IMPORTED;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
