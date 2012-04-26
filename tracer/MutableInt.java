package tracer;

/* Author: Terence Parr */

public class MutableInt extends Number implements Comparable<Number> {
	private static final long serialVersionUID = 1L;
	public int v;

	public MutableInt(int v) {
		this.v = v;
	}

	@Override
	public int compareTo(Number o) {
		// reversed to sort in descending order
		if(v < o.intValue()) {
			return 1;
		} if (v > o.intValue()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int intValue() {
		return v;
	}

	@Override
	public long longValue() {
		return v;
	}

	@Override
	public float floatValue() {
		return v;
	}

	@Override
	public double doubleValue() {
		return v;
	}

	@Override
	public String toString() {
		return String.valueOf(v);
	}
}