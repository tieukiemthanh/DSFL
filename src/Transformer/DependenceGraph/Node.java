package Transformer.DependenceGraph;

import java.util.ArrayList;

public class Node
{
    int StatementID;
    TYPE StatementTYPE;
    ArrayList<DataDep> dataDep = null;
    ControlDep conDep = null;
    ArrayList<Node> potDep = null;
	String assignedVar = null;
	
	int indexInPDG = -1;	
	String newline = "\r\n";
	String tab = "\t";
	
	public Node(int id, TYPE type, ArrayList<DataDep> data, ControlDep control,ArrayList<Node> pot) {
		StatementID = id;
		StatementTYPE = type;
		dataDep = data;
		conDep = control;
		potDep = pot;
	}
	
	public int getID() {return StatementID;}
	public ArrayList<DataDep> getDataDep() {return dataDep; }
	public ControlDep getControlDep() { return conDep; }
	public void setIndex(int id) {indexInPDG = id; }
	public void setID(int id) { StatementID = id; }
	public int getIndex() {return indexInPDG;}
	public void setAssignedVar(String var) {assignedVar = var;}

	public String toString() {
		String result = "";
		result += "StatementID = " + StatementID + newline;
		result += "StatementTYPE = " + StatementTYPE + newline;
		if (StatementTYPE == TYPE.ASSIGN && assignedVar != null)
			result += "Assigned Variable = " + assignedVar + newline;
		result += "dataDep = ";
		if (dataDep != null)
			result += newline + toString(dataDep);
		else
			result += "null" + newline;
		result += "conDep = ";
		if (conDep != null)
			result += conDep.toString() + newline;
		else
			result += "null" + newline;
		return result;
	}
	public String toString(ArrayList<DataDep> list) {
		String result = "";
		for (int i = 0; i < list.size(); i++)
			result += tab + list.get(i).toString() + newline;
		return result;
	}
}
