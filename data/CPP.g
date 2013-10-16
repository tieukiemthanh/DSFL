grammar CPP;

options {
  language = Java;
  backtrack = true;
}

@lexer::header {
  package Transformer.Parser;
}

@header{
  package Transformer.Parser;
  import Transformer.ASTs.*;
}

@members {
  private AST in, out;
  
  public AST parse() throws RecognitionException {
    program();
    return out;
  }
}

program
@init{
  AST ast1=null;
}
  : declarationList {ast1 = out; out = new ProgramAST((DeclarationListAST)ast1);}
  ;
  
declarationList 
@init{
  AST ast1=null,ast2=null,ast3=null;
}
  : declaration 
    {
      ast1 = out; 
      out = null; 
      if (in instanceof EmptyDeclarationListAST) 
        ast3 = in.parent;
    } 
    declarationList 
    {
      ast2 = out;
      if (ast1 instanceof DeclarationAST) 
        out = new DeclarationListAST((DeclarationAST)ast1, (DeclarationListAST)ast2);
      else {
         if (ast3 instanceof DeclarationListAST) {
            ((DeclarationListAST)ast3).dl = (DeclarationListAST)ast2;
            out = ast1;
         }
      }
    } 
  | {out = new EmptyDeclarationListAST();};
  
declaration
  : varDecl
  | directive
  | funcDecl
  ;
  
directive
@init{
  AST ast1=null;
}
  : '#define' id=IDENTIFIER pp_token {ast1 = out; out = new DefineDirectiveAST((Token)id, (ExprAST)ast1);}
  ;
  
pp_token
  : id=IDENTIFIER {out = new VarExprAST((Token)id);}
  | literal
  ;  
 
varDecl
  : type {in = out; out = null;}
    varList ';'
  ;
  
varList
@init{
  AST ast1=null,ast2=null;
}
  : var {ast1 = out; out = null;}
    varListRest {ast2 = out; out = null;}
    {out = new DeclarationListAST((DeclarationAST) ast1, (DeclarationListAST) ast2);}
  ;
  
varListRest
@init{
  AST ast1=null,ast2=null;
}
  : ',' var {ast1 = out; out = null;}
    varListRest {ast2 = out; out = null;}
    {out = new DeclarationListAST ((DeclarationAST) ast1, (DeclarationListAST) ast2);}
  | {out = new EmptyDeclarationListAST (); in = out;}
  ;
  
var
@init{
  AST ast1=null,ast2=null,ast3=null;
}
  : {ast1 = in;}
    id=IDENTIFIER 
    arrayDecl {ast2 = out; out = null;}
    {if (!(ast2 instanceof EmptyExprListAST))
      ast1 = new ArrayTypeAST ((TypeAST) ast1, (ExprListAST) ast2);
    }
    initializer? {ast3 = out; out = null;}
    {out = new VarDeclAST ((Token) id, (TypeAST) ast1, (InitializerAST)ast3);}
  ;
  
arrayDecl
@init{
  AST ast1=null,ast2=null;
}
  : ( '[' expr ']' {ast1 = out; out = null;}
    | '[' ']' {ast1 = new NullExprAST();}) 
    arrayDecl {ast2 = out; out = null;}
    {out = new ExprListAST ((ExprAST)ast1, (ExprListAST) ast2);}
  | {out = new EmptyExprListAST();}
  ;

  
initializer
@init{
  AST ast1=null;
}
  : '='
  ( varInit
  | arrayInit
  | arrayInitList
  )
  ;
  
arrayInitList
@init{
  AST ast1=null,ast2=null;
}
  : '{' 
    arrayInit {ast1 = out; out = null;}
    arrayInitListEx {ast2 = out; out = null;}
    '}'
    {out = new ArrayInitializerListAST((ArrayInitializerAST)ast1, (ArrayInitializerListAST)ast2);}
  ;
  
arrayInitListEx
@init{
  AST ast1=null,ast2=null;
}
  : ','
    arrayInit {ast1 = out; out = null;}
    arrayInitListEx {ast2 = out; out = null;}
    {out = new ArrayInitializerListAST((ArrayInitializerAST)ast1, (ArrayInitializerListAST)ast2);}
  | {out = new EmptyArrayInitializerListAST();}
  ;
  
