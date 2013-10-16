package Transformer.DependenceGraph;

public class ControlDep
{
    Node node = null;
    boolean branch;

	public ControlDep() {
		node = null;
	}
	
	public ControlDep(Node n, boolean b) {
		node = n;
		branch = b;
	}

	public ControlDep(ControlDep that) {
		node = that.node;
		branch = that.branch;
	}

	public boolean isEmpty() {return node == null;}
	public void setNode(Node n) {node = n;}
	public void setBranch(boolean b) {branch = b;}
	public void set(Node n, boolean b) {
		node = n;
		branch = b;
	}
	public void set(ControlDep that) {
		node = that.node;
		branch = that.branch;
	}
	
	public String toString() {
		String result = "";
		result =  "{Node " + node.getIndex() + " (lineID=" + node.getID() + "),     " + branch + "}";
		return result;
	}
}
