
package Transformer.CodeGeneration;

import Transformer.ASTs.*;
import org.antlr.runtime.*;
import Transformer.DependenceGraph.*;
import java.util.*;

/***********************************************************************************
Visitor duyet qua cay AST de tao ra MappingTable
***********************************************************************************/
public class Ast2MappingTableVisitor extends DoNothingVisitor {
	MappingTable mapTable;
	
	public Ast2MappingTableVisitor() {
		mapTable = new MappingTable();
	}

	public MappingTable getMappingTable() {return mapTable;}
	
	public void println(Object o) {
		System.out.println(o);
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException {		
		ast.dl.visit(this, o);
		return null;
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		ast.d.visit(this, o);
		ast.dl.visit(this, o);
		return null;
	}
	
	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		//System.out.println("Declaration stmt" + ast.line);
		mapTable.addMappingNode(new MappingNode(ast.line, ast));
		ast.dl.visit(this, o);
		return null;
	}	

	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		fAst.c.visit(this, o);
		return null;
	}
	
	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST cAst, Object o)
			throws CompilationException {
		cAst.s.visit(this, o);
		return null;
	}

	// StmtListAST
	public Object visitStmtListAST(StmtListAST sAst, Object o)
			throws CompilationException {
		sAst.o.visit(this, o);
		sAst.s.visit(this, o);
		return null;
	}

	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		mapTable.addMappingNode(new MappingNode(ast.line, ast));
		ast.e.visit(this, o);
		return null;
	}

	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST iAst, Object o)
			throws CompilationException {
		mapTable.addMappingNode(new MappingNode(iAst.line, iAst));
		iAst.e.visit(this, o);
		iAst.s.visit(this, o);
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST iAst, Object o)
			throws CompilationException {
		mapTable.addMappingNode(new MappingNode(iAst.line, iAst));
		iAst.e.visit(this, o);
		iAst.s1.visit(this, o);
		iAst.s2.visit(this, o);
		return null;
	}

	// ForStmtAST
	public Object visitForStmtAST(ForStmtAST fAst, Object o)
			throws CompilationException {
		mapTable.addMappingNode(new MappingNode(fAst.line, fAst));
		if (fAst.e1 != null)
			fAst.e1.visit(this, o);
		if (fAst.e2 != null)
			fAst.e2.visit(this, o);
		if (fAst.e3 != null)
			fAst.e3.visit(this, o);
		fAst.o.visit(this, o);
		return null;
	}
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST wAst, Object o)
			throws CompilationException {
		mapTable.addMappingNode(new MappingNode(wAst.line, wAst));
		wAst.e.visit(this, o);
		wAst.o.visit(this, o);
		return null;
	}
	
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST wAst, Object o)
			throws CompilationException {
		mapTable.addMappingNode(new MappingNode(wAst.line, wAst));
		wAst.o.visit(this, o);
		wAst.e.visit(this, o);
		return null;
	}

	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST rAst, Object o)
			throws CompilationException {
		if (rAst.e != null) {
			mapTable.addMappingNode(new MappingNode(rAst.line, rAst));
			rAst.e.visit(this, o);
		}
		return null;
	}

	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST breakAst, Object o)
			throws CompilationException {
		mapTable.addMappingNode(new MappingNode(breakAst.line, breakAst));
		return null;
	}		
	//trinhgiang-28/10/2013
	// ContStmtAST
	// continue stmt
	public Object visitContStmtAST(ContStmtAST continueAst, Object o)
			throws CompilationException {
		//println("ContStmtAST: " + ast.line);
		mapTable.addMappingNode(new MappingNode(continueAst.line, continueAst));
		return null;
	}
	//trinhgiang-28/10/2013
	// SwitchStmtAST
	public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o)
			throws CompilationException {
		//println("SwitchStmtAST: " + sAst.line);
		mapTable.addMappingNode(new MappingNode(sAst.line, sAst));
		sAst.e.visit(this, o);
		sAst.o.visit(this, o);
		return null;
	}	
	//trinhgiang-28/10/2013
	// CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST cAst, Object o)
			throws CompilationException {
		//println("CaseStmtAST: " + cAst.line);
		mapTable.addMappingNode(new MappingNode(cAst.line, cAst));
		cAst.e.visit(this, o);
		cAst.s.visit(this, o);
		return null;
	}
	//trinhgiang-28/10/2013
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST dAst, Object o)
			throws CompilationException {
		//println("DefaultStmtAST: " + dAst.line);
		mapTable.addMappingNode(new MappingNode(dAst.line, dAst));
		dAst.s.visit(this, o);
		return null;
	}
	
}
