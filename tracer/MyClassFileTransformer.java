package tracer;

/* Resource: http://www.antlr.org/wiki/display/CS345/Bytecode+instrumentation */

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class MyClassFileTransformer implements ClassFileTransformer, Opcodes {

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {

		className = className.replace("/", ".");
		
		if (className.startsWith("java.") || className.startsWith("sun.")
				|| className.startsWith("com.")
				|| className.startsWith("javax.") || className.startsWith("$")
				|| className.startsWith("tracer.")) {
			// System.err.println("loading class '" + className
			// + "' without on the fly sniffing");
			return classfileBuffer;
		}

		// System.err.println("loading class '" + className
		// + "' with on the fly sniffing");

		byte[] b = null;

		// adapts the class on the fly
		try {
			ClassReader cr = new ClassReader(classfileBuffer);
			ClassWriter cw = new ClassWriter(0);
			ClassVisitor cv = new MethodSniffer(cw);
			cr.accept(cv, ClassReader.EXPAND_FRAMES);
			b = cw.toByteArray();
		} catch (Exception e) {
			try {
				throw new ClassNotFoundException(className, e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// loading into memory
		return b;

	}

}
