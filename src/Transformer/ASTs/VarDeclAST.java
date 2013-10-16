package Transformer.ASTs;

import org.antlr.runtime.*;

public class VarDeclAST extends DeclarationAST {
	public Token id;
	public TypeAST	t;
	public InitializerAST init;
	
	public VarDeclAST() {
	}	
	
	public VarDeclAST(Token tk, TypeAST type, InitializerAST i) {
		id = tk;
		t = type;
		init = i;
		t.parent = this;		
		if (init != null)			
			init.parent = this;		
	}
	public VarDeclAST(Token tk, TypeAST type, InitializerAST i, int l) {
		id = tk;
		t = type;
		init = i;
		t.parent = this;		
		if (init != null)			
			init.parent = this;
		label = l;
	}	
	public Object visit(Visitor v,Object o) throws CompilationException{
		return v.visitVarDeclAST(this,o);
	}
}