package Transformer.ASTs;

import org.antlr.runtime.*;

public class FloatLiteralAST extends LiteralAST{
		
	public FloatLiteralAST(Token t){
		literal  = t;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitFloatLiteralAST(this,o);
	}
}