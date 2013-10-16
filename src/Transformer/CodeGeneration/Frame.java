/**
*	@author Dr.Nguyen Hua Phung
*	@version 1.0
*	28/6/2006
* 	This class provides facilities for method generation
*
*/
package Transformer.CodeGeneration;

import java.util.Stack;
import Transformer.CompilationException;

public class Frame {
	private int currentLabel = 1;
	private int currOpStackSize = 0;
	private int maxOpStackSize = 0;
	private int currIndex = 0;
	private int maxIndex = 0;
	private final Stack<Integer> startLabel = new Stack<Integer>();
	private final Stack<Integer> endLabel = new Stack<Integer>();
	private final Stack<Integer> indexLocal = new Stack<Integer>();
	private final Stack<Integer> conLabel = new Stack<Integer>();
	private final Stack<Integer> brkLabel = new Stack<Integer>();
	private final Stack<Integer> jumpLabel = new Stack<Integer>();

	public int getCurrIndex() {
		return currIndex;
	}

	public void setCurrIndex(int index) {
		currIndex = index;
	}
	
	/**
	*   return a new label in the method.
	*   @return an integer representing the label.
	*/
	public int getNewLabel() 
	{
		int tmp = currentLabel;
		currentLabel++;
		return tmp;
	}
	/**
	*	simulate an instruction that pushes a value onto operand stack.
	*/
	public void push() 
	{
		currOpStackSize++;
		if (maxOpStackSize < currOpStackSize)
			maxOpStackSize = currOpStackSize;
		return;
	}
	/**
	*	simulate an instruction that pops a value out of operand stack.
	*/
	public void pop() throws CompilationException 
	{
		currOpStackSize--;
		if (currOpStackSize < 0)
			throw new IllegalRuntimeException();
	}
	/**
	*	return the maximum size of the operand stack that the method needs to use.
	*	@return an integer that represent the maximum stack size
	*/
	public int getMaxOpStackSize() 
	{
		return maxOpStackSize;
	}
	/** 
	*	check if the operand stack is empty or not.
	*	@throws IllegalRuntimeException if the operand stack is not empty.
	*/
	public void checkOpStack() throws CompilationException 
	{
		if ( currOpStackSize != 0)
			throw new IllegalRuntimeException();
	}
	/**
	*	invoked when parsing into a new scope inside a method.<p>
	*	This method will create 2 new labels that represent the starting and ending points of the scope.<p>
	*	Then, these labels are pushed onto corresponding stacks.<p>
	*	These labels can be retrieved by getStartLabel() and getEndLabel().<p>
	*	In addition, this method also saves the current index of local variable.
	*/
	
	public void enterScope(boolean isProc) 
	{
		int start = getNewLabel();
		int end = getNewLabel();
		startLabel.push(start);
		endLabel.push(end);
		indexLocal.push(currIndex);
		if (isProc) {
			maxOpStackSize = 0;
			maxIndex = 0;
		}
	}
	/**
	* 	invoked when parsing out of a scope in a method.<p>
	* 	This method will pop the starting and ending labels of this scope
	*	and restore the current index
	*/
	public void exitScope() throws CompilationException 
	{
		if (startLabel.empty() || endLabel.empty() || indexLocal.empty())
			throw new IllegalRuntimeException();		
		startLabel.pop();
		endLabel.pop();
		currIndex = indexLocal.pop();
	}
	/**
	*	return the starting label of the current scope.
	*	@return an integer representing the starting label
	*/
	public int getStartLabel() throws CompilationException 
	{
		if (startLabel.empty())
			throw new IllegalRuntimeException();
		return startLabel.peek();
	}
	/**
	*	return the ending label of the current scope.
	*	@return an integer representing the ending label
	*/
	
	public int getEndLabel() throws CompilationException 
	{
		if (endLabel.empty())
			throw new IllegalRuntimeException();
		return endLabel.peek();
	}
	/**
	*	return a new index for a local variable declared in a scope. 
	*	@return an integer that represents the index of the local variable
	*/
	public int getNewIndex() 
	{
		int tmp = currIndex;
		currIndex++;
		if (currIndex > maxIndex)
			maxIndex = currIndex;
		return tmp;
	}
	/**
	*	return the maximum index used in generating code for the current method
	*	@return an integer representing the maximum index
	*/
	public int getMaxIndex()
	 {
		return maxIndex;
	}
	/**
	*	invoked when parsing into a loop statement.<p>
	*	This method creates 2 new labels that represent the starting and ending label of the loop.<p>
	*	These labels are pushed onto corresponding stacks and are retrieved by getBeginLoopLabel() and getEndLoopLabel().
	*/
	public void enterLoop() 
	{
		int con = getNewLabel();
		int brk = getNewLabel();
		int jmp = getNewLabel();
		conLabel.push(con);
		brkLabel.push(brk);
		jumpLabel.push(jmp);
	}
	/**
	*	invoked when parsing out of a loop statement.
	*	This method will take 2 labels representing the starting and ending labels of the current loop out of its stacks.
	*/
	public void exitLoop() throws CompilationException
	 {
		if (conLabel.empty() || brkLabel.empty() || jumpLabel.empty())
			throw new IllegalRuntimeException();
		conLabel.pop();
		brkLabel.pop();
		jumpLabel.pop();
	}
	/**
	*	return the label of the innest enclosing loop to which continue statement would jump
	*	@return an integer representing the continue label
	*/
	public int getContinueLabel() throws CompilationException 
	{
		if (conLabel.empty())
			throw new IllegalRuntimeException();
		return conLabel.peek();
	}
	/**
	*	return the label of the innest enclosing loop to which break statement would jump
	*	@return an integer representing the break label
	*/	
	public int getBreakLabel() throws CompilationException 
	{
		if (brkLabel.empty())
			throw new IllegalRuntimeException();
		return brkLabel.peek();
	}
	
	// dung cho for statement
	public int getjumpLabel() throws CompilationException 
	{
		if (jumpLabel.empty())
			throw new IllegalRuntimeException();
		return jumpLabel.peek();
	}
}

