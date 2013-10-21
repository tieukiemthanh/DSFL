package Transformer.ASTs;

public class WhileStmtAST extends OneStmtAST {
	public ExprAST		e;
	//trinhgiang-22/10/2013
	public OneStmtAST	o;
	//public StmtListAST s;
	public String line_str;
	
	public WhileStmtAST(ExprAST exp, OneStmtAST stmt){
		e=exp;
		o=stmt;
		e.parent=o.parent=this;
	}
	public WhileStmtAST(ExprAST exp, OneStmtAST stmt, int l){
		e=exp;
		o=stmt;
		e.parent=o.parent=this;
		label = l;
	}
	
	/*
	public WhileStmtAST(ExprAST exp, StmtListAST stmt){
		e=exp;
		s=stmt;
		e.parent=s.parent=this;
	}
	public WhileStmtAST(ExprAST exp, StmtListAST stmt, int l){
		e=exp;
		s=stmt;
		e.parent=s.parent=this;
		label = l;
	}
	*/
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitWhileStmtAST(this,o);
	}	
}
