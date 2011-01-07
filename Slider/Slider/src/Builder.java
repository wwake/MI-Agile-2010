
public class Builder {

	private Pool pool;

	public Builder(String[] strings) {
		pool = new Pool(null);
		for (String string : strings)
			pool.add(new OffsetWord(string));
	}

	public String row(int row) {
		return "bobb";
	}

	public void build() {
		// run through pool
		// pick best 2
		// remove individuals, replace with combo
		// repeat until 1 item left
	}
	
	public String result() {
		return pool.toString();
	}

}
