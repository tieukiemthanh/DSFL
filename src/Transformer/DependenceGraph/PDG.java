package Transformer.DependenceGraph;

import java.util.ArrayList;

public class PDG
{
    public ArrayList<Node> progDepGraph;
	
	String newline = "\r\n";
	String tab = "\t";
	
	public PDG() {
		progDepGraph = new ArrayList<Node>();
	}
	
	public void addNode(Node n) {
		progDepGraph.add(n);
	}
	// potential dependence
	public void updatePD() {
		for(int i = 0; i < progDepGraph.size(); i++) {
			//System.out.println(i);
			this.updatePDNode(i);
		}
	}
	// potential dependence of a node
	public void updatePDNode(int i) {
		Node n = findNodeAtLine(i);
		ArrayList<DataDep> dataDep = n.getDataDep();
		if(dataDep == null) return;
		
		for(int j = 0; j < dataDep.size(); j++) {
			DataDep d = dataDep.get(j); 
			String dVar = d.getVarName();
			int id = d.getID();
			if(id != 0 && id < i) {
				boolean isDefined = false;
				for(int k = id + 1; k < i && !isDefined; k++) {
					Node nTemp = findNodeAtLine(k);
					String nVar = nTemp.getAssignedVar();
					if(nTemp.getType() == TYPE.ASSIGN && nVar != null) {
						if(dVar != null && nVar.equals(dVar)) {
							isDefined = true;
						}
					}
				}
				// add control dependence
				if(!isDefined) {
					ControlDep conDepOfD = d.getNode().getControlDep();
					Node nControl = n.getControlDep().get();
					Node addControl = conDepOfD.get();
					if(addControl != null && addControl.getID() != 0 && (nControl == null || nControl.getID() != addControl.getID()))
						n.addPotDep(addControl);
				}
			}
		}
	}
	public Node findNodeAtLine(int line) {
		for (int i = 0; i < progDepGraph.size(); i++) {
			Node n = progDepGraph.get(i);
			if (n.getID() == line)
				return n;
		}
		return null;
	}
	// trinhgiang-31/10/2013
	// mapping line to statement
	public void mappingLine2Stmt(ArrayList<Integer> mapTable) {
		for(int i = 0; i < progDepGraph.size(); i++) {
			Node n = progDepGraph.get(i);
			int index = mapTable.indexOf(n.getID());
			if(index != -1) {
				n.setID(index);
			}
		}
	}
	public void changeLineIdAtDataDepPointToNode() {
		for (int i = 0; i < progDepGraph.size(); i++){			
			Node nodeInPDG = progDepGraph.get(i);
			
			// luu them index cua node trong PDG
			nodeInPDG.setIndex(i);
			
			ArrayList<DataDep> listData = nodeInPDG.getDataDep();
			if (listData == null) continue;
			for (int j = 0; j < listData.size(); j++){
				DataDep data = listData.get(j);				
				data.setNode(findNodeAtLine(data.getID()));
			}
		}
	}
	
	public String toString() {
		String result = newline + newline + "Program Dependence Graph: " + newline + newline;
		for (int i = 0; i < progDepGraph.size(); i++){
			result += "Node " + i + ":" + newline;
			result += progDepGraph.get(i).toString() + newline;
		}
		return result;
	}
}