arrayInit
@init{
  AST ast1=null,ast2=null;
}
  : '{' 
    varInit {ast1 = out; out = null;}
    arrayInitEx {ast2 = out; out = null;}
    '}'
    {out = new ArrayInitializerAST(new VarInitializerListAST((VarInitializerAST)ast1, (VarInitializerListAST)ast2));}
  | '{' '}' {out = new EmptyArrayInitializerAST();}
  ;
  
arrayInitEx
@init{
  AST ast1=null,ast2=null;
}
  : ',' 
    varInit {ast1 = out; out = null;}
    arrayInitEx {ast2 = out; out = null;}
    {out = new VarInitializerListAST((VarInitializerAST)ast1, (VarInitializerListAST)ast2);}
  | {out = new EmptyVarInitializerListAST();}
  ;
  
varInit
@init{
  AST ast1=null;
}
  : expr {ast1 = out; out = null;}
    {out = new VarInitializerAST((ExprAST)ast1);}
  ;
  
type
  : combinedType
  | pointerType
  ;
  
pointerType
@init{
  AST ast1=null;
}
  : combinedType {in = out; out = null;}
    pointerTypeRest
  ;
  
pointerTypeRest
@init{
  AST ast1=null;
}
  : {ast1 = in;}
    '*' {out = new PointerTypeAST ((TypeAST) ast1);}
  | {ast1 = in;}
    '*' {in = new PointerTypeAST ((TypeAST) ast1);}
    pointerTypeRest
  ;
  
combinedType
@init{
  AST ast1=null,ast2=null;
}
  : primitiveType {ast1 = out; out = new TypeListAST((TypeAST)ast1, new EmptyTypeListAST());}
  | primitiveType {ast1 = out; out = null;}
    combinedType {ast2 = out; out = new TypeListAST((TypeAST)ast1, (TypeListAST)ast2);}
  ;
  
primitiveType
  : 'char' {out = new CharTypeAST();}
  | 'wchar_t' {out = new WCharTTypeAST();}
  | 'bool' {out = new BoolTypeAST();}
  | 'short' {out = new ShortTypeAST();}
  | 'int' {out = new IntTypeAST();}
  | 'float' {out = new FloatTypeAST();}
  | 'double' {out = new DoubleTypeAST();}
  | 'void' {out = new VoidTypeAST();}
  | 'signed' {out = new SignedTypeAST();}
  | 'unsigned' {out = new UnsignedTypeAST();}
  | 'long' {out = new LongTypeAST();}
  ;

funcDecl
@init{
  AST ast1=null,ast2=null,ast3=null;
}
  : type {ast1 = out; out = null;}
    id=IDENTIFIER 
    '(' 
    paraList {ast2 = out; out = null;}
    ')' 
    blockStmt {ast3 = out; out = null;}
    {out = new FuncDeclAST ((Token)id, (ParaListAST)ast2, (TypeAST)ast1, (CompStmtAST)ast3);}
  ;
  
paraList
@init{
  AST ast1=null,ast2=null;
}
  : para {ast1 = out; out = null;}
    paraListRest {ast2 = out; out = null;}
    {out = new ParaListAST ((ParaAST) ast1, (ParaListAST) ast2);}
  | {out = new EmptyParaListAST ();}
  ;
  
para
@init{
  AST ast1=null,ast2=null;
}
  : type {ast1 = out; out = null;}
    (ref='&')?
    id=IDENTIFIER
	arrayDecl {ast2 = out; out = null;}
    {
		if (!(ast2 instanceof EmptyExprListAST))
			out = new ParaAST ((Token)id, new ArrayTypeAST ((TypeAST) ast1, (ExprListAST) ast2), ref != null);
		else
			out = new ParaAST ((Token)id, (TypeAST)ast1, ref != null);
	}
  ;
  
paraListRest
@init{
  AST ast1=null,ast2=null;
}
  : ',' para {ast1 = out; out = null;}
    paraListRest {ast2 = out; out = null;}
    {out = new ParaListAST ((ParaAST) ast1, (ParaListAST) ast2);}
  | {out = new EmptyParaListAST ();}
  ;
  
