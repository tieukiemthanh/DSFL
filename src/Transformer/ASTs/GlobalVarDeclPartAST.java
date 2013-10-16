package Transformer.ASTs;

public class GlobalVarDeclPartAST extends OneProcDeclAST {
	public VarDeclPartAST vp;
	public GlobalVarDeclPartAST(VarDeclPartAST varPart) {
		vp = varPart;
		vp.parent = this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitGlobalVarDeclPartAST(this,o);
	}

}
