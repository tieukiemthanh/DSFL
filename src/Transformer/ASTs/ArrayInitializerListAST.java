package Transformer.ASTs;

public class ArrayInitializerListAST extends InitializerAST {
	public ArrayInitializerAST a;
	public ArrayInitializerListAST al;
	
	public ArrayInitializerListAST() {
		a = null;
		al = null;
	}
	
	public ArrayInitializerListAST(ArrayInitializerAST ax, ArrayInitializerListAST alx) {
		a = ax;
		al = alx;
		a.parent = al.parent = this;
	}
	
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitArrayInitializerListAST(this,o);
	}
}
