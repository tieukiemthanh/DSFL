package Transformer.ASTs;

public class VarInitializerAST extends InitializerAST {
	public ExprAST e;
	
	public VarInitializerAST(ExprAST ex) {
		e = ex;
		e.parent = this;
	}
	
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitVarInitializerAST(this,o);
	}

}
