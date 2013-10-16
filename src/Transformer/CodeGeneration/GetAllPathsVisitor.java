// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class GetAllPathsVisitor extends DoNothingVisitor {

	public GetAllPathsVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		// visitDeclarationListAST
		List<String> paths = (List<String>) ast.dl.visit(this, null);
		
		return paths;
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		List<String> paths1 = (List<String>) ast.d.visit(this, null);
		List<String> paths2 = (List<String>) ast.dl.visit(this, null);
				
		if (paths2.size() == 0) {
			return paths1;
		} else {
			List<String> newPaths = new ArrayList<String>();
			
			for (String path1 : paths1) {
				for (String path2 : paths2) {
					newPaths.add(path1 + path2);
				}
			}
	
			return newPaths;
		}
	}

	// EmptyDeclarationListAST
	public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o)
			throws CompilationException {
		return new ArrayList<String>();
	}

	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		List<String> paths = new ArrayList<String>();
		paths.add(ast.label + ";");

		return paths;
	}
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		// visitCompStmtAST
		List<String> paths = (List<String>) fAst.c.visit(this, null);
		
		return paths;
	}

	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {		
		// visitStmtListAST or visitEmptyStmtListAST
		List<String> paths = (List<String>) ast.s.visit(this, o);
		
		return paths;
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		// visitOneStmtAST
		List<String> paths1 = (List<String>) ast.o.visit(this, o);
				
		// visitStmtListAST or visitEmptyStmtListAST
		List<String> paths2 = (List<String>) ast.s.visit(this, o);
		
		if (paths2.size() == 0) {
			return paths1;
		} else {
			List<String> newPaths = new ArrayList<String>();
			
			for (String path1 : paths1) {
				for (String path2 : paths2) {
					newPaths.add(path1 + path2);
				}
			}
	
			return newPaths;
		}
	}
	
	// EmptyStmtListAST
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o)
			throws CompilationException {
		return new ArrayList<String>();
	}

	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		List<String> paths = (List<String>) ast.dl.visit(this, o);
		
		return paths;
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		List<String> paths = new ArrayList<String>();
		paths.add(ast.label + ";");

		return paths;
	}
	
	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {
		List<String> paths = (List<String>) ast.s.visit(this, o);
		
		List<String> newPaths = new ArrayList<String>();
			
		newPaths.add(ast.label + ";");
		
		for (String path : paths) {
			newPaths.add(ast.label + ";" + path);
		}
	
		return newPaths;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {
		List<String> paths1 = (List<String>) ast.s1.visit(this, o);
		List<String> paths2 = (List<String>) ast.s2.visit(this, o);
	
		List<String> newPaths = new ArrayList<String>();

		for (String path : paths1) {
			newPaths.add(ast.label + ";" + path);
		}

		for (String path : paths2) {
			newPaths.add(ast.label + ";" + path);
		}

		return newPaths;
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		List<String> paths = (List<String>) ast.o.visit(this, o);

		List<String> newPaths = new ArrayList<String>();

		newPaths.add(ast.label + ";");
		/*
		for (String path : paths) {
			newPaths.add(ast.label + ";" + path + ast.label + ";");
		}
		/*
		for (String path1 : paths) {
			for (String path2 : paths) {
				for (String path3 : paths) {
					newPaths.add(ast.label + ";" + path1 + ast.label + ";" +
								 path2 + ast.label + ";" + path3 + ast.label + ";");
				}
			}
		}
		*/
		for (String path : paths) {
			newPaths.add(ast.label + ";" + path);
		}

		return newPaths;
	}
	
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		List<String> paths = new ArrayList<String>();
		paths.add(ast.label + ";");

		return paths;
	}
	
}