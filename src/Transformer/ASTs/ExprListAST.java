package Transformer.ASTs;

public class ExprListAST extends AST {
	public ExprAST		e;
	public ExprListAST	l;
	public String line_str;
	public ExprListAST() {
		e = null;
		l = null;
	}

	public ExprListAST(ExprAST	exp,ExprListAST expl ){
		e=exp;
		l=expl;
		e.parent=l.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitExprListAST(this,o);
	}	
}
