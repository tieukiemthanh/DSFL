package Transformer.ASTs;

import org.antlr.runtime.*;

public class BoolLiteralAST extends LiteralAST {
	public BoolLiteralAST (Token t) {
		literal = t;
	}
	
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitBoolLiteralAST(this,o);
	}
}