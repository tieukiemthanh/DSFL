package Transformer.ASTs;

public class DefaultStmtAST extends OneStmtAST {
	public StmtListAST s;
	public DefaultStmtAST(StmtListAST sl){
		s=sl;
		s.parent=this;
	}
	// trinhgiang-29/10/2013
	public DefaultStmtAST(StmtListAST sl, int l){
		s=sl;
		s.parent=this;
		label = l;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitDefaultStmtAST(this,o);
	}
}
