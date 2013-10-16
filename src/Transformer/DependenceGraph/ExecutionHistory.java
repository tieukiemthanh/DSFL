package Transformer.DependenceGraph;

import java.util.ArrayList;

public class ExecutionHistory
{
    ArrayList<ExecNode> EH;
	
	String newline = "\r\n";
	
	public ExecutionHistory() {
		EH = new ArrayList<ExecNode>();
	}
	
	public void addExecNode(ExecNode n) {
		EH.add(n);
	}
	
	public void changeLineIdAtExecNodePointToNode(PDG graph) {
		for (int i = 0; i < EH.size(); i++){
			EH.get(i).execNode = graph.findNodeAtLine(EH.get(i).lineID);
		}
	}
	
	public String toString() {
		String result = newline + newline + "Execution History: " + newline + newline;
		for (int i = 0; i < EH.size(); i++){
			result += "ExecNode at lineID =  " + EH.get(i).lineID + ":" + newline;
			result += EH.get(i).toString() + newline;
			result += newline;
		}
		return result;
	}
}