blockStmt
@init{
  AST ast1=null;
}
  : '{'
    stmtList {ast1 = out; out = null;}
    '}'
    {out = new CompStmtAST ((StmtListAST)ast1);}
  ;
  
stmtList
@init{
  AST ast1=null,ast2=null;
}
  : stmt {ast1 = out; out = null;}
    stmtList {ast2 = out; out = null;}
    {out = new StmtListAST ((OneStmtAST)ast1, (StmtListAST)ast2);}
  | {out = new EmptyStmtListAST ();}
  ;
  
stmt
  : blockStmt 
//| assignStmt 
  | exprStmt
  | ifStmt 
  | forStmt 
  | whileStmt 
  | doStmt 
  | breakStmt 
  | continueStmt 
  | returnStmt
  | switchStmt
  | caseStmt
  | defaultStmt
  | declarationStmt 
//| callStmt
  ;
  
declarationStmt
@init{
  AST ast1=null;
}
  : varDecl {ast1 = out; out = null;}
    {out = new DeclarationStmtAST ((DeclarationListAST)ast1);}
  ;
  
exprStmt
@init{
  AST ast1=null;
}
  : expr {ast1 = out; out = null;}
    ';'
    {out = new ExprStmtAST((ExprAST)ast1);}
  ;
  
ifStmt
@init{
  AST ast1=null,ast2=null,ast3=null;
}
  : 'if' 
    '(' 
    expr {ast1 = out; out = null;}
    ')' 
    stmt {ast2 = out; out = null;}
    (options {k=1;}:
    'else' 
    stmt {ast3 = out; out = null;}
    )?
    {
      if (ast3 == null)
        out = new IfThenStmtAST((ExprAST)ast1, (OneStmtAST)ast2);
      else
        out = new IfThenElseStmtAST((ExprAST)ast1, (OneStmtAST)ast2, (OneStmtAST)ast3);
    }
  ;
  
forStmt
@init{
  AST ast1=null,ast2=null,ast3=null,ast4=null;
}
  : 'for' '(' 
    forInit? {ast1 = out; out = null;}
    expr? {ast2 = out; out = null;}
    ';' 
    exprList? {ast3 = out; out = null;}
    ')' 
    stmt {ast4 = out; out = null;}
    {out = new ForStmtAST((ForInitAST)ast1, (ExprAST)ast2, (ExprListAST)ast3, (OneStmtAST)ast4);}
  ;

forInit
  @init{
  AST ast1=null;
}
  : varDecl {ast1 = out; out = new ForInitAST(1, (DeclarationListAST)ast1, null);}
  | exprList ';' {ast1 = out; out = new ForInitAST(2, null, (ExprListAST)ast1);}
  | ';'
  ;

whileStmt
@init{
  AST ast1=null,ast2=null;
}
  : 'while' 
    '(' 
    expr {ast1 = out; out = null;}
    ')' 
    stmt {ast2 = out; out = null;}
    {out = new WhileStmtAST((ExprAST)ast1, (OneStmtAST)ast2);}
  ;
  
doStmt
@init{
  AST ast1=null,ast2=null;
}
  : 'do' 
    stmt {ast1 = out; out = null;}
    'while' 
    '(' 
    expr {ast2 = out; out = null;}
    ')' 
    ';'
    {out = new DoStmtAST((ExprAST)ast2, (OneStmtAST)ast1);}
  ;
  
switchStmt
@init{
  AST ast1=null,ast2=null;
}
  : 'switch'
    '('
    expr {ast1 = out; out = null;}
    ')'
    stmt {ast2 = out; out = null;}
    {out = new SwitchStmtAST((ExprAST)ast1, (OneStmtAST)ast2);}
  ;
  
caseStmt
@init{
  AST ast1=null,ast2=null;
}
  : 'case'
    expr {ast1 = out; out = null;}
    ':'
    stmtList {ast2 = out; out = null;}
    {out = new CaseStmtAST((ExprAST)ast1, (StmtListAST)ast2);}
  ;
  
