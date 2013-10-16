package Transformer.ASTs;

public class VoidTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitVoidTypeAST(this,o);
	}
}
