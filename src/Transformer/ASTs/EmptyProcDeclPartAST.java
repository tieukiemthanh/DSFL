package Transformer.ASTs;

public class EmptyProcDeclPartAST extends ProcDeclPartAST {
	public EmptyProcDeclPartAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyProcDeclPartAST(this,o);
	}
}