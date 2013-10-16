package Transformer.ASTs;

public class CompStmtAST extends OneStmtAST {
	/*public VarDeclPartAST	v;
	public StmtListAST	s;
	public CompStmtAST(VarDeclPartAST var, StmtListAST stmt){
		v=var;
		s=stmt;
		v.parent=s.parent=this;		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCompStmtAST(this,o);
	}	*/
	public StmtListAST	s;
	public CompStmtAST(StmtListAST stmt){
		s=stmt;
		s.parent=this;		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCompStmtAST(this,o);
	}
}