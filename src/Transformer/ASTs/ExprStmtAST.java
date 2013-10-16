package Transformer.ASTs;

public class ExprStmtAST extends OneStmtAST{
	public ExprAST e;
	public String line_str;
	
	public ExprStmtAST(ExprAST ex) {
		e = ex;
		e.parent = this;
	}

	public ExprStmtAST(ExprAST ex, int l) {
		e = ex;
		e.parent = this;
		label = l;
	}
	
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitExprStmtAST(this,o);
	}
}
