package Transformer.ASTs;

public class DeclarationListAST extends AST {
	public DeclarationAST d;
	public DeclarationListAST dl;
	public DeclarationListAST(DeclarationAST decl, DeclarationListAST decll) {
		d = decl;
		dl = decll;
		d.parent = dl.parent = this;
	}
	public DeclarationListAST() {
		d = null;
		dl = null;
	}
	@Override
	public Object visit(Visitor v, Object o) throws CompilationException {
		// TODO Auto-generated method stub
		return v.visitDeclarationListAST(this, o);
	}

}
