package Transformer.ASTs;

public class EmptyParaListAST extends ParaListAST {
	public EmptyParaListAST() {
		super();		
	}

	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyParaListAST(this,o);
	}	
}