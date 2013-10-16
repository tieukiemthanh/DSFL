package Transformer.ASTs;

import org.antlr.runtime.*;

public class IntLiteralAST extends LiteralAST {
	public IntLiteralAST (Token t) {
		literal = t;
	}
	
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitIntLiteralAST(this,o);
	}
}