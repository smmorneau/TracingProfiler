package tracer;

import java.util.Set;
import java.util.TreeSet;

public class Shutdown implements Runnable {

	@Override
	public void run() {

		System.out.println("***** Tracer Profile: *****");
		System.out.println("----- Method times and invocations -----");
		
		Set<MethodFrequency> methodSet = new TreeSet<MethodFrequency>();
		
		for(String s : StatsCollector.elapsedTimes.keySet()) {
			
			String method = s.replace("/", ".");
			double elapsed = StatsCollector.elapsedTimes.get(s) / 1000000.0;
			MutableInt invocations = StatsCollector.invocations.get(s);
			
			MethodFrequency f = new MethodFrequency(method, elapsed, invocations);
			methodSet.add(f);
			
		}
		
		for (MethodFrequency f : methodSet) {
			System.out.println(f);
		}
		
		System.out.println("\n----- Instantiations -----");
		
		Set<NewObjectFrequency> newObjectSet = new TreeSet<NewObjectFrequency>();
		
		for(String s : StatsCollector.news.keySet()) {
			String method = s.replace("/", ".");
			MutableInt count = StatsCollector.news.get(s);
			
			NewObjectFrequency f = new NewObjectFrequency(method, count);
			newObjectSet.add(f);
		}
		
		for (NewObjectFrequency f : newObjectSet) {
			System.out.println(f);
		}
		
		
	}

}