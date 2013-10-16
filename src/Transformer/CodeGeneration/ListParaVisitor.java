
package Transformer.CodeGeneration;

import Transformer.ASTs.*;

public class ListParaVisitor extends DoNothingVisitor {

	String listPara = "";
	
	public ListParaVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		// visitDeclarationListAST
		ast.dl.visit(this, null);
		
		return listPara;
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		ast.d.visit(this, null);
		ast.dl.visit(this, null);
		
		return null;
	}
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		fAst.para.visit(this, null);
		
		return null;
	}

	// ParaListAST
	public Object visitParaListAST(ParaListAST ast, Object o)
			throws CompilationException {
		ast.v.visit(this, null);
		
		ast.p.visit(this, null);
		
		return null;
	}
	
	// ParaAST
	public Object visitParaAST(ParaAST ast, Object o)
			throws CompilationException{
		if (ast.t instanceof ArrayTypeAST) {
			listPara += "a";
		} else {
			listPara += "s";
		}
		
		return null;
	}

}
