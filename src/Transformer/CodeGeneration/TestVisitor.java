/*
package Transformer.CodeGeneration;

import Transformer.ASTs.*;

public class TestVisitor extends DoNothingVisitor {

	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		if (o instanceof String) {
			String data = (String) o; // cast object to string
			System.out.println("Object truyen xuong dang la String");
			System.out.println("Noi dung cua object la: " + data);
		}
		System.out.println("visiting node ProgramAST !!!");
		return "ket_qua";
	}
}
*/


package Transformer.CodeGeneration;

import java.util.Scanner;

import Transformer.ASTs.*;

public class TestVisitor extends DoNothingVisitor {

	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		
		return ast.dl.visit(this, o).toString();
		
	}
	
	//	DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		
		String head = ast.d.visit(this, o).toString();
		String tail = ast.dl.visit(this, o).toString();	
		if ( ast.dl instanceof EmptyDeclarationListAST )
			return head;
		else return head + "\n" + tail;
	
	}
	
	//	EmptyDeclarationListAST
	public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o) throws CompilationException
	{	 
		 return "";
	}
	
	// VarDeclPartAST
	public Object visitVarDeclPartAST(VarDeclPartAST ast, Object o)
			throws CompilationException {
		String head = ast.v.visit(this, o).toString();
		String tail = ast.vp.visit(this, o).toString();
		return head + " " + tail;
	}

	// EmptyVarDeclPartAST
	public Object visitEmptyVarDeclPartAST(EmptyVarDeclPartAST ast, Object o)
			throws CompilationException {
		return "";
	}

	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		
		String type = ast.t.visit(this, o).toString();
		if ( !( ast.t instanceof ArrayTypeAST ) )
		{
			if ( ast.init != null )
			{
				String init = ast.init.visit(this, o).toString();
				return type + " " + ast.id.getText() + " = " + init + ";";
			}
			else return type + " " + ast.id.getText() + ";";
		}
		else
		{
			Scanner sc = new Scanner(type);
			sc.useDelimiter(",");
			String name = sc.next();
			String indexes = "";
			
			while (sc.hasNext())
			{
				String ele = sc.next().trim();
				if ( !ele.equals("null") )
					indexes += "[" + ele + "]";
				else indexes += "[]";
			}
			return name + " " + ast.id.getText() + indexes + ";";
		}
	}

	// ArrayTypeAST
	public Object visitArrayTypeAST(ArrayTypeAST ast, Object o)
			throws CompilationException {
		String type = ast.type.visit(this, o).toString();
		String exprlst = ast.el.visit(this, o).toString();
		return type + ", " + exprlst;
	}
	
	// EmptyIntLiteralListAST
	public Object visitEmptyIntLiteralListAST(EmptyIntLiteralListAST ast, Object o)
			throws CompilationException {
		
		return "";
	}
	
	// IntLiteralListAST
	public Object visitIntLiteralListAST(IntLiteralListAST ast, Object o)
			throws CompilationException {
		String head = ast.i.visit(this, o).toString();
		String tail = ast.l.visit(this, o).toString();
		return head + " " + tail;
	}

	// EmptyExprListAST
	public Object visitEmptyExprListAST(EmptyExprListAST ast, Object o)
			throws CompilationException {
		return "";
	}

	// ExprListAST
	public Object visitExprListAST(ExprListAST ast, Object o)
			throws CompilationException {
		String head = ast.e.visit(this, o).toString();
		String tail = ast.l.visit(this, o).toString();
		if (ast.l instanceof EmptyExprListAST)
			return head;
		else return head + ", " + tail;
	}

	// ProcDeclPartAST
	public Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o)
			throws CompilationException {
		String head = ast.o.visit(this, o).toString();
		String tail = ast.p.visit(this, o).toString();
		return head + "\n" + tail;
	}

	// EmptyProDeclPartAST
	public Object visitEmptyProcDeclPartAST(EmptyProcDeclPartAST ast, Object o)
			throws CompilationException {
		return "";
	}

	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		String para = fAst.para.visit(this, o).toString();
		String compStm = fAst.c.visit(this, o).toString(); 
		String type = fAst.ret.visit(this, o).toString();
		return type + " " + fAst.name.getText() + "(" + para + ")\n" + compStm ;
	}

	// ProcDeclAST
	public Object visitProcDeclAST(ProcDeclAST ast, Object o)
			throws CompilationException {
		String para = ast.para.visit(this, o).toString();
		String compStm = ast.c.visit(this, o).toString(); 
		return "void " + ast.name.getText() + "(" + para + ")\n" + compStm;
	}
	
	// GlobalVarDeclPartAST
	public Object visitGlobalVarDeclPartAST(GlobalVarDeclPartAST ast, Object o)
			throws CompilationException {
		
		return ast.vp.visit(this, o).toString();
	}
	
	// ParaAST
	public Object visitParaAST(ParaAST ast, Object o)
			throws CompilationException{
	
			String type = ast.t.visit(this, o).toString();
			return type + " " + ast.id.getText();
		
	}
	
	
	
	// ParaListAST
	public Object visitParaListAST(ParaListAST ast, Object o)
			throws CompilationException {
		
		if ( !( ast.p instanceof EmptyParaListAST ) )
		{
				String head = ast.v.visit(this, o).toString();
				String tail = ast.p.visit(this, o).toString();
				return head + ", " + tail;
		}
		else
		{
				String head = ast.v.visit(this, o).toString();
				return head;
		}
		
	}

	// EmptyParaListAST
	public Object visitEmptyParaListAST(EmptyParaListAST ast, Object o)
			throws CompilationException {
		return "";
	}

	// BoolTypeAST
	public Object visitBoolTypeAST(BoolTypeAST ast, Object o)
			throws CompilationException {
		
		return "boolean";
	}

	// IntTypeAST
	public Object visitIntTypeAST(IntTypeAST ast, Object o)
			throws CompilationException {
		
		return "int";
	}

	// VoidTypeAST
	public Object visitVoidTypeAST(VoidTypeAST ast, Object o)
			throws CompilationException {
		
		return "void";
	}
	
	// FloatTypeAST
	public Object visitFloatTypeAST(FloatTypeAST ast, Object o)
			throws CompilationException {
		
		return "float";
	}
	
	// DoubleTypeAST
	public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o)
			throws CompilationException {
		
		return "double";
	}
	
	// CharTypeAST
	public Object visitCharTypeAST(CharTypeAST ast, Object o)
			throws CompilationException {
		
		return "char";
	}
	
	// WCharTTypeAST
	public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o)
			throws CompilationException {
		
		return "wchar_t";
	}
	
	// SignedTypeAST
	public Object visitSignedTypeAST(SignedTypeAST ast, Object o)
			throws CompilationException {
		
		return "signed";
	}
	
	// TypeListAST
	public Object visitTypeListAST(TypeListAST ast, Object o)
			throws CompilationException {
		String head = ast.t.visit(this, o).toString();
		String tail = ast.tl.visit(this, o).toString();
		if ( ast.tl instanceof EmptyTypeListAST)
			return head;
		else return head + " " + tail;
	}
	
	// EmptyTypeListAST
	public Object visitEmptyTypeListAST(EmptyTypeListAST ast, Object o)
			throws CompilationException {
		
		return "";
	}
	
	// UnsignedTypeAST
	public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o)
			throws CompilationException {
		
		return "unsigned";
	}
	
	// LongTypeAST
	public Object visitLongTypeAST(LongTypeAST ast, Object o)
			throws CompilationException {
		
		return "long";
	}
	
	// ShortTypeAST
	public Object visitShortTypeAST(ShortTypeAST ast, Object o)
			throws CompilationException {
		
		return "short";
	}
	
	// PointerTypeAST
	public Object visitPointerTypeAST(PointerTypeAST ast, Object o)
			throws CompilationException {
		
		return "*";
	}

	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		String head = ast.o.visit(this, o).toString();
		String tail = ast.s.visit(this, o).toString();
		if ( ast.s instanceof EmptyStmtListAST)
			return head;
		else return head + "\n" + tail ;
	}

	// EmptyStmtListAST
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o)
			throws CompilationException {
		return "";
	}
	
	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {
		String stmlst = ast.s.visit(this, o).toString();
		return "{\n" + stmlst + "\n}";
	}

	// AssiStmtAST
	public Object visitAssiStmtAST(AssiStmtAST ast, Object o)
			throws CompilationException {
		String lvalue = ast.l.visit(this, o).toString();
		String expr = ast.e.visit(this, o).toString();
		return expr + " = " + lvalue;
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		
		return ast.e.visit(this, o).toString() + ";";
	}

	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {
		String expr = ast.e.visit(this, o).toString();
		String stm1 = ast.s1.visit(this, o).toString();
		String stm2 = ast.s2.visit(this, o).toString();
		return "if (" + expr + ")\n  " + stm1 + "\nelse\n  " + stm2;
	}

	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {
		String expr = ast.e.visit(this, o).toString();
		String stm = ast.s.visit(this, o).toString();
		return "if (" + expr + ")\n  " + stm ;
	}

	// RepeatStmtAST
	public Object visitRepeatStmtAST(RepeatStmtAST ast, Object o)
			throws CompilationException {
		//similar to do while 
		return "";
	}

	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		String expr = ast.e.visit(this, o).toString();
		return "return " + expr + ";";
	}

	// CallStmtAST
	public Object visitCallStmtAST(CallStmtAST ast, Object o)
			throws CompilationException {
		String exprlst = ast.e.visit(this, o).toString();
		
		return ast.name + "(" + exprlst + ")";
	}

	// ForStmtAST
	public Object visitForStmtAST(ForStmtAST ast, Object o)
			throws CompilationException {
		String init = ast.e1.visit(this, o).toString();
		String expr = ast.e2.visit(this, o).toString();
		String exprlst = ast.e3.visit(this, o).toString();
		String stm = ast.o.visit(this, o).toString();
		return "for ( " + init + "; " + expr + "; " + exprlst + " )\n" + stm ;
	}

	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		String expr = ast.e.visit(this, o).toString();
		String stm = ast.o.visit(this, o).toString();
		return "while ( " + expr + " )\n" + stm;
	}
	
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST ast, Object o)
			throws CompilationException {
		String expr = ast.e.visit(this, o).toString();
		String stm = ast.o.visit(this, o).toString();
		if (ast.o instanceof CompStmtAST)
			return "do\n" + stm + "\nwhile (" + expr + ")";
		else return "do\n{\n" + stm + "\n}\n" + "while (" + expr + ")"; 
	}
	
	// SwitchStmtAST
	public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o)
			throws CompilationException {
		
		String expr = ast.e.visit(this, o).toString();
		String stm = ast.o.visit(this, o).toString();
		return "switch (" + expr + ")\n" + stm;
	}
	
	// CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST ast, Object o)
			throws CompilationException {
		String expr = ast.e.visit(this, o).toString();
		String stmlst = ast.s.visit(this, o).toString();		
		return "case " + expr + " :\n" + stmlst;
	}
	
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o)
			throws CompilationException {
		String stmlst = ast.s.visit(this, o).toString();
		return "default:\n" + stmlst;
	}
	
	// Declaration Stm
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		
		return ast.dl.visit(this, o);
	}

	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		
		return "break;";
	}

	// CompStmtAST
	public Object visitCompStatAST(CompStmtAST ast, Object o)
			throws CompilationException {
		String stmlst = ast.s.visit(this, o).toString();
		return "{\n" + stmlst + "\n}";
	}

	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
	
		return "continue;";
	}

	// LiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {

		return ast.literal.getText();
	}
	
	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {

		return ast.literal.getText();
	}
	
	//BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {

		return ast.literal.getText();
	}
	
	//StringLiteralAST
	public Object visitStringLiteralAST(StringLiteralAST ast, Object o)
			throws CompilationException {

		return ast.literal.getText();
	}
	
	//CharLiteralAST
	public Object visitCharLiteralAST(CharLiteralAST ast, Object o)
			throws CompilationException {

		return ast.literal.getText();
	}

	// UnaryExprAST
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		String expr = ast.e.visit(this, o).toString();
		if (ast.opType == 4 || ast.opType == 5) // i++, i--
			return expr + ast.op.getText() ;
		else return ast.op.getText() + expr;
	}

	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		String expr1 = ast.e1.visit(this, o).toString();
		String expr2 = ast.e2.visit(this, o).toString();
		
		if (ast.e1 instanceof BinExprAST && ast.e2 instanceof BinExprAST && ast.opType != 0 && ast.opType != 23 && ast.opType != 22)
			return "(" + expr1 +") " + ast.op.getText() + " (" + expr2 + ")";
		else if (ast.e1 instanceof BinExprAST && ast.opType != 0 && ast.opType != 23 && ast.opType != 22)
			return "(" + expr1 + ") " + ast.op.getText() + " " + expr2;
		else if (ast.e2 instanceof BinExprAST && ast.opType != 0 && ast.opType != 23 && ast.opType != 22)
			return expr1 + ast.op.getText() + " (" + expr2 + ")";
		else 
			return expr1 + " " +  ast.op.getText() + " " + expr2 ;
	}
	
	// TernaryExprAST
	public Object visitTernaryExprAST(TernaryExprAST ast, Object o)
			throws CompilationException {
		
		return "";
	}

	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		
		return ast.name.getText();
	}

	// EleExprAST
	public Object visitEleExprAST(EleExprAST ast, Object o)
			throws CompilationException {
		
		String exprlst = ast.e.visit(this, o).toString();
		Scanner sc = new Scanner(exprlst);
		sc.useDelimiter(",");
		String indexes = "";
		while (sc.hasNext())
		{
			String ele = sc.next().trim();
			indexes += "[" + ele + "]";
		}
		return ast.name.getText() + indexes;
	}

	// CallExprAST
	public Object visitCallExprAST(CallExprAST ast, Object o)
			throws CompilationException {
		
		return "";
	}
	
	// NullExprAST
	public Object visitNullExprAST(NullExprAST ast, Object o)
			throws CompilationException {
		
		return "null";
	}
	
	// CastExprAST
	public Object visitCastExprAST(CastExprAST ast, Object o)
			throws CompilationException {
		
		return "";
	}

	// CastInt2FloatAST
	public Object visitCastInt2FloatAST(CastInt2FloatAST ast, Object o)
			throws CompilationException {

		return "";
	}
	
	// initializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {

		return ast.e.visit(this, o).toString();
	}
	
	public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o)
			throws CompilationException {

		return "";
	}
	
	public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o)
			throws CompilationException {

		return "";
	}

	public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o)
			throws CompilationException {

		return "";
	}
	
	public Object visitEmptyVarInitializerListAST(EmptyVarInitializerListAST ast, Object o)
			throws CompilationException {
		return "";
	}

	public Object visitEmptyArrayInitializerListAST(EmptyArrayInitializerListAST ast,
			Object o) throws CompilationException {
		return "";
	}
	
	public Object visitEmptyArrayInitializerAST(EmptyArrayInitializerAST ast,
			Object o) throws CompilationException {
		return "";
	}
	
	public Object visitForInitAST(ForInitAST ast,
			Object o) throws CompilationException {
		return ast.e.visit(this, o).toString();
	}
	
	public Object visitDefineDirectiveAST(DefineDirectiveAST ast,
			Object o) throws CompilationException {
		return "";
	}
	
	
}
