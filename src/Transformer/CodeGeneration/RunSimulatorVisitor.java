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

	String ret; // ket qua tra ve
	
	VarTable varTable; 
	
	List<String> listInput; // tham so truyen vao
	
	static boolean signalRet = false;
	
	// break statement only apply on while statement, for statement
	static int scopeBreak = 0;
	// break statement apply on case statemnet
	static int switchBreak = 0;
	// continue statement apply on while, for statement
	static int scopeContinue = 0;
	
	public static int isPrime(int n) {
		int  flag = 1;
		int songuyento = 0;
		if (n == 0 || n == 1)
			flag = 1;
		for(int i = 2; i < n; i++) {
			if (n % i == 0)
				flag = 0;
		}
		if (flag == 1 || n == 2)
			songuyento = 1;
		return songuyento;
	}
	
	public static int isFibonaci(int n) {
		int fibonaci = 0;
	    int f = 0;
		int	f1 = 0;
		int f2 = 1;
		do {
			f = f1 + f2;
			f1 = f2;
			f2 = f;
		}
		while (f < n);
		if (f == n)
			fibonaci = 1;
		return fibonaci;
	}
	
	public static int intMax(int a, int b) {
		if( a > b) return a;
		else return b;
	}
	public static float floatMax(float a, float b) {
		if( a > b) return a;
		else return b;
	}
	public static int intMin(int a, int b) {
		if( a > b) return b;
		else return a;
	}
	public static float floatMin(float a, float b) {
		if( a > b) return b;
		else return a;
	}
	
	public static int intPow(int a, int n) {
		int ret = 1;
		for(int i = 0; i < n; i++) {
			ret = ret * a;
		}
		return ret;
	}
	public static float floatPow(float a, int n) {
		float ret = 1.0f;
		for(int i = 0; i < n; i++) {
			ret = ret * a;
		}
		return ret;
	}
	
	public RunSimulatorVisitor(String outputFile, boolean debug) throws CompilationException {
	
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		ret = "";
		varTable = new VarTable();
		listInput = new ArrayList<String>();
		signalRet = false;
		scopeBreak = 0;
		switchBreak = 0;
		scopeContinue = 0;
		
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
		
		Var v = null;
		//trinhgiang-16/10/2013
		//them xu ly voi kieu float
		//kieu array
		if(ast.t instanceof TypeListAST) {
			if(((TypeListAST)ast.t).t instanceof FloatTypeAST) {
				//System.out.println("float type");
				v = new Var(ast.id.getText(), "float", value, null);
			}
			else if(((TypeListAST)ast.t).t instanceof IntTypeAST){
				//System.out.println("integer type");
				v = new Var(ast.id.getText(), "integer", value, null);	
			}
			else if(((TypeListAST)ast.t).t instanceof BoolTypeAST)
			{
				//System.out.println("boolean type");
				v = new Var(ast.id.getText(), "boolean", value, null);
			}
		}
		else if(ast.t instanceof ArrayTypeAST) {
			TypeListAST arrType  = (TypeListAST) ((ArrayTypeAST)ast.t).type;
			if(arrType.t instanceof FloatTypeAST) {
				v = new Var(ast.id.getText(), "array", value, "float");
			}
			else if(arrType.t instanceof IntTypeAST) {
				v = new Var(ast.id.getText(), "array", value, "integer");
			}
			else if(((TypeListAST)ast.t).t instanceof BoolTypeAST) {
				v = new Var(ast.id.getText(), "array", value, "boolean");
			}
		}
		varTable.addVar(v);
		return null;
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
	// trinhgiang-29/10/2013
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
		
		if(ast.t instanceof TypeListAST) {
			if(((TypeListAST)ast.t).t instanceof FloatTypeAST) {
				v = new Var(ast.id.getText(), "float", value, null);
			}
			else  if(((TypeListAST)ast.t).t instanceof IntTypeAST) {
				v = new Var(ast.id.getText(), "integer", value, null);
			}
			else if(((TypeListAST)ast.t).t instanceof BoolTypeAST) {
				v = new Var(ast.id.getText(), "boolean", value, null);
			}
		}
		else if(ast.t instanceof ArrayTypeAST) {
			TypeListAST arrType  = (TypeListAST) ((ArrayTypeAST)ast.t).type;
			if(arrType.t instanceof FloatTypeAST) {
				v = new Var(ast.id.getText(), "array", value, "float");
			}
			else if(arrType.t instanceof IntTypeAST) {
				v = new Var(ast.id.getText(), "array", value, "integer");
			}
			else if(((TypeListAST)ast.t).t instanceof BoolTypeAST) {
				v = new Var(ast.id.getText(), "array", value, "boolean");
			}
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
		int tempScope = scopeBreak;
		int tempContinue = scopeContinue;
		
		if(o != null && o.toString().startsWith("caseF"))
		{
			// don't nothing
		}
		else {
			ast.o.visit(this, o);
		}
		
		if(!signalRet) //yet not return
		{
			// visitStmtListAST or visitEmptyStmtListAST
			//break statement
			if(o == null)
				ast.s.visit(this, o);
		    else if(o.toString().equals("while") || o.toString().equals("for"))
		    {
				//break statement
				if(tempScope > scopeBreak || tempContinue > scopeContinue) {
					// don't nothing
				}
				else if(tempScope <= scopeBreak)
					ast.s.visit(this, o);
			}
			else {
				ast.s.visit(this, o);
			}
		}
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
	//trinhgiang-22/10/2013
	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		// thoat ra khoi vong while, for, case
		if(o != null && o.toString().equals("while"))
			scopeBreak--;
		else if(o != null && o.toString().equals("for"))
			scopeBreak--;
		else if(o != null && o.toString().equals("dowhile"))
			scopeBreak--;
		return null;
	}
	// trinhgiang-19/11/2013
	// CallExprAST
	public Object visitCallExprAST(CallExprAST ast, Object o) throws CompilationException
    {
		String callName = ast.name.getText();
		if(callName.equals("abs")) {
			//--------------ham gia tri tuyet doi-----------------------
			Object e = ((ExprAST)((ExprListAST)ast.e).e).visit(this, null);
			if(e instanceof Integer) {
				//System.out.println("Goi ham abs int");
				return (Integer)Math.abs(((Integer)e).intValue());
			} else if(e instanceof Float) {
				//System.out.println("Goi ham abs float");
				return (Float)Math.abs(((Float)e).floatValue());
			}
		} else if(callName.equals("round")) {
			//--------------ham tinh so nguyen gan nhat-----------------
			Object e = ((ExprAST)((ExprListAST)ast.e).e).visit(this, null);
			if(e instanceof Integer) {
				//System.out.println("Goi ham round int");
				return (Integer)e;
			} else if(e instanceof Float) {
				//System.out.println("Goi ham round float");
				return (Integer)Math.round(((Float)e).floatValue());
			}
		} else if(callName.equals("sqrt")) {
			//----------------ham tinh can bac 2--------------------
			Object e = ((ExprAST)((ExprListAST)ast.e).e).visit(this, null);
			if(e instanceof Integer) {
				//System.out.println("Goi ham sqrt int");
				return new Float((float)Math.sqrt((double)((Integer)e).intValue()));
			} else if(e instanceof Float) {
				//System.out.println("Goi ham sqrt float");
				return new Float((float)Math.sqrt((double)((Float)e).floatValue()));
			}
		} else if(callName.equals("isPrime")) {
			//----------------ham kiem tra so nguyen to-----------------
			Object e = ((ExprAST)((ExprListAST)ast.e).e).visit(this, null);
			if(e instanceof Integer) {
				return new Integer(isPrime(((Integer)e).intValue()));
			}
		} else if(callName.equals("isFibonaci")) {
			//----------------ham kiem tra so fibonaci------------------
			Object e = ((ExprAST)((ExprListAST)ast.e).e).visit(this, null);
			if(e instanceof Integer) {
				return new Integer(isFibonaci(((Integer)e).intValue()));
			}
		} else if(callName.equals("max")) {
			//---------------ham tim max cua hai so---------------------
			ExprListAST listParam = (ExprListAST)ast.e;
			Object e1 = ((ExprAST)(listParam.e)).visit(this, null);
			Object e2 = ((ExprAST)(((ExprListAST)(listParam.l)).e)).visit(this, null);
			if(e1 instanceof Integer && e2 instanceof Integer) {
				int i1 = ((Integer)e1).intValue();
				int i2 = ((Integer)e2).intValue();
				return new Integer(intMax(i1, i2));
			} else if(e1 instanceof Float && e1 instanceof Float) {
				float i1 = ((Float)e1).floatValue();
				float i2 = ((Float)e2).floatValue();
				return new Float(floatMax(i1, i2));
			}
		} else if(callName.equals("min")) {
			//---------------ham tim min cua hai so---------------------
			ExprListAST listParam = (ExprListAST)ast.e;
			Object e1 = ((ExprAST)(listParam.e)).visit(this, null);
			Object e2 = ((ExprAST)(((ExprListAST)(listParam.l)).e)).visit(this, null);
			if(e1 instanceof Integer && e2 instanceof Integer) {
				int i1 = ((Integer)e1).intValue();
				int i2 = ((Integer)e2).intValue();
				return new Integer(intMin(i1, i2));
			} else if(e1 instanceof Float && e1 instanceof Float) {
				float i1 = ((Float)e1).floatValue();
				float i2 = ((Float)e2).floatValue();
				return new Float(floatMin(i1, i2));
			}
		} else if(callName.equals("pow")) {
			//---------------ham tim a mu n---------------------
			ExprListAST listParam = (ExprListAST)ast.e;
			Object e1 = ((ExprAST)(listParam.e)).visit(this, null);
			Object e2 = ((ExprAST)(((ExprListAST)(listParam.l)).e)).visit(this, null);
			if(e1 instanceof Integer && e2 instanceof Integer) {
				int i1 = ((Integer)e1).intValue();
				int i2 = ((Integer)e2).intValue();
				return new Integer(intPow(i1, i2));
			} else if(e1 instanceof Float && e1 instanceof Integer) {
				float i1 = ((Float)e1).floatValue();
				int i2 = ((Float)e2).intValue();
				return new Float(floatPow(i1, i2));
			}
		}
        return null;
    }
    // trinhgiang-29/10/2013
	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
		// thoat ra khoi vong while, fot
		if(o != null && o.toString().equals("while"))
	    {
			scopeContinue--;
		}
		else if(o != null && o.toString().equals("for"))
		{
			scopeContinue--;
		}
		else if(o != null && o.toString().equals("dowhile"))
		{
			scopeContinue--;
		}
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
		scopeBreak++;
		scopeContinue++;
		int tempContinue = scopeContinue;
		int tempScope = scopeBreak;
		while (b) {
			ast.o.visit(this, "while");
			// continue statement
			if(scopeContinue < tempContinue)
			{
				b = (Boolean) ast.e.visit(this, null);
			}
			// break statement
			else if(tempScope <= scopeBreak)
				b = (Boolean) ast.e.visit(this, null);
			else {
				b = false;
			}
		}
		scopeContinue = tempContinue - 1;
		scopeBreak = tempScope - 1;	
		return null;
	}
	// trinhgiang-29/10/2013
	// ForStmtAST
	public Object visitForStmtAST(ForStmtAST ast, Object o)
			throws CompilationException {
		ast.e1.visit(this, null);
		boolean b = (Boolean) ast.e2.visit(this, null);
		scopeBreak++;
		scopeContinue++;
		int tempContinue = scopeContinue;
		int tempScope = scopeBreak;
		while (b) {
			ast.o.visit(this, "for");
			// continue statement
			if(scopeContinue < tempContinue) {
				ast.e3.visit(this, null);
				b = (Boolean) ast.e2.visit(this, null);
			}
			// break statement
			else if(scopeBreak >= tempScope) {
				ast.e3.visit(this, null);
				b = (Boolean) ast.e2.visit(this, null);
			}
			else
			{
				b = false;
			}
		}
		scopeBreak = tempScope - 1;
		scopeContinue = tempContinue - 1;
		return null;
	}
	// trinhgiang-29/10/2013
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST ast, Object o)
			throws CompilationException {
		scopeBreak++;
		scopeContinue++;
		int tempContinue = scopeContinue;
		int tempScope = scopeBreak;
		
		ast.o.visit(this, "dowhile");
		// continue statement
		if((scopeContinue < tempContinue) || (scopeBreak >= tempScope))
		{
			boolean b = (Boolean) ast.e.visit(this, null);
			while (b) {
				ast.o.visit(this, "dowhile");
				// continue statement
				if(scopeContinue < tempContinue) {
					b = (Boolean) ast.e.visit(this, null);
				}
				else if(scopeBreak >= tempScope) {
					b = (Boolean) ast.e.visit(this, null);
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
		return null;
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
	// not complete
	public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o)
			throws CompilationException {
		String valueSwitch = sAst.e.visit(this, null).toString();
		int tempSwitchBreak = switchBreak;
		sAst.o.visit(this, valueSwitch);
		return null;
	}
	// trinhgiang-28/10/2013
	public Object visitCaseStmtAST(CaseStmtAST cAst, Object o)
			throws CompilationException {
		String valueCase = cAst.e.visit(this, null).toString();
		if(o != null && o.toString().equals("caseT")) {
			//System.out.println("Da tim ra case true");
			return null;
		}
		if(o != null && o.toString().startsWith("caseF")) {
			//System.out.println("Chay trong case false");
			if(valueCase.equals(o.toString().substring(5)))
			{   
				cAst.s.visit(this, "caseT");
			}
			else {
				cAst.s.visit(this, o);
			}
		}
		// first case statement
		else {
			//System.out.println("Chay trong first case");
			if(valueCase.equals(o.toString()))
			{   
				cAst.s.visit(this, "caseT");
			}
			else {
				cAst.s.visit(this, "caseF" + o.toString());
			}
		}
		return null;
	}
	// trinhgiang-29/10/2013
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST dAst, Object o)
			throws CompilationException {
		dAst.s.visit(this, "default");		
		return null;
	}
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		if (ret.equals("")) {
			ret += ast.e.visit(this, o);
			//sign return statement of function
			signalRet = true;
		}
		
		return null;
	}
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		Var v = varTable.getVar(ast.name.getText());
		//trinhgiang-16/10/2013
		//them phan xu ly kieu float
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
	// EleExprAST
	public Object visitEleExprAST(EleExprAST ast, Object o)
			throws CompilationException {
		Var v = varTable.getVar(ast.name.getText());
		// index of array element
		Integer i = (Integer) ((ExprListAST) ast.e).e.visit(this, null);
		//trinhgiang-30/10/2013
		String ele = v.getArrayValue(i);
		String eleType = v.getEleType();
		// only support one dimension array
		if(eleType.equals("float")) {
			// element is float literal
			return Float.parseFloat(ele);
		}
		else if(eleType.equals("boolean")) {
			// element is boolean literal
			return Boolean.parseBoolean(ele);
		}
		else if (eleType.equals("integer")) {
			// element is integer literal
			return Integer.parseInt(ele);
		}
		else return ele;
		//return null;
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
		return new Float(ast.literal.getText());
	}
	
	// UnaryExprAST
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		Object e = ast.e.visit(this, null);		
		
		if ((ast.opType >= UnaryExprAST.PRE_INC) && (ast.opType <= UnaryExprAST.POST_DEC)) {
			int opType;
            boolean pre_increment; // true if (++i or --i), false if (i++ or i--)
            boolean increment; // true if(++i ot i++), false if(--i or i--)
            Integer index = new Integer(-1);
            
            String varName = "";
            if (ast.e instanceof VarExprAST) {
                varName = ((VarExprAST) ast.e).name.getText();
            }
            else if (ast.e instanceof EleExprAST) {
                varName = ((EleExprAST) ast.e).name.getText();
                index = (Integer) ((ExprListAST) ((EleExprAST) ast.e).e).e.visit(this, null);
            }
            
            if (ast.opType == UnaryExprAST.PRE_INC) {
                // ++i
                opType = BinExprAST.PLUS;
                pre_increment = true;
                increment = true;
            }
            else if (ast.opType == UnaryExprAST.PRE_DEC) {
                // --i
                opType = BinExprAST.MINUS;
                pre_increment = true;
                increment = false;
            }
            else if (ast.opType == UnaryExprAST.POST_INC) {
                // i++
                opType = BinExprAST.PLUS;
                pre_increment = false;
                increment = true;
            }
            else {
                // i--
                opType = BinExprAST.MINUS;
                pre_increment = false;
                increment = false;
            }
            
            Object newVal = null;
            if(pre_increment) {
				if(increment) {
					// ++i
					// chi ap dung cho so nguyen
					newVal = (Integer) e + 1;
				}
				else {
					// --i
					newVal = (Integer) e - 1;
				}
				// update variable table
				Var v = varTable.getVar(varName);
				if(index.intValue() == -1)
					v.setValue(newVal.toString());
				else 
					v.setArrayValue(index.intValue(), newVal.toString());
				return newVal;
			}
			else {
				if(increment) {
					// i++
					newVal = (Integer) e + 1;
				}
				else {
					// i--
					newVal = (Integer) e - 1;
				}
				// update variable table
				Var v = varTable.getVar(varName);
				if(index.intValue() == -1)
					v.setValue(newVal.toString());
				else 
					v.setArrayValue(index.intValue(), newVal.toString());
				return e;
			}
		} else if (ast.opType == UnaryExprAST.UNARY_MINUS) {
			if(e instanceof Float)
				return 0 - (Float) e;
			else
				return 0 - (Integer) e;
		} 
		else {
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
				//index of array element
				int i = (Integer) ((ExprListAST) ((EleExprAST) ast.e1).e).e.visit(this, null);
				Var v = varTable.getVar(name);
				
				Object e2 = ast.e2.visit(this, null);
				v.setArrayValue(i, e2.toString());
			}
			
			return null;
		}
		//trinhgiang-18/10/2013
		//added deal with float type
		else 
		{		
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
