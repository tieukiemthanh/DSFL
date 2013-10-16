package Transformer.ASTs;

import org.antlr.runtime.*;

public class ProcDeclAST extends OneProcDeclAST {
	public Token		name;
	public ParaListAST	para;
	public CompStmtAST	c;
	public ProcDeclAST(Token n,ParaListAST pl, CompStmtAST comp) {
		name = n;
		para = pl;
		c = comp;
		para.parent = c.parent = this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitProcDeclAST(this,o);
	}
}