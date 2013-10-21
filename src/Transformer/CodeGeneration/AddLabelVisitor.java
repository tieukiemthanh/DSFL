// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class AddLabelVisitor extends DoNothingVisitor {

	int num = 1;
	
	public int getNum() {
		return num;
	}
	
	public AddLabelVisitor(String outputFile, boolean debug) throws CompilationException {
		
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		// visitDeclarationListAST
		DeclarationListAST dl = (DeclarationListAST) ast.dl.visit(this, null);
		
		return new ProgramAST(dl);
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		DeclarationAST d = (DeclarationAST) ast.d.visit(this, null);
		DeclarationListAST dl = (DeclarationListAST) ast.dl.visit(this, null);
		
		return new DeclarationListAST(d, dl);
	}

	// EmptyDeclarationListAST
	public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o)
			throws CompilationException {
		return new EmptyDeclarationListAST();
	}

	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		return new VarDeclAST(ast.id, ast.t, ast.init, num++);
	}
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		// visitCompStmtAST
		CompStmtAST c = (CompStmtAST) fAst.c.visit(this, null);
		
		return new FuncDeclAST(fAst.name, fAst.para, fAst.ret, c);
	}

	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {		
		// visitStmtListAST or visitEmptyStmtListAST
		StmtListAST s = (StmtListAST) ast.s.visit(this, o);
		
		return new CompStmtAST(s);
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		// visitOneStmtAST
		OneStmtAST os = (OneStmtAST) ast.o.visit(this, o);
		
		// visitStmtListAST or visitEmptyStmtListAST
		StmtListAST s = (StmtListAST) ast.s.visit(this, o);
		
		return new StmtListAST(os, s);
	}
	
	// EmptyStmtListAST
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o)
			throws CompilationException {
		return new EmptyStmtListAST();
	}

	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		DeclarationListAST dl = (DeclarationListAST) ast.dl.visit(this, o);
		
		return new DeclarationStmtAST(dl);
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		return new ExprStmtAST(ast.e, num++);
	}
	
	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {
		int label = num++;
		OneStmtAST s = (OneStmtAST) ast.s.visit(this, o);
		return new IfThenStmtAST(ast.e, s, label);
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {	
		int label = num++;
		OneStmtAST s1 = (OneStmtAST) ast.s1.visit(this, o);
		OneStmtAST s2 = (OneStmtAST) ast.s2.visit(this, o);
		return new IfThenElseStmtAST(ast.e, s1, s2, label);
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		int label = num++;
		//trinhgiang-22/10/2013
		OneStmtAST s = (OneStmtAST) ast.o.visit(this, o);
		//StmtListAST s = (StmtListAST) ast.o.visit(this, o);
		return new WhileStmtAST(ast.e, s, label);
	}
	
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
	    int label = num++; 
		return new RetStmtAST(ast.e, label);
	}
	
	//trinhgiang-22/10/2013
	//CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST ast, Object o)
			throws CompilationException {
		int label = num++;
	    StmtListAST s = (StmtListAST) ast.s.visit(this, o);
		return new CaseStmtAST(ast.e, s, label);
	}
}
