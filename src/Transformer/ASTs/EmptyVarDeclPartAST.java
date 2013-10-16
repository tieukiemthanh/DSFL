package Transformer.ASTs;

public class EmptyVarDeclPartAST extends VarDeclPartAST {

	public EmptyVarDeclPartAST() {
			
	}

	public Object visit(Visitor v, Object o) throws CompilationException{
		return v.visitEmptyVarDeclPartAST(this,o);
	}
}