package Transformer.TestCasesGenerator;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;
import Transformer.DependenceGraph.*;

import java.util.*;
import java.io.*;

public abstract class Generator {

	List<String> paths;

	AST tree;

	PrintWriter writer;

	String testcases;

	public long generate() {
		return 0;
	}

	public void write() {
		Utils.write(writer, testcases);
	}

	public void close() {
		writer.close();
	}

}