package Transformer.ASTs;

public class TernaryExprAST extends ExprAST {
	public ExprAST 	e1;
	public ExprAST	e2;
	public ExprAST	e3;
	public int		l1;
	public int		l2;
	public int		l3;
	public String 	s1;
	public String 	s2;
	public String 	s3;
	
	public String line_str = "";
	public int line_else = -1;
	
	public TernaryExprAST(ExprAST exp1, ExprAST exp2, ExprAST exp3) {
		e1=exp1;
		e2=exp2;
		e3=exp3;
		e1.parent=e2.parent=e3.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitTernaryExprAST(this,o);
	}	
}
