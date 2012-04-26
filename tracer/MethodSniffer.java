package tracer;

/* Resource: http://www.antlr.org/wiki/display/CS345/Bytecode+instrumentation */

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;
 
public class MethodSniffer extends ClassVisitor {
    private String owner;
 
    public MethodSniffer(ClassVisitor cv) { super(Opcodes.ASM4, cv); }
 
    public void visit(final int version,
                      final int access,
                      final String name,
                      final String signature,
                      final String superName,
                      final String[] interfaces)
    {
        super.visit(version, access, name, signature, superName, interfaces);
        owner = name.replace("/", ".");
    }
 
    @Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
//		System.out.println("visitMethod " + name);
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);

		return mv == null ? null : new InsertPrintStatement(mv, access, name, desc);
	}
    
    class InsertPrintStatement extends AdviceAdapter implements Opcodes {
    	
    	String methodName;
    	
    	public InsertPrintStatement(final MethodVisitor mv, final int access,
    			final String name, final String desc) {
    		super(ASM4, mv, access, name, desc);
    		methodName = name;
    	}

    	@Override
    	protected void onMethodEnter() {
//    		System.out.println("onMethodEnter");
    		//System.out.println("enter:"+owner+"."+methodName);
    		mv.visitLdcInsn(owner);
    		mv.visitLdcInsn(methodName);
    		mv.visitMethodInsn(INVOKESTATIC, "tracer/StatsCollector", "start", "(Ljava/lang/String;Ljava/lang/String;)V");
    		super.onMethodEnter();
    	}
    	
    	@Override
    	protected void onMethodExit(int opcode) {
//    		System.out.println("onMethodExit");
    		//System.out.println("exit:"+owner+"."+methodName);
    		mv.visitLdcInsn(owner);
    		mv.visitLdcInsn(methodName);
    		mv.visitMethodInsn(INVOKESTATIC, "tracer/StatsCollector", "end", "(Ljava/lang/String;Ljava/lang/String;)V");
    		super.onMethodExit(opcode);
    	}
    	
    	@Override
    	public void visitTypeInsn(int opcode, String type) {
    		if(opcode == Opcodes.NEW) {
    			mv.visitLdcInsn(type);
    			mv.visitMethodInsn(INVOKESTATIC, "tracer/StatsCollector", "countNews", "(Ljava/lang/String;)V");
    		}
    		super.visitTypeInsn(opcode, type);
    	}

    	@Override
    	public void visitMaxs(int maxStack, int maxLocals) {
//    		System.out.println("max=" + maxStack);
    		super.visitMaxs(maxStack + 2, maxLocals); // must be here, not in onMethodEnter
    	}
    }
    
    
}