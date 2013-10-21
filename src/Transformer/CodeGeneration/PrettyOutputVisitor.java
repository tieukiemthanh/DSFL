package Transformer.CodeGeneration;

import java.util.*;
import Transformer.ASTs.*;

public class PrettyOutputVisitor extends DoNothingVisitor {
	Emitter em;
	Frame frm;		
	String tab = "\t";
	int scope = 0;
	int line = 1;
	boolean outputLine = false;	

	public PrettyOutputVisitor(String outputVerify_c, boolean debug) throws CompilationException {
		em = new Emitter();
		frm = new Frame();
		em.setFilename(outputVerify_c);
		
		if (debug == false)
			em.print_to_console = false;
		else
			System.out.println("\n\nOUTPUT_C: ("+outputVerify_c+")");
	}		

	public void setModeOutputLine(boolean on) {
		outputLine = on;
	}
	
	public String newline() {
		String newline_str = "\r\n";
		line++;
		return newline_str;
	}
	
	private int getKey() {
		return frm.getNewLabel();
	}
	
	public void inScope() {
		scope++;
	}
	
	public void outScope() {
		scope--;
	}
	
	public void indentString() {
		String indent = "";
		for (int i = 0; i < scope; i++)
			indent += tab;
		String print_line;
		if (line < 10)
			print_line = "  " + line;
		else if (line < 100)
			print_line = " " + line;
		else
			print_line = "" + line;
			
		if (!outputLine)
			em.printout(indent);
		else
			em.printout(print_line + ":  " + indent);
	}
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o)
			throws CompilationException {		
		String option = (String) o;
		if (option != null) {
			if (option.equals("output_line"))
				setModeOutputLine(true);
			else
				setModeOutputLine(false);
		}
		ast.dl.visit(this, o);
		em.printToC();
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
			
		/*String checkForInit = (String) o;
		if (checkForInit != null && checkForInit.equals("forinit")) {
			ast.t.visit(this, o);
			em.printout(ast.id.getText());
			if (ast.init != null) {
				em.printout(" = ");
				ast.init.visit(this, o);
			}
			
			return null;
		}*/
				
		indentString();
		if (ast.t instanceof ArrayTypeAST) {
			ast.t.visit(this, ast.id.getText());
			return null;
		}
		ast.t.visit(this, o);
		//em.printout(" ");
		em.printout(ast.id.getText());
		if (ast.init != null) {
			em.printout(" = ");
			ast.init.visit(this, o);
		}
		em.printout(";" + newline());				
		return null;
	}
	
	// PointerTypeAST
	public Object visitPointerTypeAST(PointerTypeAST ast, Object o)
			throws CompilationException {
		
		return null;
	}
	
	// IntTypeAST
	public Object visitIntTypeAST(IntTypeAST ast, Object o)
			throws CompilationException {
		em.printout("int");
		return null;
	}
	
	// initializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {
		ast.e.visit(this, o);
		return null;
	}
	
	// IntLiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {		
		em.printout(ast.literal.getText());
		return ast.literal.getText();
	}

	// BoolTypeAST
	public Object visitBoolTypeAST(BoolTypeAST ast, Object o)
			throws CompilationException {
		em.printout("bool");
		return null;
	}

	// VoidTypeAST
	public Object visitVoidTypeAST(VoidTypeAST ast, Object o)
			throws CompilationException {
		em.printout("void");
		return "voidType";
	}
	
	// FloatTypeAST
	public Object visitFloatTypeAST(FloatTypeAST ast, Object o)
			throws CompilationException {
		em.printout("float");
		return null;
	}
	
	// CharTypeAST
	public Object visitCharTypeAST(CharTypeAST ast, Object o)
			throws CompilationException {
		em.printout("char");
		return null;
	}
	
	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	//BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	//StringLiteralAST
	public Object visitStringLiteralAST(StringLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	//CharLiteralAST
	public Object visitCharLiteralAST(CharLiteralAST ast, Object o)
			throws CompilationException {
		em.printout(ast.literal.getText());
		return null;
	}
	
	// ArrayTypeAST
	public Object visitArrayTypeAST(ArrayTypeAST ast, Object o)
			throws CompilationException {
		String id = (String) o;
		ast.type.visit(this, o);
		if (id != null) {
			em.printout(id);
			ast.el.visit(this, "arraytype");		
			em.printout(";" + newline());
		}
		return null;
	}
	
	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		ast.line = line;
		ast.dl.visit(this, o);
		return null;
	}
	
	/*/ ProcDeclPartAST
	public Object visitProcDeclPartAST(ProcDeclPartAST ast, Object o)
			throws CompilationException {
		ast.o.visit(this, o);
		ast.p.visit(this, o);
		return null;
	}*/
	
	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		fAst.line = line;
		indentString();
		fAst.ret.visit(this, o);
		em.printout(fAst.name.getText());
		em.printout("(");
		fAst.para.visit(this, o);		
		em.printout(")" + newline());
		fAst.c.visit(this, o);
		return null;
	}

	// ParaListAST
	public Object visitParaListAST(ParaListAST ast, Object o)
			throws CompilationException {
		String checkMorePara = (String) o;
		if (checkMorePara != null && checkMorePara.equals("morePara"))
			em.printout(", ");
		ast.v.visit(this, o);
		
		if (ast.p.v != null) {
			String morePara = "morePara";
			ast.p.visit(this, morePara);
		}
		else
			ast.p.visit(this, o);
		
		return null;
	}
	
	// ParaAST
	public Object visitParaAST(ParaAST ast, Object o)
			throws CompilationException{
		ast.t.visit(this, null);
		em.printout(ast.id.getText());
		if (ast.t instanceof ArrayTypeAST) {
			em.printout("[]");
		}
		return ast.id.getText();
	}

	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST ast, Object o)
			throws CompilationException {		
		indentString();
		em.printout("{" + newline());
		String checkSwitch = (String) o;
		if (checkSwitch != null && checkSwitch.equals("switch"))
			ast.s.visit(this, "switch");
		else
			ast.s.visit(this, "block");
		return null;
	}

	// StmtListAST
	public Object visitStmtListAST(StmtListAST ast, Object o)
			throws CompilationException {
		String checkBlock = (String) o;		
		if (checkBlock != null && (checkBlock.equals("block") || checkBlock.equals("switch")))
			inScope();	
		ast.o.visit(this, null);
		if (checkBlock != null && (checkBlock.equals("case") || checkBlock.equals("switch")))
			ast.s.visit(this, o);
		else
			ast.s.visit(this, "statement");		
		return null;
	}

	// EmptyStmtListAST
	public Object visitEmptyStmtListAST(EmptyStmtListAST ast, Object o)
			throws CompilationException {
		String checkBlock = (String) o;
		if (checkBlock != null) {
			if (checkBlock.equals("switch")) {
				outScope();outScope();
				indentString();
				em.printout("}" + newline());
			}
			if (checkBlock.equals("block")) {
				indentString();
				em.printout("}" + newline());
			}
			if (checkBlock.equals("statement")) {
				outScope();
				indentString();
				em.printout("}" + newline());
			}
		}
		return null;
	}	

	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {		
		em.printout(ast.name.getText());
		return ast.name.getText();
	}

	// EleExprAST
	//public Token			name;
	//public ExprListAST	e;
	public Object visitEleExprAST(EleExprAST ast, Object o)
			throws CompilationException {
		em.printout(ast.name.getText());
		ast.e.visit(this, "arraytype");
		return null;
	}

	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		indentString();
		if (!(ast.e instanceof TernaryExprAST)) {
			ast.line = line;			
			em.setFilter(true);
		}		
		String short_if = (String) ast.e.visit(this, o);
		if (short_if != null && short_if.equals("short_if"))
			return null;
		em.printout(";"+newline());
		if (!(ast.e instanceof TernaryExprAST)) {			
			ast.line_str = em.setFilter(false);
		}
		return null;
	}
	
	// UnaryExprAST	
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		if (ast.opType == 4 || ast.opType == 5) {
			// dau x++, x--
			ast.e.visit(this, o);
			em.printout(ast.op.getText());
		}
		else {
			em.printout(ast.op.getText());
			ast.e.visit(this, o);
		}
		return null;
	}
	
	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		String checkAssign = (String) o;
		boolean checkAssgn = false;
		if (checkAssign != null && checkAssign.equals("assign"))
			checkAssgn = true;
		
		if (ast.opType >= 24 && ast.opType <= 28) {
			// opType: + - * / %
			if (!checkAssgn)
				em.printout("(");
			ast.e1.visit(this, null);			
			em.printout(" " + ast.op.getText() + " ");			
			ast.e2.visit(this, null);
			if (!checkAssgn)
				em.printout(")");
		}
		else {
			boolean assiStmt = false;
			if (ast.opType == 0 && ast.parent instanceof ExprStmtAST) {				
				assiStmt = true;				
			}
			else 
				em.printout("(");
							
			ast.e1.visit(this, null);
			em.printout(" " + ast.op.getText() + " ");
			if (assiStmt) // phep gan				
				ast.e2.visit(this, "assign");
			else
				ast.e2.visit(this, null);
			if (!assiStmt)
				em.printout(")");
		}
		return null;
	}	
	
	// TernaryExprAST
	// x == 0 ? y = 0 : y = 1
		//indentString();
		//ast.e.visit(this, o);
		//em.printout(";"+newline());
	public Object visitTernaryExprAST(TernaryExprAST ast, Object o)
			throws CompilationException {		
		ast.l1 = line;
		em.printout("if (");
		
		em.setFilter(true);
		ast.e1.visit(this, o);
		ast.s1 = em.setFilter(false);		
		
		em.printout(")" + newline());			
			inScope();
			ast.l2 = line;
			indentString();
			em.setFilter(true);
			ast.e2.visit(this, o);			
			outScope();
			em.printout(";"+newline());
			ast.s2 = em.setFilter(false);
		indentString();
		em.printout("else" + newline());
			inScope();
			ast.l3 = line;
			indentString();
			em.setFilter(true);
			ast.e3.visit(this, o);			
			outScope();
			em.printout(";"+newline());
			ast.s3 = em.setFilter(false);
		return "short_if";
	}
	
	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST ast, Object o)
			throws CompilationException {		
		ast.line = line;
		indentString();
		em.printout("if (");
		
		em.setFilter(true);
		ast.e.visit(this, o);
		ast.line_str = em.setFilter(false);		
		
		em.printout(")" + newline());
		if (ast.s instanceof CompStmtAST)
			ast.s.visit(this, o);
		else {
			inScope();
			ast.s.visit(this, o);
			outScope();
		}
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST ast, Object o)
			throws CompilationException {	
		ast.line = line;
		indentString();
		em.printout("if (");
		
		em.setFilter(true);
		ast.e.visit(this, o);
		ast.line_str = em.setFilter(false);		
		
		em.printout(")" + newline());
		if (ast.s1 instanceof CompStmtAST)
			ast.s1.visit(this, o);
		else {
			indentString();
			em.printout("{" + newline());
			inScope();
			ast.s1.visit(this, o);
			outScope();
			indentString();
			em.printout("}" + newline());
		}
		indentString();
		
		ast.line_else = line;
		
		em.printout("else" + newline());
		if (ast.s2 instanceof CompStmtAST)
			ast.s2.visit(this, o);
		else {
			indentString();
			em.printout("{" + newline());
			inScope();
			ast.s2.visit(this, o);
			outScope();
			indentString();
			em.printout("}" + newline());
		}
		return null;
	}

	// SwitchStmtAST
	public Object visitSwitchStmtAST(SwitchStmtAST ast, Object o)
			throws CompilationException {
	//public ExprAST e;
	//public OneStmtAST o;
		indentString();
		em.printout("switch (");
		ast.e.visit(this, null);
		em.printout(")" + newline());
		ast.o.visit(this, "switch");
		inLineCase = false;
		return null;
	}

	boolean inLineCase = false;
	// CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST ast, Object o)
			throws CompilationException {
	//public ExprAST e;
	//public StmtListAST s;
		if (inLineCase)
			outScope();
		indentString();
		em.printout("case ");
		ast.e.visit(this, null);
		em.printout(": " + newline());
		inScope();
		inLineCase = true;
		ast.s.visit(this, "case");
		return null;
	}
	
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST ast, Object o)
			throws CompilationException {
		if (inLineCase)
			outScope();
		indentString();
		em.printout("default:"+newline());
		inScope();
		ast.s.visit(this, "case");
		return null;
	}	

	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		ast.line = line;
		indentString();
		em.printout("break;"+newline());
		return null;
	}	

	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST ast, Object o)
			throws CompilationException {
		ast.line = line;
		indentString();
		em.printout("while (");
		em.setFilter(true);
		ast.e.visit(this, o);
		ast.line_str = em.setFilter(false);		
		em.printout(")" + newline());
		if (ast.o instanceof CompStmtAST)
			ast.o.visit(this, o);
		else {
			inScope();
			ast.o.visit(this, o);
			outScope();
		}
		return null;
	}
	
	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
		ast.line = line;
		indentString();
		em.printout("continue;" + newline());
		return null;
	}
	
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST ast, Object o)
			throws CompilationException {		
		indentString();
		em.printout("do" + newline());
		if (ast.o instanceof CompStmtAST)
			ast.o.visit(this, o);
		else {
			inScope();
			ast.o.visit(this, o);
			outScope();
		}
		ast.line = line;
		indentString();
		em.printout("while (");
		em.setFilter(true);
		ast.e.visit(this, o);
		ast.line_str = em.setFilter(false);		
		em.printout(");" + newline());
		return null;
	}

	// ExprListAST
	public Object visitExprListAST(ExprListAST ast, Object o)
			throws CompilationException {
		String check = (String) o;
		if (check != null && check.equals("for")) {
			ast.line = line;
			em.setFilter(true);
			ast.e.visit(this, o);
			ast.line_str = em.setFilter(false);
			ast.line_str += ";\r\n";
			ast.l.visit(this, "for2");
		}	
		if (check != null && check.equals("for2")) {
			em.printout(", ");
			ast.line = line;
			em.setFilter(true);
			ast.e.visit(this, o);
			ast.line_str = em.setFilter(false);
			ast.line_str += ";\r\n";
			ast.l.visit(this, "for2");
		}
		if (check != null && check.equals("arraytype")) {
			em.printout("[");
			ast.e.visit(this, o);
			em.printout("]");
			ast.l.visit(this, o);
		}
		if (check != null && check.equals("CallExpr")) {
			ast.e.visit(this, o);
			ast.l.visit(this, "CallExpr2");
		}
		if (check != null && check.equals("CallExpr2")) {
			em.printout(", ");
			ast.e.visit(this, o);
			ast.l.visit(this, o);
		}
		return null;		
	}
	
	// ForStmtAST
	//public ForInitAST		e1;
	//public ExprAST			e2;
	//public ExprListAST		e3;
	//public OneStmtAST		o;
	public Object visitForStmtAST(ForStmtAST ast, Object o)
			throws CompilationException {
		if (ast.e1.type == 2) {
			// for (i=1,j=1; ... ; ...)
			ast.line = line;
			indentString();
			em.printout("for (");			
			ast.e1.visit(this, "for");
			em.printout("; ");
			if (ast.e2 != null) {
				em.setFilter(true);
				ast.e2.visit(this, o);
				ast.s2 = em.setFilter(false);
			}
			em.printout("; ");			
			ast.e3.visit(this, "for");			
			em.printout(")" + newline());
		}
		else {
			if (ast.e1.type == 1) {
				// for (int i=1; ... ; ...)
				//em.setForInit(true);
				ast.e1.visit(this, o);
				//String for_init = em.setForInit(false);
				/*String[] arr_init = for_init.split(";");
				arr_init[0] = arr_init[0].trim();arr_init[1] = arr_init[1].trim();
				if (arr_init.length == 3) arr_init[2] = arr_init[2].trim();*/
				//indentString();
				//em.printout(for_init + ";" + newline());
			}
			ast.line = line;
			indentString();			
			em.printout("for ( ; ");						
			if (ast.e2 != null) {
				em.setFilter(true);
				ast.e2.visit(this, o);
				ast.s2 = em.setFilter(false);
			}
			em.printout("; ");			
			ast.e3.visit(this, "for");			
			em.printout(")" + newline());
		}
		
		if (ast.o instanceof CompStmtAST)
			ast.o.visit(this, o);
		else {
			inScope();
			ast.o.visit(this, o);
			outScope();
		}
		return null;
	}
	
	//ForInitAST
	//public int type; //1:localVarDecl, 2: expressions, 3:null
	//public DeclarationListAST d;
	//public ExprListAST e;
	public Object visitForInitAST(ForInitAST ast, Object o)
			throws CompilationException {
		if (ast.type == 1) {
			ast.d.visit(this, o);
		}
		if (ast.type == 2) {
			ast.e.visit(this, o);
		}
		return null;
	}
	
	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST ast, Object o)
			throws CompilationException {
		ast.line = line;
		indentString();
		em.setFilter(true);
		em.printout("return ");
		ast.e.visit(this, o);
		em.printout(";" + newline());
		ast.line_str = em.setFilter(false);
		return null;
	}

	//extended grammar:
	// TypeListAST
	//TypeAST t;
	//TypeListAST tl;	
	public Object visitTypeListAST(TypeListAST ast, Object o)
			throws CompilationException {
		ast.t.visit(this, o);
		em.printout(" ");
		ast.tl.visit(this, o);
		return null;
	}

	//////////////////////////////////////////////////////////////////
	// DoubleTypeAST
	public Object visitDoubleTypeAST(DoubleTypeAST ast, Object o)
			throws CompilationException {
		em.printout("double");
		return null;
	}
	
	// WCharTTypeAST
	public Object visitWCharTTypeAST(WCharTTypeAST ast, Object o)
			throws CompilationException {
		em.printout("wchar_t");
		return null;
	}
	
	// SignedTypeAST
	public Object visitSignedTypeAST(SignedTypeAST ast, Object o)
			throws CompilationException {
		em.printout("signed");
		return null;
	}

	// UnsignedTypeAST
	public Object visitUnsignedTypeAST(UnsignedTypeAST ast, Object o)
			throws CompilationException {
		em.printout("unsigned");
		return null;
	}
	
	// LongTypeAST
	public Object visitLongTypeAST(LongTypeAST ast, Object o)
			throws CompilationException {
		em.printout("long");
		return null;
	}
	
	// ShortTypeAST
	public Object visitShortTypeAST(ShortTypeAST ast, Object o)
			throws CompilationException {
		em.printout("short");
		return null;
	}
	
	//////////////////////////////////////////////////////////////////
	
	//DefineDirectiveAST #define
	public Object visitDefineDirectiveAST(DefineDirectiveAST ast, Object o) 
		throws CompilationException
	{
		ast.line = line;
		indentString();
		em.printout("#define ");
		em.printout(ast.id.getText());
		em.printout(" ");
		ast.l.visit(this, o);
		em.printout(newline());
		return null;
	}
	
	//////////////////////////////////////////////////////////////////
	// Function Call
	// CallExprAST
	public Object visitCallExprAST(CallExprAST ast, Object o)
			throws CompilationException {		
		em.printout(ast.name.getText() + "(");
		ast.e.visit(this, "CallExpr");
		em.printout(")");
		return null;
	}

	
}

/*
ExprStmtAST
DeclarationStmtAST => VarDeclAST
IfThenStmtAST
IfThenElseStmtAST
WhileStmtAST
DoStmtAST
ForStmtAST

BreakStmtAST
ContStmtAST
RetStmtAST

SwitchStmtAST
CaseStmtAST
DefaultStmtAST

CompStmtAST
*/
