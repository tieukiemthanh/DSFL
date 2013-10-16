// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class BlockVisitor extends DoNothingVisitor {

	int numBlock = -1;
	int scope = 0;
	
	boolean first = false;
	
	String content = "";
	
	public void inScope() {
		scope++;
	}
	
	public void outScope() {
		scope--;
	}
	
	public String newline() {
		String newline_str = "\n";
		return newline_str;
	}
	
	// use to set indent
	public void indentString() {
		String indent = "";
		for (int i = 0; i < scope; i++) {
			indent += "\t";
		}
			
		content += indent;
	}

	public BlockVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {		
		// visitDeclarationListAST
		ast.dl.visit(this, o);
	
		return content;
	}
	
	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		ast.d.visit(this, o);
		ast.dl.visit(this, o);
		return null;
	}
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		// visitCompStmtAST
		first = true;
		fAst.c.visit(this, o);
		
		return null;
	}

	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {		
		// visitStmtListAST or visitEmptyStmtListAST
		ast.s.visit(this, o);
		
		return null;
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		if (!(ast.o instanceof IfThenStmtAST) && !(ast.o instanceof IfThenElseStmtAST) &&
				!(ast.o instanceof WhileStmtAST) && first == true) {
			numBlock++;
			indentString();
			content += "S" + numBlock + newline();
			first = false;
		}
		
		// visitOneStmtAST
		ast.o.visit(this, o);
		
		if ((ast.o instanceof IfThenStmtAST) || (ast.o instanceof IfThenElseStmtAST) ||
				(ast.o instanceof WhileStmtAST)) {
			first = true;
		}
		
		// visitStmtListAST or visitEmptyStmtListAST
		ast.s.visit(this, o);
		
		return null;
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		return null;
	}
	
	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {		
		numBlock++;
		indentString();
		content += "if S" + numBlock + newline();
		inScope();
		
		first = true;
		ast.s.visit(this, o);
		
		outScope();
		
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {	
		numBlock++;
		indentString();
		content += "if S" + numBlock + newline();
		inScope();
		
		first = true;
		ast.s1.visit(this, o);
		
		outScope();
		
		indentString();
		content += "else" + newline();
		inScope();
		
		first = true;
		ast.s2.visit(this, o);
		
		outScope();
		
		return null;
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		numBlock++;
		indentString();
		content += "while S" + numBlock + newline();
		inScope();
		
		first = true;
		ast.o.visit(this, o);
		
		outScope();
		
		return null;
	}
	
	public Object visitDoStmtAST(DoStmtAST ast, Object o)
			throws CompilationException {		
		return null;
	}
	
	// ForStmtAST
	public Object visitForStmtAST(ForStmtAST ast, Object o)
			throws CompilationException {
		return null;
	}
		
	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		return null;
	}		
	
	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
		return null;
	}
	
	// CallExprAST
	public Object visitCallExprAST(CallExprAST ast, Object o)
			throws CompilationException {
		return null;
	}
	
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		return null;
	}
	
}