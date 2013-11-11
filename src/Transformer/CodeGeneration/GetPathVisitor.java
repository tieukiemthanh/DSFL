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
	
	// break statement apply on while, for statement
	static int scopeBreak = 0;
	// break statement apply on case statemnet
	static int switchBreak = 0;
	// continue statement apply on while, for statement
	static int scopeContinue = 0;

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
		scopeContinue = 0;
		
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
		// trinhgiang-18/10/2013
		// them xu ly voi kieu float
		Var v = null;
		//kieu array
		if(ast.t instanceof TypeListAST) {
			if(((TypeListAST)ast.t).t instanceof ArrayTypeAST) {
				v = new Var(ast.id.getText(), "array", value);
			}
			else if(((TypeListAST)ast.t).t instanceof FloatTypeAST) {
				v = new Var(ast.id.getText(), "float", value);
			}
			else if(((TypeListAST)ast.t).t instanceof IntTypeAST) {
				v = new Var(ast.id.getText(), "integer", value);
			}
			else if(((TypeListAST)ast.t).t instanceof BoolTypeAST) {
				v = new Var(ast.id.getText(), "boolean", value);
			}
		}
		varTable.addVar(v);
		String path = ast.label + ";";
			
		return path;
	}
	// initializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {
		return ast.e.visit(this, o);
	}
	// list variable initializer
	public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o)
			throws CompilationException {
		// for array value
		if(ast.vl instanceof EmptyVarInitializerListAST)
			return ast.v.visit(this, o);
		else 
			return ast.v.visit(this, o) + "!" + ast.vl.visit(this, o);
	}
	// array initializer
	public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o)
			throws CompilationException {
		return ast.v.visit(this, o);
	}
	// for initializer
	public Object visitForInitAST(ForInitAST ast, Object o)
			throws CompilationException {
	    //if(ast.type == 1)
			//ast.d.visit(this, null);
		//else if(ast.type == 2)
		ast.e.visit(this, null);
		return null;
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
		
		if(ast.t instanceof TypeListAST) {
			if ((((TypeListAST)ast.t).t instanceof ArrayTypeAST) && value.contains("!")) {
				v = new Var(ast.id.getText(), "array", value);
			}
			else if(((TypeListAST)ast.t).t instanceof FloatTypeAST) {
				v = new Var(ast.id.getText(), "float", value);
			}
			else if(((TypeListAST)ast.t).t instanceof IntTypeAST) {
				v = new Var(ast.id.getText(), "integer", value);
			}
			else if(((TypeListAST)ast.t).t instanceof BoolTypeAST) {
				v = new Var(ast.id.getText(), "boolean", value);
			}
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
      int tempContinue = scopeContinue;
        		
		String path1 = "";
		// false case statement
		if(o != null && o.toString().startsWith("caseF")) {
			if(ast.o instanceof CaseStmtAST || ast.o instanceof DefaultStmtAST){
				path1 = (String) ast.o.visit(this, o);
			}
			else {
				
			}
		}
		else {
			path1 = (String) ast.o.visit(this, o);
		}
		//sign teminal statement
		if(signalRet) return path1;
		else {
			// break statement
			if(o != null && (o.toString().equals("while") || o.toString().equals("for") || o.toString().equals("dowhile")) && (tempScope > scopeBreak || tempContinue > scopeContinue))
		   {
				System.out.println("BreakorContinueWhile");
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
	    {
			System.out.println("Da qua while break");
			scopeBreak--;
		}
		else if(o != null && o.toString().equals("for"))
		{
			System.out.println("Da qua for break");
			scopeBreak--;
		}
		else if(o != null && o.toString().equals("dowhile"))
		{
			System.out.println("Da qua dowhile break");
			scopeBreak--;
		}
		return path;
	}
	// trinhgiang-29/10/2013
	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
		String path = ast.label + ";";
		//thoat ra khoi vong while, for
		if(o != null && o.toString().equals("while"))
	    {
			System.out.println("Da qua while continue");
			scopeContinue--;
		}
		else if(o != null && o.toString().equals("for"))
		{
			System.out.println("Da qua for continue");
			scopeContinue--;
		}
		else if(o != null && o.toString().equals("dowhile"))
		{
			System.out.println("Da qua dowhile continue");
			scopeContinue--;
		}
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
	
	// trinhgiang-28/10/2013
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		boolean b = (Boolean) ast.e.visit(this, null);
		String path = ast.label + ";";
		scopeBreak++;
		scopeContinue++;
		int tempContinue = scopeContinue;
		int tempScope = scopeBreak;
		while (b) {
			path += ast.o.visit(this, "while");
			// continue statement
			if(scopeContinue < tempContinue)
			{
				b = (Boolean) ast.e.visit(this, null);
				path += ast.label + ";";
			}	
			// break statement
			else if(scopeBreak >= tempScope) {
				b = (Boolean) ast.e.visit(this, null);
				path += ast.label + ";";
			}
			else
			{
				b = false;
			}
		}
		scopeContinue = tempContinue - 1;
		scopeBreak = tempScope - 1;
		return path;
	}
	// trinhgiang-29/10/2013
	// ForStmtAST
	public Object visitForStmtAST(ForStmtAST ast, Object o)
			throws CompilationException {
		ast.e1.visit(this, null);
		boolean b = (Boolean) ast.e2.visit(this, null);
		String path = ast.label + ";";
		scopeBreak++;
		scopeContinue++;
		int tempContinue = scopeContinue;
		int tempScope = scopeBreak;
		while (b) {
			path += ast.o.visit(this, "for");
			// continue statement
			if(scopeContinue < tempContinue) {
				ast.e3.visit(this, null);
				b = (Boolean) ast.e2.visit(this, null);
				path += ast.label + ";";
			}
			// break statement
			else if(scopeBreak >= tempScope) {
				ast.e3.visit(this, null);
				b = (Boolean) ast.e2.visit(this, null);
				path += ast.label + ";";
			}
			else
			{
				b = false;
			}
		}
		scopeBreak = tempScope - 1;
		scopeContinue = tempContinue - 1;
		return path;
	}
	// trinhgiang-29/10/2013
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST ast, Object o)
			throws CompilationException {
		String path = ast.label + ";";
		scopeBreak++;
		scopeContinue++;
		int tempContinue = scopeContinue;
		int tempScope = scopeBreak;
		
		path += ast.o.visit(this, "dowhile");
		// continue statement
		if((scopeContinue < tempContinue) || (scopeBreak >= tempScope))
		{
			boolean b = (Boolean) ast.e.visit(this, null);
			while (b) {
				path += ast.o.visit(this, "dowhile");
				// continue statement
				if(scopeContinue < tempContinue) {
					b = (Boolean) ast.e.visit(this, null);
					path += ast.label + ";";
				}
				else if(scopeBreak >= tempScope) {
					b = (Boolean) ast.e.visit(this, null);
					path += ast.label + ";";
				}
				else
				{
					// break statement
					b = false;
				}
			}
		}
		// break statement
		else if(scopeBreak < tempScope) {
		}
		scopeBreak = tempScope - 1;
		scopeContinue = tempContinue - 1;
		return path;
	}
	// trinhgiang-29/10/2013
	// ExprListAST
	public Object visitExprListAST(ExprListAST ast, Object o)
			throws CompilationException {
		ast.e.visit(this, null);
		ast.l.visit(this, null);
		return null;
	}
	// trinhgiang-28/10/2013
	// switch statement
	// not complete
	public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o)
			throws CompilationException {
		String path = sAst.label + ";";
		String valueSwitch = sAst.e.visit(this, null).toString();
		path += sAst.o.visit(this, valueSwitch);
		return path;
	}
	// trinhgiang-28/10/2013
	// case statement
	public Object visitCaseStmtAST(CaseStmtAST cAst, Object o)
			throws CompilationException {
		String path = cAst.label + ";";
		String valueCase = cAst.e.visit(this, null).toString();
		if(o != null && o.toString().equals("caseT")) {
			System.out.println("Da tim ra case true");
			return "";
		}
		if(o != null && o.toString().startsWith("caseF")) {
			//System.out.println("Chay trong case false");
			if(valueCase.equals(o.toString().substring(5)))
			{   
				path += cAst.s.visit(this, "caseT");
			}
			else {
				path += cAst.s.visit(this, o);
			}
		}
		// first case statement
		else {
			//System.out.println("Chay trong first case");
			if(valueCase.equals(o.toString()))
			{   
				path += cAst.s.visit(this, "caseT");
			}
			else {
				path += cAst.s.visit(this, "caseF" + o.toString());
			}
		}
		return path;
	}
	// trinhgiang-29/10/2013
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST dAst, Object o)
			throws CompilationException {
		String path = dAst.label + ";";
		path += dAst.s.visit(this, "default");		
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
		// index of array element
		Integer i = (Integer) ((ExprListAST) ast.e).e.visit(this, null);
		// trinhgiang-29/10/2013
		String ele = v.getArrayValue(i);
		String eleType = v.getType();
		// only support one dimension array
		if(eleType.equals("float")) {
			// element is float literal
			return Float.parseFloat(ele);
		}
		else if(eleType.equals("boolean") && (ele.contains("true") || ele.contains("false"))) {
			// element is boolean literal
			return Boolean.parseBoolean(ele);
		}
		else if (eleType.equals("integer")) {
			// element is integer literal
			return Integer.parseInt(ele);
		}
		return null;
	}
	// IntLiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {		
		// return int value
		return Integer.parseInt(ast.literal.getText());
	}
	// BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {
		// return bool value
		return Boolean.parseBoolean(ast.literal.getText());
	}
	// trinhgiang-18/10/2013
	// FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {
		// return float value
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