defaultStmt
@init{
  AST ast1=null;
}
  : 'default'
    ':'
    stmtList {ast1 = out; out = null;}
    {out = new DefaultStmtAST((StmtListAST)ast1);}
  ;
    
  
breakStmt
  : br='break' ';'
    {out = new BreakStmtAST ((Token)br);}
  ;
  
continueStmt
  : cont='continue' ';'
    {out = new ContStmtAST ((Token)cont);}
  ;
  
returnStmt
@init{
  AST ast1=null;
}
  : 'return' 
    (expr {ast1 = out; out = null;})? 
    ';'
    {
      if (ast1 != null)
        out = new RetStmtAST((ExprAST) ast1);
      else
        out = new RetStmtAST();
    }
  ;
  
callStmt
@init{
  AST ast1=null;
}
  : id=IDENTIFIER 
    '(' 
    exprList {ast1 = out; out = null;}
    ')' 
    ';'
    {out = new CallStmtAST((Token)id, (ExprListAST)ast1);}
  ;
  
exprList
@init{
  AST ast1=null,ast2=null;
}
  : expr {ast1 = out; out = null;} 
    exprListRest {ast2 = out; out = null;}
    {out = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);}
  | {out = new EmptyExprListAST();}
  ;
  
exprListRest
@init{
  AST ast1=null,ast2=null;
}
  : ',' 
    expr {ast1 = out; out = null;} 
    exprListRest {ast2 = out; out = null;}
    {out = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);}
  | {out = new EmptyExprListAST();}
  ;
  
expr
  : exprASSIGN
  ;
  
exprASSIGN
@init{
  AST ast1=null,ast2=null;
  int type = 0;
}
  : exprLOGICAL_OR {ast1 = out; out = null;}
    ( op='='   {type = BinExprAST.ASSIGN;}
    | op='+='  {type = BinExprAST.PLUS_ASSIGN;}
    | op='-='  {type = BinExprAST.MINUS_ASSIGN;}
    | op='*='  {type = BinExprAST.STAR_ASSIGN;}
    | op='/='  {type = BinExprAST.DIV_ASSIGN;}
    | op='%='  {type = BinExprAST.MOD_ASSIGN;}
    | op='<<=' {type = BinExprAST.SHIFT_LEFT_ASSIGN;}
    | op='>>=' {type = BinExprAST.SHIFT_RIGHT_ASSIGN;}
    | op='&='  {type = BinExprAST.AND_ASSIGN;}
    | op='^='  {type = BinExprAST.XOR_ASSIGN;}
    | op='|='  {type = BinExprAST.OR_ASSIGN;}
    )
    exprASSIGN {ast2 = out; out = null;}
    {out = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);}
  | exprTERNARY
  ;
  
exprTERNARY
  : exprLOGICAL_OR {in = out;}
    exprTERNARYEx
  ;
  
exprTERNARYEx
@init{
  AST ast1=null,ast2=null,ast3=null;
}
  : {ast1 = in;}
    '?' 
    exprASSIGN {ast2 = out; out = null;}
    ':' 
    exprASSIGN {ast3 = out; out = null;}
    {out = new TernaryExprAST((ExprAST)ast1, (ExprAST)ast2, (ExprAST)ast3);}
  |
  ;

exprLOGICAL_OR
  : exprLOGICAL_AND {in = out;}
    exprLOGICAL_OREx
  ;
  
exprLOGICAL_OREx
@init{
  AST ast1=null,ast2=null;
}
  : {ast1 = in;}
    op='||' 
    exprLOGICAL_AND {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, BinExprAST.LOGICAL_OR, (Token)op, (ExprAST)ast2);}
    exprLOGICAL_OREx
  | {out = in;}
  ;
  
exprLOGICAL_AND
  : exprBITWISE_OR {in = out;}
    exprLOGICAL_ANDEx
  ;
  
exprLOGICAL_ANDEx
@init{
  AST ast1=null,ast2=null;
}
  : {ast1 = in;}
    op='&&' 
    exprBITWISE_OR {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, BinExprAST.LOGICAL_AND, (Token)op, (ExprAST)ast2);}
    exprLOGICAL_ANDEx
  | {out = in;}
  ;
  
