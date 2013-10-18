// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class GetPathVisitor extends DoNothingVisitor {

	VarTable varTable = new VarTable();
	
	List<String> listInput = new ArrayList<String>();

	public GetPathVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		varTable = new VarTable();
		listInput = new ArrayList<String>();

		String[] arrayInput = ((String) o).split(";");
		
		for (String input : arrayInput) {
			listInput.add(input);
		}
		// visitDeclarationListAST
		String path = (String) ast.dl.visit(this, null);
		
		return path;
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		String path1 = (String) ast.d.visit(this, null);
		String path2 = (String) ast.dl.visit(this, null);
		
		return path1 + path2;
	}
	
	// EmptyDeclarationListAST
	public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o)
			throws CompilationException {
		return "";
	}

	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		String value = ast.init.visit(this, o).toString();
		//trinhgiang-18/10/2013
		//them xu ly voi kieu float
		Var v = null;
		if(ast.t instanceof FloatTypeAST)
			v = new Var(ast.id.getText(), "float", value);
		else 
			v = new Var(ast.id.getText(), "integer", value);
		
		varTable.addVar(v);
		String path = ast.label + ";";
			
		return path;
	}
	
	// initializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {
		return ast.e.visit(this, o);
	}
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		fAst.para.visit(this, null);
		// visitCompStmtAST
		String path = (String) fAst.c.visit(this, null);
		
		return path;
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
		String value = listInput.remove(0);
		Var v = null;
		
		if (value.contains("!")) {
			v = new Var(ast.id.getText(), "array", value);
		}
		//trinhgiang-18/10/2013
		//them xu ly kieu float 
		else if(value.contains("."))
		{
			v = new Var(ast.id.getText(), "float", value);
		}
		else 
		{
			v = new Var(ast.id.getText(), "integer", value);
		}
		
		varTable.addVar(v);
		
		return null;
	}
	
	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {		
		// visitStmtListAST or visitEmptyStmtListAST
		String path = (String) ast.s.visit(this, o);
		
		return path;
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		// visitOneStmtAST
		String path1 = (String) ast.o.visit(this, o);
				
		// visitStmtListAST or visitEmptyStmtListAST
		String path2 = (String) ast.s.visit(this, o);
		
		return path1 + path2;
	}
	
	// EmptyStmtListAST
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o)
			throws CompilationException {
		return "";
	}

	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		String path = (String) ast.dl.visit(this, o);
		
		return path;
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		ast.e.visit(this, o);
		String path = ast.label + ";";
		
		return path;
	}
	
	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {		
		boolean b = (Boolean) ast.e.visit(this, null);

		String path = ast.label + ";";
		
		if (b) {
			path += ast.s.visit(this, o);
		}
				
		return path;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {	
		boolean b = (Boolean) ast.e.visit(this, null);
		
		String path = ast.label + ";";
		
		if (b) {
			path += ast.s1.visit(this, o);
		} else {
			path += ast.s2.visit(this, o);
		}
				
		return path;
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		boolean b = (Boolean) ast.e.visit(this, null);
		String path = ast.label + ";";
		
		while (b) {
			path += ast.o.visit(this, o);
			b = (Boolean) ast.e.visit(this, null);
			path += ast.label + ";";
		}
				
		return path;
	}
	
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		return ast.label + ";";
	}
	
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		Var v = varTable.getVar(ast.name.getText());
		if(v.getType().equals("float")){
			return Float.parseFloat(v.getValue() + "f");
		}
		else if (v.getType().equals("integer")) {
			return Integer.parseInt(v.getValue());
		} else if (v.getType().equals("boolean")) {
			return Boolean.parseBoolean(v.getValue());
		} else if (v.getType().equals("array")) {
			return v.getValue();
		}
		
		return null;
	}
	
	public Object visitEleExprAST(EleExprAST ast, Object o)
			throws CompilationException {
		Var v = varTable.getVar(ast.name.getText());
		Integer i = (Integer) ((ExprListAST) ast.e).e.visit(this, null);
		//xu ly sau
		return Integer.parseInt(v.getArrayValue(i));
	}
	
	// IntLiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {		
		// return int value
		return Integer.parseInt(ast.literal.getText());
	}
	
	//BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {
		// return bool value
		return Boolean.parseBoolean(ast.literal.getText());
	}
	
	//trinhgiang-18/10/2013
	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {
		//return float value
		return Float.parseFloat(ast.literal.getText() + "f");
	}
	
	// UnaryExprAST	
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		Object e = ast.e.visit(this, null);		
		
		if (ast.opType == UnaryExprAST.UNARY_MINUS) {
			if(e instanceof Float)
				//return -(Float) e;
				return new Float(e.toString() + "f");
			else
				return - (Integer) e;
		} else {
			return ! (Boolean) e;
		}
	}
	
	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		if (ast.opType == BinExprAST.ASSIGN) {
			if (ast.e1 instanceof VarExprAST) {
				String name = ((VarExprAST) ast.e1).name.getText();
				Var v = varTable.getVar(name);
				
				Object e2 = ast.e2.visit(this, null);
				v.setValue(e2.toString());
			} else {
				String name = ((EleExprAST) ast.e1).name.getText();
				int i = (Integer) ((ExprListAST) ((EleExprAST) ast.e1).e).e.visit(this, null);
				Var v = varTable.getVar(name);
				
				Object e2 = ast.e2.visit(this, null);
				v.setArrayValue(i, e2.toString());
			}
			
			return null;
		} else {
			Object e1 = ast.e1.visit(this, null);
			Object e2 = ast.e2.visit(this, null);
			//trinhgiang-18/10/2013
			//them xu ly voi kieu float
			if (ast.opType == BinExprAST.PLUS) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return (Float) e1 + (Float) e2;
				else 
					return (Integer) e1 + (Integer) e2;
			} else if (ast.opType == BinExprAST.MINUS) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return (Float) e1 - (Float) e2;
				else 
					return (Integer) e1 - (Integer) e2;
			} else if (ast.opType == BinExprAST.STAR) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return (Float) e1 * (Float) e2;
				else 
					return (Integer) e1 * (Integer) e2;
			} else if (ast.opType == BinExprAST.DIV) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return (Float) e1 / (Float) e2;
				else 
					return (Integer) e1 / (Integer) e2;
			} else if (ast.opType == BinExprAST.MOD) 
			{
				//toan tu nay chi thao tac voi so nguyen
				return (Integer) e1 % (Integer) e2;
			} else if (ast.opType == BinExprAST.LOGICAL_AND) 
			{
				return (Boolean) e1 && (Boolean) e2;
			} else if (ast.opType == BinExprAST.LOGICAL_OR) 
			{
				return (Boolean) e1 || (Boolean) e2;
			} else if (ast.opType == BinExprAST.LESS_OR_EQUAL) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return new Boolean((Float) e1 <= (Float) e2);
				else 
					return new Boolean((Integer) e1 <= (Integer) e2);
			} else if (ast.opType == BinExprAST.GREATER_OR_EQUAL) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return new Boolean((Float) e1 >= (Float) e2);
				else 
					return new Boolean((Integer) e1 >= (Integer) e2);
			} else if (ast.opType == BinExprAST.GREATER_THAN) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1>v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1>v2);
				} else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1>v2);
				} else {
					//float v1 = ((Float) e1).floatValue();
					float v1 = new Float(e1.toString() + "f");
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1>v2);
				}
			} else if (ast.opType == BinExprAST.LESS_THAN) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return new Boolean((Float) e1 < (Float) e2);
				else 
					return new Boolean((Integer) e1 < (Integer) e2);
			} else if (ast.opType == BinExprAST.EQUAL) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return new Boolean((Float) e1 == (Float) e2);
				else 
					return new Boolean((Integer) e1 == (Integer) e2);
			} else if (ast.opType == BinExprAST.NOT_EQUAL) 
			{
				if(e1 instanceof Float || e2 instanceof Float)
					return new Boolean((Float) e1 != (Float) e2);
				else 
					return new Boolean((Integer) e1 != (Integer) e2);
			}
			
			return null;
		}
	}
	
}
