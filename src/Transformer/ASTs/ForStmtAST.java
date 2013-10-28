package Transformer.ASTs;

public class ForStmtAST extends OneStmtAST {
	//public Token		name;
	public ForInitAST		e1;
	public ExprAST		e2;
	public ExprListAST		e3;
	public OneStmtAST	o;	
	public String s2 = "";	
	public ForStmtAST(ForInitAST fi, ExprAST exp2, ExprListAST exp3, OneStmtAST one){
		//name=t;
		e1=fi;
		e2=exp2;
		e3=exp3;
		o=one;
		if (e1 != null)
			e1.parent = this;
		if (e2 != null)
			e2.parent = this;
		if (e3 != null)
			e3.parent = this;
	}
	// trinhgiang-29/10/2013
	public ForStmtAST(ForInitAST fi, ExprAST exp2, ExprListAST exp3, OneStmtAST one, int l){
		//name=t;
		e1=fi;
		e2=exp2;
		e3=exp3;
		o=one;
		if (e1 != null)
			e1.parent = this;
		if (e2 != null)
			e2.parent = this;
		if (e3 != null)
			e3.parent = this;
		label = l;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitForStmtAST(this,o);
	}	
}
