// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class TestMatrixVisitor extends DoNothingVisitor {

	boolean first = false;

	public List<Integer> indexBlock = new ArrayList<Integer>();
	public List<Integer> lengthBlock = new ArrayList<Integer>();
	//public List<Byte> kindLoop = new ArrayList<Byte>();
	
	int ifPartSize = 0;
	
	public TestMatrixVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {		
		List<List<Byte>> testMatrix = new ArrayList<List<Byte>>();
		
		// visitDeclarationListAST
		ast.dl.visit(this, testMatrix);
		/*
		for (List<Integer> testRow : testMatrix) {
			for (int i = 0; i < testRow.size(); i++) {
				if (testRow.get(i) == -1) {
					testRow.set(i, 1);
				}
			}
		}
		*/
		/*
		System.out.print("Index: ");
		for (byte b : indexBlock) {
			System.out.print(b + ", ");
		}
		System.out.println();		
		
		System.out.print("Length: ");
		for (byte b : lengthBlock) {
			System.out.print(b + ", ");
		}
		System.out.println();
		*/
		return testMatrix;
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
		first = true;
		
		ast.s.visit(this, o);
		
		return null;
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		List<List<Byte>> testMatrix = (List<List<Byte>>) o;
		
		if (first == true && !(ast.o instanceof WhileStmtAST)) {
			if (testMatrix.size() == 0) {
				testMatrix.add(new ArrayList<Byte>(Arrays.asList((byte) 1)));
			} else {
				for (List<Byte> testRow : testMatrix) {
					if (testRow.contains((byte)-1)) {
						testRow.add((byte)0);
					} else {
						testRow.add((byte)1);
					}
				}
			}
		}
		
		first = false;
		
		if (ast.o instanceof WhileStmtAST) {
			if (testMatrix.size() == 0) {
				testMatrix.add(new ArrayList<Byte>(Arrays.asList((byte)1)));
			} else {
				for (List<Byte> testRow : testMatrix) {
					if (testRow.contains((byte)-1)) {
						testRow.add((byte)0);
					} else {
						testRow.add((byte)1);
					}
				}
			}
		}
		
		// visitOneStmtAST
		ast.o.visit(this, o);
		
		if (ast.o instanceof RetStmtAST) {
			for (List<Byte> testRow : testMatrix) {
				if (testRow.get(testRow.size() - 1) == 1) {
					testRow.set(testRow.size() - 1, (byte)-1);
				}
			}
		}/* else if (ast.o instanceof BreakStmtAST) {
			for (List<Byte> testRow : testMatrix) {
				if (testRow.get(testRow.size() - 1) == 1) {
					testRow.set(testRow.size() - 1, (byte)-2);
				}
			}
		}*/
		
		if (ast.o instanceof IfThenStmtAST || ast.o instanceof IfThenElseStmtAST || ast.o instanceof WhileStmtAST) {
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
		List<List<Byte>> newTestMatrix = new ArrayList<List<Byte>>();
		
		ifPartSize += 1; // moi them
		
		first = true;
		ast.s.visit(this, newTestMatrix);
		
		ifPartSize -= 1; // moi them
		
		int size = newTestMatrix.get(0).size();
		
		List<Byte> zeroPart = new ArrayList<Byte>();
		for (int i = 0; i < size; i++) {
			zeroPart.add((byte) 0);
		}
		
		List<List<Byte>> testMatrix = (List<List<Byte>>) o;
		List<List<Byte>> oldTestMatrix = new ArrayList<List<Byte>>(testMatrix);
		
		testMatrix.clear();
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			if (!oldTestRow.contains((byte)-1)) {
				for (List<Byte> newTestRow : newTestMatrix) {
					List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
					testRow.addAll(newTestRow);
					testMatrix.add(testRow);
				}
			}
		}
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
			testRow.addAll(zeroPart);
			testMatrix.add(testRow);
		}
				
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {	
		List<List<Byte>> newTestMatrix1 = new ArrayList<List<Byte>>();
		
		ifPartSize += 1; // moi them
		
		first = true;
		ast.s1.visit(this, newTestMatrix1);
		
		ifPartSize -= 1; // moi them
		
		int size1 = newTestMatrix1.get(0).size();
		ifPartSize += size1 + 1; // moi them
		
		List<List<Byte>> newTestMatrix2 = new ArrayList<List<Byte>>();
		
		first = true;
		ast.s2.visit(this, newTestMatrix2);
		
		ifPartSize -= size1 + 1; // moi them
		
		List<Byte> zeroPart1 = new ArrayList<Byte>();
		for (int i = 0; i < size1; i++) {
			zeroPart1.add((byte)0);
		}
		
		int size2 = newTestMatrix2.get(0).size();
		
		List<Byte> zeroPart2 = new ArrayList<Byte>();
		for (int i = 0; i < size2; i++) {
			zeroPart2.add((byte)0);
		}
		
		List<List<Byte>> testMatrix = (List<List<Byte>>) o;
		List<List<Byte>> oldTestMatrix = new ArrayList<List<Byte>>(testMatrix);
		
		testMatrix.clear();
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			if (oldTestRow.contains((byte)-1)) {
				List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
				testRow.addAll(zeroPart1);
				testRow.addAll(zeroPart2);
				testMatrix.add(testRow);
			}
		}
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			if (!oldTestRow.contains((byte)-1)) {
				for (List<Byte> newTestRow : newTestMatrix1) {
					List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
					testRow.addAll(newTestRow);
					testRow.addAll(zeroPart2);
					testMatrix.add(testRow);
				}
			}
		}
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			if (!oldTestRow.contains((byte)-1)) {
				for (List<Byte> newTestRow : newTestMatrix2) {
					List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
					testRow.addAll(zeroPart1);
					testRow.addAll(newTestRow);
					testMatrix.add(testRow);
				}
			}
		}
		
		return null;
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		List<List<Byte>> testMatrix = (List<List<Byte>>) o;
		
		List<List<Byte>> newTestMatrix1 = new ArrayList<List<Byte>>();

		List<Integer> tempIndexBlock = new ArrayList<Integer>(indexBlock);
		
		first = true;
		
		ast.o.visit(this, newTestMatrix1);
		
		if (tempIndexBlock.size() == indexBlock.size()) {
			//indexBlock.add((byte) (testMatrix.get(0).size() - 1));
			indexBlock.add(testMatrix.get(0).size() - 1 + ifPartSize);
			lengthBlock.add(newTestMatrix1.get(0).size() + 1);
		} else {
			//indexBlock.add(tempIndexBlock.size(), (byte) (testMatrix.get(0).size() - 1));
			indexBlock.add(tempIndexBlock.size(), testMatrix.get(0).size() - 1 + ifPartSize);
			for (int i = 1; i < indexBlock.size(); i++) {
				indexBlock.set(i, indexBlock.get(i) + indexBlock.get(0) + 1);
			}
			lengthBlock.add(tempIndexBlock.size(), newTestMatrix1.get(0).size() + 1);
		}
		
		//kindLoop.add(0);
		
		for (List<Byte> newTestRow : newTestMatrix1) {
			if (newTestRow.contains((byte)-1)) {// || newTestRow.contains((byte)-2)) {
				newTestRow.add((byte)0);
			} else {
				newTestRow.add((byte)1);
			}
		}
		
		List<List<Byte>> newTestMatrix2 = new ArrayList<List<Byte>>();
		
		for (List<Byte> testRow1 : newTestMatrix1) {
			for (List<Byte> testRow2 : newTestMatrix1) {
				for (List<Byte> testRow3 : newTestMatrix1) {
					List<Byte> testRow = new ArrayList<Byte>(testRow1);
					int size = testRow.size();
					
					if (testRow.contains((byte)-1)) {// || testRow.contains((byte)-2)) {
						for (int i = 1; i <= size * 2; i++) {
							testRow.add((byte)0);
						}
					} else {
						testRow.addAll(testRow2);
						if (testRow.contains((byte)-1)) {// || testRow.contains((byte)-2)) {
							
							for (int i = 1; i <= size; i++) {
								testRow.add((byte)0);
							}
						} else {
							testRow.addAll(testRow3);
						}
					}
					newTestMatrix2.add(testRow);
				}
			}
		}
		
		int size1 = newTestMatrix2.get(0).size();
		
		List<Byte> zeroPart1 = new ArrayList<Byte>();
		for (int i = 0; i < size1; i++) {
			zeroPart1.add((byte)0);
		}
				
		int size2 = newTestMatrix2.get(0).size() - newTestMatrix1.get(0).size();
		
		List<Byte> zeroPart2 = new ArrayList<Byte>();
		for (int i = 0; i < size2; i++) {
			zeroPart2.add((byte)0);
		}
		
		for (List<Byte> testRow : newTestMatrix1) {
			testRow.addAll(zeroPart2);
		}
		
		List<List<Byte>> oldTestMatrix = new ArrayList<List<Byte>>(testMatrix);
		
		testMatrix.clear();
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
			testRow.addAll(zeroPart1);
			testMatrix.add(testRow);
		}
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			if (!oldTestRow.contains((byte)-1)) {
				for (List<Byte> newTestRow1 : newTestMatrix1) {
					List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
					testRow.addAll(newTestRow1);
					testMatrix.add(testRow);
				}
			}
		}
		
		for (List<Byte> oldTestRow : oldTestMatrix) {
			if (!oldTestRow.contains((byte)-1)) {
				for (List<Byte> newTestRow2 : newTestMatrix2) {
					List<Byte> testRow = new ArrayList<Byte>(oldTestRow);
					testRow.addAll(newTestRow2);
					testMatrix.add(testRow);
				}
			}
		}
		/*
		for (List<Byte> testRow : testMatrix) {
			for (int i = 0; i < testRow.size(); i++) {
				if (testRow.get(i) == -2) {
					testRow.set(i, (byte)1);
				}
			}
		}
		*/
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