package Transformer.ASTs;

public class CaseStmtAST extends OneStmtAST {
	public ExprAST e;
	public StmtListAST s;
	public CaseStmtAST(ExprAST expr,StmtListAST sl){
		//name=t;
		e=expr;
		s=sl;
		e.parent= this;
		s.parent=this;
	}
	//trinhgiang-22/10/2013
	public CaseStmtAST(ExprAST expr,StmtListAST sl, int l){
		//name=t;
		e=expr;
		s=sl;
		e.parent= this;
		s.parent=this;
		label = l;
	} 
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCaseStmtAST(this,o);
	}
}
