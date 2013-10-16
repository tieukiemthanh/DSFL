package Transformer.ASTs;

import org.antlr.runtime.*;

public class VarExprAST extends LvalueAST {
	public Token		name;
	public VarExprAST(Token t){
		name=t;		
	}
	public Object visit(Visitor v,Object o) throws CompilationException{
		return v.visitVarExprAST(this,o);
	}
}