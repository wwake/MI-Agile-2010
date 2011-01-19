
public abstract class TextSection {
	
	private String name;
	private int offset;

	public TextSection() {
		this.setName("");
		offset = 0;
	}
	
	abstract int width();	
	
	public void setOffset(int newOffset)
	{
		offset = newOffset;
	}
	
	public int offset()
	{
		return offset;
	}

	public void setName(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}

	public Boolean IsLine()
	{
		return false;
	}
	
	public Boolean isBlock()
	{
		return false;
	}
	
}
