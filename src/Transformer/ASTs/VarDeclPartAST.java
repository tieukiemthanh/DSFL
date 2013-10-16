package Transformer.ASTs;

public class VarDeclPartAST extends AST {
	public VarDeclAST 	  	v;
	public VarDeclPartAST	vp;
	public VarDeclPartAST() {
		v = null;
		vp = null;
	}
	public VarDeclPartAST(VarDeclAST var, VarDeclPartAST varPart) {
		v = var;
		vp = varPart;
		v.parent = vp.parent = this;
	}
	public Object visit(Visitor v,Object o) throws CompilationException{
		return v.visitVarDeclPartAST(this,o);
	}
}