package Transformer.ASTs;

public class SwitchStmtAST extends OneStmtAST {
	public ExprAST e;
	public OneStmtAST o;
	public SwitchStmtAST(ExprAST expr,OneStmtAST one){
		//name=t;
		e=expr;
		o=one;
		e.parent= this;
		one.parent=this;
	}
	//trinhgiang-28/10/2013
	public SwitchStmtAST(ExprAST expr,OneStmtAST one, int l){
		//name=t;
		e=expr;
		o=one;
		e.parent= this;
		one.parent=this;
		label = l;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitSwitchStmtAST(this,o);
	}
}
