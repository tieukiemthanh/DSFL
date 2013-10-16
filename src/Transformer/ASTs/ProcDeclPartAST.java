package Transformer.ASTs;

public class ProcDeclPartAST extends AST{
	public OneProcDeclAST 	o;
	public ProcDeclPartAST	p;
	public ProcDeclPartAST() {
		o = null;
		p = null;
	}
	public ProcDeclPartAST(OneProcDeclAST one, ProcDeclPartAST pro){
		o = one;
		p = pro;
		o.parent = p.parent = this;		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitProcDeclPartAST(this,o);
	}
}