package Transformer.ASTs;

public class EmptyIntLiteralListAST extends IntLiteralListAST {
	public EmptyIntLiteralListAST() {
		super();
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyIntLiteralListAST(this,o);
	}
}