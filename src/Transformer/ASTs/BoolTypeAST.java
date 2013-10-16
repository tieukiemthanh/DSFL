package Transformer.ASTs;

public class BoolTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitBoolTypeAST(this,o);
	}
}