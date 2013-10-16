package Transformer.CodeGeneration;
import java.io.*;
import java.util.Stack;

public class Emitter {
	private final String prefixVarName = "m";
	private String switch_str = "";
	private boolean case_on = false;
	private String output = "";
	
	private boolean no_print = false;	
	public boolean print_to_console = true;
	public boolean store_var = false;
	private boolean filter = false;
	private boolean no_print_in_mode_filter = false;
	
	private String mapping = "";
	private String filename = "";	
	private String filterStr = "";
	
	private int[] scopeVariable;
	private String[] nameVariable;
	private int currentIndex = 0;
	
	public Emitter() {
		scopeVariable = new int[50];
		nameVariable = new String[50];
	}
	
	public void  printout(String in) {
		if (no_print)
			return;
				
		if (!case_on) {
			if (print_to_console) System.out.print(in);
			if (filter) filterStr += in;
			if (!no_print_in_mode_filter) output += in;
		}
		else
			switch_str += in;			
	}
	
	public void setCaseOn(boolean on) {
		case_on = on;
	}
	
	public String setFilter(boolean on) {
		String result = null;
		filter = on;
		if (on == false) {
			result = filterStr;
			filterStr = "";
		}
		return result;
	}
	
	public String setFilter(boolean on, boolean no_print) {
		no_print_in_mode_filter = no_print;
		String result = null;
		filter = on;
		if (on == false) {
			result = filterStr;
			filterStr = "";
		}
		return result;
	}
	
	public void setNoPrint(boolean on) {
		no_print = on;
	}
		
	public void setFilename(String name) {
		filename = name;
	}
	
	public String getSwitch() {
		String returnStr = new String(switch_str);
		switch_str = "";
		return returnStr;
	}
	
	public String emitVAR(String varName, int scope) {
		if (store_var) {
			// storing name of variable and its scope to stack
			nameVariable[currentIndex] = new String(varName);
			scopeVariable[currentIndex] = scope;
			currentIndex++;
			return prefixVarName + scope + "_" + varName;
		}
		else {
			int correct_scope = checkScope(varName);
			if (scope == -1)
				return prefixVarName + "_" + varName;
			else
				return prefixVarName + correct_scope + "_" + varName;
		}
	}
	
	public int checkScope(String varName) {
		int i, new_scope = -1;
		for (i = currentIndex-1; i >= 0; i--) {			
			if (nameVariable[i].equals(varName)) {
				new_scope = scopeVariable[i];
				break;
			}
		}
		return new_scope;
	}
	
	public void deleteScope(int scope) {
		int i = currentIndex-1;
		while (i>=0) {
			if (scopeVariable[i] != scope)
				break;
			i--;
		}
		currentIndex = i+1;
	}
	
	public void printToPromela() {
		if (filename.equals(""))
			return;
		try{
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			out.close();
		}catch (Exception e){}
	}
	
	public void printToC() {
		if (filename.equals(""))
			return;
		try{
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			out.close();
		}catch (Exception e){}
	}
}
