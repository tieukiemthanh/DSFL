/**
*	@author Dr.Nguyen Hua Phung
*	@version 1.0
*	28/6/2006
* 	Exception that may happen when generating code
*
*/
package Transformer.CodeGeneration;
import Transformer.CompilationException;
public class IllegalOperandException extends CompilationException{
	public IllegalOperandException(){}
	public IllegalOperandException(String s){}
	public String getMessage() {
		return "Illegal Operand Exception"; 
	}	

}
