package Transformer.ASTs;

public class ArrayTypeAST extends TypeAST {
	public TypeAST	type;
	public ExprListAST el;
	public ArrayTypeAST(TypeAST prim, ExprListAST l ){
		type=prim;
		el=l;
		type.parent = el.parent = this;		
	}
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitArrayTypeAST(this,o);
	}
}