exprBITWISE_OR
  : exprBITWISE_XOR {in = out;}
    exprBITWISE_OREx
  ;
  
exprBITWISE_OREx
@init{
  AST ast1=null,ast2=null;
}
  : {ast1 = in;}
    op='|' 
    exprBITWISE_XOR {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, BinExprAST.OR, (Token)op, (ExprAST)ast2);}
    exprBITWISE_OREx
  | {out = in;}
  ;
  
exprBITWISE_XOR
  : exprBITWISE_AND {in = out;}
    exprBITWISE_XOREx
  ;
  
exprBITWISE_XOREx
@init{
  AST ast1=null,ast2=null;
}
  : {ast1 = in;}
    op='^' 
    exprBITWISE_AND {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, BinExprAST.XOR, (Token)op, (ExprAST)ast2);}
    exprBITWISE_XOREx
  | {out = in;}
  ;
  
exprBITWISE_AND
  : exprEQUALITY {in = out;}
    exprBITWISE_ANDEx
  ;
  
exprBITWISE_ANDEx
@init{
  AST ast1=null,ast2=null;
}
  : {ast1 = in;}
    op='&' 
    exprEQUALITY {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, BinExprAST.AND, (Token)op, (ExprAST)ast2);}
    exprBITWISE_ANDEx
  | {out = in;}
  ;
  
exprEQUALITY
  : exprRELATION {in = out;}
    exprEQUALITYEx
  ;
  
exprEQUALITYEx
@init{
  AST ast1=null,ast2=null;
  int type = 0;
}
  : {ast1 = in;}
    ( op='=='  {type = BinExprAST.EQUAL;}
    | op='!=' {type = BinExprAST.NOT_EQUAL;} 
	)
    exprRELATION {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);}
    exprEQUALITYEx
  | {out = in;}
  ;
  
exprRELATION
  : exprSHIFT {in = out;}
    exprRELATIONEx
  ;
  
exprRELATIONEx
@init{
  AST ast1=null,ast2=null;
  int type = 0;
}
  : {ast1 = in;}
    ( op='>'   {type = BinExprAST.GREATER_THAN;}
    | op='>='  {type = BinExprAST.GREATER_OR_EQUAL;}
    | op='<'   {type = BinExprAST.LESS_THAN;}
    | op='<='  {type = BinExprAST.LESS_OR_EQUAL;}
    ) 
    exprSHIFT {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);}
    exprRELATIONEx
  | {out = in;}
  ;
  
exprSHIFT
  : exprADD {in = out;}
    exprSHIFTEx
  ;
  
exprSHIFTEx
@init{
  AST ast1=null,ast2=null;
  int type = 0;
}
  : {ast1 = in;}
    ( op='>>' {type = BinExprAST.SHIFT_RIGHT;}
    | op='<<' {type = BinExprAST.SHIFT_LEFT;}
    ) 
    exprADD {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);}
    exprSHIFTEx
  | {out = in;}
  ;
  
exprADD
  : exprMUL {in = out;}
    exprADDEx
  ;
  
exprADDEx
@init{
  AST ast1=null,ast2=null;
  int type = 0;
}
  : {ast1 = in;}
    ( op='+'   {type = BinExprAST.PLUS;}
    | op='-'   {type = BinExprAST.MINUS;}
    ) 
    exprMUL {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);}
    exprADDEx
  | {out = in;}
  ;

exprMUL
  : exprUNARY {in = out;}
    exprMULEx
  ;

exprMULEx
@init{
  AST ast1=null,ast2=null;
  int type = 0;
}
  : {ast1 = in;}
    ( op='*'   {type = BinExprAST.STAR;}
    | op='/'   {type = BinExprAST.DIV;}
    | op='%'   {type = BinExprAST.MOD;}
    ) 
    exprUNARY {ast2 = out; out = null;}
    {in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);}
    exprMULEx
  | {out = in;}
  ;
  
