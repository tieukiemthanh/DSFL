package Transformer.ASTs;

public class EmptyStmtListAST extends StmtListAST {

	public EmptyStmtListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyStmtListAST(this,o);
	}
}