package Transformer.ASTs;

public class TypeListAST extends TypeAST {
	public TypeAST t;
	public TypeListAST tl;
	
	public TypeListAST() {
		t = null;
		tl = null;
	}

	public TypeListAST(TypeAST type, TypeListAST typel ){
		t = type;
		tl = typel;
		t.parent = tl.parent = this;
	}
	
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitTypeListAST(this, o);
	}

}
