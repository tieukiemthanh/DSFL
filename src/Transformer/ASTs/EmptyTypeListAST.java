package Transformer.ASTs;

public class EmptyTypeListAST extends TypeListAST {
	public EmptyTypeListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyTypeListAST(this,o);
	}
}
