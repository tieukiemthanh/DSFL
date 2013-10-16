package Transformer.ASTs;

public class WhileStmtAST extends OneStmtAST {
	public ExprAST		e;
	public OneStmtAST	o;
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
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitWhileStmtAST(this,o);
	}	
}