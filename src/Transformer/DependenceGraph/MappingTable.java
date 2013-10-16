package Transformer.DependenceGraph;

import java.util.*;

public class MappingTable
{
	ArrayList<MappingNode> mapTable;
	
	public MappingTable() {
		mapTable = new ArrayList<MappingNode>();
	}
	
	public void addMappingNode(MappingNode n) {
		mapTable.add(n);
	}

	String newline = "\r\n";
	
	public String toString() {
		String result = newline + newline + "Mapping Tables: " + newline + newline;
		for (int i = 0; i < mapTable.size(); i++){
			result += mapTable.get(i).toString() + newline;
		}
		return result;
	}
}
