package Transformer.ASTs;

public class ShortTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitShortTypeAST(this,o);
	}
}
