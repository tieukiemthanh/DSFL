package Transformer.ASTs;

import org.antlr.runtime.*;

public class ContStmtAST extends OneStmtAST {
	public Token t;
	public ContStmtAST(Token c){
		t = c;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitContStmtAST(this,o);
	}	
}