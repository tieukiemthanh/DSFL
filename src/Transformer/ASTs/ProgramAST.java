//
package Transformer.ASTs;

public class ProgramAST extends AST {
		public DeclarationListAST dl;
		public ProgramAST(DeclarationListAST decll) {
			dl = decll;
			dl.parent = this;
		}
		
		public Object visit(Visitor v, Object o) throws CompilationException{
			return v.visitProgramAST(this,o);		
		}
}
