package Transformer.ASTs;

public class CharTypeAST extends PrimTypeAST {
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCharTypeAST(this,o);
	}
}