exprUNARY
@init{
  AST ast1=null;
  int type = 0;
}
  : ( op='+'   {type = UnaryExprAST.UNARY_PLUS;}
    | op='-'   {type = UnaryExprAST.UNARY_MINUS;}
    | op='++'  {type = UnaryExprAST.PRE_INC;}
    | op='--'  {type = UnaryExprAST.PRE_DEC;}
    | op='*'   {type = UnaryExprAST.INDIRECTION;}
    | op='&'   {type = UnaryExprAST.ADDR_OF;}
    | op='!'   {type = UnaryExprAST.LOGICAL_NOT;}
    | op='~'   {type = UnaryExprAST.NOT;}
    )
    exprUNARY {ast1 = out; out = null;} 
    {out = new UnaryExprAST(type, (Token)op, (ExprAST)ast1);}
  | exprPostfix
  ;
  
exprPostfix
  : exprPrimary {in = out;}
    exprPostfixEx
  ;
  
exprPostfixEx
@init{
  AST ast1=null;
  int type = 0;
}
  : {ast1 = in;}
    ( op='++'  {type = UnaryExprAST.POST_INC;}
    | op='--'  {type = UnaryExprAST.POST_DEC;}
    ) 
    {in = new UnaryExprAST(type, (Token)op, (ExprAST)ast1);}
    exprPostfixEx
  | {out = in;}
  ;
  
exprPrimary
  : literal 
  | id=IDENTIFIER {out = new VarExprAST((Token)id);}
  | '(' expr ')' 
  | callExpr 
  | arrayElement
  ;
  
callExpr
@init{
  AST ast1=null;
}
  : id=IDENTIFIER 
    '(' 
    exprList {ast1 = out; out = null;} 
    ')'
    {out = new CallExprAST((Token)id, (ExprListAST)ast1);}
  ;
  
arrayElement
@init{
  AST ast1=null,ast2=null,ast3=null;
}
  : id=IDENTIFIER 
    '[' 
    expr {ast1 = out; out = null;}
    ']' 
    index {ast2 = out; out = null;}
    {
      ast3 = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);
      out = new EleExprAST((Token)id, (ExprListAST)ast3);
    }
  ;
  
index
@init{
  AST ast1=null,ast2=null;
}
  : '[' 
    expr {ast1 = out; out = null;}
    ']' 
    index {ast2 = out; out = null;}
    {out = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);}
  | {out = new EmptyExprListAST();}
  ;
  
literal
  : val=CHARACTER_LITERAL {out = new CharLiteralAST((Token)val);}
  | val=STRING_LITERAL {out = new StringLiteralAST((Token)val);}
  | val=HEX_LITERAL {out = new IntLiteralAST((Token)val);}
  | val=DECIMAL_LITERAL {out = new IntLiteralAST((Token)val);}
  | val=OCTAL_LITERAL {out = new IntLiteralAST((Token)val);}
  | val=FLOATING_POINT_LITERAL {out = new FloatLiteralAST((Token)val);}
  ; 

IDENTIFIER
  : LETTER (LETTER | '0'..'9')*
  ;
  
fragment
LETTER
  : 'A'..'Z'
  | 'a'..'z'
  | '_'
  ;

CHARACTER_LITERAL
  :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
  ;

STRING_LITERAL
  :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
  ;

HEX_LITERAL 
  : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? 
  ;

DECIMAL_LITERAL 
  : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? 
  ;

OCTAL_LITERAL 
  : '0' ('0'..'7')+ IntegerTypeSuffix? 
  ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix
  : ('u'|'U')? ('l'|'L')
  | ('u'|'U')
  ;

FLOATING_POINT_LITERAL
  :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
  |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
  |   ('0'..'9')+ Exponent
  |   ('0'..'9')+ Exponent? FloatTypeSuffix
  ;

fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

fragment
EscapeSequence
  :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
  |   OctalEscape
  ;

fragment
OctalEscape
  :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
  |   '\\' ('0'..'7') ('0'..'7')
  |   '\\' ('0'..'7')
  ;

fragment
UnicodeEscape
  :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
  ;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN; skip();}
    ;

COMMENT
  :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN; skip();}
  ;

LINE_COMMENT
  : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN; skip();}
  ;
