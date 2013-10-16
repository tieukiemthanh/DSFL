package Transformer.ASTs;

import org.antlr.runtime.*;

public class FuncDeclAST extends DeclarationAST {
	public Token		name;
	public ParaListAST	para;
	public CompStmtAST	c;
	public TypeAST		ret;
	public FuncDeclAST(Token n, ParaListAST pl, TypeAST rt, CompStmtAST comp) {
		name = n;
		para = pl;
		ret = rt;
		c = comp;
		//para.parent = ret.parent = c.parent = this;
		para.parent = ret.parent = this;
	}

	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitFuncDeclAST(this,o);
	}
}	