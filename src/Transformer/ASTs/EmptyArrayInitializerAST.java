package Transformer.ASTs;

public class EmptyArrayInitializerAST extends ArrayInitializerAST {
	public EmptyArrayInitializerAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyArrayInitializerAST(this,o);
	}
}
