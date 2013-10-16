package Transformer;

import org.antlr.runtime.*;

public class CompilationException extends Exception {
     public Token t;
     public CompilationException() {
	     t = null;
     }
     public CompilationException(Token t) {
	   this.t = t;
     } 
     public Token getToken() {
	     return t;
     }
}


