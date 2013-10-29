package Transformer.ASTs;

public class DoStmtAST extends OneStmtAST {
	public ExprAST		e;
	public OneStmtAST	o;
	public String line_str;
	
	public DoStmtAST(ExprAST exp, OneStmtAST stmt){
		e=exp;
		o=stmt;
		e.parent=o.parent=this;
	}
	// trinhgiang-29/10/2013
	public DoStmtAST(ExprAST exp, OneStmtAST stmt, int l){
		e=exp;
		o=stmt;
		e.parent=o.parent=this;
		label = l;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitDoStmtAST(this,o);
	}	
}
