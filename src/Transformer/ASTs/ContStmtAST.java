package Transformer.ASTs;

import org.antlr.runtime.*;

public class ContStmtAST extends OneStmtAST {
	public Token t;
	public ContStmtAST(Token c){
		t = c;
	}
	//trinhgiang-28/10/2013
	public ContStmtAST(Token c, int l){
		t = c;
		label = l;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitContStmtAST(this,o);
	}	
}
