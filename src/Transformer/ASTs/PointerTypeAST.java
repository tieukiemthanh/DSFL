package Transformer.ASTs;

public class PointerTypeAST extends PrimTypeAST{
	public TypeAST t;
	
	public PointerTypeAST(TypeAST type) {
		t = type;
	}
	
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitPointerTypeAST(this,o);
	}
}