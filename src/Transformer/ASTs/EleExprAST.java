package Transformer.ASTs;

import org.antlr.runtime.*;

public class EleExprAST extends LvalueAST {
	public Token		name;
	public ExprListAST		e;
	public EleExprAST(Token t, ExprListAST exp ){
		name=t;
		e=exp;
		e.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEleExprAST(this,o);
	}	
}