package Transformer.ASTs;

import org.antlr.runtime.*;

public class ParaAST extends AST{
	public Token id;
	public TypeAST	t;
	public boolean ref;//true if pass by reference, false otherwise
	public ParaAST(Token i, TypeAST type, boolean r) {
		id = i;
		t = type;
		ref = r;
		t.parent = this;
	}	
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitParaAST(this,o);
	}
}
