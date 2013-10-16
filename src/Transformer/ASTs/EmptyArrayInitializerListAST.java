package Transformer.ASTs;

public class EmptyArrayInitializerListAST extends ArrayInitializerListAST {
	public EmptyArrayInitializerListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyArrayInitializerListAST(this,o);
	}
}
