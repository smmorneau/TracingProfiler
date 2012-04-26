package tracer;

import java.lang.instrument.Instrumentation;

public class TracerAgent {

	/**
	 * JVM hook to statically load the javaagent at startup.
	 * 
	 * After the Java Virtual Machine (JVM) has initialized, the premain method
	 * will be called. Then the real application main method will be called.
	 * 
	 * @param args
	 * @param inst
	 * @throws Exception
	 */
	public static void premain(String args, Instrumentation i) throws Exception {

		i.addTransformer(new MyClassFileTransformer());

		Thread shutdown = new Thread(new Shutdown());
		Runtime.getRuntime().addShutdownHook(shutdown);
	}
}