package Transformer.ASTs;

public class AssiStmtAST extends OneStmtAST {
	public LvalueAST	l;
	public ExprAST		e;
	public AssiStmtAST(LvalueAST lval, ExprAST exp){
		l = lval;
		e = exp;
		e.parent = this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitAssiStmtAST(this,o);
	}	
}