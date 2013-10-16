package Transformer.ASTs;

public class EmptyVarInitializerListAST extends VarInitializerListAST {
	public EmptyVarInitializerListAST() {
		super();		
	}
	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyVarInitializerListAST(this,o);
	}
}
