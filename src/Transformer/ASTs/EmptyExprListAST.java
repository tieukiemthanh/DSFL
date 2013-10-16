package Transformer.ASTs;

public class EmptyExprListAST extends ExprListAST {
	public EmptyExprListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyExprListAST(this,o);
	}
}