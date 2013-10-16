// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class TwoSATVisitor extends DoNothingVisitor {

	String code = "";
	
	int index = 0;
	int block = 0;
	
	public List<Integer> indexBlock = new ArrayList<Integer>();
	public List<Integer> lengthBlock = new ArrayList<Integer>();
	
	boolean first = true;
	int ifPartSize = 0;
	
	public TwoSATVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {		
		ast.dl.visit(this, 0);
		/*
		System.out.print("Index: ");
		for (int i : indexBlock) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		System.out.print("Length: ");
		for (int i : lengthBlock) {
			System.out.print(i + ", ");
		}
		System.out.println();
		*/
		return code;
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		ast.d.visit(this, o);
		ast.dl.visit(this, o);
				
		return null;
	}
	
	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		return null;
	}
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		// visitCompStmtAST
		fAst.c.visit(this, o);
		
		return null;
	}

	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {		
		// visitStmtListAST or visitEmptyStmtListAST
		return ast.s.visit(this, o);
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		Integer size = (Integer) o;
		o = new Integer(size + 1);
		
		if (ast.o instanceof RetStmtAST) {
			code += index++ + ";" + block + "R\n";
			return o;
		} else if (ast.o instanceof IfThenStmtAST || ast.o instanceof IfThenElseStmtAST) {
			o = ast.o.visit(this, o);
			//code += "S" + index + " | FALSE";
			if (ast.s instanceof EmptyStmtListAST) {
				return o;
			}
			first = true;
		} else if (ast.o instanceof WhileStmtAST) {
			if (first) {
				o = ast.o.visit(this, o);
			} else {
				code += index++ + ";" + block + "\n";
				o = ast.o.visit(this, o);
			}
			
			if (ast.s instanceof EmptyStmtListAST) {
				return o;
			}
			first = true;
		} else {
			first = false;
		}
			
		ast.s.visit(this, o);
		
		return o;
	}
	
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o) {
		code += index++ + ";" + block + "\n";
		return o;
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		return o;
	}
	
	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {
		int i = index++;
		
		block++;
		
		ifPartSize++;
		int new_size = (Integer) ast.s.visit(this, 0);
		ifPartSize--;
		
		Integer size = (Integer) o;
		o = new Integer(size + new_size);
		
		block--;
		
		code += "Joint:" + (i + 1) + ";" + (block + 1) + "\n";
		code += "-" + (i + 1) + "\n";
		code += i + ";" + block + "\n";
		code += i + "->" + (i + 1) + "\n";
				
		return o;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {
		int i = index++;
		
		block += 1;
		
		ifPartSize++;
		int new_size1 = (Integer) ast.s1.visit(this, 0);
		ifPartSize--;
		
		ifPartSize += new_size1 + 1;
				
		block -= 1;
		
		int j = index - 1;
		
		block++;
		
		int new_size2 = (Integer) ast.s2.visit(this, 0);
		ifPartSize -= new_size1 + 1;
		
		Integer size = (Integer) o;
		o = new Integer(size + new_size1 + new_size2);
		
		block--;
		
		code += (i + 1) + "|" + (j + 1) + "\n";
		code += i + ";" + block + "\n";
		code += i + "->" + (i + 1) + "\n";
		
		return o;
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		int i = index++;
		
		List<Integer> tempindexBlock = new ArrayList<Integer>(indexBlock);
		
		block++;
		int size = (Integer) o;
		int new_size = (Integer) ast.o.visit(this, 0);
		block--;
		
		if (tempindexBlock.size() == indexBlock.size()) {
			indexBlock.add(size - 1 + ifPartSize);
			lengthBlock.add(new_size + 1);
		} else {
			indexBlock.add(tempindexBlock.size(), size - 1 + ifPartSize);
			for (int j = 1; j < indexBlock.size(); j++) {
				indexBlock.set(j, indexBlock.get(j) + indexBlock.get(0) + 1);
			}
			lengthBlock.add(tempindexBlock.size(), new_size + 1);
		}
		
		o = new Integer(size + (new_size + 1) * 3);
		
		code += "Joint:" + (i + 1) + ";" + (block + 1) + "\n";
		code += "L" + (i + 1) + "\n";
		code += i + ";" + block + "\n";
		code += i + "->" + (i + 1) + "\n";
			
		return o;
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