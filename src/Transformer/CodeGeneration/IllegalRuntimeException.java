/**
*	@author Dr.Nguyen Hua Phung
*	@version 1.0
*	28/6/2006
* 	Exception that may happen when running code
*
*/
package Transformer.CodeGeneration;

import Transformer.CompilationException;

public class IllegalRuntimeException extends CompilationException {
	public IllegalRuntimeException(){}
	public IllegalRuntimeException(String s){}
	public String getMessage() {
		return "Illegal Runtime Exception"; 
	}	
}