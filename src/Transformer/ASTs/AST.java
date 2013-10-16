package Transformer.ASTs;

public abstract class AST {
	public AST parent;
	public int line;
	abstract public Object visit(Visitor a, Object o) throws CompilationException;
}


