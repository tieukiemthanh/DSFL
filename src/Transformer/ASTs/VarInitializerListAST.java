package Transformer.ASTs;

public class VarInitializerListAST extends InitializerAST {
	public VarInitializerAST v;
	public VarInitializerListAST vl;
	
	public VarInitializerListAST() {
		v = null;
		vl = null;
	}
	
	public VarInitializerListAST(VarInitializerAST vx, VarInitializerListAST vlx) {
		v = vx;
		vl = vlx;
		v.parent = vl.parent = this;
	}
	
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitVarInitializerListAST(this,o);
	}
}
