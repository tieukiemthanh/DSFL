// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 CPP.g 2011-06-19 10:04:13

  package Transformer.Parser;
  import Transformer.ASTs.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class CPPParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENTIFIER", "CHARACTER_LITERAL", "STRING_LITERAL", "HEX_LITERAL", "DECIMAL_LITERAL", "OCTAL_LITERAL", "FLOATING_POINT_LITERAL", "LETTER", "EscapeSequence", "HexDigit", "IntegerTypeSuffix", "Exponent", "FloatTypeSuffix", "OctalEscape", "UnicodeEscape", "WS", "COMMENT", "LINE_COMMENT", "'#define'", "';'", "','", "'['", "']'", "'='", "'{'", "'}'", "'*'", "'char'", "'wchar_t'", "'bool'", "'short'", "'int'", "'float'", "'double'", "'void'", "'signed'", "'unsigned'", "'long'", "'('", "')'", "'&'", "'if'", "'else'", "'for'", "'while'", "'do'", "'switch'", "'case'", "':'", "'default'", "'break'", "'continue'", "'return'", "'+='", "'-='", "'*='", "'/='", "'%='", "'<<='", "'>>='", "'&='", "'^='", "'|='", "'?'", "'||'", "'&&'", "'|'", "'^'", "'=='", "'!='", "'>'", "'>='", "'<'", "'<='", "'>>'", "'<<'", "'+'", "'-'", "'/'", "'%'", "'++'", "'--'", "'!'", "'~'"
    };
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__29=29;
    public static final int T__65=65;
    public static final int T__28=28;
    public static final int T__62=62;
    public static final int T__27=27;
    public static final int T__63=63;
    public static final int T__26=26;
    public static final int FloatTypeSuffix=16;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int LETTER=11;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int Exponent=15;
    public static final int T__61=61;
    public static final int EOF=-1;
    public static final int T__60=60;
    public static final int HexDigit=13;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int STRING_LITERAL=6;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int FLOATING_POINT_LITERAL=10;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int IDENTIFIER=4;
    public static final int T__59=59;
    public static final int HEX_LITERAL=7;
    public static final int COMMENT=20;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__80=80;
    public static final int T__46=46;
    public static final int T__81=81;
    public static final int T__47=47;
    public static final int T__82=82;
    public static final int T__44=44;
    public static final int T__83=83;
    public static final int T__45=45;
    public static final int LINE_COMMENT=21;
    public static final int IntegerTypeSuffix=14;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int OCTAL_LITERAL=9;
    public static final int CHARACTER_LITERAL=5;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int WS=19;
    public static final int T__71=71;
    public static final int T__33=33;
    public static final int T__72=72;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__70=70;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int UnicodeEscape=18;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int OctalEscape=17;
    public static final int EscapeSequence=12;
    public static final int DECIMAL_LITERAL=8;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;

    // delegates
    // delegators


        public CPPParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CPPParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CPPParser.tokenNames; }
    public String getGrammarFileName() { return "CPP.g"; }


      private AST in, out;
      
      public AST parse() throws RecognitionException {
        program();
        return out;
      }



    // $ANTLR start "program"
    // CPP.g:26:1: program : declarationList ;
    public final void program() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:30:3: ( declarationList )
            // CPP.g:30:5: declarationList
            {
            pushFollow(FOLLOW_declarationList_in_program59);
            declarationList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = new ProgramAST((DeclarationListAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "program"


    // $ANTLR start "declarationList"
    // CPP.g:33:1: declarationList : ( declaration declarationList | );
    public final void declarationList() throws RecognitionException {

          AST ast1=null,ast2=null,ast3=null;

        try {
            // CPP.g:37:3: ( declaration declarationList | )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==22||(LA1_0>=31 && LA1_0<=41)) ) {
                alt1=1;
            }
            else if ( (LA1_0==EOF) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // CPP.g:37:5: declaration declarationList
                    {
                    pushFollow(FOLLOW_declaration_in_declarationList81);
                    declaration();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {

                            ast1 = out; 
                            out = null; 
                            if (in instanceof EmptyDeclarationListAST) 
                              ast3 = in.parent;
                          
                    }
                    pushFollow(FOLLOW_declarationList_in_declarationList95);
                    declarationList();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {

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

                    }
                    break;
                case 2 :
                    // CPP.g:56:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyDeclarationListAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declarationList"


    // $ANTLR start "declaration"
    // CPP.g:58:1: declaration : ( varDecl | directive | funcDecl );
    public final void declaration() throws RecognitionException {
        try {
            // CPP.g:59:3: ( varDecl | directive | funcDecl )
            int alt2=3;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // CPP.g:59:5: varDecl
                    {
                    pushFollow(FOLLOW_varDecl_in_declaration121);
                    varDecl();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:60:5: directive
                    {
                    pushFollow(FOLLOW_directive_in_declaration127);
                    directive();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // CPP.g:61:5: funcDecl
                    {
                    pushFollow(FOLLOW_funcDecl_in_declaration133);
                    funcDecl();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declaration"


    // $ANTLR start "directive"
    // CPP.g:64:1: directive : '#define' id= IDENTIFIER pp_token ;
    public final void directive() throws RecognitionException {
        Token id=null;


          AST ast1=null;

        try {
            // CPP.g:68:3: ( '#define' id= IDENTIFIER pp_token )
            // CPP.g:68:5: '#define' id= IDENTIFIER pp_token
            {
            match(input,22,FOLLOW_22_in_directive152); if (state.failed) return ;
            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_directive156); if (state.failed) return ;
            pushFollow(FOLLOW_pp_token_in_directive158);
            pp_token();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = new DefineDirectiveAST((Token)id, (ExprAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "directive"


    // $ANTLR start "pp_token"
    // CPP.g:71:1: pp_token : (id= IDENTIFIER | literal );
    public final void pp_token() throws RecognitionException {
        Token id=null;

        try {
            // CPP.g:72:3: (id= IDENTIFIER | literal )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==IDENTIFIER) ) {
                alt3=1;
            }
            else if ( ((LA3_0>=CHARACTER_LITERAL && LA3_0<=FLOATING_POINT_LITERAL)) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // CPP.g:72:5: id= IDENTIFIER
                    {
                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_pp_token177); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new VarExprAST((Token)id);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:73:5: literal
                    {
                    pushFollow(FOLLOW_literal_in_pp_token185);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "pp_token"


    // $ANTLR start "varDecl"
    // CPP.g:76:1: varDecl : type varList ';' ;
    public final void varDecl() throws RecognitionException {
        try {
            // CPP.g:77:3: ( type varList ';' )
            // CPP.g:77:5: type varList ';'
            {
            pushFollow(FOLLOW_type_in_varDecl201);
            type();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out; out = null;
            }
            pushFollow(FOLLOW_varList_in_varDecl209);
            varList();

            state._fsp--;
            if (state.failed) return ;
            match(input,23,FOLLOW_23_in_varDecl211); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "varDecl"


    // $ANTLR start "varList"
    // CPP.g:81:1: varList : var varListRest ;
    public final void varList() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:85:3: ( var varListRest )
            // CPP.g:85:5: var varListRest
            {
            pushFollow(FOLLOW_var_in_varList230);
            var();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            pushFollow(FOLLOW_varListRest_in_varList238);
            varListRest();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new DeclarationListAST((DeclarationAST) ast1, (DeclarationListAST) ast2);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "varList"


    // $ANTLR start "varListRest"
    // CPP.g:90:1: varListRest : ( ',' var varListRest | );
    public final void varListRest() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:94:3: ( ',' var varListRest | )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==24) ) {
                alt4=1;
            }
            else if ( (LA4_0==EOF||LA4_0==23) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // CPP.g:94:5: ',' var varListRest
                    {
                    match(input,24,FOLLOW_24_in_varListRest265); if (state.failed) return ;
                    pushFollow(FOLLOW_var_in_varListRest267);
                    var();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_varListRest_in_varListRest275);
                    varListRest();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new DeclarationListAST ((DeclarationAST) ast1, (DeclarationListAST) ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:97:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyDeclarationListAST (); in = out;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "varListRest"


    // $ANTLR start "var"
    // CPP.g:100:1: var : id= IDENTIFIER arrayDecl ( initializer )? ;
    public final void var() throws RecognitionException {
        Token id=null;


          AST ast1=null,ast2=null,ast3=null;

        try {
            // CPP.g:104:3: (id= IDENTIFIER arrayDecl ( initializer )? )
            // CPP.g:104:5: id= IDENTIFIER arrayDecl ( initializer )?
            {
            if ( state.backtracking==0 ) {
              ast1 = in;
            }
            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_var316); if (state.failed) return ;
            pushFollow(FOLLOW_arrayDecl_in_var323);
            arrayDecl();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              if (!(ast2 instanceof EmptyExprListAST))
                    ast1 = new ArrayTypeAST ((TypeAST) ast1, (ExprListAST) ast2);
                  
            }
            // CPP.g:110:5: ( initializer )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==27) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // CPP.g:0:0: initializer
                    {
                    pushFollow(FOLLOW_initializer_in_var337);
                    initializer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              ast3 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new VarDeclAST ((Token) id, (TypeAST) ast1, (InitializerAST)ast3);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "var"


    // $ANTLR start "arrayDecl"
    // CPP.g:114:1: arrayDecl : ( ( '[' expr ']' | '[' ']' ) arrayDecl | );
    public final void arrayDecl() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:118:3: ( ( '[' expr ']' | '[' ']' ) arrayDecl | )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==25) ) {
                alt7=1;
            }
            else if ( (LA7_0==EOF||(LA7_0>=23 && LA7_0<=24)||LA7_0==27||LA7_0==43) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // CPP.g:118:5: ( '[' expr ']' | '[' ']' ) arrayDecl
                    {
                    // CPP.g:118:5: ( '[' expr ']' | '[' ']' )
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==25) ) {
                        int LA6_1 = input.LA(2);

                        if ( (LA6_1==26) ) {
                            alt6=2;
                        }
                        else if ( ((LA6_1>=IDENTIFIER && LA6_1<=FLOATING_POINT_LITERAL)||LA6_1==30||LA6_1==42||LA6_1==44||(LA6_1>=80 && LA6_1<=81)||(LA6_1>=84 && LA6_1<=87)) ) {
                            alt6=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return ;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 6, 1, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;
                    }
                    switch (alt6) {
                        case 1 :
                            // CPP.g:118:7: '[' expr ']'
                            {
                            match(input,25,FOLLOW_25_in_arrayDecl367); if (state.failed) return ;
                            pushFollow(FOLLOW_expr_in_arrayDecl369);
                            expr();

                            state._fsp--;
                            if (state.failed) return ;
                            match(input,26,FOLLOW_26_in_arrayDecl371); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              ast1 = out; out = null;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:119:7: '[' ']'
                            {
                            match(input,25,FOLLOW_25_in_arrayDecl381); if (state.failed) return ;
                            match(input,26,FOLLOW_26_in_arrayDecl383); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              ast1 = new NullExprAST();
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_arrayDecl_in_arrayDecl393);
                    arrayDecl();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new ExprListAST ((ExprAST)ast1, (ExprListAST) ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:122:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyExprListAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayDecl"


    // $ANTLR start "initializer"
    // CPP.g:126:1: initializer : '=' ( varInit | arrayInit | arrayInitList ) ;
    public final void initializer() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:130:3: ( '=' ( varInit | arrayInit | arrayInitList ) )
            // CPP.g:130:5: '=' ( varInit | arrayInit | arrayInitList )
            {
            match(input,27,FOLLOW_27_in_initializer427); if (state.failed) return ;
            // CPP.g:131:3: ( varInit | arrayInit | arrayInitList )
            int alt8=3;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=IDENTIFIER && LA8_0<=FLOATING_POINT_LITERAL)||LA8_0==30||LA8_0==42||LA8_0==44||(LA8_0>=80 && LA8_0<=81)||(LA8_0>=84 && LA8_0<=87)) ) {
                alt8=1;
            }
            else if ( (LA8_0==28) ) {
                int LA8_2 = input.LA(2);

                if ( ((LA8_2>=IDENTIFIER && LA8_2<=FLOATING_POINT_LITERAL)||(LA8_2>=29 && LA8_2<=30)||LA8_2==42||LA8_2==44||(LA8_2>=80 && LA8_2<=81)||(LA8_2>=84 && LA8_2<=87)) ) {
                    alt8=2;
                }
                else if ( (LA8_2==28) ) {
                    alt8=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // CPP.g:131:5: varInit
                    {
                    pushFollow(FOLLOW_varInit_in_initializer433);
                    varInit();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:132:5: arrayInit
                    {
                    pushFollow(FOLLOW_arrayInit_in_initializer439);
                    arrayInit();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // CPP.g:133:5: arrayInitList
                    {
                    pushFollow(FOLLOW_arrayInitList_in_initializer445);
                    arrayInitList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "initializer"


    // $ANTLR start "arrayInitList"
    // CPP.g:137:1: arrayInitList : '{' arrayInit arrayInitListEx '}' ;
    public final void arrayInitList() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:141:3: ( '{' arrayInit arrayInitListEx '}' )
            // CPP.g:141:5: '{' arrayInit arrayInitListEx '}'
            {
            match(input,28,FOLLOW_28_in_arrayInitList468); if (state.failed) return ;
            pushFollow(FOLLOW_arrayInit_in_arrayInitList475);
            arrayInit();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            pushFollow(FOLLOW_arrayInitListEx_in_arrayInitList483);
            arrayInitListEx();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            match(input,29,FOLLOW_29_in_arrayInitList491); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new ArrayInitializerListAST((ArrayInitializerAST)ast1, (ArrayInitializerListAST)ast2);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayInitList"


    // $ANTLR start "arrayInitListEx"
    // CPP.g:148:1: arrayInitListEx : ( ',' arrayInit arrayInitListEx | );
    public final void arrayInitListEx() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:152:3: ( ',' arrayInit arrayInitListEx | )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==24) ) {
                alt9=1;
            }
            else if ( (LA9_0==EOF||LA9_0==29) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // CPP.g:152:5: ',' arrayInit arrayInitListEx
                    {
                    match(input,24,FOLLOW_24_in_arrayInitListEx516); if (state.failed) return ;
                    pushFollow(FOLLOW_arrayInit_in_arrayInitListEx522);
                    arrayInit();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_arrayInitListEx_in_arrayInitListEx530);
                    arrayInitListEx();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new ArrayInitializerListAST((ArrayInitializerAST)ast1, (ArrayInitializerListAST)ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:156:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyArrayInitializerListAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayInitListEx"


    // $ANTLR start "arrayInit"
    // CPP.g:159:1: arrayInit : ( '{' varInit arrayInitEx '}' | '{' '}' );
    public final void arrayInit() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:163:3: ( '{' varInit arrayInitEx '}' | '{' '}' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==28) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==29) ) {
                    alt10=2;
                }
                else if ( ((LA10_1>=IDENTIFIER && LA10_1<=FLOATING_POINT_LITERAL)||LA10_1==30||LA10_1==42||LA10_1==44||(LA10_1>=80 && LA10_1<=81)||(LA10_1>=84 && LA10_1<=87)) ) {
                    alt10=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // CPP.g:163:5: '{' varInit arrayInitEx '}'
                    {
                    match(input,28,FOLLOW_28_in_arrayInit563); if (state.failed) return ;
                    pushFollow(FOLLOW_varInit_in_arrayInit570);
                    varInit();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_arrayInitEx_in_arrayInit578);
                    arrayInitEx();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    match(input,29,FOLLOW_29_in_arrayInit586); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new ArrayInitializerAST(new VarInitializerListAST((VarInitializerAST)ast1, (VarInitializerListAST)ast2));
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:168:5: '{' '}'
                    {
                    match(input,28,FOLLOW_28_in_arrayInit598); if (state.failed) return ;
                    match(input,29,FOLLOW_29_in_arrayInit600); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new EmptyArrayInitializerAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayInit"


    // $ANTLR start "arrayInitEx"
    // CPP.g:171:1: arrayInitEx : ( ',' varInit arrayInitEx | );
    public final void arrayInitEx() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:175:3: ( ',' varInit arrayInitEx | )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==24) ) {
                alt11=1;
            }
            else if ( (LA11_0==EOF||LA11_0==29) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // CPP.g:175:5: ',' varInit arrayInitEx
                    {
                    match(input,24,FOLLOW_24_in_arrayInitEx621); if (state.failed) return ;
                    pushFollow(FOLLOW_varInit_in_arrayInitEx628);
                    varInit();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_arrayInitEx_in_arrayInitEx636);
                    arrayInitEx();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new VarInitializerListAST((VarInitializerAST)ast1, (VarInitializerListAST)ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:179:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyVarInitializerListAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayInitEx"


    // $ANTLR start "varInit"
    // CPP.g:182:1: varInit : expr ;
    public final void varInit() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:186:3: ( expr )
            // CPP.g:186:5: expr
            {
            pushFollow(FOLLOW_expr_in_varInit669);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new VarInitializerAST((ExprAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "varInit"


    // $ANTLR start "type"
    // CPP.g:190:1: type : ( combinedType | pointerType );
    public final void type() throws RecognitionException {
        try {
            // CPP.g:191:3: ( combinedType | pointerType )
            int alt12=2;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // CPP.g:191:5: combinedType
                    {
                    pushFollow(FOLLOW_combinedType_in_type692);
                    combinedType();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:192:5: pointerType
                    {
                    pushFollow(FOLLOW_pointerType_in_type698);
                    pointerType();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type"


    // $ANTLR start "pointerType"
    // CPP.g:195:1: pointerType : combinedType pointerTypeRest ;
    public final void pointerType() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:199:3: ( combinedType pointerTypeRest )
            // CPP.g:199:5: combinedType pointerTypeRest
            {
            pushFollow(FOLLOW_combinedType_in_pointerType717);
            combinedType();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out; out = null;
            }
            pushFollow(FOLLOW_pointerTypeRest_in_pointerType725);
            pointerTypeRest();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "pointerType"


    // $ANTLR start "pointerTypeRest"
    // CPP.g:203:1: pointerTypeRest : ( '*' | '*' pointerTypeRest );
    public final void pointerTypeRest() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:207:3: ( '*' | '*' pointerTypeRest )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==30) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==30) ) {
                    alt13=2;
                }
                else if ( (LA13_1==IDENTIFIER||LA13_1==44) ) {
                    alt13=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // CPP.g:207:5: '*'
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    match(input,30,FOLLOW_30_in_pointerTypeRest750); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new PointerTypeAST ((TypeAST) ast1);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:209:5: '*' pointerTypeRest
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    match(input,30,FOLLOW_30_in_pointerTypeRest764); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      in = new PointerTypeAST ((TypeAST) ast1);
                    }
                    pushFollow(FOLLOW_pointerTypeRest_in_pointerTypeRest772);
                    pointerTypeRest();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "pointerTypeRest"


    // $ANTLR start "combinedType"
    // CPP.g:214:1: combinedType : ( primitiveType | primitiveType combinedType );
    public final void combinedType() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:218:3: ( primitiveType | primitiveType combinedType )
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // CPP.g:218:5: primitiveType
                    {
                    pushFollow(FOLLOW_primitiveType_in_combinedType791);
                    primitiveType();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = new TypeListAST((TypeAST)ast1, new EmptyTypeListAST());
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:219:5: primitiveType combinedType
                    {
                    pushFollow(FOLLOW_primitiveType_in_combinedType799);
                    primitiveType();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_combinedType_in_combinedType807);
                    combinedType();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = new TypeListAST((TypeAST)ast1, (TypeListAST)ast2);
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "combinedType"


    // $ANTLR start "primitiveType"
    // CPP.g:223:1: primitiveType : ( 'char' | 'wchar_t' | 'bool' | 'short' | 'int' | 'float' | 'double' | 'void' | 'signed' | 'unsigned' | 'long' );
    public final void primitiveType() throws RecognitionException {
        try {
            // CPP.g:224:3: ( 'char' | 'wchar_t' | 'bool' | 'short' | 'int' | 'float' | 'double' | 'void' | 'signed' | 'unsigned' | 'long' )
            int alt15=11;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt15=1;
                }
                break;
            case 32:
                {
                alt15=2;
                }
                break;
            case 33:
                {
                alt15=3;
                }
                break;
            case 34:
                {
                alt15=4;
                }
                break;
            case 35:
                {
                alt15=5;
                }
                break;
            case 36:
                {
                alt15=6;
                }
                break;
            case 37:
                {
                alt15=7;
                }
                break;
            case 38:
                {
                alt15=8;
                }
                break;
            case 39:
                {
                alt15=9;
                }
                break;
            case 40:
                {
                alt15=10;
                }
                break;
            case 41:
                {
                alt15=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // CPP.g:224:5: 'char'
                    {
                    match(input,31,FOLLOW_31_in_primitiveType824); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new CharTypeAST();
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:225:5: 'wchar_t'
                    {
                    match(input,32,FOLLOW_32_in_primitiveType832); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new WCharTTypeAST();
                    }

                    }
                    break;
                case 3 :
                    // CPP.g:226:5: 'bool'
                    {
                    match(input,33,FOLLOW_33_in_primitiveType840); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new BoolTypeAST();
                    }

                    }
                    break;
                case 4 :
                    // CPP.g:227:5: 'short'
                    {
                    match(input,34,FOLLOW_34_in_primitiveType848); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new ShortTypeAST();
                    }

                    }
                    break;
                case 5 :
                    // CPP.g:228:5: 'int'
                    {
                    match(input,35,FOLLOW_35_in_primitiveType856); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new IntTypeAST();
                    }

                    }
                    break;
                case 6 :
                    // CPP.g:229:5: 'float'
                    {
                    match(input,36,FOLLOW_36_in_primitiveType864); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new FloatTypeAST();
                    }

                    }
                    break;
                case 7 :
                    // CPP.g:230:5: 'double'
                    {
                    match(input,37,FOLLOW_37_in_primitiveType872); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new DoubleTypeAST();
                    }

                    }
                    break;
                case 8 :
                    // CPP.g:231:5: 'void'
                    {
                    match(input,38,FOLLOW_38_in_primitiveType880); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new VoidTypeAST();
                    }

                    }
                    break;
                case 9 :
                    // CPP.g:232:5: 'signed'
                    {
                    match(input,39,FOLLOW_39_in_primitiveType888); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new SignedTypeAST();
                    }

                    }
                    break;
                case 10 :
                    // CPP.g:233:5: 'unsigned'
                    {
                    match(input,40,FOLLOW_40_in_primitiveType896); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new UnsignedTypeAST();
                    }

                    }
                    break;
                case 11 :
                    // CPP.g:234:5: 'long'
                    {
                    match(input,41,FOLLOW_41_in_primitiveType904); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new LongTypeAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "primitiveType"


    // $ANTLR start "funcDecl"
    // CPP.g:237:1: funcDecl : type id= IDENTIFIER '(' paraList ')' blockStmt ;
    public final void funcDecl() throws RecognitionException {
        Token id=null;


          AST ast1=null,ast2=null,ast3=null;

        try {
            // CPP.g:241:3: ( type id= IDENTIFIER '(' paraList ')' blockStmt )
            // CPP.g:241:5: type id= IDENTIFIER '(' paraList ')' blockStmt
            {
            pushFollow(FOLLOW_type_in_funcDecl923);
            type();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_funcDecl933); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_funcDecl940); if (state.failed) return ;
            pushFollow(FOLLOW_paraList_in_funcDecl947);
            paraList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_funcDecl955); if (state.failed) return ;
            pushFollow(FOLLOW_blockStmt_in_funcDecl962);
            blockStmt();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast3 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new FuncDeclAST ((Token)id, (ParaListAST)ast2, (TypeAST)ast1, (CompStmtAST)ast3);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "funcDecl"


    // $ANTLR start "paraList"
    // CPP.g:250:1: paraList : ( para paraListRest | );
    public final void paraList() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:254:3: ( para paraListRest | )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=31 && LA16_0<=41)) ) {
                alt16=1;
            }
            else if ( (LA16_0==43) ) {
                alt16=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // CPP.g:254:5: para paraListRest
                    {
                    pushFollow(FOLLOW_para_in_paraList989);
                    para();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_paraListRest_in_paraList997);
                    paraListRest();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new ParaListAST ((ParaAST) ast1, (ParaListAST) ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:257:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyParaListAST ();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "paraList"


    // $ANTLR start "para"
    // CPP.g:260:1: para : type (ref= '&' )? id= IDENTIFIER arrayDecl ;
    public final void para() throws RecognitionException {
        Token ref=null;
        Token id=null;


          AST ast1=null,ast2=null;

        try {
            // CPP.g:264:3: ( type (ref= '&' )? id= IDENTIFIER arrayDecl )
            // CPP.g:264:5: type (ref= '&' )? id= IDENTIFIER arrayDecl
            {
            pushFollow(FOLLOW_type_in_para1030);
            type();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            // CPP.g:265:5: (ref= '&' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==44) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // CPP.g:265:6: ref= '&'
                    {
                    ref=(Token)match(input,44,FOLLOW_44_in_para1041); if (state.failed) return ;

                    }
                    break;

            }

            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_para1051); if (state.failed) return ;
            pushFollow(FOLLOW_arrayDecl_in_para1054);
            arrayDecl();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            if ( state.backtracking==0 ) {

              		if (!(ast2 instanceof EmptyExprListAST))
              			out = new ParaAST ((Token)id, new ArrayTypeAST ((TypeAST) ast1, (ExprListAST) ast2), ref != null);
              		else
              			out = new ParaAST ((Token)id, (TypeAST)ast1, ref != null);
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "para"


    // $ANTLR start "paraListRest"
    // CPP.g:276:1: paraListRest : ( ',' para paraListRest | );
    public final void paraListRest() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:280:3: ( ',' para paraListRest | )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==24) ) {
                alt18=1;
            }
            else if ( (LA18_0==EOF||LA18_0==43) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // CPP.g:280:5: ',' para paraListRest
                    {
                    match(input,24,FOLLOW_24_in_paraListRest1081); if (state.failed) return ;
                    pushFollow(FOLLOW_para_in_paraListRest1083);
                    para();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_paraListRest_in_paraListRest1091);
                    paraListRest();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new ParaListAST ((ParaAST) ast1, (ParaListAST) ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:283:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyParaListAST ();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "paraListRest"


    // $ANTLR start "blockStmt"
    // CPP.g:286:1: blockStmt : '{' stmtList '}' ;
    public final void blockStmt() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:290:3: ( '{' stmtList '}' )
            // CPP.g:290:5: '{' stmtList '}'
            {
            match(input,28,FOLLOW_28_in_blockStmt1124); if (state.failed) return ;
            pushFollow(FOLLOW_stmtList_in_blockStmt1130);
            stmtList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,29,FOLLOW_29_in_blockStmt1138); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new CompStmtAST ((StmtListAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "blockStmt"


    // $ANTLR start "stmtList"
    // CPP.g:296:1: stmtList : ( stmt stmtList | );
    public final void stmtList() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:300:3: ( stmt stmtList | )
            int alt19=2;
            alt19 = dfa19.predict(input);
            switch (alt19) {
                case 1 :
                    // CPP.g:300:5: stmt stmtList
                    {
                    pushFollow(FOLLOW_stmt_in_stmtList1163);
                    stmt();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_stmtList_in_stmtList1171);
                    stmtList();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new StmtListAST ((OneStmtAST)ast1, (StmtListAST)ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:303:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyStmtListAST ();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "stmtList"


    // $ANTLR start "stmt"
    // CPP.g:306:1: stmt : ( blockStmt | exprStmt | ifStmt | forStmt | whileStmt | doStmt | breakStmt | continueStmt | returnStmt | switchStmt | caseStmt | defaultStmt | declarationStmt );
    public final void stmt() throws RecognitionException {
        try {
            // CPP.g:307:3: ( blockStmt | exprStmt | ifStmt | forStmt | whileStmt | doStmt | breakStmt | continueStmt | returnStmt | switchStmt | caseStmt | defaultStmt | declarationStmt )
            int alt20=13;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt20=1;
                }
                break;
            case IDENTIFIER:
            case CHARACTER_LITERAL:
            case STRING_LITERAL:
            case HEX_LITERAL:
            case DECIMAL_LITERAL:
            case OCTAL_LITERAL:
            case FLOATING_POINT_LITERAL:
            case 30:
            case 42:
            case 44:
            case 80:
            case 81:
            case 84:
            case 85:
            case 86:
            case 87:
                {
                alt20=2;
                }
                break;
            case 45:
                {
                alt20=3;
                }
                break;
            case 47:
                {
                alt20=4;
                }
                break;
            case 48:
                {
                alt20=5;
                }
                break;
            case 49:
                {
                alt20=6;
                }
                break;
            case 54:
                {
                alt20=7;
                }
                break;
            case 55:
                {
                alt20=8;
                }
                break;
            case 56:
                {
                alt20=9;
                }
                break;
            case 50:
                {
                alt20=10;
                }
                break;
            case 51:
                {
                alt20=11;
                }
                break;
            case 53:
                {
                alt20=12;
                }
                break;
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
                {
                alt20=13;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // CPP.g:307:5: blockStmt
                    {
                    pushFollow(FOLLOW_blockStmt_in_stmt1200);
                    blockStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:309:5: exprStmt
                    {
                    pushFollow(FOLLOW_exprStmt_in_stmt1208);
                    exprStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // CPP.g:310:5: ifStmt
                    {
                    pushFollow(FOLLOW_ifStmt_in_stmt1214);
                    ifStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // CPP.g:311:5: forStmt
                    {
                    pushFollow(FOLLOW_forStmt_in_stmt1221);
                    forStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // CPP.g:312:5: whileStmt
                    {
                    pushFollow(FOLLOW_whileStmt_in_stmt1228);
                    whileStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // CPP.g:313:5: doStmt
                    {
                    pushFollow(FOLLOW_doStmt_in_stmt1235);
                    doStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // CPP.g:314:5: breakStmt
                    {
                    pushFollow(FOLLOW_breakStmt_in_stmt1242);
                    breakStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // CPP.g:315:5: continueStmt
                    {
                    pushFollow(FOLLOW_continueStmt_in_stmt1249);
                    continueStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // CPP.g:316:5: returnStmt
                    {
                    pushFollow(FOLLOW_returnStmt_in_stmt1256);
                    returnStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // CPP.g:317:5: switchStmt
                    {
                    pushFollow(FOLLOW_switchStmt_in_stmt1262);
                    switchStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // CPP.g:318:5: caseStmt
                    {
                    pushFollow(FOLLOW_caseStmt_in_stmt1268);
                    caseStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // CPP.g:319:5: defaultStmt
                    {
                    pushFollow(FOLLOW_defaultStmt_in_stmt1274);
                    defaultStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 13 :
                    // CPP.g:320:5: declarationStmt
                    {
                    pushFollow(FOLLOW_declarationStmt_in_stmt1280);
                    declarationStmt();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "stmt"


    // $ANTLR start "declarationStmt"
    // CPP.g:324:1: declarationStmt : varDecl ;
    public final void declarationStmt() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:328:3: ( varDecl )
            // CPP.g:328:5: varDecl
            {
            pushFollow(FOLLOW_varDecl_in_declarationStmt1301);
            varDecl();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new DeclarationStmtAST ((DeclarationListAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declarationStmt"


    // $ANTLR start "exprStmt"
    // CPP.g:332:1: exprStmt : expr ';' ;
    public final void exprStmt() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:336:3: ( expr ';' )
            // CPP.g:336:5: expr ';'
            {
            pushFollow(FOLLOW_expr_in_exprStmt1328);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,23,FOLLOW_23_in_exprStmt1336); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new ExprStmtAST((ExprAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprStmt"


    // $ANTLR start "ifStmt"
    // CPP.g:341:1: ifStmt : 'if' '(' expr ')' stmt ( options {k=1; } : 'else' stmt )? ;
    public final void ifStmt() throws RecognitionException {

          AST ast1=null,ast2=null,ast3=null;

        try {
            // CPP.g:345:3: ( 'if' '(' expr ')' stmt ( options {k=1; } : 'else' stmt )? )
            // CPP.g:345:5: 'if' '(' expr ')' stmt ( options {k=1; } : 'else' stmt )?
            {
            match(input,45,FOLLOW_45_in_ifStmt1361); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_ifStmt1368); if (state.failed) return ;
            pushFollow(FOLLOW_expr_in_ifStmt1375);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_ifStmt1383); if (state.failed) return ;
            pushFollow(FOLLOW_stmt_in_ifStmt1390);
            stmt();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            // CPP.g:350:5: ( options {k=1; } : 'else' stmt )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==46) ) {
                int LA21_2 = input.LA(2);

                if ( (synpred43_CPP()) ) {
                    alt21=1;
                }
            }
            switch (alt21) {
                case 1 :
                    // CPP.g:351:5: 'else' stmt
                    {
                    match(input,46,FOLLOW_46_in_ifStmt1411); if (state.failed) return ;
                    pushFollow(FOLLOW_stmt_in_ifStmt1418);
                    stmt();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast3 = out; out = null;
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                    if (ast3 == null)
                      out = new IfThenStmtAST((ExprAST)ast1, (OneStmtAST)ast2);
                    else
                      out = new IfThenElseStmtAST((ExprAST)ast1, (OneStmtAST)ast2, (OneStmtAST)ast3);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "ifStmt"


    // $ANTLR start "forStmt"
    // CPP.g:362:1: forStmt : 'for' '(' ( forInit )? ( expr )? ';' ( exprList )? ')' stmt ;
    public final void forStmt() throws RecognitionException {

          AST ast1=null,ast2=null,ast3=null,ast4=null;

        try {
            // CPP.g:366:3: ( 'for' '(' ( forInit )? ( expr )? ';' ( exprList )? ')' stmt )
            // CPP.g:366:5: 'for' '(' ( forInit )? ( expr )? ';' ( exprList )? ')' stmt
            {
            match(input,47,FOLLOW_47_in_forStmt1452); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_forStmt1454); if (state.failed) return ;
            // CPP.g:367:5: ( forInit )?
            int alt22=2;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // CPP.g:0:0: forInit
                    {
                    pushFollow(FOLLOW_forInit_in_forStmt1461);
                    forInit();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            // CPP.g:368:5: ( expr )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=IDENTIFIER && LA23_0<=FLOATING_POINT_LITERAL)||LA23_0==30||LA23_0==42||LA23_0==44||(LA23_0>=80 && LA23_0<=81)||(LA23_0>=84 && LA23_0<=87)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // CPP.g:0:0: expr
                    {
                    pushFollow(FOLLOW_expr_in_forStmt1470);
                    expr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            match(input,23,FOLLOW_23_in_forStmt1479); if (state.failed) return ;
            // CPP.g:370:5: ( exprList )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=IDENTIFIER && LA24_0<=FLOATING_POINT_LITERAL)||LA24_0==30||LA24_0==42||LA24_0==44||(LA24_0>=80 && LA24_0<=81)||(LA24_0>=84 && LA24_0<=87)) ) {
                alt24=1;
            }
            else if ( (LA24_0==43) ) {
                int LA24_2 = input.LA(2);

                if ( (synpred46_CPP()) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // CPP.g:0:0: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_forStmt1486);
                    exprList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              ast3 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_forStmt1495); if (state.failed) return ;
            pushFollow(FOLLOW_stmt_in_forStmt1502);
            stmt();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast4 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new ForStmtAST((ForInitAST)ast1, (ExprAST)ast2, (ExprListAST)ast3, (OneStmtAST)ast4);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "forStmt"


    // $ANTLR start "forInit"
    // CPP.g:376:1: forInit : ( varDecl | exprList ';' | ';' );
    public final void forInit() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:380:3: ( varDecl | exprList ';' | ';' )
            int alt25=3;
            switch ( input.LA(1) ) {
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
                {
                alt25=1;
                }
                break;
            case IDENTIFIER:
            case CHARACTER_LITERAL:
            case STRING_LITERAL:
            case HEX_LITERAL:
            case DECIMAL_LITERAL:
            case OCTAL_LITERAL:
            case FLOATING_POINT_LITERAL:
            case 30:
            case 42:
            case 44:
            case 80:
            case 81:
            case 84:
            case 85:
            case 86:
            case 87:
                {
                alt25=2;
                }
                break;
            case 23:
                {
                int LA25_3 = input.LA(2);

                if ( (synpred48_CPP()) ) {
                    alt25=2;
                }
                else if ( (true) ) {
                    alt25=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // CPP.g:380:5: varDecl
                    {
                    pushFollow(FOLLOW_varDecl_in_forInit1529);
                    varDecl();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = new ForInitAST(1, (DeclarationListAST)ast1, null);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:381:5: exprList ';'
                    {
                    pushFollow(FOLLOW_exprList_in_forInit1537);
                    exprList();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,23,FOLLOW_23_in_forInit1539); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = new ForInitAST(2, null, (ExprListAST)ast1);
                    }

                    }
                    break;
                case 3 :
                    // CPP.g:382:5: ';'
                    {
                    match(input,23,FOLLOW_23_in_forInit1547); if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "forInit"


    // $ANTLR start "whileStmt"
    // CPP.g:385:1: whileStmt : 'while' '(' expr ')' stmt ;
    public final void whileStmt() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:389:3: ( 'while' '(' expr ')' stmt )
            // CPP.g:389:5: 'while' '(' expr ')' stmt
            {
            match(input,48,FOLLOW_48_in_whileStmt1564); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_whileStmt1571); if (state.failed) return ;
            pushFollow(FOLLOW_expr_in_whileStmt1578);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_whileStmt1586); if (state.failed) return ;
            pushFollow(FOLLOW_stmt_in_whileStmt1593);
            stmt();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new WhileStmtAST((ExprAST)ast1, (OneStmtAST)ast2);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "whileStmt"


    // $ANTLR start "doStmt"
    // CPP.g:397:1: doStmt : 'do' stmt 'while' '(' expr ')' ';' ;
    public final void doStmt() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:401:3: ( 'do' stmt 'while' '(' expr ')' ';' )
            // CPP.g:401:5: 'do' stmt 'while' '(' expr ')' ';'
            {
            match(input,49,FOLLOW_49_in_doStmt1620); if (state.failed) return ;
            pushFollow(FOLLOW_stmt_in_doStmt1627);
            stmt();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,48,FOLLOW_48_in_doStmt1635); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_doStmt1642); if (state.failed) return ;
            pushFollow(FOLLOW_expr_in_doStmt1649);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_doStmt1657); if (state.failed) return ;
            match(input,23,FOLLOW_23_in_doStmt1664); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new DoStmtAST((ExprAST)ast2, (OneStmtAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "doStmt"


    // $ANTLR start "switchStmt"
    // CPP.g:411:1: switchStmt : 'switch' '(' expr ')' stmt ;
    public final void switchStmt() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:415:3: ( 'switch' '(' expr ')' stmt )
            // CPP.g:415:5: 'switch' '(' expr ')' stmt
            {
            match(input,50,FOLLOW_50_in_switchStmt1689); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_switchStmt1695); if (state.failed) return ;
            pushFollow(FOLLOW_expr_in_switchStmt1701);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_switchStmt1709); if (state.failed) return ;
            pushFollow(FOLLOW_stmt_in_switchStmt1715);
            stmt();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new SwitchStmtAST((ExprAST)ast1, (OneStmtAST)ast2);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "switchStmt"


    // $ANTLR start "caseStmt"
    // CPP.g:423:1: caseStmt : 'case' expr ':' stmtList ;
    public final void caseStmt() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:427:3: ( 'case' expr ':' stmtList )
            // CPP.g:427:5: 'case' expr ':' stmtList
            {
            match(input,51,FOLLOW_51_in_caseStmt1742); if (state.failed) return ;
            pushFollow(FOLLOW_expr_in_caseStmt1748);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,52,FOLLOW_52_in_caseStmt1756); if (state.failed) return ;
            pushFollow(FOLLOW_stmtList_in_caseStmt1762);
            stmtList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new CaseStmtAST((ExprAST)ast1, (StmtListAST)ast2);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "caseStmt"


    // $ANTLR start "defaultStmt"
    // CPP.g:434:1: defaultStmt : 'default' ':' stmtList ;
    public final void defaultStmt() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:438:3: ( 'default' ':' stmtList )
            // CPP.g:438:5: 'default' ':' stmtList
            {
            match(input,53,FOLLOW_53_in_defaultStmt1789); if (state.failed) return ;
            match(input,52,FOLLOW_52_in_defaultStmt1795); if (state.failed) return ;
            pushFollow(FOLLOW_stmtList_in_defaultStmt1801);
            stmtList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            if ( state.backtracking==0 ) {
              out = new DefaultStmtAST((StmtListAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "defaultStmt"


    // $ANTLR start "breakStmt"
    // CPP.g:445:1: breakStmt : br= 'break' ';' ;
    public final void breakStmt() throws RecognitionException {
        Token br=null;

        try {
            // CPP.g:446:3: (br= 'break' ';' )
            // CPP.g:446:5: br= 'break' ';'
            {
            br=(Token)match(input,54,FOLLOW_54_in_breakStmt1831); if (state.failed) return ;
            match(input,23,FOLLOW_23_in_breakStmt1833); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new BreakStmtAST ((Token)br);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "breakStmt"


    // $ANTLR start "continueStmt"
    // CPP.g:450:1: continueStmt : cont= 'continue' ';' ;
    public final void continueStmt() throws RecognitionException {
        Token cont=null;

        try {
            // CPP.g:451:3: (cont= 'continue' ';' )
            // CPP.g:451:5: cont= 'continue' ';'
            {
            cont=(Token)match(input,55,FOLLOW_55_in_continueStmt1856); if (state.failed) return ;
            match(input,23,FOLLOW_23_in_continueStmt1858); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new ContStmtAST ((Token)cont);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "continueStmt"


    // $ANTLR start "returnStmt"
    // CPP.g:455:1: returnStmt : 'return' ( expr )? ';' ;
    public final void returnStmt() throws RecognitionException {

          AST ast1=null;

        try {
            // CPP.g:459:3: ( 'return' ( expr )? ';' )
            // CPP.g:459:5: 'return' ( expr )? ';'
            {
            match(input,56,FOLLOW_56_in_returnStmt1883); if (state.failed) return ;
            // CPP.g:460:5: ( expr )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=IDENTIFIER && LA26_0<=FLOATING_POINT_LITERAL)||LA26_0==30||LA26_0==42||LA26_0==44||(LA26_0>=80 && LA26_0<=81)||(LA26_0>=84 && LA26_0<=87)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // CPP.g:460:6: expr
                    {
                    pushFollow(FOLLOW_expr_in_returnStmt1891);
                    expr();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }

                    }
                    break;

            }

            match(input,23,FOLLOW_23_in_returnStmt1902); if (state.failed) return ;
            if ( state.backtracking==0 ) {

                    if (ast1 != null)
                      out = new RetStmtAST((ExprAST) ast1);
                    else
                      out = new RetStmtAST();
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "returnStmt"


    // $ANTLR start "callStmt"
    // CPP.g:470:1: callStmt : id= IDENTIFIER '(' exprList ')' ';' ;
    public final void callStmt() throws RecognitionException {
        Token id=null;


          AST ast1=null;

        try {
            // CPP.g:474:3: (id= IDENTIFIER '(' exprList ')' ';' )
            // CPP.g:474:5: id= IDENTIFIER '(' exprList ')' ';'
            {
            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_callStmt1929); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_callStmt1936); if (state.failed) return ;
            pushFollow(FOLLOW_exprList_in_callStmt1943);
            exprList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_callStmt1951); if (state.failed) return ;
            match(input,23,FOLLOW_23_in_callStmt1958); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new CallStmtAST((Token)id, (ExprListAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "callStmt"


    // $ANTLR start "exprList"
    // CPP.g:482:1: exprList : ( expr exprListRest | );
    public final void exprList() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:486:3: ( expr exprListRest | )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=IDENTIFIER && LA27_0<=FLOATING_POINT_LITERAL)||LA27_0==30||LA27_0==42||LA27_0==44||(LA27_0>=80 && LA27_0<=81)||(LA27_0>=84 && LA27_0<=87)) ) {
                alt27=1;
            }
            else if ( (LA27_0==EOF||LA27_0==23||LA27_0==43) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // CPP.g:486:5: expr exprListRest
                    {
                    pushFollow(FOLLOW_expr_in_exprList1983);
                    expr();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_exprListRest_in_exprList1992);
                    exprListRest();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:489:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyExprListAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprList"


    // $ANTLR start "exprListRest"
    // CPP.g:492:1: exprListRest : ( ',' expr exprListRest | );
    public final void exprListRest() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:496:3: ( ',' expr exprListRest | )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==24) ) {
                alt28=1;
            }
            else if ( (LA28_0==EOF||LA28_0==23||LA28_0==43) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // CPP.g:496:5: ',' expr exprListRest
                    {
                    match(input,24,FOLLOW_24_in_exprListRest2025); if (state.failed) return ;
                    pushFollow(FOLLOW_expr_in_exprListRest2032);
                    expr();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    pushFollow(FOLLOW_exprListRest_in_exprListRest2041);
                    exprListRest();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:500:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyExprListAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprListRest"


    // $ANTLR start "expr"
    // CPP.g:503:1: expr : exprASSIGN ;
    public final void expr() throws RecognitionException {
        try {
            // CPP.g:504:3: ( exprASSIGN )
            // CPP.g:504:5: exprASSIGN
            {
            pushFollow(FOLLOW_exprASSIGN_in_expr2070);
            exprASSIGN();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expr"


    // $ANTLR start "exprASSIGN"
    // CPP.g:507:1: exprASSIGN : ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN | exprTERNARY );
    public final void exprASSIGN() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;
          int type = 0;

        try {
            // CPP.g:512:3: ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN | exprTERNARY )
            int alt30=2;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // CPP.g:512:5: exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN
                    {
                    pushFollow(FOLLOW_exprLOGICAL_OR_in_exprASSIGN2089);
                    exprLOGICAL_OR();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    // CPP.g:513:5: (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' )
                    int alt29=11;
                    switch ( input.LA(1) ) {
                    case 27:
                        {
                        alt29=1;
                        }
                        break;
                    case 57:
                        {
                        alt29=2;
                        }
                        break;
                    case 58:
                        {
                        alt29=3;
                        }
                        break;
                    case 59:
                        {
                        alt29=4;
                        }
                        break;
                    case 60:
                        {
                        alt29=5;
                        }
                        break;
                    case 61:
                        {
                        alt29=6;
                        }
                        break;
                    case 62:
                        {
                        alt29=7;
                        }
                        break;
                    case 63:
                        {
                        alt29=8;
                        }
                        break;
                    case 64:
                        {
                        alt29=9;
                        }
                        break;
                    case 65:
                        {
                        alt29=10;
                        }
                        break;
                    case 66:
                        {
                        alt29=11;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;
                    }

                    switch (alt29) {
                        case 1 :
                            // CPP.g:513:7: op= '='
                            {
                            op=(Token)match(input,27,FOLLOW_27_in_exprASSIGN2101); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.ASSIGN;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:514:7: op= '+='
                            {
                            op=(Token)match(input,57,FOLLOW_57_in_exprASSIGN2115); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.PLUS_ASSIGN;
                            }

                            }
                            break;
                        case 3 :
                            // CPP.g:515:7: op= '-='
                            {
                            op=(Token)match(input,58,FOLLOW_58_in_exprASSIGN2128); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.MINUS_ASSIGN;
                            }

                            }
                            break;
                        case 4 :
                            // CPP.g:516:7: op= '*='
                            {
                            op=(Token)match(input,59,FOLLOW_59_in_exprASSIGN2141); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.STAR_ASSIGN;
                            }

                            }
                            break;
                        case 5 :
                            // CPP.g:517:7: op= '/='
                            {
                            op=(Token)match(input,60,FOLLOW_60_in_exprASSIGN2154); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.DIV_ASSIGN;
                            }

                            }
                            break;
                        case 6 :
                            // CPP.g:518:7: op= '%='
                            {
                            op=(Token)match(input,61,FOLLOW_61_in_exprASSIGN2167); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.MOD_ASSIGN;
                            }

                            }
                            break;
                        case 7 :
                            // CPP.g:519:7: op= '<<='
                            {
                            op=(Token)match(input,62,FOLLOW_62_in_exprASSIGN2180); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.SHIFT_LEFT_ASSIGN;
                            }

                            }
                            break;
                        case 8 :
                            // CPP.g:520:7: op= '>>='
                            {
                            op=(Token)match(input,63,FOLLOW_63_in_exprASSIGN2192); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.SHIFT_RIGHT_ASSIGN;
                            }

                            }
                            break;
                        case 9 :
                            // CPP.g:521:7: op= '&='
                            {
                            op=(Token)match(input,64,FOLLOW_64_in_exprASSIGN2204); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.AND_ASSIGN;
                            }

                            }
                            break;
                        case 10 :
                            // CPP.g:522:7: op= '^='
                            {
                            op=(Token)match(input,65,FOLLOW_65_in_exprASSIGN2217); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.XOR_ASSIGN;
                            }

                            }
                            break;
                        case 11 :
                            // CPP.g:523:7: op= '|='
                            {
                            op=(Token)match(input,66,FOLLOW_66_in_exprASSIGN2230); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.OR_ASSIGN;
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprASSIGN_in_exprASSIGN2245);
                    exprASSIGN();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:527:5: exprTERNARY
                    {
                    pushFollow(FOLLOW_exprTERNARY_in_exprASSIGN2259);
                    exprTERNARY();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprASSIGN"


    // $ANTLR start "exprTERNARY"
    // CPP.g:530:1: exprTERNARY : exprLOGICAL_OR exprTERNARYEx ;
    public final void exprTERNARY() throws RecognitionException {
        try {
            // CPP.g:531:3: ( exprLOGICAL_OR exprTERNARYEx )
            // CPP.g:531:5: exprLOGICAL_OR exprTERNARYEx
            {
            pushFollow(FOLLOW_exprLOGICAL_OR_in_exprTERNARY2274);
            exprLOGICAL_OR();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprTERNARYEx_in_exprTERNARY2282);
            exprTERNARYEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprTERNARY"


    // $ANTLR start "exprTERNARYEx"
    // CPP.g:535:1: exprTERNARYEx : ( '?' exprASSIGN ':' exprASSIGN | );
    public final void exprTERNARYEx() throws RecognitionException {

          AST ast1=null,ast2=null,ast3=null;

        try {
            // CPP.g:539:3: ( '?' exprASSIGN ':' exprASSIGN | )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==67) ) {
                alt31=1;
            }
            else if ( (LA31_0==EOF||(LA31_0>=23 && LA31_0<=24)||LA31_0==26||LA31_0==29||LA31_0==43||LA31_0==52) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // CPP.g:539:5: '?' exprASSIGN ':' exprASSIGN
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    match(input,67,FOLLOW_67_in_exprTERNARYEx2307); if (state.failed) return ;
                    pushFollow(FOLLOW_exprASSIGN_in_exprTERNARYEx2314);
                    exprASSIGN();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    match(input,52,FOLLOW_52_in_exprTERNARYEx2322); if (state.failed) return ;
                    pushFollow(FOLLOW_exprASSIGN_in_exprTERNARYEx2329);
                    exprASSIGN();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast3 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new TernaryExprAST((ExprAST)ast1, (ExprAST)ast2, (ExprAST)ast3);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:546:3: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprTERNARYEx"


    // $ANTLR start "exprLOGICAL_OR"
    // CPP.g:548:1: exprLOGICAL_OR : exprLOGICAL_AND exprLOGICAL_OREx ;
    public final void exprLOGICAL_OR() throws RecognitionException {
        try {
            // CPP.g:549:3: ( exprLOGICAL_AND exprLOGICAL_OREx )
            // CPP.g:549:5: exprLOGICAL_AND exprLOGICAL_OREx
            {
            pushFollow(FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OR2354);
            exprLOGICAL_AND();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OR2362);
            exprLOGICAL_OREx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprLOGICAL_OR"


    // $ANTLR start "exprLOGICAL_OREx"
    // CPP.g:553:1: exprLOGICAL_OREx : (op= '||' exprLOGICAL_AND exprLOGICAL_OREx | );
    public final void exprLOGICAL_OREx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;

        try {
            // CPP.g:557:3: (op= '||' exprLOGICAL_AND exprLOGICAL_OREx | )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==68) ) {
                alt32=1;
            }
            else if ( (LA32_0==EOF||(LA32_0>=23 && LA32_0<=24)||(LA32_0>=26 && LA32_0<=27)||LA32_0==29||LA32_0==43||LA32_0==52||(LA32_0>=57 && LA32_0<=67)) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // CPP.g:557:5: op= '||' exprLOGICAL_AND exprLOGICAL_OREx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    op=(Token)match(input,68,FOLLOW_68_in_exprLOGICAL_OREx2389); if (state.failed) return ;
                    pushFollow(FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OREx2396);
                    exprLOGICAL_AND();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, BinExprAST.LOGICAL_OR, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OREx2410);
                    exprLOGICAL_OREx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:562:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprLOGICAL_OREx"


    // $ANTLR start "exprLOGICAL_AND"
    // CPP.g:565:1: exprLOGICAL_AND : exprBITWISE_OR exprLOGICAL_ANDEx ;
    public final void exprLOGICAL_AND() throws RecognitionException {
        try {
            // CPP.g:566:3: ( exprBITWISE_OR exprLOGICAL_ANDEx )
            // CPP.g:566:5: exprBITWISE_OR exprLOGICAL_ANDEx
            {
            pushFollow(FOLLOW_exprBITWISE_OR_in_exprLOGICAL_AND2431);
            exprBITWISE_OR();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_AND2439);
            exprLOGICAL_ANDEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprLOGICAL_AND"


    // $ANTLR start "exprLOGICAL_ANDEx"
    // CPP.g:570:1: exprLOGICAL_ANDEx : (op= '&&' exprBITWISE_OR exprLOGICAL_ANDEx | );
    public final void exprLOGICAL_ANDEx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;

        try {
            // CPP.g:574:3: (op= '&&' exprBITWISE_OR exprLOGICAL_ANDEx | )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==69) ) {
                alt33=1;
            }
            else if ( (LA33_0==EOF||(LA33_0>=23 && LA33_0<=24)||(LA33_0>=26 && LA33_0<=27)||LA33_0==29||LA33_0==43||LA33_0==52||(LA33_0>=57 && LA33_0<=68)) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // CPP.g:574:5: op= '&&' exprBITWISE_OR exprLOGICAL_ANDEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    op=(Token)match(input,69,FOLLOW_69_in_exprLOGICAL_ANDEx2466); if (state.failed) return ;
                    pushFollow(FOLLOW_exprBITWISE_OR_in_exprLOGICAL_ANDEx2473);
                    exprBITWISE_OR();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, BinExprAST.LOGICAL_AND, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_ANDEx2487);
                    exprLOGICAL_ANDEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:579:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprLOGICAL_ANDEx"


    // $ANTLR start "exprBITWISE_OR"
    // CPP.g:582:1: exprBITWISE_OR : exprBITWISE_XOR exprBITWISE_OREx ;
    public final void exprBITWISE_OR() throws RecognitionException {
        try {
            // CPP.g:583:3: ( exprBITWISE_XOR exprBITWISE_OREx )
            // CPP.g:583:5: exprBITWISE_XOR exprBITWISE_OREx
            {
            pushFollow(FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OR2508);
            exprBITWISE_XOR();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OR2516);
            exprBITWISE_OREx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprBITWISE_OR"


    // $ANTLR start "exprBITWISE_OREx"
    // CPP.g:587:1: exprBITWISE_OREx : (op= '|' exprBITWISE_XOR exprBITWISE_OREx | );
    public final void exprBITWISE_OREx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;

        try {
            // CPP.g:591:3: (op= '|' exprBITWISE_XOR exprBITWISE_OREx | )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==70) ) {
                alt34=1;
            }
            else if ( (LA34_0==EOF||(LA34_0>=23 && LA34_0<=24)||(LA34_0>=26 && LA34_0<=27)||LA34_0==29||LA34_0==43||LA34_0==52||(LA34_0>=57 && LA34_0<=69)) ) {
                alt34=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // CPP.g:591:5: op= '|' exprBITWISE_XOR exprBITWISE_OREx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    op=(Token)match(input,70,FOLLOW_70_in_exprBITWISE_OREx2543); if (state.failed) return ;
                    pushFollow(FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OREx2550);
                    exprBITWISE_XOR();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, BinExprAST.OR, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OREx2564);
                    exprBITWISE_OREx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:596:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprBITWISE_OREx"


    // $ANTLR start "exprBITWISE_XOR"
    // CPP.g:599:1: exprBITWISE_XOR : exprBITWISE_AND exprBITWISE_XOREx ;
    public final void exprBITWISE_XOR() throws RecognitionException {
        try {
            // CPP.g:600:3: ( exprBITWISE_AND exprBITWISE_XOREx )
            // CPP.g:600:5: exprBITWISE_AND exprBITWISE_XOREx
            {
            pushFollow(FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOR2585);
            exprBITWISE_AND();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOR2593);
            exprBITWISE_XOREx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprBITWISE_XOR"


    // $ANTLR start "exprBITWISE_XOREx"
    // CPP.g:604:1: exprBITWISE_XOREx : (op= '^' exprBITWISE_AND exprBITWISE_XOREx | );
    public final void exprBITWISE_XOREx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;

        try {
            // CPP.g:608:3: (op= '^' exprBITWISE_AND exprBITWISE_XOREx | )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==71) ) {
                alt35=1;
            }
            else if ( (LA35_0==EOF||(LA35_0>=23 && LA35_0<=24)||(LA35_0>=26 && LA35_0<=27)||LA35_0==29||LA35_0==43||LA35_0==52||(LA35_0>=57 && LA35_0<=70)) ) {
                alt35=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // CPP.g:608:5: op= '^' exprBITWISE_AND exprBITWISE_XOREx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    op=(Token)match(input,71,FOLLOW_71_in_exprBITWISE_XOREx2620); if (state.failed) return ;
                    pushFollow(FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOREx2627);
                    exprBITWISE_AND();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, BinExprAST.XOR, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOREx2641);
                    exprBITWISE_XOREx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:613:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprBITWISE_XOREx"


    // $ANTLR start "exprBITWISE_AND"
    // CPP.g:616:1: exprBITWISE_AND : exprEQUALITY exprBITWISE_ANDEx ;
    public final void exprBITWISE_AND() throws RecognitionException {
        try {
            // CPP.g:617:3: ( exprEQUALITY exprBITWISE_ANDEx )
            // CPP.g:617:5: exprEQUALITY exprBITWISE_ANDEx
            {
            pushFollow(FOLLOW_exprEQUALITY_in_exprBITWISE_AND2662);
            exprEQUALITY();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_AND2670);
            exprBITWISE_ANDEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprBITWISE_AND"


    // $ANTLR start "exprBITWISE_ANDEx"
    // CPP.g:621:1: exprBITWISE_ANDEx : (op= '&' exprEQUALITY exprBITWISE_ANDEx | );
    public final void exprBITWISE_ANDEx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;

        try {
            // CPP.g:625:3: (op= '&' exprEQUALITY exprBITWISE_ANDEx | )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==44) ) {
                alt36=1;
            }
            else if ( (LA36_0==EOF||(LA36_0>=23 && LA36_0<=24)||(LA36_0>=26 && LA36_0<=27)||LA36_0==29||LA36_0==43||LA36_0==52||(LA36_0>=57 && LA36_0<=71)) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // CPP.g:625:5: op= '&' exprEQUALITY exprBITWISE_ANDEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    op=(Token)match(input,44,FOLLOW_44_in_exprBITWISE_ANDEx2697); if (state.failed) return ;
                    pushFollow(FOLLOW_exprEQUALITY_in_exprBITWISE_ANDEx2704);
                    exprEQUALITY();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, BinExprAST.AND, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_ANDEx2718);
                    exprBITWISE_ANDEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:630:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprBITWISE_ANDEx"


    // $ANTLR start "exprEQUALITY"
    // CPP.g:633:1: exprEQUALITY : exprRELATION exprEQUALITYEx ;
    public final void exprEQUALITY() throws RecognitionException {
        try {
            // CPP.g:634:3: ( exprRELATION exprEQUALITYEx )
            // CPP.g:634:5: exprRELATION exprEQUALITYEx
            {
            pushFollow(FOLLOW_exprRELATION_in_exprEQUALITY2739);
            exprRELATION();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprEQUALITYEx_in_exprEQUALITY2747);
            exprEQUALITYEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprEQUALITY"


    // $ANTLR start "exprEQUALITYEx"
    // CPP.g:638:1: exprEQUALITYEx : ( (op= '==' | op= '!=' ) exprRELATION exprEQUALITYEx | );
    public final void exprEQUALITYEx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;
          int type = 0;

        try {
            // CPP.g:643:3: ( (op= '==' | op= '!=' ) exprRELATION exprEQUALITYEx | )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( ((LA38_0>=72 && LA38_0<=73)) ) {
                alt38=1;
            }
            else if ( (LA38_0==EOF||(LA38_0>=23 && LA38_0<=24)||(LA38_0>=26 && LA38_0<=27)||LA38_0==29||(LA38_0>=43 && LA38_0<=44)||LA38_0==52||(LA38_0>=57 && LA38_0<=71)) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // CPP.g:643:5: (op= '==' | op= '!=' ) exprRELATION exprEQUALITYEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    // CPP.g:644:5: (op= '==' | op= '!=' )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==72) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==73) ) {
                        alt37=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 37, 0, input);

                        throw nvae;
                    }
                    switch (alt37) {
                        case 1 :
                            // CPP.g:644:7: op= '=='
                            {
                            op=(Token)match(input,72,FOLLOW_72_in_exprEQUALITYEx2776); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.EQUAL;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:645:7: op= '!='
                            {
                            op=(Token)match(input,73,FOLLOW_73_in_exprEQUALITYEx2789); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.NOT_EQUAL;
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprRELATION_in_exprEQUALITYEx2801);
                    exprRELATION();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprEQUALITYEx_in_exprEQUALITYEx2815);
                    exprEQUALITYEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:650:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprEQUALITYEx"


    // $ANTLR start "exprRELATION"
    // CPP.g:653:1: exprRELATION : exprSHIFT exprRELATIONEx ;
    public final void exprRELATION() throws RecognitionException {
        try {
            // CPP.g:654:3: ( exprSHIFT exprRELATIONEx )
            // CPP.g:654:5: exprSHIFT exprRELATIONEx
            {
            pushFollow(FOLLOW_exprSHIFT_in_exprRELATION2836);
            exprSHIFT();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprRELATIONEx_in_exprRELATION2844);
            exprRELATIONEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprRELATION"


    // $ANTLR start "exprRELATIONEx"
    // CPP.g:658:1: exprRELATIONEx : ( (op= '>' | op= '>=' | op= '<' | op= '<=' ) exprSHIFT exprRELATIONEx | );
    public final void exprRELATIONEx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;
          int type = 0;

        try {
            // CPP.g:663:3: ( (op= '>' | op= '>=' | op= '<' | op= '<=' ) exprSHIFT exprRELATIONEx | )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=74 && LA40_0<=77)) ) {
                alt40=1;
            }
            else if ( (LA40_0==EOF||(LA40_0>=23 && LA40_0<=24)||(LA40_0>=26 && LA40_0<=27)||LA40_0==29||(LA40_0>=43 && LA40_0<=44)||LA40_0==52||(LA40_0>=57 && LA40_0<=73)) ) {
                alt40=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // CPP.g:663:5: (op= '>' | op= '>=' | op= '<' | op= '<=' ) exprSHIFT exprRELATIONEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    // CPP.g:664:5: (op= '>' | op= '>=' | op= '<' | op= '<=' )
                    int alt39=4;
                    switch ( input.LA(1) ) {
                    case 74:
                        {
                        alt39=1;
                        }
                        break;
                    case 75:
                        {
                        alt39=2;
                        }
                        break;
                    case 76:
                        {
                        alt39=3;
                        }
                        break;
                    case 77:
                        {
                        alt39=4;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 39, 0, input);

                        throw nvae;
                    }

                    switch (alt39) {
                        case 1 :
                            // CPP.g:664:7: op= '>'
                            {
                            op=(Token)match(input,74,FOLLOW_74_in_exprRELATIONEx2873); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.GREATER_THAN;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:665:7: op= '>='
                            {
                            op=(Token)match(input,75,FOLLOW_75_in_exprRELATIONEx2887); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.GREATER_OR_EQUAL;
                            }

                            }
                            break;
                        case 3 :
                            // CPP.g:666:7: op= '<'
                            {
                            op=(Token)match(input,76,FOLLOW_76_in_exprRELATIONEx2900); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.LESS_THAN;
                            }

                            }
                            break;
                        case 4 :
                            // CPP.g:667:7: op= '<='
                            {
                            op=(Token)match(input,77,FOLLOW_77_in_exprRELATIONEx2914); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.LESS_OR_EQUAL;
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprSHIFT_in_exprRELATIONEx2930);
                    exprSHIFT();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprRELATIONEx_in_exprRELATIONEx2944);
                    exprRELATIONEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:672:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprRELATIONEx"


    // $ANTLR start "exprSHIFT"
    // CPP.g:675:1: exprSHIFT : exprADD exprSHIFTEx ;
    public final void exprSHIFT() throws RecognitionException {
        try {
            // CPP.g:676:3: ( exprADD exprSHIFTEx )
            // CPP.g:676:5: exprADD exprSHIFTEx
            {
            pushFollow(FOLLOW_exprADD_in_exprSHIFT2965);
            exprADD();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprSHIFTEx_in_exprSHIFT2973);
            exprSHIFTEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprSHIFT"


    // $ANTLR start "exprSHIFTEx"
    // CPP.g:680:1: exprSHIFTEx : ( (op= '>>' | op= '<<' ) exprADD exprSHIFTEx | );
    public final void exprSHIFTEx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;
          int type = 0;

        try {
            // CPP.g:685:3: ( (op= '>>' | op= '<<' ) exprADD exprSHIFTEx | )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( ((LA42_0>=78 && LA42_0<=79)) ) {
                alt42=1;
            }
            else if ( (LA42_0==EOF||(LA42_0>=23 && LA42_0<=24)||(LA42_0>=26 && LA42_0<=27)||LA42_0==29||(LA42_0>=43 && LA42_0<=44)||LA42_0==52||(LA42_0>=57 && LA42_0<=77)) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // CPP.g:685:5: (op= '>>' | op= '<<' ) exprADD exprSHIFTEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    // CPP.g:686:5: (op= '>>' | op= '<<' )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==78) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==79) ) {
                        alt41=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;
                    }
                    switch (alt41) {
                        case 1 :
                            // CPP.g:686:7: op= '>>'
                            {
                            op=(Token)match(input,78,FOLLOW_78_in_exprSHIFTEx3002); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.SHIFT_RIGHT;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:687:7: op= '<<'
                            {
                            op=(Token)match(input,79,FOLLOW_79_in_exprSHIFTEx3014); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.SHIFT_LEFT;
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprADD_in_exprSHIFTEx3029);
                    exprADD();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprSHIFTEx_in_exprSHIFTEx3043);
                    exprSHIFTEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:692:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprSHIFTEx"


    // $ANTLR start "exprADD"
    // CPP.g:695:1: exprADD : exprMUL exprADDEx ;
    public final void exprADD() throws RecognitionException {
        try {
            // CPP.g:696:3: ( exprMUL exprADDEx )
            // CPP.g:696:5: exprMUL exprADDEx
            {
            pushFollow(FOLLOW_exprMUL_in_exprADD3064);
            exprMUL();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprADDEx_in_exprADD3072);
            exprADDEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprADD"


    // $ANTLR start "exprADDEx"
    // CPP.g:700:1: exprADDEx : ( (op= '+' | op= '-' ) exprMUL exprADDEx | );
    public final void exprADDEx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;
          int type = 0;

        try {
            // CPP.g:705:3: ( (op= '+' | op= '-' ) exprMUL exprADDEx | )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( ((LA44_0>=80 && LA44_0<=81)) ) {
                alt44=1;
            }
            else if ( (LA44_0==EOF||(LA44_0>=23 && LA44_0<=24)||(LA44_0>=26 && LA44_0<=27)||LA44_0==29||(LA44_0>=43 && LA44_0<=44)||LA44_0==52||(LA44_0>=57 && LA44_0<=79)) ) {
                alt44=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // CPP.g:705:5: (op= '+' | op= '-' ) exprMUL exprADDEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    // CPP.g:706:5: (op= '+' | op= '-' )
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==80) ) {
                        alt43=1;
                    }
                    else if ( (LA43_0==81) ) {
                        alt43=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 43, 0, input);

                        throw nvae;
                    }
                    switch (alt43) {
                        case 1 :
                            // CPP.g:706:7: op= '+'
                            {
                            op=(Token)match(input,80,FOLLOW_80_in_exprADDEx3101); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.PLUS;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:707:7: op= '-'
                            {
                            op=(Token)match(input,81,FOLLOW_81_in_exprADDEx3115); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.MINUS;
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprMUL_in_exprADDEx3132);
                    exprMUL();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprADDEx_in_exprADDEx3146);
                    exprADDEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:712:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprADDEx"


    // $ANTLR start "exprMUL"
    // CPP.g:715:1: exprMUL : exprUNARY exprMULEx ;
    public final void exprMUL() throws RecognitionException {
        try {
            // CPP.g:716:3: ( exprUNARY exprMULEx )
            // CPP.g:716:5: exprUNARY exprMULEx
            {
            pushFollow(FOLLOW_exprUNARY_in_exprMUL3165);
            exprUNARY();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprMULEx_in_exprMUL3173);
            exprMULEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprMUL"


    // $ANTLR start "exprMULEx"
    // CPP.g:720:1: exprMULEx : ( (op= '*' | op= '/' | op= '%' ) exprUNARY exprMULEx | );
    public final void exprMULEx() throws RecognitionException {
        Token op=null;


          AST ast1=null,ast2=null;
          int type = 0;

        try {
            // CPP.g:725:3: ( (op= '*' | op= '/' | op= '%' ) exprUNARY exprMULEx | )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==30||(LA46_0>=82 && LA46_0<=83)) ) {
                alt46=1;
            }
            else if ( (LA46_0==EOF||(LA46_0>=23 && LA46_0<=24)||(LA46_0>=26 && LA46_0<=27)||LA46_0==29||(LA46_0>=43 && LA46_0<=44)||LA46_0==52||(LA46_0>=57 && LA46_0<=81)) ) {
                alt46=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // CPP.g:725:5: (op= '*' | op= '/' | op= '%' ) exprUNARY exprMULEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    // CPP.g:726:5: (op= '*' | op= '/' | op= '%' )
                    int alt45=3;
                    switch ( input.LA(1) ) {
                    case 30:
                        {
                        alt45=1;
                        }
                        break;
                    case 82:
                        {
                        alt45=2;
                        }
                        break;
                    case 83:
                        {
                        alt45=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 45, 0, input);

                        throw nvae;
                    }

                    switch (alt45) {
                        case 1 :
                            // CPP.g:726:7: op= '*'
                            {
                            op=(Token)match(input,30,FOLLOW_30_in_exprMULEx3200); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.STAR;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:727:7: op= '/'
                            {
                            op=(Token)match(input,82,FOLLOW_82_in_exprMULEx3214); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.DIV;
                            }

                            }
                            break;
                        case 3 :
                            // CPP.g:728:7: op= '%'
                            {
                            op=(Token)match(input,83,FOLLOW_83_in_exprMULEx3228); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = BinExprAST.MOD;
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprUNARY_in_exprMULEx3245);
                    exprUNARY();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      in = new BinExprAST((ExprAST)ast1, type, (Token)op, (ExprAST)ast2);
                    }
                    pushFollow(FOLLOW_exprMULEx_in_exprMULEx3259);
                    exprMULEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:733:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprMULEx"


    // $ANTLR start "exprUNARY"
    // CPP.g:736:1: exprUNARY : ( (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' | op= '!' | op= '~' ) exprUNARY | exprPostfix );
    public final void exprUNARY() throws RecognitionException {
        Token op=null;


          AST ast1=null;
          int type = 0;

        try {
            // CPP.g:741:3: ( (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' | op= '!' | op= '~' ) exprUNARY | exprPostfix )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==30||LA48_0==44||(LA48_0>=80 && LA48_0<=81)||(LA48_0>=84 && LA48_0<=87)) ) {
                alt48=1;
            }
            else if ( ((LA48_0>=IDENTIFIER && LA48_0<=FLOATING_POINT_LITERAL)||LA48_0==42) ) {
                alt48=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // CPP.g:741:5: (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' | op= '!' | op= '~' ) exprUNARY
                    {
                    // CPP.g:741:5: (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' | op= '!' | op= '~' )
                    int alt47=8;
                    switch ( input.LA(1) ) {
                    case 80:
                        {
                        alt47=1;
                        }
                        break;
                    case 81:
                        {
                        alt47=2;
                        }
                        break;
                    case 84:
                        {
                        alt47=3;
                        }
                        break;
                    case 85:
                        {
                        alt47=4;
                        }
                        break;
                    case 30:
                        {
                        alt47=5;
                        }
                        break;
                    case 44:
                        {
                        alt47=6;
                        }
                        break;
                    case 86:
                        {
                        alt47=7;
                        }
                        break;
                    case 87:
                        {
                        alt47=8;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 47, 0, input);

                        throw nvae;
                    }

                    switch (alt47) {
                        case 1 :
                            // CPP.g:741:7: op= '+'
                            {
                            op=(Token)match(input,80,FOLLOW_80_in_exprUNARY3288); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.UNARY_PLUS;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:742:7: op= '-'
                            {
                            op=(Token)match(input,81,FOLLOW_81_in_exprUNARY3302); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.UNARY_MINUS;
                            }

                            }
                            break;
                        case 3 :
                            // CPP.g:743:7: op= '++'
                            {
                            op=(Token)match(input,84,FOLLOW_84_in_exprUNARY3316); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.PRE_INC;
                            }

                            }
                            break;
                        case 4 :
                            // CPP.g:744:7: op= '--'
                            {
                            op=(Token)match(input,85,FOLLOW_85_in_exprUNARY3329); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.PRE_DEC;
                            }

                            }
                            break;
                        case 5 :
                            // CPP.g:745:7: op= '*'
                            {
                            op=(Token)match(input,30,FOLLOW_30_in_exprUNARY3342); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.INDIRECTION;
                            }

                            }
                            break;
                        case 6 :
                            // CPP.g:746:7: op= '&'
                            {
                            op=(Token)match(input,44,FOLLOW_44_in_exprUNARY3356); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.ADDR_OF;
                            }

                            }
                            break;
                        case 7 :
                            // CPP.g:747:7: op= '!'
                            {
                            op=(Token)match(input,86,FOLLOW_86_in_exprUNARY3370); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.LOGICAL_NOT;
                            }

                            }
                            break;
                        case 8 :
                            // CPP.g:748:7: op= '~'
                            {
                            op=(Token)match(input,87,FOLLOW_87_in_exprUNARY3384); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.NOT;
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_exprUNARY_in_exprUNARY3400);
                    exprUNARY();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new UnaryExprAST(type, (Token)op, (ExprAST)ast1);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:752:5: exprPostfix
                    {
                    pushFollow(FOLLOW_exprPostfix_in_exprUNARY3415);
                    exprPostfix();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprUNARY"


    // $ANTLR start "exprPostfix"
    // CPP.g:755:1: exprPostfix : exprPrimary exprPostfixEx ;
    public final void exprPostfix() throws RecognitionException {
        try {
            // CPP.g:756:3: ( exprPrimary exprPostfixEx )
            // CPP.g:756:5: exprPrimary exprPostfixEx
            {
            pushFollow(FOLLOW_exprPrimary_in_exprPostfix3430);
            exprPrimary();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              in = out;
            }
            pushFollow(FOLLOW_exprPostfixEx_in_exprPostfix3438);
            exprPostfixEx();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprPostfix"


    // $ANTLR start "exprPostfixEx"
    // CPP.g:760:1: exprPostfixEx : ( (op= '++' | op= '--' ) exprPostfixEx | );
    public final void exprPostfixEx() throws RecognitionException {
        Token op=null;


          AST ast1=null;
          int type = 0;

        try {
            // CPP.g:765:3: ( (op= '++' | op= '--' ) exprPostfixEx | )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( ((LA50_0>=84 && LA50_0<=85)) ) {
                alt50=1;
            }
            else if ( (LA50_0==EOF||(LA50_0>=23 && LA50_0<=24)||(LA50_0>=26 && LA50_0<=27)||(LA50_0>=29 && LA50_0<=30)||(LA50_0>=43 && LA50_0<=44)||LA50_0==52||(LA50_0>=57 && LA50_0<=83)) ) {
                alt50=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // CPP.g:765:5: (op= '++' | op= '--' ) exprPostfixEx
                    {
                    if ( state.backtracking==0 ) {
                      ast1 = in;
                    }
                    // CPP.g:766:5: (op= '++' | op= '--' )
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==84) ) {
                        alt49=1;
                    }
                    else if ( (LA49_0==85) ) {
                        alt49=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 49, 0, input);

                        throw nvae;
                    }
                    switch (alt49) {
                        case 1 :
                            // CPP.g:766:7: op= '++'
                            {
                            op=(Token)match(input,84,FOLLOW_84_in_exprPostfixEx3467); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.POST_INC;
                            }

                            }
                            break;
                        case 2 :
                            // CPP.g:767:7: op= '--'
                            {
                            op=(Token)match(input,85,FOLLOW_85_in_exprPostfixEx3480); if (state.failed) return ;
                            if ( state.backtracking==0 ) {
                              type = UnaryExprAST.POST_DEC;
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      in = new UnaryExprAST(type, (Token)op, (ExprAST)ast1);
                    }
                    pushFollow(FOLLOW_exprPostfixEx_in_exprPostfixEx3502);
                    exprPostfixEx();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:771:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = in;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprPostfixEx"


    // $ANTLR start "exprPrimary"
    // CPP.g:774:1: exprPrimary : ( literal | id= IDENTIFIER | '(' expr ')' | callExpr | arrayElement );
    public final void exprPrimary() throws RecognitionException {
        Token id=null;

        try {
            // CPP.g:775:3: ( literal | id= IDENTIFIER | '(' expr ')' | callExpr | arrayElement )
            int alt51=5;
            switch ( input.LA(1) ) {
            case CHARACTER_LITERAL:
            case STRING_LITERAL:
            case HEX_LITERAL:
            case DECIMAL_LITERAL:
            case OCTAL_LITERAL:
            case FLOATING_POINT_LITERAL:
                {
                alt51=1;
                }
                break;
            case IDENTIFIER:
                {
                switch ( input.LA(2) ) {
                case 42:
                    {
                    alt51=4;
                    }
                    break;
                case 25:
                    {
                    alt51=5;
                    }
                    break;
                case EOF:
                case 23:
                case 24:
                case 26:
                case 27:
                case 29:
                case 30:
                case 43:
                case 44:
                case 52:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                    {
                    alt51=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 51, 2, input);

                    throw nvae;
                }

                }
                break;
            case 42:
                {
                alt51=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // CPP.g:775:5: literal
                    {
                    pushFollow(FOLLOW_literal_in_exprPrimary3523);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // CPP.g:776:5: id= IDENTIFIER
                    {
                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_exprPrimary3532); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new VarExprAST((Token)id);
                    }

                    }
                    break;
                case 3 :
                    // CPP.g:777:5: '(' expr ')'
                    {
                    match(input,42,FOLLOW_42_in_exprPrimary3540); if (state.failed) return ;
                    pushFollow(FOLLOW_expr_in_exprPrimary3542);
                    expr();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,43,FOLLOW_43_in_exprPrimary3544); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // CPP.g:778:5: callExpr
                    {
                    pushFollow(FOLLOW_callExpr_in_exprPrimary3551);
                    callExpr();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // CPP.g:779:5: arrayElement
                    {
                    pushFollow(FOLLOW_arrayElement_in_exprPrimary3558);
                    arrayElement();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exprPrimary"


    // $ANTLR start "callExpr"
    // CPP.g:782:1: callExpr : id= IDENTIFIER '(' exprList ')' ;
    public final void callExpr() throws RecognitionException {
        Token id=null;


          AST ast1=null;

        try {
            // CPP.g:786:3: (id= IDENTIFIER '(' exprList ')' )
            // CPP.g:786:5: id= IDENTIFIER '(' exprList ')'
            {
            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_callExpr3579); if (state.failed) return ;
            match(input,42,FOLLOW_42_in_callExpr3586); if (state.failed) return ;
            pushFollow(FOLLOW_exprList_in_callExpr3593);
            exprList();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,43,FOLLOW_43_in_callExpr3602); if (state.failed) return ;
            if ( state.backtracking==0 ) {
              out = new CallExprAST((Token)id, (ExprListAST)ast1);
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "callExpr"


    // $ANTLR start "arrayElement"
    // CPP.g:793:1: arrayElement : id= IDENTIFIER '[' expr ']' index ;
    public final void arrayElement() throws RecognitionException {
        Token id=null;


          AST ast1=null,ast2=null,ast3=null;

        try {
            // CPP.g:797:3: (id= IDENTIFIER '[' expr ']' index )
            // CPP.g:797:5: id= IDENTIFIER '[' expr ']' index
            {
            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_arrayElement3629); if (state.failed) return ;
            match(input,25,FOLLOW_25_in_arrayElement3636); if (state.failed) return ;
            pushFollow(FOLLOW_expr_in_arrayElement3643);
            expr();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast1 = out; out = null;
            }
            match(input,26,FOLLOW_26_in_arrayElement3651); if (state.failed) return ;
            pushFollow(FOLLOW_index_in_arrayElement3658);
            index();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
              ast2 = out; out = null;
            }
            if ( state.backtracking==0 ) {

                    ast3 = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);
                    out = new EleExprAST((Token)id, (ExprListAST)ast3);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayElement"


    // $ANTLR start "index"
    // CPP.g:808:1: index : ( '[' expr ']' index | );
    public final void index() throws RecognitionException {

          AST ast1=null,ast2=null;

        try {
            // CPP.g:812:3: ( '[' expr ']' index | )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==25) ) {
                alt52=1;
            }
            else if ( (LA52_0==EOF||(LA52_0>=23 && LA52_0<=24)||(LA52_0>=26 && LA52_0<=27)||(LA52_0>=29 && LA52_0<=30)||(LA52_0>=43 && LA52_0<=44)||LA52_0==52||(LA52_0>=57 && LA52_0<=85)) ) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // CPP.g:812:5: '[' expr ']' index
                    {
                    match(input,25,FOLLOW_25_in_index3685); if (state.failed) return ;
                    pushFollow(FOLLOW_expr_in_index3692);
                    expr();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast1 = out; out = null;
                    }
                    match(input,26,FOLLOW_26_in_index3700); if (state.failed) return ;
                    pushFollow(FOLLOW_index_in_index3707);
                    index();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      ast2 = out; out = null;
                    }
                    if ( state.backtracking==0 ) {
                      out = new ExprListAST((ExprAST)ast1, (ExprListAST)ast2);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:817:5: 
                    {
                    if ( state.backtracking==0 ) {
                      out = new EmptyExprListAST();
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "index"


    // $ANTLR start "literal"
    // CPP.g:820:1: literal : (val= CHARACTER_LITERAL | val= STRING_LITERAL | val= HEX_LITERAL | val= DECIMAL_LITERAL | val= OCTAL_LITERAL | val= FLOATING_POINT_LITERAL );
    public final void literal() throws RecognitionException {
        Token val=null;

        try {
            // CPP.g:821:3: (val= CHARACTER_LITERAL | val= STRING_LITERAL | val= HEX_LITERAL | val= DECIMAL_LITERAL | val= OCTAL_LITERAL | val= FLOATING_POINT_LITERAL )
            int alt53=6;
            switch ( input.LA(1) ) {
            case CHARACTER_LITERAL:
                {
                alt53=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt53=2;
                }
                break;
            case HEX_LITERAL:
                {
                alt53=3;
                }
                break;
            case DECIMAL_LITERAL:
                {
                alt53=4;
                }
                break;
            case OCTAL_LITERAL:
                {
                alt53=5;
                }
                break;
            case FLOATING_POINT_LITERAL:
                {
                alt53=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }

            switch (alt53) {
                case 1 :
                    // CPP.g:821:5: val= CHARACTER_LITERAL
                    {
                    val=(Token)match(input,CHARACTER_LITERAL,FOLLOW_CHARACTER_LITERAL_in_literal3738); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new CharLiteralAST((Token)val);
                    }

                    }
                    break;
                case 2 :
                    // CPP.g:822:5: val= STRING_LITERAL
                    {
                    val=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_literal3748); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new StringLiteralAST((Token)val);
                    }

                    }
                    break;
                case 3 :
                    // CPP.g:823:5: val= HEX_LITERAL
                    {
                    val=(Token)match(input,HEX_LITERAL,FOLLOW_HEX_LITERAL_in_literal3758); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new IntLiteralAST((Token)val);
                    }

                    }
                    break;
                case 4 :
                    // CPP.g:824:5: val= DECIMAL_LITERAL
                    {
                    val=(Token)match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_literal3768); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new IntLiteralAST((Token)val);
                    }

                    }
                    break;
                case 5 :
                    // CPP.g:825:5: val= OCTAL_LITERAL
                    {
                    val=(Token)match(input,OCTAL_LITERAL,FOLLOW_OCTAL_LITERAL_in_literal3778); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new IntLiteralAST((Token)val);
                    }

                    }
                    break;
                case 6 :
                    // CPP.g:826:5: val= FLOATING_POINT_LITERAL
                    {
                    val=(Token)match(input,FLOATING_POINT_LITERAL,FOLLOW_FLOATING_POINT_LITERAL_in_literal3788); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                      out = new FloatLiteralAST((Token)val);
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "literal"

    // $ANTLR start synpred2_CPP
    public final void synpred2_CPP_fragment() throws RecognitionException {   
        // CPP.g:59:5: ( varDecl )
        // CPP.g:59:5: varDecl
        {
        pushFollow(FOLLOW_varDecl_in_synpred2_CPP121);
        varDecl();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_CPP

    // $ANTLR start synpred14_CPP
    public final void synpred14_CPP_fragment() throws RecognitionException {   
        // CPP.g:191:5: ( combinedType )
        // CPP.g:191:5: combinedType
        {
        pushFollow(FOLLOW_combinedType_in_synpred14_CPP692);
        combinedType();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_CPP

    // $ANTLR start synpred30_CPP
    public final void synpred30_CPP_fragment() throws RecognitionException {   
        // CPP.g:300:5: ( stmt stmtList )
        // CPP.g:300:5: stmt stmtList
        {
        pushFollow(FOLLOW_stmt_in_synpred30_CPP1163);
        stmt();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_stmtList_in_synpred30_CPP1171);
        stmtList();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred30_CPP

    // $ANTLR start synpred43_CPP
    public final void synpred43_CPP_fragment() throws RecognitionException {   
        // CPP.g:351:5: ( 'else' stmt )
        // CPP.g:351:5: 'else' stmt
        {
        match(input,46,FOLLOW_46_in_synpred43_CPP1411); if (state.failed) return ;
        pushFollow(FOLLOW_stmt_in_synpred43_CPP1418);
        stmt();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred43_CPP

    // $ANTLR start synpred44_CPP
    public final void synpred44_CPP_fragment() throws RecognitionException {   
        // CPP.g:367:5: ( forInit )
        // CPP.g:367:5: forInit
        {
        pushFollow(FOLLOW_forInit_in_synpred44_CPP1461);
        forInit();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_CPP

    // $ANTLR start synpred46_CPP
    public final void synpred46_CPP_fragment() throws RecognitionException {   
        // CPP.g:370:5: ( exprList )
        // CPP.g:370:5: exprList
        {
        pushFollow(FOLLOW_exprList_in_synpred46_CPP1486);
        exprList();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred46_CPP

    // $ANTLR start synpred48_CPP
    public final void synpred48_CPP_fragment() throws RecognitionException {   
        // CPP.g:381:5: ( exprList ';' )
        // CPP.g:381:5: exprList ';'
        {
        pushFollow(FOLLOW_exprList_in_synpred48_CPP1537);
        exprList();

        state._fsp--;
        if (state.failed) return ;
        match(input,23,FOLLOW_23_in_synpred48_CPP1539); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_CPP

    // $ANTLR start synpred62_CPP
    public final void synpred62_CPP_fragment() throws RecognitionException {   
        Token op=null;

        // CPP.g:512:5: ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN )
        // CPP.g:512:5: exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN
        {
        pushFollow(FOLLOW_exprLOGICAL_OR_in_synpred62_CPP2089);
        exprLOGICAL_OR();

        state._fsp--;
        if (state.failed) return ;
        // CPP.g:513:5: (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' )
        int alt55=11;
        switch ( input.LA(1) ) {
        case 27:
            {
            alt55=1;
            }
            break;
        case 57:
            {
            alt55=2;
            }
            break;
        case 58:
            {
            alt55=3;
            }
            break;
        case 59:
            {
            alt55=4;
            }
            break;
        case 60:
            {
            alt55=5;
            }
            break;
        case 61:
            {
            alt55=6;
            }
            break;
        case 62:
            {
            alt55=7;
            }
            break;
        case 63:
            {
            alt55=8;
            }
            break;
        case 64:
            {
            alt55=9;
            }
            break;
        case 65:
            {
            alt55=10;
            }
            break;
        case 66:
            {
            alt55=11;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 55, 0, input);

            throw nvae;
        }

        switch (alt55) {
            case 1 :
                // CPP.g:513:7: op= '='
                {
                op=(Token)match(input,27,FOLLOW_27_in_synpred62_CPP2101); if (state.failed) return ;

                }
                break;
            case 2 :
                // CPP.g:514:7: op= '+='
                {
                op=(Token)match(input,57,FOLLOW_57_in_synpred62_CPP2115); if (state.failed) return ;

                }
                break;
            case 3 :
                // CPP.g:515:7: op= '-='
                {
                op=(Token)match(input,58,FOLLOW_58_in_synpred62_CPP2128); if (state.failed) return ;

                }
                break;
            case 4 :
                // CPP.g:516:7: op= '*='
                {
                op=(Token)match(input,59,FOLLOW_59_in_synpred62_CPP2141); if (state.failed) return ;

                }
                break;
            case 5 :
                // CPP.g:517:7: op= '/='
                {
                op=(Token)match(input,60,FOLLOW_60_in_synpred62_CPP2154); if (state.failed) return ;

                }
                break;
            case 6 :
                // CPP.g:518:7: op= '%='
                {
                op=(Token)match(input,61,FOLLOW_61_in_synpred62_CPP2167); if (state.failed) return ;

                }
                break;
            case 7 :
                // CPP.g:519:7: op= '<<='
                {
                op=(Token)match(input,62,FOLLOW_62_in_synpred62_CPP2180); if (state.failed) return ;

                }
                break;
            case 8 :
                // CPP.g:520:7: op= '>>='
                {
                op=(Token)match(input,63,FOLLOW_63_in_synpred62_CPP2192); if (state.failed) return ;

                }
                break;
            case 9 :
                // CPP.g:521:7: op= '&='
                {
                op=(Token)match(input,64,FOLLOW_64_in_synpred62_CPP2204); if (state.failed) return ;

                }
                break;
            case 10 :
                // CPP.g:522:7: op= '^='
                {
                op=(Token)match(input,65,FOLLOW_65_in_synpred62_CPP2217); if (state.failed) return ;

                }
                break;
            case 11 :
                // CPP.g:523:7: op= '|='
                {
                op=(Token)match(input,66,FOLLOW_66_in_synpred62_CPP2230); if (state.failed) return ;

                }
                break;

        }

        pushFollow(FOLLOW_exprASSIGN_in_synpred62_CPP2245);
        exprASSIGN();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred62_CPP

    // Delegated rules

    public final boolean synpred48_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred48_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred62_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred62_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred46_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred46_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred43_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred44_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred44_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_CPP() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_CPP_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA19 dfa19 = new DFA19(this);
    protected DFA22 dfa22 = new DFA22(this);
    protected DFA30 dfa30 = new DFA30(this);
    static final String DFA2_eotS =
        "\17\uffff";
    static final String DFA2_eofS =
        "\17\uffff";
    static final String DFA2_minS =
        "\1\26\13\0\3\uffff";
    static final String DFA2_maxS =
        "\1\51\13\0\3\uffff";
    static final String DFA2_acceptS =
        "\14\uffff\1\2\1\1\1\3";
    static final String DFA2_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\3\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\14\10\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1"+
            "\13",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "58:1: declaration : ( varDecl | directive | funcDecl );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_1 = input.LA(1);

                         
                        int index2_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA2_2 = input.LA(1);

                         
                        int index2_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA2_3 = input.LA(1);

                         
                        int index2_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA2_4 = input.LA(1);

                         
                        int index2_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA2_5 = input.LA(1);

                         
                        int index2_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA2_6 = input.LA(1);

                         
                        int index2_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA2_7 = input.LA(1);

                         
                        int index2_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA2_8 = input.LA(1);

                         
                        int index2_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA2_9 = input.LA(1);

                         
                        int index2_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA2_10 = input.LA(1);

                         
                        int index2_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA2_11 = input.LA(1);

                         
                        int index2_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CPP()) ) {s = 13;}

                        else if ( (true) ) {s = 14;}

                         
                        input.seek(index2_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA12_eotS =
        "\16\uffff";
    static final String DFA12_eofS =
        "\16\uffff";
    static final String DFA12_minS =
        "\1\37\13\0\2\uffff";
    static final String DFA12_maxS =
        "\1\51\13\0\2\uffff";
    static final String DFA12_acceptS =
        "\14\uffff\1\1\1\2";
    static final String DFA12_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\2\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "190:1: type : ( combinedType | pointerType );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA12_1 = input.LA(1);

                         
                        int index12_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA12_2 = input.LA(1);

                         
                        int index12_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA12_3 = input.LA(1);

                         
                        int index12_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA12_4 = input.LA(1);

                         
                        int index12_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA12_5 = input.LA(1);

                         
                        int index12_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA12_6 = input.LA(1);

                         
                        int index12_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA12_7 = input.LA(1);

                         
                        int index12_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA12_8 = input.LA(1);

                         
                        int index12_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA12_9 = input.LA(1);

                         
                        int index12_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA12_10 = input.LA(1);

                         
                        int index12_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA12_11 = input.LA(1);

                         
                        int index12_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_CPP()) ) {s = 12;}

                        else if ( (true) ) {s = 13;}

                         
                        input.seek(index12_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA14_eotS =
        "\16\uffff";
    static final String DFA14_eofS =
        "\1\uffff\13\14\2\uffff";
    static final String DFA14_minS =
        "\1\37\13\4\2\uffff";
    static final String DFA14_maxS =
        "\1\51\13\54\2\uffff";
    static final String DFA14_acceptS =
        "\14\uffff\1\1\1\2";
    static final String DFA14_specialS =
        "\16\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "\1\14\31\uffff\1\14\13\15\2\uffff\1\14",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "214:1: combinedType : ( primitiveType | primitiveType combinedType );";
        }
    }
    static final String DFA19_eotS =
        "\53\uffff";
    static final String DFA19_eofS =
        "\1\47\52\uffff";
    static final String DFA19_minS =
        "\1\4\46\0\4\uffff";
    static final String DFA19_maxS =
        "\1\127\46\0\4\uffff";
    static final String DFA19_acceptS =
        "\47\uffff\1\2\2\uffff\1\1";
    static final String DFA19_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
        "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45"+
        "\4\uffff}>";
    static final String[] DFA19_transitionS = {
            "\1\20\1\12\1\13\1\14\1\15\1\16\1\17\21\uffff\1\1\1\47\1\6\1"+
            "\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\21\1"+
            "\uffff\1\7\1\22\1\47\1\23\1\24\1\25\1\31\1\32\1\uffff\1\33\1"+
            "\26\1\27\1\30\27\uffff\1\2\1\3\2\uffff\1\4\1\5\1\10\1\11",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "296:1: stmtList : ( stmt stmtList | );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_1 = input.LA(1);

                         
                        int index19_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA19_2 = input.LA(1);

                         
                        int index19_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA19_3 = input.LA(1);

                         
                        int index19_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA19_4 = input.LA(1);

                         
                        int index19_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA19_5 = input.LA(1);

                         
                        int index19_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA19_6 = input.LA(1);

                         
                        int index19_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA19_7 = input.LA(1);

                         
                        int index19_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA19_8 = input.LA(1);

                         
                        int index19_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA19_9 = input.LA(1);

                         
                        int index19_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA19_10 = input.LA(1);

                         
                        int index19_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA19_11 = input.LA(1);

                         
                        int index19_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA19_12 = input.LA(1);

                         
                        int index19_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA19_13 = input.LA(1);

                         
                        int index19_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA19_14 = input.LA(1);

                         
                        int index19_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA19_15 = input.LA(1);

                         
                        int index19_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA19_16 = input.LA(1);

                         
                        int index19_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA19_17 = input.LA(1);

                         
                        int index19_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_17);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA19_18 = input.LA(1);

                         
                        int index19_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_18);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA19_19 = input.LA(1);

                         
                        int index19_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_19);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA19_20 = input.LA(1);

                         
                        int index19_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_20);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA19_21 = input.LA(1);

                         
                        int index19_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_21);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA19_22 = input.LA(1);

                         
                        int index19_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_22);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA19_23 = input.LA(1);

                         
                        int index19_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_23);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA19_24 = input.LA(1);

                         
                        int index19_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_24);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA19_25 = input.LA(1);

                         
                        int index19_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_25);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA19_26 = input.LA(1);

                         
                        int index19_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_26);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA19_27 = input.LA(1);

                         
                        int index19_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_27);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA19_28 = input.LA(1);

                         
                        int index19_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_28);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA19_29 = input.LA(1);

                         
                        int index19_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_29);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA19_30 = input.LA(1);

                         
                        int index19_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_30);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA19_31 = input.LA(1);

                         
                        int index19_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_31);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA19_32 = input.LA(1);

                         
                        int index19_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_32);
                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA19_33 = input.LA(1);

                         
                        int index19_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_33);
                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA19_34 = input.LA(1);

                         
                        int index19_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_34);
                        if ( s>=0 ) return s;
                        break;
                    case 34 : 
                        int LA19_35 = input.LA(1);

                         
                        int index19_35 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_35);
                        if ( s>=0 ) return s;
                        break;
                    case 35 : 
                        int LA19_36 = input.LA(1);

                         
                        int index19_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_36);
                        if ( s>=0 ) return s;
                        break;
                    case 36 : 
                        int LA19_37 = input.LA(1);

                         
                        int index19_37 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_37);
                        if ( s>=0 ) return s;
                        break;
                    case 37 : 
                        int LA19_38 = input.LA(1);

                         
                        int index19_38 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_CPP()) ) {s = 42;}

                        else if ( (true) ) {s = 39;}

                         
                        input.seek(index19_38);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 19, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA22_eotS =
        "\36\uffff";
    static final String DFA22_eofS =
        "\36\uffff";
    static final String DFA22_minS =
        "\1\4\13\uffff\21\0\1\uffff";
    static final String DFA22_maxS =
        "\1\127\13\uffff\21\0\1\uffff";
    static final String DFA22_acceptS =
        "\1\uffff\1\1\33\uffff\1\2";
    static final String DFA22_specialS =
        "\14\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\1\16\1\17\1\20\1\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\32\1\24\1\25\1\26\1\27\1\30\1\31\14\uffff\1\34\6\uffff\1"+
            "\20\13\1\1\33\1\uffff\1\21\43\uffff\1\14\1\15\2\uffff\1\16\1"+
            "\17\1\22\1\23",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "367:5: ( forInit )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA22_12 = input.LA(1);

                         
                        int index22_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_12);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA22_13 = input.LA(1);

                         
                        int index22_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_13);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA22_14 = input.LA(1);

                         
                        int index22_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_14);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA22_15 = input.LA(1);

                         
                        int index22_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_15);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA22_16 = input.LA(1);

                         
                        int index22_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_16);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA22_17 = input.LA(1);

                         
                        int index22_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_17);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA22_18 = input.LA(1);

                         
                        int index22_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_18);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA22_19 = input.LA(1);

                         
                        int index22_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_19);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA22_20 = input.LA(1);

                         
                        int index22_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_20);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA22_21 = input.LA(1);

                         
                        int index22_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_21);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA22_22 = input.LA(1);

                         
                        int index22_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_22);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA22_23 = input.LA(1);

                         
                        int index22_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_23);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA22_24 = input.LA(1);

                         
                        int index22_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_24);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA22_25 = input.LA(1);

                         
                        int index22_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_25);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA22_26 = input.LA(1);

                         
                        int index22_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_26);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA22_27 = input.LA(1);

                         
                        int index22_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_27);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA22_28 = input.LA(1);

                         
                        int index22_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_CPP()) ) {s = 1;}

                        else if ( (true) ) {s = 29;}

                         
                        input.seek(index22_28);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 22, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA30_eotS =
        "\23\uffff";
    static final String DFA30_eofS =
        "\23\uffff";
    static final String DFA30_minS =
        "\1\4\20\0\2\uffff";
    static final String DFA30_maxS =
        "\1\127\20\0\2\uffff";
    static final String DFA30_acceptS =
        "\21\uffff\1\1\1\2";
    static final String DFA30_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\1\16\1\17\2\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\17\1\11\1\12\1\13\1\14\1\15\1\16\23\uffff\1\5\13\uffff\1"+
            "\20\1\uffff\1\6\43\uffff\1\1\1\2\2\uffff\1\3\1\4\1\7\1\10",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "507:1: exprASSIGN : ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN | exprTERNARY );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_1 = input.LA(1);

                         
                        int index30_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA30_2 = input.LA(1);

                         
                        int index30_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA30_3 = input.LA(1);

                         
                        int index30_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA30_4 = input.LA(1);

                         
                        int index30_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA30_5 = input.LA(1);

                         
                        int index30_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA30_6 = input.LA(1);

                         
                        int index30_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA30_7 = input.LA(1);

                         
                        int index30_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA30_8 = input.LA(1);

                         
                        int index30_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA30_9 = input.LA(1);

                         
                        int index30_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA30_10 = input.LA(1);

                         
                        int index30_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA30_11 = input.LA(1);

                         
                        int index30_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA30_12 = input.LA(1);

                         
                        int index30_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA30_13 = input.LA(1);

                         
                        int index30_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA30_14 = input.LA(1);

                         
                        int index30_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA30_15 = input.LA(1);

                         
                        int index30_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA30_16 = input.LA(1);

                         
                        int index30_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred62_CPP()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index30_16);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 30, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_declarationList_in_program59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarationList81 = new BitSet(new long[]{0x000003FF80400000L});
    public static final BitSet FOLLOW_declarationList_in_declarationList95 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDecl_in_declaration121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directive_in_declaration127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcDecl_in_declaration133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_directive152 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_directive156 = new BitSet(new long[]{0x00000000000007F0L});
    public static final BitSet FOLLOW_pp_token_in_directive158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_pp_token177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_pp_token185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDecl201 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_varList_in_varDecl209 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_varDecl211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_varList230 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_varListRest_in_varList238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_varListRest265 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_var_in_varListRest267 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_varListRest_in_varListRest275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_var316 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_arrayDecl_in_var323 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_initializer_in_var337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_arrayDecl367 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_arrayDecl369 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_arrayDecl371 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_arrayDecl381 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_arrayDecl383 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_arrayDecl_in_arrayDecl393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_initializer427 = new BitSet(new long[]{0x00001400500007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_varInit_in_initializer433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayInit_in_initializer439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayInitList_in_initializer445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_arrayInitList468 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_arrayInit_in_arrayInitList475 = new BitSet(new long[]{0x0000000021000000L});
    public static final BitSet FOLLOW_arrayInitListEx_in_arrayInitList483 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_arrayInitList491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_arrayInitListEx516 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_arrayInit_in_arrayInitListEx522 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_arrayInitListEx_in_arrayInitListEx530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_arrayInit563 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_varInit_in_arrayInit570 = new BitSet(new long[]{0x0000000021000000L});
    public static final BitSet FOLLOW_arrayInitEx_in_arrayInit578 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_arrayInit586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_arrayInit598 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_arrayInit600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_arrayInitEx621 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_varInit_in_arrayInitEx628 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_arrayInitEx_in_arrayInitEx636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_varInit669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_combinedType_in_type692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointerType_in_type698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_combinedType_in_pointerType717 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_pointerTypeRest_in_pointerType725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_pointerTypeRest750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_pointerTypeRest764 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_pointerTypeRest_in_pointerTypeRest772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_combinedType791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_combinedType799 = new BitSet(new long[]{0x000003FF80000000L});
    public static final BitSet FOLLOW_combinedType_in_combinedType807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_primitiveType824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_primitiveType832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_primitiveType840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_primitiveType848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_primitiveType856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_primitiveType864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_primitiveType872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_primitiveType880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_primitiveType888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_primitiveType896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_primitiveType904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_funcDecl923 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_funcDecl933 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_funcDecl940 = new BitSet(new long[]{0x00000BFF80000000L});
    public static final BitSet FOLLOW_paraList_in_funcDecl947 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_funcDecl955 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_blockStmt_in_funcDecl962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_para_in_paraList989 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_paraListRest_in_paraList997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_para1030 = new BitSet(new long[]{0x0000100000000010L});
    public static final BitSet FOLLOW_44_in_para1041 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENTIFIER_in_para1051 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_arrayDecl_in_para1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_paraListRest1081 = new BitSet(new long[]{0x000003FF80000000L});
    public static final BitSet FOLLOW_para_in_paraListRest1083 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_paraListRest_in_paraListRest1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_blockStmt1124 = new BitSet(new long[]{0x01EFB7FFF00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmtList_in_blockStmt1130 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_blockStmt1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stmt_in_stmtList1163 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmtList_in_stmtList1171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockStmt_in_stmt1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprStmt_in_stmt1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStmt_in_stmt1214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStmt_in_stmt1221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStmt_in_stmt1228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doStmt_in_stmt1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_breakStmt_in_stmt1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continueStmt_in_stmt1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStmt_in_stmt1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchStmt_in_stmt1262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_caseStmt_in_stmt1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defaultStmt_in_stmt1274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationStmt_in_stmt1280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDecl_in_declarationStmt1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprStmt1328 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_exprStmt1336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ifStmt1361 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ifStmt1368 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_ifStmt1375 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_ifStmt1383 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmt_in_ifStmt1390 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_46_in_ifStmt1411 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmt_in_ifStmt1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_forStmt1452 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_forStmt1454 = new BitSet(new long[]{0x000017FFC08007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_forInit_in_forStmt1461 = new BitSet(new long[]{0x00001400408007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_forStmt1470 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_forStmt1479 = new BitSet(new long[]{0x00001C00400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprList_in_forStmt1486 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_forStmt1495 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmt_in_forStmt1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDecl_in_forInit1529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprList_in_forInit1537 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_forInit1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_forInit1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_whileStmt1564 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_whileStmt1571 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_whileStmt1578 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_whileStmt1586 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmt_in_whileStmt1593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_doStmt1620 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmt_in_doStmt1627 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_doStmt1635 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_doStmt1642 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_doStmt1649 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_doStmt1657 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_doStmt1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_switchStmt1689 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_switchStmt1695 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_switchStmt1701 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_switchStmt1709 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmt_in_switchStmt1715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_caseStmt1742 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_caseStmt1748 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_caseStmt1756 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmtList_in_caseStmt1762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_defaultStmt1789 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_defaultStmt1795 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmtList_in_defaultStmt1801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_breakStmt1831 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_breakStmt1833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_continueStmt1856 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_continueStmt1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_returnStmt1883 = new BitSet(new long[]{0x00001400408007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_returnStmt1891 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_returnStmt1902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_callStmt1929 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_callStmt1936 = new BitSet(new long[]{0x00001C00400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprList_in_callStmt1943 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_callStmt1951 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_callStmt1958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprList1983 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_exprListRest_in_exprList1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_exprListRest2025 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_exprListRest2032 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_exprListRest_in_exprListRest2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprASSIGN_in_expr2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprLOGICAL_OR_in_exprASSIGN2089 = new BitSet(new long[]{0xFE00000008000000L,0x0000000000000007L});
    public static final BitSet FOLLOW_27_in_exprASSIGN2101 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_57_in_exprASSIGN2115 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_58_in_exprASSIGN2128 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_59_in_exprASSIGN2141 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_60_in_exprASSIGN2154 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_61_in_exprASSIGN2167 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_62_in_exprASSIGN2180 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_63_in_exprASSIGN2192 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_64_in_exprASSIGN2204 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_65_in_exprASSIGN2217 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_66_in_exprASSIGN2230 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprASSIGN_in_exprASSIGN2245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprTERNARY_in_exprASSIGN2259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprLOGICAL_OR_in_exprTERNARY2274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_exprTERNARYEx_in_exprTERNARY2282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_exprTERNARYEx2307 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprASSIGN_in_exprTERNARYEx2314 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_exprTERNARYEx2322 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprASSIGN_in_exprTERNARYEx2329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OR2354 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OR2362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_exprLOGICAL_OREx2389 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OREx2396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OREx2410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprBITWISE_OR_in_exprLOGICAL_AND2431 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_AND2439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_exprLOGICAL_ANDEx2466 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprBITWISE_OR_in_exprLOGICAL_ANDEx2473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_ANDEx2487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OR2508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OR2516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_exprBITWISE_OREx2543 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OREx2550 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OREx2564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOR2585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOR2593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_exprBITWISE_XOREx2620 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOREx2627 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOREx2641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprEQUALITY_in_exprBITWISE_AND2662 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_AND2670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_exprBITWISE_ANDEx2697 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprEQUALITY_in_exprBITWISE_ANDEx2704 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_ANDEx2718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprRELATION_in_exprEQUALITY2739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_exprEQUALITYEx_in_exprEQUALITY2747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_exprEQUALITYEx2776 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_73_in_exprEQUALITYEx2789 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprRELATION_in_exprEQUALITYEx2801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000300L});
    public static final BitSet FOLLOW_exprEQUALITYEx_in_exprEQUALITYEx2815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprSHIFT_in_exprRELATION2836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003C00L});
    public static final BitSet FOLLOW_exprRELATIONEx_in_exprRELATION2844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_exprRELATIONEx2873 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_75_in_exprRELATIONEx2887 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_76_in_exprRELATIONEx2900 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_77_in_exprRELATIONEx2914 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprSHIFT_in_exprRELATIONEx2930 = new BitSet(new long[]{0x0000000000000000L,0x0000000000003C00L});
    public static final BitSet FOLLOW_exprRELATIONEx_in_exprRELATIONEx2944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprADD_in_exprSHIFT2965 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C000L});
    public static final BitSet FOLLOW_exprSHIFTEx_in_exprSHIFT2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_exprSHIFTEx3002 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_79_in_exprSHIFTEx3014 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprADD_in_exprSHIFTEx3029 = new BitSet(new long[]{0x0000000000000000L,0x000000000000C000L});
    public static final BitSet FOLLOW_exprSHIFTEx_in_exprSHIFTEx3043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprMUL_in_exprADD3064 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_exprADDEx_in_exprADD3072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_exprADDEx3101 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_81_in_exprADDEx3115 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprMUL_in_exprADDEx3132 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_exprADDEx_in_exprADDEx3146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprUNARY_in_exprMUL3165 = new BitSet(new long[]{0x0000000040000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_exprMULEx_in_exprMUL3173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_exprMULEx3200 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_82_in_exprMULEx3214 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_83_in_exprMULEx3228 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprUNARY_in_exprMULEx3245 = new BitSet(new long[]{0x0000000040000000L,0x00000000000C0000L});
    public static final BitSet FOLLOW_exprMULEx_in_exprMULEx3259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_exprUNARY3288 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_81_in_exprUNARY3302 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_84_in_exprUNARY3316 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_85_in_exprUNARY3329 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_30_in_exprUNARY3342 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_44_in_exprUNARY3356 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_86_in_exprUNARY3370 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_87_in_exprUNARY3384 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprUNARY_in_exprUNARY3400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPostfix_in_exprUNARY3415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprPrimary_in_exprPostfix3430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000300000L});
    public static final BitSet FOLLOW_exprPostfixEx_in_exprPostfix3438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_exprPostfixEx3467 = new BitSet(new long[]{0x0000000000000000L,0x0000000000300000L});
    public static final BitSet FOLLOW_85_in_exprPostfixEx3480 = new BitSet(new long[]{0x0000000000000000L,0x0000000000300000L});
    public static final BitSet FOLLOW_exprPostfixEx_in_exprPostfixEx3502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_exprPrimary3523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_exprPrimary3532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_exprPrimary3540 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_exprPrimary3542 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_exprPrimary3544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_callExpr_in_exprPrimary3551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayElement_in_exprPrimary3558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_callExpr3579 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_callExpr3586 = new BitSet(new long[]{0x00001C00400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprList_in_callExpr3593 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_callExpr3602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_arrayElement3629 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_arrayElement3636 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_arrayElement3643 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_arrayElement3651 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_index_in_arrayElement3658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_index3685 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_expr_in_index3692 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_index3700 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_index_in_index3707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_LITERAL_in_literal3738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_literal3748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_LITERAL_in_literal3758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_literal3768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OCTAL_LITERAL_in_literal3778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATING_POINT_LITERAL_in_literal3788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varDecl_in_synpred2_CPP121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_combinedType_in_synpred14_CPP692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stmt_in_synpred30_CPP1163 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmtList_in_synpred30_CPP1171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred43_CPP1411 = new BitSet(new long[]{0x01EFB7FFD00007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_stmt_in_synpred43_CPP1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forInit_in_synpred44_CPP1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprList_in_synpred46_CPP1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprList_in_synpred48_CPP1537 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_synpred48_CPP1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprLOGICAL_OR_in_synpred62_CPP2089 = new BitSet(new long[]{0xFE00000008000000L,0x0000000000000007L});
    public static final BitSet FOLLOW_27_in_synpred62_CPP2101 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_57_in_synpred62_CPP2115 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_58_in_synpred62_CPP2128 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_59_in_synpred62_CPP2141 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_60_in_synpred62_CPP2154 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_61_in_synpred62_CPP2167 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_62_in_synpred62_CPP2180 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_63_in_synpred62_CPP2192 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_64_in_synpred62_CPP2204 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_65_in_synpred62_CPP2217 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_66_in_synpred62_CPP2230 = new BitSet(new long[]{0x00001400400007F0L,0x0000000000F30000L});
    public static final BitSet FOLLOW_exprASSIGN_in_synpred62_CPP2245 = new BitSet(new long[]{0x0000000000000002L});

}