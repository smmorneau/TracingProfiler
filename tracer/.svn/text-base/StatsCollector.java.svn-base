package tracer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {

	public static Map<String, Stack<Long>> startTimes = new ConcurrentHashMap<String, Stack<Long>>();
	public static Map<String, Long> elapsedTimes = new HashMap<String, Long>();
	
	public static FrequencySet<String> invocations = new FrequencySet<String>();
	
	public static FrequencySet<String> news = new FrequencySet<String>();

	public static void start(String className, String methodName) {
		String name = className + "." + methodName;
		
		Stack<Long> curr = startTimes.get(name);
		
		if(curr == null) {
			curr = new Stack<Long>();
			startTimes.put(name, curr);
		}
		
		// start time
		curr.push(System.nanoTime());
				
		// new object count
		invocations.add(name);
	}

	public static void end(String className, String methodName) {
		String name = className + "." + methodName;
		
		Long currTime = elapsedTimes.get(name);
		
		Stack<Long> starts = startTimes.get(name);
		
		if(starts == null) {
			System.out.println("stack " + starts + " in " + name  + " is NULL");
		}
		
		long start = starts.pop();
		
		if(currTime == null) {
			elapsedTimes.put(name, System.nanoTime() - start);
		} else {
			long total = currTime + (System.nanoTime() - start);
			elapsedTimes.put(name, total);
		}
		
	}
	
	public static void countNews(String className) {
		news.add(className);
	}
	
}