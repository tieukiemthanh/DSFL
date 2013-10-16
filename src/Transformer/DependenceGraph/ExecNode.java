package Transformer.DependenceGraph;

public class ExecNode
{
    public Node execNode;
	public int lineID;
    char state;

	public ExecNode(int line, char s) {
		lineID = line;
		state = s;
		execNode = null;
	}
	
	int indexInExecutionHistory = -1;	
	String newline = "\r\n";
	
	public String toString() {
		String result = "";
		if (state == 'T')
			result += "State = Condition_True";
		else if (state == 'F')
			result += "State = Condition_False";
		else if (state == 'N')
			result += "State = Assign_Stmt";
		result += newline;
		if (execNode != null)
			result += execNode.toString();
		else
			result += "LineID = " + lineID;
		return result;
	}
}
