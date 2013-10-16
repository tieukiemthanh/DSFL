package Transformer.ASTs;

import org.antlr.runtime.*;

public class BinExprAST extends ExprAST {
	public static final int ASSIGN = 0;
	public static final int PLUS_ASSIGN = 1;
	public static final int MINUS_ASSIGN = 2;
	public static final int STAR_ASSIGN = 3;
	public static final int DIV_ASSIGN = 4;
	public static final int MOD_ASSIGN = 5;
	public static final int AND_ASSIGN = 6;
	public static final int OR_ASSIGN = 7;
	public static final int XOR_ASSIGN = 8;
	public static final int SHIFT_RIGHT_ASSIGN = 9;
	public static final int SHIFT_LEFT_ASSIGN = 10;
	
	public static final int LOGICAL_OR = 11; // ||
	public static final int LOGICAL_AND = 12; // &&
	public static final int OR = 13;
	public static final int XOR = 14;
	public static final int AND = 15;
	public static final int EQUAL = 16; // ==
	public static final int NOT_EQUAL = 17; // !=
	
	public static final int LESS_OR_EQUAL = 18; // <=
	public static final int GREATER_OR_EQUAL = 19; // >=
	public static final int GREATER_THAN = 20; // >
	public static final int LESS_THAN = 21; // <
	public static final int SHIFT_RIGHT = 22;
	public static final int SHIFT_LEFT = 23;
	
	public static final int PLUS = 24;
	public static final int MINUS = 25;
	public static final int STAR = 26;
	public static final int DIV  = 27;
	public static final int MOD = 28;
	
	public ExprAST 	e1;
	public Token	op;
	public int opType;
	public ExprAST	e2;
	public BinExprAST(ExprAST exp1, int o, Token t, ExprAST exp2) {
		e1=exp1;
		op=t;
		opType=o;
		e2=exp2;
		e1.parent=e2.parent=this;
	}
	public Object visit(Visitor v, Object o) throws CompilationException {
		return v.visitBinExprAST(this,o);
	}	
}