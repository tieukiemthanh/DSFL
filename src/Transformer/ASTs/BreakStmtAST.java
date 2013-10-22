package Transformer.ASTs;

import org.antlr.runtime.*;

public class BreakStmtAST extends OneStmtAST {
	public Token t;
	//trinhgiang-22/10/2013
	public BreakStmtAST(Token b){
		t = b;
		//isBreak = true;
	}
	//trinhgiang-22/10/2013
	//add label for break statement
	public BreakStmtAST(Token b, int l){
		t = b;
		label = l;
		//isBreak = true;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitBreakStmtAST(this,o);
	}	
}
