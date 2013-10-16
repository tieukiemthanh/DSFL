package Transformer.ASTs;

public class FloatTypeAST extends PrimTypeAST{
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitFloatTypeAST(this,o);
	}
}