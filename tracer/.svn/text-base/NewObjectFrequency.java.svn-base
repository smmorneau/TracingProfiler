package tracer;

public class NewObjectFrequency implements Comparable<NewObjectFrequency> {

	String className;
	MutableInt instantiations;

	public NewObjectFrequency(String name, MutableInt instantiations) {
		this.className = name;
		this.instantiations = instantiations;
	}

	@Override
	public int compareTo(NewObjectFrequency f) {

		// sort in descending order
		if (this.instantiations.longValue() < f.instantiations.longValue()) {
			return 1;
		} else if (this.instantiations.longValue() > f.instantiations
				.longValue()) {
			return -1;
		} else {
			return 0;
		}
	}

	public String toString() {
		return String.format("%s: %s", className, instantiations);
	}

}
