package Transformer.DependenceGraph;

import java.util.ArrayList;

public class DataDep
{
	int lineID;
    String var_depend = null;
    Node node = null;
	
	String newline = "\r\n";
	String tab = "\t";	

	public DataDep(int id, String var) {
		lineID = id;
		var_depend = var;
	}
	
	public void setVarName(String var) {var_depend = var;}
	public String getVarName() {return var_depend;}
	public int getID() {return lineID;}
	public void setNode(Node n) {node = n;}
	public Node getNode() {
		return node;
	}
	
	public String toString() {
		String result = "";
		if (node != null)
			result = "{Node " + node.getIndex() + " (lineID=" + node.getID() + "),     "
					+ var_depend + "}";
		else		
			result = "{Node with lineID = " + lineID + ", " + var_depend + "}";
		return result;
	}
}
