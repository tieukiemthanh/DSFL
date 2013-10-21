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

	//trinhgiang-21/10/2013
	public int getSize()
	{
		return mapTable.size();
	}
	
	public int getStatementId(int index) 
	{
		return mapTable.get(index).getId();
	}
	
	String newline = "\r\n";
	
	public String toString() {
		//trinhgiang-21/10/2013
		String result = newline + newline + "Mapping Tables: " + newline + newline;
		//String result = "";
		for (int i = 0; i < mapTable.size(); i++){
			result += mapTable.get(i).toString() + newline;
		}
		return result;
	}
}
