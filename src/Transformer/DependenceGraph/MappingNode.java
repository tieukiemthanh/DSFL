package Transformer.DependenceGraph;

import Transformer.ASTs.*;

public class MappingNode
{
    int StatementID;
	OneStmtAST oneStmt;
	
	public MappingNode(int id, OneStmtAST o) {
		StatementID = id;
		oneStmt = o;
	}
	
	public String toString() {
		return StatementID + ": " + oneStmt.getClass();
	}
}
