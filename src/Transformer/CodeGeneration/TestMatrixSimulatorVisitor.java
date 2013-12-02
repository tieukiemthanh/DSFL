// This file is written specially for sorting checker with input is
// an one-dimension integer array, so some methods have not been
// implemented yet.
// Special thank to Mr. Hoang Le Nghia Duc. He is the original author
// of this file. I am just an inheritor.

package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

class Var {

	private String name, type, value, arrayEleType;

	public Var(String name, String type, String value) {
		this.name = name;
		this.type = type;
		
		if(value.contains(".")) {
			this.value = String.format("%.9f", (new Float(value)).floatValue());
		}
		else { 
			this.value = value;
		}
	}
	public Var(String name, String type, String value, String arrayEleType) {
		this.name = name;
		this.type = type;
		if(value.contains(".")) {
			this.value = String.format("%.9f", (new Float(value)).floatValue());
		}
		else { 
			this.value = value;
		}
		this.arrayEleType = arrayEleType;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		if(value.contains(".")) {
			return String.format("%.9f", (new Float(value)).floatValue());
		}
		else { 
			return value;
		}
	}
	
	public String getEleType() {
		return arrayEleType;
	}
	
	public void setValue(String value) {
		
		if(value.contains(".")) {
			this.value = String.format("%.9f", (new Float(value)).floatValue());
		}
		else { 
			this.value = value;
		}
	}
	
	public String getArrayValue(int index) {
		String[] arrayValue = this.value.split("!");
		if(arrayValue[index].contains(".")) {
			return String.format("%.9f", (new Float(arrayValue[index])).floatValue());
		}
		else { 
			return arrayValue[index];
		}
	}
	
	public void setArrayValue(int index, String value) {
		String[] arrayValue = this.value.split("!");
		if(value.contains(".")) {
			arrayValue[index] = String.format("%.9f", (new Float(value)).floatValue());
		}
		else { 
			arrayValue[index] = value;
		}
		
		
		this.value = "";
		for (int i = 0; i < arrayValue.length; i++) {
			this.value += arrayValue[i] + "!";
		}
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

public class TestMatrixSimulatorVisitor extends DoNothingVisitor {

	boolean first = false;
	
	String path = "";
	
	String retPath = null;
	
	int numBlock = -1;
	
	VarTable varTable = new VarTable();
	
	List<String> listInput = new ArrayList<String>();
	
	public TestMatrixSimulatorVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		String[] arrayInput = ((String) o).split(";");
		
		for (String input : arrayInput) {
			listInput.add(input);
		}
		// visitDeclarationListAST
		ast.dl.visit(this, null);
		
		if (retPath == null) {
			//System.out.println(path);
			return path;
		} else {
			//sSystem.out.println(retPath);
			return retPath;
		}
		
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
		if (o == null) {
			String value = ast.init.visit(this, o).toString();
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
			v = new Var(ast.id.getText(), "integer", value);
		}
		
		varTable.addVar(v);
		
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
		if (!(ast.o instanceof IfThenStmtAST) && !(ast.o instanceof IfThenElseStmtAST) &&
				!(ast.o instanceof WhileStmtAST) && first == true) {
			numBlock++;
			
			if (o == null) {
				path += "S" + numBlock;
			}
			
			first = false;
		}
		
		// visitOneStmtAST
		ast.o.visit(this, o);
				
		if ((ast.o instanceof IfThenStmtAST) || (ast.o instanceof IfThenElseStmtAST) ||
				(ast.o instanceof WhileStmtAST)) {
			first = true;
		}
		
		if (ast.o instanceof RetStmtAST && retPath == null && o == null) {
			retPath = path;
		}
		
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
		if (first == true) {
			numBlock++;
			if (o == null) {
				path += "S" + numBlock;
			}
		}
		
		boolean b = false;
		if (o == null) {
			b = (Boolean) ast.e.visit(this, null);
		}
		
		first = true;
		
		if (o == null) {
			if (b) {
				ast.s.visit(this, o);
			} else {
				ast.s.visit(this, "");
			}
		} else {
			ast.s.visit(this, "");
		}
				
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {	
		if (first == true) {
			numBlock++;
			if (o == null) {
				path += "S" + numBlock;
			}
		}
						
		boolean b = false;
		if (o == null) {
			b = (Boolean) ast.e.visit(this, null);
		}
		
		first = true;
		
		if (o == null) {
			if (b) {
				ast.s1.visit(this, o);
				ast.s2.visit(this, "");
			} else {
				ast.s1.visit(this, "");
				ast.s2.visit(this, o);
			}
		} else {
			ast.s1.visit(this, "");
			ast.s2.visit(this, "");
		}
				
		return null;
	}
	
	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		int temp = ++numBlock;
		
		if (o == null) {
			path += "S" + numBlock;
		}
		
		boolean b = false;
		if (o == null) {
			b = (Boolean) ast.e.visit(this, null);
		}
		
		if (o == null) {
			while (b) {
				ast.o.visit(this, o);
				
				numBlock = temp;
				
				if (o == null) {
					path += "S" + numBlock;
				}
				
				b = (Boolean) ast.e.visit(this, null);
			}
			
			ast.o.visit(this, "");
		} else {
			ast.o.visit(this, "");
		}
		
		return null;
	}
	
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		Var v = varTable.getVar(ast.name.getText());
		
		if (v.getType().equals("integer")) {
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
	
	// UnaryExprAST	
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		Object e = ast.e.visit(this, null);		
		
		if (ast.opType == UnaryExprAST.UNARY_MINUS) {
			return - (Integer) e;
		} else {
			return ! (Boolean) e;
		}
	}
	
	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		if (o == null) {
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
			} else {
				Object e1 = ast.e1.visit(this, null);
				Object e2 = ast.e2.visit(this, null);
				
				if (ast.opType == BinExprAST.PLUS) {
					return (Integer) e1 + (Integer) e2;
				} else if (ast.opType == BinExprAST.MINUS) {
					return (Integer) e1 - (Integer) e2;
				} else if (ast.opType == BinExprAST.STAR) {
					return (Integer) e1 * (Integer) e2;
				} else if (ast.opType == BinExprAST.DIV) {
					return (Integer) e1 / (Integer) e2;
				} else if (ast.opType == BinExprAST.MOD) {
					return (Integer) e1 % (Integer) e2;
				} else if (ast.opType == BinExprAST.LOGICAL_AND) {
					return (Boolean) e1 && (Boolean) e2;
				} else if (ast.opType == BinExprAST.LOGICAL_OR) {
					return (Boolean) e1 || (Boolean) e2;
				} else if (ast.opType == BinExprAST.LESS_OR_EQUAL) {
					return (Integer) e1 <= (Integer) e2;
				} else if (ast.opType == BinExprAST.GREATER_OR_EQUAL) {
					return (Integer) e1 >= (Integer) e2;
				} else if (ast.opType == BinExprAST.GREATER_THAN) {
					return (Integer) e1 > (Integer) e2;
				} else if (ast.opType == BinExprAST.LESS_THAN) {
					return (Integer) e1 < (Integer) e2;
				} else if (ast.opType == BinExprAST.EQUAL) {
					return (Integer) e1 == (Integer) e2;
				} else if (ast.opType == BinExprAST.NOT_EQUAL) {
					return (Integer) e1 != (Integer) e2;
				}
			}
		}
		
		return null;
	}
	
}
