package Transformer.ASTs;

import org.antlr.runtime.*;

public class StringLiteralAST extends LiteralAST {
	public StringLiteralAST (Token t) {
		literal = t;
	}
	
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitStringLiteralAST(this,o);
	}
}