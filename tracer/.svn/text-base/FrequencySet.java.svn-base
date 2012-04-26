package tracer;

import java.util.Hashtable;

/** Count how many of each key we have; not thread safe */
public class FrequencySet<T> extends Hashtable<T, MutableInt> {
	private static final long serialVersionUID = 1L;

	public int count(T key) {
		MutableInt value = get(key);
		if (value == null)
			return 0;
		return value.v;
	}

	public void add(T key) {
		MutableInt value = get(key);
		if (value == null) {
			value = new MutableInt(1);
			put(key, value);
		} else {
			value.v++;
		}
	}
}
