package Transformer.ASTs;

import org.antlr.runtime.*;

public class CallStmtAST extends OneStmtAST {
	public Token 		name;
	public ExprListAST	e;
	public CallStmtAST(Token t, ExprListAST exp){
		name=t;
		e=exp;
		e.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitCallStmtAST(this,o);
	}	
}