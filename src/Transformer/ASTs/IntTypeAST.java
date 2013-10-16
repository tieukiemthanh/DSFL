package Transformer.ASTs;

public class IntTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitIntTypeAST(this,o);
	}
}
