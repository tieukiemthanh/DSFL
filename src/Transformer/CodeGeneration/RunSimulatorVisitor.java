// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;
/*
class Var {

	private String name, type, value;

	public Var(String name, String type, String value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}

class VarTable {
	
	List<Var> table = new ArrayList<Var>();
	
	public void addVar(Var v) {
		table.add(v);
	}
	
	public Var getVar(String name) {
		for (Var v : table) {
			if (v.getName().equals(name)) {
				return v;
			}
		}
		
		return null;
	}
	
}
*/
public class RunSimulatorVisitor extends DoNothingVisitor {

	String ret; //ket qua tra ve
	
	VarTable varTable; 
	
	List<String> listInput; //tham so truyen vao
	
	public RunSimulatorVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		ret = "";
		varTable = new VarTable();
		listInput = new ArrayList<String>();
		
		//Object o la testcase truyen vao
		// day co dang a;b
		String[] arrayInput = ((String) o).split(";");
		
		for (String input : arrayInput) {
			listInput.add(input);
		}
			
		// visitDeclarationListAST
		ast.dl.visit(this, null);
		
		return ret;
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		ast.d.visit(this, null);
		ast.dl.visit(this, null);
		
		return null;
	}
	
	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		String value = ast.init.visit(this, o).toString();
		//trinhgiang-16/10/2013
		//them xu ly voi kieu float
		if(ast.t instanceof FloatTypeAST) {
			Var v = new Var(ast.id.getText(), "float", value);
			varTable.addVar(v);
		}
		else {
			Var v = new Var(ast.id.getText(), "integer", value);
			varTable.addVar(v);
		}
			
		return null;
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
		fAst.c.visit(this, null);
		
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
		String value = listInput.remove(0);
		Var v = null;
		
		if (value.contains("!")) {
			v = new Var(ast.id.getText(), "array", value);
		} else {
			//trinhgiang-16/10/2013
			//them xu ly voi kieu float
			//if(ast.t instanceof FloatTypeAST)
			if(value.contains("."))
			{
				System.out.println("Da bat duoc bien float");
				v = new Var(ast.id.getText(), "float", value);
			}
			else v = new Var(ast.id.getText(), "integer", value);
		}
		
		varTable.addVar(v);
		
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
		// visitOneStmtAST
		ast.o.visit(this, o);
				
		// visitStmtListAST or visitEmptyStmtListAST
		ast.s.visit(this, o);
		
		return null;
	}
	
	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		ast.dl.visit(this, o);
		
		return null;
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		ast.e.visit(this, o);
		
		return null;
	}
	
	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {		
		boolean b = (Boolean) ast.e.visit(this, null);
		
		if (b) {
			ast.s.visit(this, o);
		}
				
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {	
		boolean b = (Boolean) ast.e.visit(this, null);
		
		if (b) {
			ast.s1.visit(this, o);
		} else {
			ast.s2.visit(this, o);
		}
				
		return null;
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		boolean b = (Boolean) ast.e.visit(this, null);
		
		while (b) {
			ast.o.visit(this, o);
			b = (Boolean) ast.e.visit(this, null);
		}
				
		return null;
	}
	
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		if (ret.equals("")) {
			ret += ast.e.visit(this, o);
		}
		
		return null;
	}
	
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		Var v = varTable.getVar(ast.name.getText());
		//trinhgiang-16/10/2013
		//them phan xu ly kieu float
		if(v.getType().equals("float"))
		{
			//return Float.parseFloat(v.getValue() + "f"); 
			return new Float(v.getValue() + "f"); 
		}
		else if (v.getType().equals("integer")) {
			System.out.println("Da vao phan bien int");
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
		//trinhgiang-18/10/2013
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
	
	//trinhgiang-16/10/2013
	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {
		//return float value
		//return Float.parseFloat(ast.literal.getText()+"f");
		return new Float(ast.literal.getText()+"f");
	}
	
	// UnaryExprAST
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		Object e = ast.e.visit(this, null);		
		
		//mot so toan tu khac chua xu ly
		if (ast.opType == UnaryExprAST.UNARY_MINUS) {
			//trinhgiang-16/10/2013
			//them xu ly voi kieu float
			if(e instanceof Float)
			{	//return - (Float) e;
				System.out.println("Unary -float");
				System.out.println(new Float("-" + e.toString() + "f"));
				return new Float("-" + e.toString() + "f");
			}
			else 
				return - (Integer) e;
		} else {
			//logical not
			return ! (Boolean) e;
		}
	}
	
	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		//assignment statement
		if (ast.opType == BinExprAST.ASSIGN) {
			//lhs is variable
			if (ast.e1 instanceof VarExprAST) {
				String name = ((VarExprAST) ast.e1).name.getText();
				Var v = varTable.getVar(name);
				
				Object e2 = ast.e2.visit(this, null);
				v.setValue(e2.toString());
			} else {
				//lhs is array element
				String name = ((EleExprAST) ast.e1).name.getText();
				int i = (Integer) ((ExprListAST) ((EleExprAST) ast.e1).e).e.visit(this, null);
				Var v = varTable.getVar(name);
				
				Object e2 = ast.e2.visit(this, null);
				v.setArrayValue(i, e2.toString());
			}
			
			return null;
		}
		//trinhgiang-18/10/2013
		else 
		{
			
			//airthm operator
			Object e2 = ast.e2.visit(this, null);
			System.out.println("Binary");
			Object e1 = ast.e1.visit(this, null);
			
			//trinhgiang-16/10/2013
			//added deal with float type
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
					System.out.println("Truong hop int > int");
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
					System.out.println("Truong hop float > int");
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
