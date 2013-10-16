package Transformer.ASTs;

public class CastInt2FloatAST extends ExprAST {
	public ExprAST e;	
	public CastInt2FloatAST(ExprAST exp1){
		e=exp1;
		e.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitCastInt2FloatAST(this, o);
	}
}
