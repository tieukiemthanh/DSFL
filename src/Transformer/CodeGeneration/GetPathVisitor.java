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
	
	static boolean signalRet = false;
	
	//break statement apply on while statement
	static int scopeBreak = 0;
	//break statement apply on case statemnet
	static int switchBreak = 0;

	public GetPathVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		varTable = new VarTable();
		listInput = new ArrayList<String>();
		signalRet = false;
		scopeBreak = 0;
		switchBreak = 0;

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
		//kieu array
		if(ast.t instanceof ArrayTypeAST) {
			v = new Var(ast.id.getText(), "array", value);
		}
		else if(ast.t instanceof FloatTypeAST)
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
	    //duyet parameter cua ham
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
		else if(value.contains(".")) {
			v = new Var(ast.id.getText(), "float", value);
		}
		else {
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
		// trinhgiang-22/10/2013
		int tempScope = scopeBreak;
		int tempSwitchScope = switchBreak;
		
		String path1 = (String) ast.o.visit(this, o);
		//sign teminal statement
		if(signalRet) return path1;
		else {
			// break statement
			if(o != null && o.toString().equals("while") && tempScope > scopeBreak)
		    {
				System.out.println("aaaaaaaaBreakWhile");
				return path1;
			}
			// case break statement
			if(o != null && (o.toString().equals("case") || o.toString().startsWith("switch")) && tempSwitchScope > switchBreak)
			{
				//switchBreak = tempSwitchBreak;
				System.out.println("aaaaaaaaBreakSwitch");
				return path1;
			}
		}
		
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
	//trinhgiang-22/10/2013
	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		String path = ast.label + ";";
		//thoat ra khoi vong while, for, case
		if(o != null && o.toString().equals("while"))
			scopeBreak--;
		if(o != null && o.toString().equals("case"))
			switchBreak--;
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
		} 
		else {
			path += ast.s2.visit(this, o);
		}
				
		return path;
	}
	
	//trinhgiang-28/10/2013
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		boolean b = (Boolean) ast.e.visit(this, null);
		String path = ast.label + ";";
		scopeBreak++;
		int tempScope = scopeBreak;
		while (b) {
			path += ast.o.visit(this, "while");
			if(scopeBreak >= tempScope) {
				b = (Boolean) ast.e.visit(this, null);
				path += ast.label + ";";
			}
			else
			{
				b = false;
			}
		}
		scopeBreak = tempScope - 1;
		return path;
	}
	//trinhgiang-28/10/2013
	// switch statement
	public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o)
			throws CompilationException {
		String path = sAst.label + ";";
		String valueSwitch = sAst.e.visit(this, null).toString();
		switchBreak++;
		int tempSwitchBreak = switchBreak;
		path += sAst.o.visit(this, "switch" + valueSwitch);
		switchBreak = tempSwitchBreak - 1;
		return path;
	}
	//trinhgiang-28/10/2013
	// case statement
	public Object visitCaseStmtAST(CaseStmtAST cAst, Object o)
			throws CompilationException {
		String path = cAst.label + ";";
		String valueCase = cAst.e.visit(this, null).toString();
		if(valueCase.trim().equals(o.toString().trim().substring(6)))
		{   
			path += cAst.s.visit(this, "case");
		}		
		return path;
	}
	// return statement
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		//trinhgiang-28/10/2013
		signalRet = true;
		return ast.label + ";";
	}
	
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		Var v = varTable.getVar(ast.name.getText());
		if(v.getType().equals("float")){
			return Float.parseFloat(v.getValue());
		}
		else if (v.getType().equals("integer")) {
			return Integer.parseInt(v.getValue());
		} 
		else if (v.getType().equals("boolean")) {
			return Boolean.parseBoolean(v.getValue());
		} 
		else if (v.getType().equals("array")) {
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
		return Float.parseFloat(ast.literal.getText());
	}
	
	// UnaryExprAST	
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		Object e = ast.e.visit(this, null);		
		
		if (ast.opType == UnaryExprAST.UNARY_MINUS) {
			if(e instanceof Float)
				return - (Float) e;
			else
				return - (Integer) e;
		} 
		else {
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
			} 
			else {
				String name = ((EleExprAST) ast.e1).name.getText();
				//index of array element
				int i = (Integer) ((ExprListAST) ((EleExprAST) ast.e1).e).e.visit(this, null);
				Var v = varTable.getVar(name);
				
				Object e2 = ast.e2.visit(this, null);
				v.setArrayValue(i, e2.toString());
			}
			
			return null;
		} 
		else {
			//airthm operator
			Object e2 = ast.e2.visit(this, null);
			Object e1 = ast.e1.visit(this, null);
		
			// e1 + e2
			if (ast.opType == BinExprAST.PLUS) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Integer(v1+v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1+v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1+v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Float(v1+v2);
				}
			} 
			// e1 - e2
			else if (ast.opType == BinExprAST.MINUS) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Integer(v1-v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1-v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1-v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Float(v1-v2);
				}
			} 
			// e1 * e2
			else if (ast.opType == BinExprAST.STAR) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Integer(v1*v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1*v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1*v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Float(v1*v2);
				}
			} 
			// e1 / e2
			else if (ast.opType == BinExprAST.DIV) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Integer(v1/v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1/v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Float(v1/v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Float(v1/v2);
				}
			} 
			// e1 MOD e2
			else if (ast.opType == BinExprAST.MOD) 
			{
				//toan tu nay chi thao tac voi so nguyen
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Integer(v1%v2);
				}
			} 
			// e1 && e2
			else if (ast.opType == BinExprAST.LOGICAL_AND) 
			{
				if (e1 instanceof Boolean && e2 instanceof Boolean) {
					boolean v1 = ((Boolean) e1).booleanValue();
					boolean v2 = ((Boolean) e2).booleanValue();
					return new Boolean(v1&&v2);
				}
			} 
			// e1 || e2
			else if (ast.opType == BinExprAST.LOGICAL_OR) 
			{
				if (e1 instanceof Boolean && e2 instanceof Boolean) {
					boolean v1 = ((Boolean) e1).booleanValue();
					boolean v2 = ((Boolean) e2).booleanValue();
					return new Boolean(v1||v2);
				}
			} 
			// e1 <= e2
			else if (ast.opType == BinExprAST.LESS_OR_EQUAL) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1<=v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1<=v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1<=v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1<=v2);
				}
			} 
			// e1 >= e2
			else if (ast.opType == BinExprAST.GREATER_OR_EQUAL) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1>=v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1>=v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1>=v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1>=v2);
				}
			}
			// e1 > e2  
			else if (ast.opType == BinExprAST.GREATER_THAN) 
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
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1>v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1>v2);
				}
			}
			// e1 < e2 
			else if (ast.opType == BinExprAST.LESS_THAN) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1<v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1<v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1<v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1<v2);
				}
			}
			// e1 == e2 
			else if (ast.opType == BinExprAST.EQUAL) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1==v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1==v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1==v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1==v2);
				}
			} 
			// e1 != e2
			else if (ast.opType == BinExprAST.NOT_EQUAL) 
			{
				if (e1 instanceof Integer && e2 instanceof Integer) {
					int v1 = ((Integer) e1).intValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1!=v2);
				}
				else if (e1 instanceof Float && e2 instanceof Float) {
					float v1 = ((Float) e1).floatValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1!=v2);
				} 
				else if (e1 instanceof Integer && e2 instanceof Float) {
					int v1 = ((Integer) e1).intValue();
					float v2 = ((Float) e2).floatValue();
					return new Boolean(v1!=v2);
				} 
				else {
					float v1 = ((Float) e1).floatValue();
					int v2 = ((Integer) e2).intValue();
					return new Boolean(v1!=v2);
				}
			}
			
			return null;
		}
	}
	
}
