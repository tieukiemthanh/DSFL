package Transformer.ASTs;

public class ForInitAST extends AST{
	public int type; //1:localVarDecl, 2: expressions, 3:null
	public DeclarationListAST d;
	public ExprListAST e;	
	public ForInitAST(int t, DeclarationListAST decl, ExprListAST expr) {
		type = t;
		d = decl;
		e =expr;
		if (d != null)
			d.parent = this;
		if (e != null)
			e.parent = this;
	}

	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitForInitAST(this,o);		
	}
}