
package Transformer.CodeGeneration;

import Transformer.ASTs.*;
import org.antlr.runtime.*;
import Transformer.DependenceGraph.*;
import java.util.*;

/***********************************************************************************
Class Variable luu (kieu, ten, gia tri) cua 1 bien duoc tao ra trong chuong trinh
***********************************************************************************/
class Variable {
	public TypeAST type;
	public String varName;
	public Object value;
	
	public Variable(TypeAST t, String n) {
		type = t; varName = n; value = null;
	}
	public Variable(TypeAST t, String n, Object v) {
		type = t; varName = n; value = v;
	}
	public String toString() {
		String result = "";
		if (type instanceof BoolTypeAST)
			result += "bool ";
		else if (type instanceof ShortTypeAST)
			result += "short ";
		else if (type instanceof IntTypeAST)
			result += "int ";
		else if (type instanceof LongTypeAST)
			result += "long ";
		else if (type instanceof FloatTypeAST)
			result += "float ";
		else if (type instanceof DoubleTypeAST)
			result += "double ";
		else if (type instanceof CharTypeAST)
			result += "char ";
		result += varName;
		if (value != null)
			result +=  " = " + value.toString();
		return result;
	}
}
/***********************************************************************************
class SimulationMemory gia lap lai bo nho luu tru gia tri cua cac bien (valueTableOfVariables)
***********************************************************************************/
class SimulationMemory {
	Stack<Variable> valueTableOfVariables;
	Stack<Integer>  scope = new Stack<Integer>();
	String newline = "\r\n";
	public SimulationMemory() {
		valueTableOfVariables = new Stack<Variable>();
		enterScope();
	}
	// invoked when entering a new scope
	public void enterScope() {
		scope.push(valueTableOfVariables.size());
	}
	// invoked when exitting a scope
	public void exitScope() {
		if (scope.empty()) {
			System.out.println("IllegalOperationException");
			return;
		}
		int top = scope.pop();
		if (valueTableOfVariables.size() < top) {
			System.out.println("IllegalOperationException");
			return;
		}
		// when exitting a scope, remove every variable declared in this scope.
		valueTableOfVariables.setSize(top);
	}
	// them 1 bien moi (xay ra khi gap cau lenh: int a)
	public void addNewVariable(TypeAST t, String n) {
		valueTableOfVariables.push(new Variable(t, n));
	}
	// them 1 bien moi co gia tri (xay ra khi gap cau lenh: int a = 1)
	public void addNewVariable(TypeAST t, String n, Object v) {
		valueTableOfVariables.push(new Variable(t, n, v));
	}
	// cap nhat lai gia tri cua bien (xay ra khi gap phep gan =, +=, -=, *=, /=)
	public void updateValueOfVariable(String var, Object newVal) {
		for (int i = valueTableOfVariables.size()-1; i >= 0; i--) {
			if (valueTableOfVariables.elementAt(i).varName.equals(var)) {
				valueTableOfVariables.elementAt(i).value = newVal;
				break;
			}
		}
	}
	// doc gia tri cua 1 bien
	public Object getValueOfVariable(String var) {
		for (int i = valueTableOfVariables.size()-1; i >= 0 ; i--) {
			if (valueTableOfVariables.elementAt(i).varName.equals(var))
				return valueTableOfVariables.elementAt(i).value;
		}
		return null;
	}
	public String toString() {
		String result = newline + "Table value of all variables in the program:" + newline;
		for (int i = 0; i < valueTableOfVariables.size(); i++)
			result += valueTableOfVariables.elementAt(i).toString() + newline;
		return result;
	}
}

/***********************************************************************************
Visitor duyet qua cay AST de thuc hien simulation tim ra Execution History
***********************************************************************************/
public class AstSimulationVisitor extends DoNothingVisitor {
	SimulationMemory simulation;
	ExecutionHistory execHistory;
	ArrayList<String> listInput;
	// bien tra ve cua 1 function
	static final String RET_VAR_NAME = "retvar";
	
