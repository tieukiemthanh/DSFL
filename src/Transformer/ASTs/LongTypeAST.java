package Transformer.ASTs;

public class LongTypeAST extends PrimTypeAST {

	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitLongTypeAST(this,o);
	}

}