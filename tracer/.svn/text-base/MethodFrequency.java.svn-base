package tracer;

public class MethodFrequency implements Comparable<MethodFrequency> {

	String methodName;
	double time;
	MutableInt invocations;
	
	public MethodFrequency(String method, double time, MutableInt invocations) {
		this.methodName = method;
		this.time = time;
		this.invocations = invocations;
	}

	@Override
	public int compareTo(MethodFrequency f) {

		// sort in descending order
		if(this.time < f.time) {
			return 1;
		} else if (this.time > f.time) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public String toString() { 
		return String.format("%s: %sms %s", methodName, time, invocations);
	}

}
