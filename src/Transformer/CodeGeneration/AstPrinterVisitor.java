
package Transformer.CodeGeneration;

import Transformer.ASTs.*;
import java.io.*;

public class AstPrinterVisitor extends DoNothingVisitor {
	private int indent;
	private String astLogContent;
	private String astLogFilename;

	public AstPrinterVisitor(String filename) {
		indent = 0;
		astLogContent = "";
		astLogFilename = filename;
	}

	private String indentString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indent; ++i) {			
			//sb.append("  ");
			sb.append("\t");
		}
		return sb.toString();
	}

	private void print(String s) {		
		//System.out.println(s);
		astLogContent += s + "\r\n";
	}
	
	private void printToFile() {
		if (astLogFilename.equals(""))
			return;
		try{
			FileWriter fstream = new FileWriter(astLogFilename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(astLogContent);
			out.close();
		}catch (Exception e){e.printStackTrace();}
	}

	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Program(");
		++indent;
		ast.dl.visit(this, o);
		--indent;
		print(indentString() + ")");
		this.printToFile();
		return null;
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
		VarDeclAST vAst = (VarDeclAST) ast;
		print(indentString() + "VarDecl(");
		++indent;
		print(indentString() + vAst.id.getText());
		vAst.t.visit(this, o);
		if (vAst.init != null) vAst.init.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// TypeListAST
	public Object visitTypeListAST(TypeListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "TypeList(");
		++indent;
		ast.t.visit(this, o);
		ast.tl.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// BoolTypeAST
	public Object visitBoolTypeAST(BoolTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Boolean");
		return null;
	}

	// IntTypeAST
	public Object visitIntTypeAST(IntTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Int");
		return null;
	}

	// VoidTypeAST
	public Object visitVoidTypeAST(VoidTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Void");
		return null;
	}
	
	// FloatTypeAST
	public Object visitFloatTypeAST(FloatTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Float");
		return null;
	}
	
	// CharTypeAST
	public Object visitCharTypeAST(CharTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Char");
		return null;
	}
	
	// UnsignedTypeAST
	public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Unsigned");
		return null;
	}
	
	// WCharTTypeAST
	public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "WChar_T");
		return null;
	}
	
	// SignedTypeAST
	public Object visitSignedTypeAST(SignedTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Signed");
		return null;
	}
	
	// LongTypeAST
	public Object visitLongTypeAST(LongTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Long");
		return null;
	}
	
	// ShortTypeAST
	public Object visitShortTypeAST(ShortTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Short");
		return null;
	}
	
	// DoubleTypeAST
	public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Double");
		return null;
	}
	
	// ArrayTypeAST
	public Object visitArrayTypeAST(ArrayTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "ArrayType(");
		++indent;
		ast.type.visit(this, o);
		ast.el.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// PointerTypeAST
	public Object visitPointerTypeAST(PointerTypeAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Pointer(");
		++indent;
		ast.t.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// ExprListAST
	public Object visitExprListAST(ExprListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "ExprList(");
		++indent;
		ast.e.visit(this, o);
		ast.l.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// NullExprAST
	public Object visitNullExprAST(NullExprAST ast, Object o)
			throws CompilationException {
		print(indentString() + "Null(");
		print(indentString() + ")");
		return null;
	}
	
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		print(indentString() + "VarExpr(");
		++indent;
		print(indentString() + ast.name.getText());
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// IntLiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {

		print(indentString() + "IntLiteral(");
		++indent;
		print(indentString() + ast.literal.getText());
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {

		print(indentString() + "FloatLiteral(");
		++indent;
		print(indentString() + ast.literal.getText());
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	//BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {

		print(indentString() + "BoolLiteral(");
		++indent;
		print(indentString() + ast.literal.getText());
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	//StringLiteralAST
	public Object visitStringLiteralAST(StringLiteralAST ast, Object o)
			throws CompilationException {

		print(indentString() + "StringLiteral(");
		++indent;
		print(indentString() + ast.literal.getText());
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	//CharLiteralAST
	public Object visitCharLiteralAST(CharLiteralAST ast, Object o)
			throws CompilationException {

		print(indentString() + "CharLiteral(");
		++indent;
		print(indentString() + ast.literal.getText());
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// UnaryExprAST
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		UnaryExprAST uAst = (UnaryExprAST) ast;
		print(indentString() + "UnaryExpr(");
		++indent;
		print(indentString() + uAst.op.getText());
		uAst.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}

	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		BinExprAST bAst = (BinExprAST) ast;
		print(indentString() + "BinExpr(");
		++indent;
		bAst.e1.visit(this, o);
		print(indentString() + bAst.op.getText());
		bAst.e2.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// TernaryExprAST
	public Object visitTernaryExprAST(TernaryExprAST ast, Object o)
			throws CompilationException {
		TernaryExprAST tAst = (TernaryExprAST) ast;
		print(indentString() + "TernaryExpr(");
		++indent;
		tAst.e1.visit(this, o);
		tAst.e2.visit(this, o);
		tAst.e3.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// CallExprAST
	public Object visitCallExprAST(CallExprAST ast, Object o)
			throws CompilationException {
		CallExprAST cAst = (CallExprAST) ast;
		print(indentString() + "CallExpr(");
		++indent;
		print(indentString() + cAst.name.getText());
		cAst.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}

	// EleExprAST
	public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException { 
		EleExprAST eAst = (EleExprAST) ast;
		print(indentString() + "EleExpr(");
		++indent;
		print(indentString() + eAst.name.getText());
		eAst.e.visit(this,o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// VarInitializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {

		print(indentString() + "VarInitializer(");
		++indent;
		ast.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// ArrayInitializer
	public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o)
	throws CompilationException {
		print(indentString() + "ArrayInitializer(");
		++indent;
		ast.v.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// VarInitializerList
	public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o)
		throws CompilationException {
		print(indentString() + "VarInitializerList(");
		++indent;
		ast.v.visit(this, o);
		ast.vl.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// ArrayInitializerList
	public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o)
		throws CompilationException {
	
		print(indentString() + "ArrayInitializerList(");
		++indent;
		ast.a.visit(this, o);
		ast.al.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		print(indentString() + "FuncDecl(");
		++indent;
		print(indentString() + fAst.name.getText());
		fAst.para.visit(this, o);
		fAst.ret.visit(this, o);
		fAst.c.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// ParaAST
	public Object visitParaAST(ParaAST ast, Object o)
			throws CompilationException{
		ParaAST pAst = (ParaAST) ast;
		print(indentString() + "Para(");
		++indent;
		print(indentString() + pAst.id.getText());
		pAst.t.visit(this, o);
		if (pAst.ref)
			print(indentString() + "ref");
		--indent;
		print(indentString() + ")");
		return null;
	}

	// ParaListAST
	public Object visitParaListAST(ParaListAST ast, Object o)
			throws CompilationException {
		ParaListAST pAst = (ParaListAST) ast;
		print(indentString() + "ParaList(");
		++indent;
		pAst.v.visit(this, o);
		pAst.p.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		StmtListAST sAst = (StmtListAST) ast;
		print(indentString() + "StmtList(");
		++indent;
		sAst.o.visit(this, o);
		sAst.s.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {
		CompStmtAST cAst = (CompStmtAST) ast;
		print(indentString() + "CompStmt(");
		++indent;
		cAst.s.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST ast, Object o)
			throws CompilationException {
		CaseStmtAST cAst = (CaseStmtAST) ast;
		print(indentString() + "CaseStmt(");
		++indent;
		cAst.e.visit(this, o);
		cAst.s.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o)
			throws CompilationException {
		DefaultStmtAST dAst = (DefaultStmtAST) ast;
		print(indentString() + "DefaultStmt(");
		++indent;
		dAst.s.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		print(indentString() + "ExprStmt(");
		++indent;
		ast.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		print(indentString() + "DeclarationStmt(");
		++indent;
		ast.dl.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {
		IfThenElseStmtAST iAst = (IfThenElseStmtAST) ast;
		print(indentString() + "IfThenElseStmt(");
		++indent;
		iAst.e.visit(this, o);
		iAst.s1.visit(this, o);
		iAst.s2.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}

	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {
		IfThenStmtAST iAst = (IfThenStmtAST) ast;
		print(indentString() + "IfThenStmt(");
		++indent;
		iAst.e.visit(this, o);
		iAst.s.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}

	// RepeatStmtAST
	public Object visitRepeatStmtAST(RepeatStmtAST ast, Object o)
			throws CompilationException {
		RepeatStmtAST rAst = (RepeatStmtAST) ast;
		print(indentString() + "RepeatStmt(");
		++indent;
		rAst.s.visit(this, o);
		rAst.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}

	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		RetStmtAST rAst = (RetStmtAST) ast;
		if (rAst.e != null) {
			print(indentString() + "RetStmt(");
			++indent;
			rAst.e.visit(this, o);
			--indent;
			print(indentString() + ")");
		}
		else
			print(indentString() + "RetStmt()");
		return null;
	}

	// CallStmtAST
	public Object visitCallStmtAST(CallStmtAST ast, Object o)
			throws CompilationException {

		print(indentString() + "CallStmt(");
		++indent;
		print(indentString() + ast.name.getText());
		ast.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}

	// ForStmtAST
	public Object visitForStmtAST(ForStmtAST ast, Object o)
			throws CompilationException {
		ForStmtAST fAst = (ForStmtAST) ast;
		print(indentString() + "ForStmt(");
		++indent;
		//print(indentString() + fAst.name.getText());
		if (fAst.e1 != null)
			fAst.e1.visit(this, o);
		if (fAst.e2 != null)
			fAst.e2.visit(this, o);
		if (fAst.e3 != null)
			fAst.e3.visit(this, o);
		fAst.o.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	public Object visitForInitAST(ForInitAST ast, Object o)
			throws CompilationException {
		print(indentString() + "ForInit(");
		++indent;
		if (ast.d != null)
			ast.d.visit(this, o);
		if (ast.e != null)
			ast.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}

	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		WhileStmtAST wAst = (WhileStmtAST) ast;
		print(indentString() + "WhileStmt(");
		++indent;
		wAst.e.visit(this, o);
		//trinhgiang-22/10/2013
		wAst.o.visit(this, o);
		//wAst.s.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST ast, Object o)
			throws CompilationException {
		DoStmtAST wAst = (DoStmtAST) ast;
		print(indentString() + "DoStmt(");
		++indent;
		wAst.o.visit(this, o);
		wAst.e.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// SwitchStmtAST
	public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o)
			throws CompilationException {
		SwitchStmtAST sAst = (SwitchStmtAST) ast;
		print(indentString() + "SwitchStmt(");
		++indent;
		sAst.e.visit(this, o);
		sAst.o.visit(this, o);
		--indent;
		print(indentString() + ")");
		return null;
	}
	
	// EmptyStmtListAST
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "EmptyStmtList()");
		return null;
	}

	// EmptyParaListAST
	public Object visitEmptyParaListAST(EmptyParaListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "EmptyParaList()");
		return null;
	}
	
	//	EmptyDeclarationListAST
	public Object visitEmptyDeclarationListAST(EmptyDeclarationListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "EmptyDeclarationList()");
		return null;
	}
	
	// EmptyIntLiteralListAST
	public Object visitEmptyIntLiteralListAST(EmptyIntLiteralListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "EmptyIntLiteralList()");
		return null;
	}

	// EmptyExprListAST
	public Object visitEmptyExprListAST(EmptyExprListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "EmptyExprList()");
		return null;
	}
	
	// EmptyTypeListAST
	public Object visitEmptyTypeListAST(EmptyTypeListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "EmptyTypeList()");
		return null;
	}

	// EmptyVarInitializerListAST
	public Object visitEmptyVarInitializerListAST(EmptyVarInitializerListAST ast, Object o)
			throws CompilationException {
		print(indentString() + "EmptyVarInitializerList()");
		return null;
	}
/*
	public final void print(AST ast) throws CompilationException {
		ast.visit(this, null);
	}
*/
}
