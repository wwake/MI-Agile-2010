import java.util.Vector;

// Note: this corresponds to Seq. Project

public class Library {
	
	private Vector<Document> documents;
	
	public Library()
	{
		documents = new Vector<Document>();
	}

	public void input(Document aDocument)
	{
		documents.add(aDocument);
	}
}