	public AstSimulationVisitor() {
		simulation = new SimulationMemory();
		execHistory = new ExecutionHistory();
	}
	public AstSimulationVisitor(ArrayList<String> list) {
		simulation = new SimulationMemory();
		execHistory = new ExecutionHistory();
		listInput = list;
	}
	
	public ExecutionHistory getExecutionHistory() {return execHistory;}
	
	public void println(Object o) {
		System.out.println(o);
	}
	public void printArrayList(ArrayList list) {
		System.out.println("ArrayList:");
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		System.out.println("EndArrayList:");
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
		ast.dl.visit(this, o);
		return null;
	}	
	
	// VarInitializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {
		return ast.e.visit(this, o);
	}
	
	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		ast.t.visit(this, o);
		TypeAST t = ((TypeListAST) ast.t).t;
		if (ast.init != null) {
			simulation.addNewVariable(t, ast.id.getText(), ast.init.visit(this, o));
			execHistory.addExecNode(new ExecNode(ast.line, 'N'));
		}
		else
			simulation.addNewVariable(t, ast.id.getText());
		return null;
	}

	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		simulation.enterScope();
		fAst.para.visit(this, o);
		fAst.ret.visit(this, o);
		
		TypeAST retType = ((TypeListAST) fAst.ret).t;
		simulation.addNewVariable(retType, RET_VAR_NAME);
		
		fAst.c.visit(this, o);

		simulation.exitScope();
		return null;
	}

	// ParaListAST
	public Object visitParaListAST(ParaListAST pAst, Object o)
			throws CompilationException {
		pAst.v.visit(this, o);
		pAst.p.visit(this, o);
		return null;
	}
	
	// ParaAST
	public Object visitParaAST(ParaAST pAst, Object o)
			throws CompilationException{		
		pAst.t.visit(this, o);
		TypeAST t = ((TypeListAST) pAst.t).t;
		
		// get the input value from listInput
		String input = (String) listInput.remove(0);
		Object value;
		if (input.indexOf('.') != -1)
			value = Float.parseFloat(input);
		else if (input.indexOf('"') != -1)
			value = input.substring(1, input.length() - 1);
		else
			value = Integer.parseInt(input);
			
		simulation.addNewVariable(t, pAst.id.getText(), value);
		return null;
	}

	// StmtListAST
	public Object visitStmtListAST(StmtListAST sAst, Object o)
			throws CompilationException {
		sAst.o.visit(this, o);
		sAst.s.visit(this, o);
		return null;
	}
	
	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST cAst, Object o)
			throws CompilationException {
		cAst.s.visit(this, o);
		return null;
	}

	// ExprListAST
	public Object visitExprListAST(ExprListAST ast, Object o)
			throws CompilationException {
		ast.e.visit(this, o);
		ast.l.visit(this, o);
		return null;
	}
	
	/***********************************************************************************
	Handling ExprAST
	***********************************************************************************/
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {		
		return simulation.getValueOfVariable(ast.name.getText());
	}

	//CharLiteralAST
	public Object visitCharLiteralAST(CharLiteralAST ast, Object o)
			throws CompilationException {
		String str  = ast.literal.getText();
		str = str.substring(1, str.length()-1);
		return str;
	}

	//StringLiteralAST
	public Object visitStringLiteralAST(StringLiteralAST ast, Object o)
			throws CompilationException {
		String str  = ast.literal.getText();
		str = str.substring(1, str.length()-1);
		return str;
	}

	// IntLiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {		
		return new Integer(ast.literal.getText());
	}

	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {
		return new Float(ast.literal.getText());
	}

	// UnaryExprAST
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		Object val = ast.e.visit(this, o);
		if (ast.opType >= UnaryExprAST.PRE_INC && ast.opType <= UnaryExprAST.POST_DEC) {
			int opType;
			boolean pre_increment; // true if (++i or --i), false if (i++ or i--)
			String varName = "";
			if (ast.e instanceof VarExprAST)
				varName = ((VarExprAST) ast.e).name.getText();
			else if (ast.e instanceof EleExprAST) 
				varName = ((EleExprAST) ast.e).name.getText();
			
			if (ast.opType == UnaryExprAST.PRE_INC) {
				//++i
				opType = BinExprAST.PLUS;
				pre_increment = true;
			} else if (ast.opType == UnaryExprAST.PRE_DEC) {
				//--i
				opType = BinExprAST.MINUS;
				pre_increment = true;
			} else if (ast.opType == UnaryExprAST.POST_INC) {
				//i++
				opType = BinExprAST.PLUS;
				pre_increment = false;
			} else {
				//i--
				opType = BinExprAST.MINUS;
				pre_increment = false;
			}
			ExprAST addingOneBinExpr = new BinExprAST(ast.e, opType, null, new NullExprAST());
			Object new_value = addingOneBinExpr.visit(this, "increment");
			simulation.updateValueOfVariable(varName, new_value);
			if (pre_increment) {
				// increment first then return the new value
				return new_value;
			} else {
				// return the old value and also
				return val;
			}
		}
		else if (ast.opType == UnaryExprAST.UNARY_MINUS) {
			//-i
			ExprAST zeroMinusBinExpr = new BinExprAST(new NullExprAST(), BinExprAST.MINUS, null, ast.e);
			return zeroMinusBinExpr.visit(this, "zero_minus");
		} else if (ast.opType == UnaryExprAST.LOGICAL_NOT) {
			//!bool
			if (val instanceof Boolean) {
				boolean v = ((Boolean) val).booleanValue();
				return !v;
			}
		}
		return val;
	}

	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		Object val1;
		if ("zero_minus".equals(o))
			// phep toan 0 - ast.e2
			val1 = new Integer(0);
		else
			val1 = ast.e1.visit(this, o);
		Object val2;
		if ("increment".equals(o))
			// phep toan ast.e1 + 1
			val2 = new Integer(1);
		else
			val2 = ast.e2.visit(this, o);
		
		// cac loai phep gan =, +=, -=, *=, /=, %=
		if (ast.opType >= BinExprAST.ASSIGN && ast.opType <= BinExprAST.MOD_ASSIGN) {
			String varName = "";
			if (ast.e1 instanceof VarExprAST)
				varName = ((VarExprAST) ast.e1).name.getText();
			else if (ast.e1 instanceof EleExprAST) 
				varName = ((EleExprAST) ast.e1).name.getText();
			
			if (ast.opType == BinExprAST.ASSIGN) {
				// phep gan =
				simulation.updateValueOfVariable(varName, val2);
			}
			else {
				// phep gan +=, -=, *=, /=, %=
				// opType cua phep gan: 			1, 2, 3, 4, 5
				// opType cua phep toan tuong ung:	24, 25, 26, 27, 28
				ExprAST binExpr = new BinExprAST(ast.e1, ast.opType+23, null, ast.e2);
				Object val = binExpr.visit(this, o);
				simulation.updateValueOfVariable(varName, val);
			}		
		}
		// phep luan ly giua cac Boolean
		else if (ast.opType == BinExprAST.LOGICAL_OR || ast.opType == BinExprAST.LOGICAL_AND) {
			// phep luan ly hoac-va || &&
			// 1. so sanh giua 2 boolean => return Boolean
			if (val1 instanceof Boolean && val2 instanceof Boolean) {
				boolean v1 = ((Boolean) val1).booleanValue();
				boolean v2 = ((Boolean) val2).booleanValue();
				if (ast.opType == BinExprAST.LOGICAL_OR)
					// phep luan ly ||
					return new Boolean(v1||v2);
				else
					// phep luan ly &&
					return new Boolean(v1&&v2);
			}
		}
		// cac loai phep so sanh
		else if (ast.opType == BinExprAST.EQUAL) {
			// phep so sanh ==
			// 1. so sanh == giua 2 so nguyen => return Boolean
			// 2. so sanh == giua 2 so thuc => return Boolean
			// 3. so sanh == giua 2 String => return Boolean
			// 4. so sanh == giua so nguyen va thuc => return Boolean
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				return new Boolean(v1==v2);
			}
			else if (val1 instanceof Float && val2 instanceof Float) {
				float v1 = ((Float) val1).floatValue();
				float v2 = ((Float) val2).floatValue();
				return new Boolean(v1==v2);
			} else if (val1 instanceof Integer && val2 instanceof Float) {
				int v1 = ((Integer) val1).intValue();
				float v2 = ((Float) val2).floatValue();
				return new Boolean(v1==v2);
			} else if (val1 instanceof Float && val2 instanceof Integer) {
				float v1 = ((Float) val1).floatValue();
				int v2 = ((Integer) val2).intValue();
				return new Boolean(v1==v2);
			} else if (val1 instanceof String && val2 instanceof String) {
				String v1 = (String) val1;
				String v2 = (String) val2;
				return new Boolean(v1.equals(v2));
			}
		} else if (ast.opType == BinExprAST.NOT_EQUAL) {
			// phep so sanh !=
			// 1. so sanh != giua 2 so nguyen => return Boolean
			// 2. so sanh != giua 2 so thuc => return Boolean
			// 3. so sanh != giua 2 String => return Boolean
			// 4. so sanh != giua so nguyen va thuc => return Boolean
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				return new Boolean(v1!=v2);
			}
			else if (val1 instanceof Float && val2 instanceof Float) {
				float v1 = ((Float) val1).floatValue();
				float v2 = ((Float) val2).floatValue();
				return new Boolean(v1!=v2);
			} else if (val1 instanceof Integer && val2 instanceof Float) {
				int v1 = ((Integer) val1).intValue();
				float v2 = ((Float) val2).floatValue();
				return new Boolean(v1!=v2);
			} else if (val1 instanceof Float && val2 instanceof Integer) {
				float v1 = ((Float) val1).floatValue();
				int v2 = ((Integer) val2).intValue();
				return new Boolean(v1!=v2);
			} else if (val1 instanceof String && val2 instanceof String) {
				String v1 = (String) val1;
				String v2 = (String) val2;
				return new Boolean(!v1.equals(v2));
			}
		} else if (ast.opType >= BinExprAST.LESS_OR_EQUAL 
					&& ast.opType <= BinExprAST.LESS_THAN) {
			// phep so sanh <= (18) >= (19) > (20) < (21)
			// 1. so sanh giua 2 so nguyen => return Boolean
			// 2. so sanh giua 2 so thuc => return Boolean
			// 3. so sanh giua so nguyen va thuc => return Boolean
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				if (ast.opType == BinExprAST.LESS_OR_EQUAL)
					// phep <=
					return new Boolean(v1<=v2);
				else if (ast.opType == BinExprAST.GREATER_OR_EQUAL)
					// phep >=
					return new Boolean(v1>=v2);
				else if (ast.opType == BinExprAST.GREATER_THAN)
					// phep >
					return new Boolean(v1>v2);
				else
					// phep <
					return new Boolean(v1<v2);
			}
			else if (val1 instanceof Float && val2 instanceof Float) {
				float v1 = ((Float) val1).floatValue();
				float v2 = ((Float) val2).floatValue();
				if (ast.opType == BinExprAST.LESS_OR_EQUAL)
					// phep <=
					return new Boolean(v1<=v2);
				else if (ast.opType == BinExprAST.GREATER_OR_EQUAL)
					// phep >=
					return new Boolean(v1>=v2);
				else if (ast.opType == BinExprAST.GREATER_THAN)
					// phep >
					return new Boolean(v1>v2);
				else
					// phep <
					return new Boolean(v1<v2);
			} else if (val1 instanceof Integer && val2 instanceof Float) {
				int v1 = ((Integer) val1).intValue();
				float v2 = ((Float) val2).floatValue();
				if (ast.opType == BinExprAST.LESS_OR_EQUAL)
					// phep <=
					return new Boolean(v1<=v2);
				else if (ast.opType == BinExprAST.GREATER_OR_EQUAL)
					// phep >=
					return new Boolean(v1>=v2);
				else if (ast.opType == BinExprAST.GREATER_THAN)
					// phep >
					return new Boolean(v1>v2);
				else
					// phep <
					return new Boolean(v1<v2);
			} else if (val1 instanceof Float && val2 instanceof Integer) {
				float v1 = ((Float) val1).floatValue();
				int v2 = ((Integer) val2).intValue();
				if (ast.opType == BinExprAST.LESS_OR_EQUAL)
					// phep <=
					return new Boolean(v1<=v2);
				else if (ast.opType == BinExprAST.GREATER_OR_EQUAL)
					// phep >=
					return new Boolean(v1>=v2);
				else if (ast.opType == BinExprAST.GREATER_THAN)
					// phep >
					return new Boolean(v1>v2);
				else
					// phep <
					return new Boolean(v1<v2);
			}
		}		
		// cac loai phep toan
		else if (ast.opType == BinExprAST.PLUS) {
			// phep toan +
			// 1. cong giua 2 so nguyen => return so nguyen
			// 2. cong giua 2 so thuc => return so thuc
			// 3. cong giua 2 string => return string
			// 4. cong giua so nguyen va thuc => return so thuc
			// 5. cong giua so nguyen va string => return string
			// 6. cong giua so thuc va string => return string
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				return new Integer(v1+v2);
			} else if (val1 instanceof Float && val2 instanceof Float) {
				float v1 = ((Float) val1).floatValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1+v2);
			} else if (val1 instanceof String && val2 instanceof String) {
				String v1 = (String) val1;
				String v2 = (String) val2;
				return (v1 + v2);
			} else if (val1 instanceof Integer && val2 instanceof Float) {
				int v1 = ((Integer) val1).intValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1+v2);
			} else if (val1 instanceof Float && val2 instanceof Integer) {
				float v1 = ((Float) val1).floatValue();
				int v2 = ((Integer) val2).intValue();
				return new Float(v1+v2);
			} else if (val1 instanceof Integer && val2 instanceof String) {
				int v1 = ((Integer) val1).intValue();
				String v2 = (String) val2;
				return String.valueOf(v1) + v2;
			} else if (val1 instanceof String && val2 instanceof Integer) {
				String v1 = (String) val1;
				int v2 = ((Integer) val2).intValue();
				return v1 + String.valueOf(v2);
			} else if (val1 instanceof Float && val2 instanceof String) {
				float v1 = ((Float) val1).floatValue();
				String v2 = (String) val2;
				return String.valueOf(v1) + v2;
			} else if (val1 instanceof String && val2 instanceof Float) {
				String v1 = (String) val1;
				float v2 = ((Float) val2).floatValue();
				return v1 + String.valueOf(v2);
			}
		} else if (ast.opType == BinExprAST.MINUS) {
			// phep toan -
			// 1. tru giua 2 so nguyen => return so nguyen
			// 2. tru giua 2 so thuc => return so thuc
			// 3. tru giua 2 so nguyen va thuc => return so thuc
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				return new Integer(v1-v2);
			} else if (val1 instanceof Float && val2 instanceof Float) {
				float v1 = ((Float) val1).floatValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1-v2);
			} else if (val1 instanceof Integer && val2 instanceof Float) {
				int v1 = ((Integer) val1).intValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1-v2);
			} else if (val1 instanceof Float && val2 instanceof Integer) {
				float v1 = ((Float) val1).floatValue();
				int v2 = ((Integer) val2).intValue();
				return new Float(v1-v2);
			}
		} else if (ast.opType == BinExprAST.STAR) {
			// phep toan *
			// 1. nhan giua 2 so nguyen => return so nguyen
			// 2. nhan giua 2 so thuc => return so thuc
			// 3. nhan giua 2 so nguyen va thuc => return so thuc
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				return new Integer(v1*v2);
			} else if (val1 instanceof Float && val2 instanceof Float) {
				float v1 = ((Float) val1).floatValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1*v2);
			} else if (val1 instanceof Integer && val2 instanceof Float) {
				int v1 = ((Integer) val1).intValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1*v2);
			} else if (val1 instanceof Float && val2 instanceof Integer) {
				float v1 = ((Float) val1).floatValue();
				int v2 = ((Integer) val2).intValue();
				return new Float(v1*v2);
			}
		} else if (ast.opType == BinExprAST.DIV) {
			// phep toan /
			// 1. chia giua 2 so nguyen => return so nguyen
			// 2. chia giua 2 so thuc => return so thuc
			// 3. chia giua 2 so nguyen va thuc => return so thuc
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				return new Integer(v1/v2);
			} else if (val1 instanceof Float && val2 instanceof Float) {
				float v1 = ((Float) val1).floatValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1/v2);
			} else if (val1 instanceof Integer && val2 instanceof Float) {
				int v1 = ((Integer) val1).intValue();
				float v2 = ((Float) val2).floatValue();
				return new Float(v1/v2);
			} else if (val1 instanceof Float && val2 instanceof Integer) {
				float v1 = ((Float) val1).floatValue();
				int v2 = ((Integer) val2).intValue();
				return new Float(v1/v2);
			}
		} else if (ast.opType == BinExprAST.MOD) {
			// phep toan %
			// 1. mod giua 2 so nguyen => return so nguyen
			if (val1 instanceof Integer && val2 instanceof Integer) {
				int v1 = ((Integer) val1).intValue();
				int v2 = ((Integer) val2).intValue();
				return new Integer(v1%v2);
			}
		}
		return null;
	}
	
	// CallExprAST
	public Object visitCallExprAST(CallExprAST ast, Object o)
			throws CompilationException {
		if (ast.name.getText().equals("println")) {
			Object value = ast.e.e.visit(this, o);
			println(value);
			return null;
		}
		println("Simulation of Call Function is NOT SUPPORTED !!!");
		return null;
	}

	// EleExprAST
	// Bieu thuc truy xuat mang: array[...][...][...]
	public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException { 
		println("Simulation of Array manipulation is NOT SUPPORTED !!!");
		String varName = ast.name.getText();	
		ast.e.visit(this,o);
		return null;
	}

	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		ast.e.visit(this, o);
		execHistory.addExecNode(new ExecNode(ast.line, 'N'));
		return null;
	}

	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST iAst, Object o)
			throws CompilationException {
		boolean value = ((Boolean) iAst.e.visit(this, o)).booleanValue();
		if (value) {
			execHistory.addExecNode(new ExecNode(iAst.line, 'T'));
			simulation.enterScope();
			iAst.s.visit(this, o);
			simulation.exitScope();
		}
		else
			execHistory.addExecNode(new ExecNode(iAst.line, 'F'));
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST iAst, Object o)
			throws CompilationException {
		boolean value = ((Boolean) iAst.e.visit(this, o)).booleanValue();
		if (value) {
			execHistory.addExecNode(new ExecNode(iAst.line, 'T'));
			simulation.enterScope();
			iAst.s1.visit(this, o);
			simulation.exitScope();
		} else {
			execHistory.addExecNode(new ExecNode(iAst.line, 'F'));
			simulation.enterScope();
			iAst.s2.visit(this, o);
			simulation.exitScope();
		}
		return null;
	}

	// ForStmtAST
	// for (int i = 1; i < 10; i++) print(x)
	// <==> int i = 1; while (i < 10) {print(x); i++}
	public Object visitForStmtAST(ForStmtAST fAst, Object o)
			throws CompilationException {
		simulation.enterScope();
		if (fAst.e1 != null)
			fAst.e1.visit(this, o);
		boolean value = true;
		if (fAst.e2 != null)
			value = ((Boolean) fAst.e2.visit(this, o)).booleanValue();
		if (value)
			execHistory.addExecNode(new ExecNode(fAst.line, 'T'));
		else
			execHistory.addExecNode(new ExecNode(fAst.line, 'F'));
		while (value) {
			simulation.enterScope();
			fAst.o.visit(this, o);
			simulation.exitScope();
			if (fAst.e3 != null)
				fAst.e3.visit(this, o);
			if (fAst.e2 != null)
				value = ((Boolean) fAst.e2.visit(this, o)).booleanValue();
			if (value)
				execHistory.addExecNode(new ExecNode(fAst.line, 'T'));
			else
				execHistory.addExecNode(new ExecNode(fAst.line, 'F'));
		}
		simulation.exitScope();
		return null;
	}
	// ForInitAST
	public Object visitForInitAST(ForInitAST ast, Object o)
			throws CompilationException {
		if (ast.d != null)
			// for (int i = 1 ; ...)
			ast.d.visit(this, o);
		if (ast.e != null)
			// for (i = 1, j = 2, k = 3 ; ...)
			return ast.e.visit(this, o);
		return null;
	}

	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST wAst, Object o)
			throws CompilationException {
		boolean value = ((Boolean) wAst.e.visit(this, o)).booleanValue();
		while (value) {
			execHistory.addExecNode(new ExecNode(wAst.line, 'T'));
			simulation.enterScope();
			wAst.o.visit(this, o);
			value = ((Boolean) wAst.e.visit(this, o)).booleanValue();
			simulation.exitScope();
		}
		execHistory.addExecNode(new ExecNode(wAst.line, 'F'));
		return null;
	}
	
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST wAst, Object o)
			throws CompilationException {
		boolean value;
		do {
			simulation.enterScope();
			wAst.o.visit(this, o);
			simulation.exitScope();
		
			value = ((Boolean) wAst.e.visit(this, o)).booleanValue();
			if (value) 
				execHistory.addExecNode(new ExecNode(wAst.line, 'T'));
			else
				execHistory.addExecNode(new ExecNode(wAst.line, 'F'));
		} while (value);
		return null;
	}

	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST rAst, Object o)
			throws CompilationException {
		if (rAst.e != null) {
			execHistory.addExecNode(new ExecNode(rAst.line, 'N'));
			Object value = rAst.e.visit(this, o);
			simulation.updateValueOfVariable(RET_VAR_NAME, value);
		}
		return null;
	}

	///////////////////////////////////////////////////////////////////////////////////
	// chua xu ly cac cau lenh ben duoi
	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		println("BreakStmtAST: " + ast.line);
		return null;
	}		
	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
		println("ContStmtAST: " + ast.line);
		return null;
	}
	// SwitchStmtAST
	public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o)
			throws CompilationException {
		println("SwitchStmtAST: " + sAst.line);
		sAst.e.visit(this, o);
		sAst.o.visit(this, o);
		return null;
	}	
	// CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST cAst, Object o)
			throws CompilationException {
		println("CaseStmtAST: " + cAst.line);
		cAst.e.visit(this, o);
		cAst.s.visit(this, o);
		return null;
	}	
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST dAst, Object o)
			throws CompilationException {
		println("DefaultStmtAST: " + dAst.line);
		dAst.s.visit(this, o);
		return null;
	}
	//
	///////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////////////////////////////////////////
	// chua can xet cac ham ben duoi
	// TernaryExprAST
	public Object visitTernaryExprAST(TernaryExprAST ast, Object o)
			throws CompilationException {
		ast.e1.visit(this, o);
		ast.e2.visit(this, o);
		ast.e3.visit(this, o);
		return null;
	}
	// TypeListAST
	public Object visitTypeListAST(TypeListAST ast, Object o)
			throws CompilationException {
		ast.t.visit(this, o);
		ast.tl.visit(this, o);
		return null;
	}	
	// ArrayTypeAST
	public Object visitArrayTypeAST(ArrayTypeAST ast, Object o)
			throws CompilationException {
		ast.type.visit(this, o);
		ast.el.visit(this, o);
		return null;
	}	
	// PointerTypeAST
	public Object visitPointerTypeAST(PointerTypeAST ast, Object o)
			throws CompilationException {
		ast.t.visit(this, o);
		return null;
	}
	// ArrayInitializer
	public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o)
	throws CompilationException {
		ast.v.visit(this, o);
		return null;
	}	
	// VarInitializerList
	public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o)
		throws CompilationException {
		ast.v.visit(this, o);
		ast.vl.visit(this, o);
		return null;
	}
	// ArrayInitializerList
	public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o)
		throws CompilationException {
		ast.a.visit(this, o);
		ast.al.visit(this, o);
		return null;
	}
	//BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {
		return null;
	}
	//
	///////////////////////////////////////////////////////////////////////////////////

}
