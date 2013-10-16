package Transformer.ASTs;

public class DoubleTypeAST extends PrimTypeAST {

	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitDoubleTypeAST(this,o);
	}

}
