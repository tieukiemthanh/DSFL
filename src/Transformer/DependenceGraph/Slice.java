// trinhgiang-30/10/2013
package Transformer.DependenceGraph;

import java.util.ArrayList;

public class Slice
{
	public ArrayList<Integer> listLine; 

	String newline = "\r\n";
	String tab = "\t";

	public Slice() {
		listLine = new ArrayList<Integer>();
	}
	// get size of slice
	public int getSize() {
		return listLine.size();
	}
	// getLine
	public int getLine(int index) {
		return listLine.get(index);
	}
	// add a line into slice
	public void addLine(int nLine) {
		if(!listLine.contains(nLine))
			listLine.add(nLine);
	}
	// union two slice
	public void addSlice(Slice nSlice) {
		for(int i = 0; i < nSlice.getSize(); i++) {
			this.addLine(nSlice.getLine(i));
		}
	}
	// slice to string
	public String toString() {
		String resultString = "";
		for(int i = 0; i < listLine.size(); i++)
		{
			if(i == 0) resultString += listLine.get(i).toString();
			else {
				resultString += ";" + listLine.get(i).toString();
			}
		}
		return resultString;
	}
}
