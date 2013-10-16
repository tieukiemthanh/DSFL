
package Transformer.CodeGeneration;

import Transformer.ASTs.*;
import org.antlr.runtime.*;
import Transformer.DependenceGraph.*;
import java.util.*;

/***********************************************************************************
Class chua 1 table luu danh sach cac bien duoc gan tai moi dong. Vi du:
Line 1: int x = 2;
Line 2: y = x + 1;
==> Table se luu thong tin: (1, ArrayList(x)), (2, ArrayList(y))
Luu y: 1 dong co the co nhieu bien duoc gan
***********************************************************************************/
class TableAssignedVariable {
	Map<Integer, ArrayList<String>> tableAssignedVar;
	
	// cac bien duoc gan trong nhanh if cua IfElseStmt se duoc luu trong bien nay.
	Map<Integer, ArrayList<String>> backupListVarInIfBranch; 
	boolean isUsingBackupTableFlag = false;
	
	// sau khi duyet het nhanh if cua IfElseStmt, bien backup se duoc push vo Stack.
	Stack<Map<Integer, ArrayList<String>>> backupMultiListInIfBranch;	
	
	public TableAssignedVariable() {
		tableAssignedVar = new HashMap<Integer, ArrayList<String>>();
		backupListVarInIfBranch = new HashMap<Integer, ArrayList<String>>();
		backupMultiListInIfBranch = new Stack<Map<Integer, ArrayList<String>>>();
	}
	// them 1 cap (dong va bien duoc gan)
	public void addAssignedVar(int line, String varname) {
		Map<Integer, ArrayList<String>> listVar;
		if (!isUsingBackupTableFlag)
			listVar = tableAssignedVar;
		else
			listVar = backupListVarInIfBranch;
		if (listVar.containsKey(line)) {
			ArrayList<String> listVarNames = (ArrayList<String>) listVar.get(line);
			if (!listVarNames.contains(varname))
				listVarNames.add(varname);
		}
		else {
			ArrayList<String> vars = new  ArrayList<String>();
			vars.add(varname);
			listVar.put(new Integer(line), vars);
		}
	}
	// ham se duoc kich hoat truoc khi bat dau duyet nhanh if cua IfElseStmt
	public void useBackUpTable() {
		isUsingBackupTableFlag = true;
	}
	// ham se duoc kich hoat sau khi duyet xong nhanh if cua IfElseStmt
	public void finishUsingBackUpTable() {
		backupMultiListInIfBranch.push(backupListVarInIfBranch);
		backupListVarInIfBranch = new HashMap<Integer, ArrayList<String>>();
		isUsingBackupTableFlag = false;
	}
	// ham se duoc kich hoat sau khi duyet het nhanh else cua IfElseStmt
	public void restoreBackUpTable() {
		Map<Integer, ArrayList<String>> oneList = backupMultiListInIfBranch.pop();
		tableAssignedVar.putAll(oneList);
	}
	// tim tat ca cac dong ma bien varname duoc gan
	public ArrayList<Integer> getLinesOfAssignedVar(String varname) {
		//Get Map in Set interface to get key and value
        Set s = tableAssignedVar.entrySet();
        //Move next key and value of Map by iterator
        Iterator it = s.iterator();
		ArrayList<Integer> lines = new ArrayList<Integer>();
        while(it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            int line = (Integer) m.getKey();
            ArrayList<String> listVar = (ArrayList<String>) m.getValue();
			if (listVar.contains(varname))
				lines.add(line);
        }
		if (isUsingBackupTableFlag) {
			Iterator iter = backupListVarInIfBranch.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry m = (Map.Entry) iter.next();
				int line = (Integer) m.getKey();
				ArrayList<String> listVar = (ArrayList<String>) m.getValue();
				if (listVar.contains(varname))
					lines.add(line);
			}
		}
		return lines;
	}
	public void printArrayList(ArrayList list) {
		System.out.println("ArrayList:");
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		System.out.println("EndArrayList:");
	}		
	public String toString() {
		String result = "";
		String newline = "\r\n";
		result += newline+newline+"List of assigned variable in the source code:"+newline;
        //Get Map in Set interface to get key and value
        Set s = tableAssignedVar.entrySet();
        //Move next key and value of Map by iterator
        Iterator it = s.iterator();
        while(it.hasNext())
        {
            Map.Entry m = (Map.Entry) it.next();
            int line = (Integer) m.getKey();
            ArrayList<String> listVarName = (ArrayList<String>) m.getValue();
            result += "Line: " + line + "       Assigned Variable: ";
			for (int i = 0; i < listVarName.size(); i++)
				result += listVarName.get(i) + " ";
			result += newline;
        }
		result += newline;
		return result;
	}
}

/***********************************************************************************
Visitor duyet qua cay AST de tao ra Program Dependence Graph
***********************************************************************************/
public class Ast2GraphVisitor extends DoNothingVisitor {
	TableAssignedVariable tableAssignedVar; // <=== table luu bien duoc gan tai moi dong
	PDG graph;
	ControlDep currentLevelNode; // <=== dung de tinh toan phu thuoc dieu khien
	
	// bien constant bao hieu dang duyet cac lenh trong vong loop de tim nhung bien duoc gan
	// khong tao ra node hay tim DataDep trong luc duyet
	static final String FIND_ASSIGN_STMT_IN_LOOP = "findAssiInLoop";
	
	// bien constant bao hieu 1 statement dang o trong 1 loop
	static final String STMT_IN_LOOP = "stmtInLoop";
	
	public Ast2GraphVisitor() {
		tableAssignedVar = new TableAssignedVariable();
		graph = new PDG();
		currentLevelNode = new ControlDep();
	}
	
	public PDG getProgramDependenceGraph() {return graph;}
	public String getTableAssignedVar() {return tableAssignedVar.toString();}
	
	/***********************************************************************/	
	public void println(Object o) {
		System.out.println(o);
	}
	public boolean checkEquals(Object o, String s) {
		String str = (String) o;
		if (s.equals(str))
			return true;
		return false;
	}
	public int getLineFromNode(OneStmtAST ast) {
		int line = 0;
		if (ast instanceof ExprStmtAST)
			line = getLineOfExpr(((ExprStmtAST) ast).e);
		else if (ast instanceof IfThenElseStmtAST)
			line = getLineOfExpr(((IfThenElseStmtAST) ast).e);
		else if (ast instanceof IfThenStmtAST)
			line = getLineOfExpr(((IfThenStmtAST) ast).e);
		else if (ast instanceof WhileStmtAST)
			line = getLineOfExpr(((WhileStmtAST) ast).e);
		else if (ast instanceof ForStmtAST)
			line = getLineOfExpr(((ForStmtAST) ast).e2);
		else if (ast instanceof SwitchStmtAST)
			line = getLineOfExpr(((SwitchStmtAST) ast).e);			
		else if (ast instanceof CaseStmtAST)
			line = getLineOfExpr(((CaseStmtAST) ast).e);
		else if (ast instanceof BreakStmtAST)
			line = ((BreakStmtAST) ast).t.getLine();
		else if (ast instanceof ContStmtAST)
			line = ((ContStmtAST) ast).t.getLine();
		else if (ast instanceof RetStmtAST)
			line = getLineOfExpr(((RetStmtAST) ast).e);

		return line;
	}
	public int getLineOfExpr(ExprAST exprAST) {
		int line = 0;
		if (exprAST instanceof BinExprAST)
			line = ((BinExprAST) exprAST).op.getLine();
		if (exprAST instanceof UnaryExprAST)
			line = ((UnaryExprAST) exprAST).op.getLine();
		if (exprAST instanceof CallExprAST)
			line = ((CallExprAST) exprAST).name.getLine();
		if (exprAST instanceof VarExprAST)
			line = ((VarExprAST) exprAST).name.getLine();
		if (exprAST instanceof LiteralAST)
			line = ((LiteralAST) exprAST).literal.getLine();		
		if (exprAST instanceof EleExprAST)
			line = ((EleExprAST) exprAST).name.getLine();
		return line;
	}
	public void add2ListDataDep(ArrayList<DataDep> list1, ArrayList<DataDep> list2) {
		int size1 = list1.size();
		for (int i = 0; i < list2.size(); i++) {
			boolean existsInList1 = false;
			for (int j = 0; j < size1; j++) {
				if (list1.get(j).getID() == list2.get(i).getID() &&
					list1.get(j).getVarName().equals(list2.get(i).getVarName())) {
					existsInList1 = true;
					break;
				}
			}
			if (!existsInList1)
				list1.add(list2.get(i));
		}
	}
	public void printArrayList(ArrayList list) {
		System.out.println("ArrayList:");
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		System.out.println("EndArrayList:");
	}	
	/***********************************************************************/
	
	// ProgramAST
	public Object visitProgramAST(ProgramAST ast, Object o) throws CompilationException {		
		ast.dl.visit(this, o);
		
		// gan gia tri bien node trong DataDep,refer den dung Node ma:
		// Node.StatementID == DataDep.lineID
		graph.changeLineIdAtDataDepPointToNode();
		return null;
	}

	// DeclarationListAST
	public Object visitDeclarationListAST(DeclarationListAST ast, Object o)
			throws CompilationException {
		ast.d.visit(this, o);
		ast.dl.visit(this, o);
		return null;
	}
	
	// DeclarationStmtAST
	public Object visitDeclarationStmtAST(DeclarationStmtAST ast, Object o)
			throws CompilationException {
		ast.dl.visit(this, o);
		return null;
	}	
	
	// VarInitializer
	public Object visitVarInitializerAST(VarInitializerAST ast, Object o)
			throws CompilationException {
		return ast.e.visit(this, o);
	}
	
	// VarDeclAST
	public Object visitVarDeclAST(VarDeclAST ast, Object o)
			throws CompilationException {
		ast.line = ast.id.getLine();
		if (ast.parent.parent instanceof DeclarationStmtAST)
			ast.parent.parent.line = ast.line;

		ast.t.visit(this, o);
		if (ast.init != null) {
			// day la phep gan
			String varNameAssigned = ast.id.getText();
			// luu bien duoc gan vao danh sach
			tableAssignedVar.addAssignedVar(ast.line, varNameAssigned);			
			// neu return true <=> dang tim kiem lenh gan trong vong lap
			if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP))
				return null;
			ArrayList<DataDep> data = (ArrayList<DataDep>) ast.init.visit(this, o);
			ControlDep conDep = null;
			if (!currentLevelNode.isEmpty())
				conDep = new ControlDep(currentLevelNode);
			Node assignNode = new Node(ast.line, TYPE.ASSIGN, data, conDep, null);
			assignNode.setAssignedVar(varNameAssigned);
			graph.addNode(assignNode);
		} else {
			// neu return true <=> dang tim kiem lenh gan trong vong lap
			if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP))
				return null;
			ControlDep conDep = null;
			if (!currentLevelNode.isEmpty())
				conDep = new ControlDep(currentLevelNode);				
			graph.addNode(new Node(ast.line, TYPE.DECLARATION, null, conDep, null));
		}
		return null;
	}

	// FuncDeclAST
	public Object visitFuncDeclAST(FuncDeclAST fAst, Object o)
			throws CompilationException {
		fAst.line = fAst.name.getLine();
		fAst.para.visit(this, o);
		fAst.ret.visit(this, o);		
		Node funcNode = new Node(fAst.line, TYPE.ENTRANCE, null, null, null);
		graph.addNode(funcNode);
		currentLevelNode.set(funcNode, true);
		fAst.c.visit(this, o);
		return null;
	}
	
	// ParaAST
	public Object visitParaAST(ParaAST pAst, Object o)
			throws CompilationException{		
		pAst.line = pAst.id.getLine();
		//bien khai bao tai para trong ham duoc luu vao table
		String varNameAssigned = pAst.id.getText();
		// luu bien duoc gan vao danh sach
		tableAssignedVar.addAssignedVar(pAst.line, varNameAssigned);
		pAst.t.visit(this, o);
		return null;
	}

	// ParaListAST
	public Object visitParaListAST(ParaListAST pAst, Object o)
			throws CompilationException {
		pAst.v.visit(this, o);
		pAst.p.visit(this, o);
		return null;
	}
	
	// StmtListAST
	public Object visitStmtListAST(StmtListAST sAst, Object o)
			throws CompilationException {
		sAst.o.visit(this, o);
		sAst.s.visit(this, o);
		return null;
	}
	
	// CompStmtAST
	public Object visitCompStmtAST(CompStmtAST cAst, Object o)
			throws CompilationException {
		cAst.s.visit(this, o);
		return null;
	}

	// ExprListAST
	public Object visitExprListAST(ExprListAST ast, Object o)
			throws CompilationException {
		ArrayList<DataDep> list = (ArrayList<DataDep>) ast.e.visit(this, o);
		ArrayList<DataDep> list2 = (ArrayList<DataDep>) ast.l.visit(this, o);
		if (list != null && list2 != null) {
			add2ListDataDep(list, list2);
			return list;
		}
		if (list == null && list2 != null)
			return list2;
		if (list != null && list2 == null)
			return list;	
		return null;
	}
	
	// VarExprAST
	public Object visitVarExprAST(VarExprAST ast, Object o)
			throws CompilationException {
		String varName = ast.name.getText();
		ArrayList<Integer> linesOfVar = tableAssignedVar.getLinesOfAssignedVar(varName);
		ArrayList<DataDep> result = new ArrayList<DataDep>();
		for (int i = 0; i < linesOfVar.size(); i++)
			result.add(new DataDep(linesOfVar.get(i).intValue(), varName));
		if (result.size() == 0)
			result.add(new DataDep(0, varName));
		return result;
	}

	// UnaryExprAST
	public Object visitUnaryExprAST(UnaryExprAST ast, Object o)
			throws CompilationException {
		ast.line = ast.op.getLine();
		ArrayList<DataDep> list = (ArrayList<DataDep>) ast.e.visit(this, o);
		if (ast.op.getText().equals("++") || ast.op.getText().equals("--")) {	
			// day la phep gan tang hoac giam 1 don vi
			String varNameAssigned = ((VarExprAST) ast.e).name.getText();
			// luu bien duoc gan vao danh sach
			tableAssignedVar.addAssignedVar(ast.line, varNameAssigned);
			if (ast.parent instanceof ExprStmtAST) {				
				// neu return true <=> dang tim kiem lenh gan trong vong lap
				if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP))
					return "skip_adding_node";
			}
		}
		return list;
	}

	// BinExprAST
	public Object visitBinExprAST(BinExprAST ast, Object o)
			throws CompilationException {
		ast.line = ast.op.getLine();
		ArrayList<DataDep> list1 = (ArrayList<DataDep>) ast.e1.visit(this, o);
		ArrayList<DataDep> list2 = (ArrayList<DataDep>) ast.e2.visit(this, o);
		
		// cac loai phep gan =, +=, -=, *=, /=, %=
		if (ast.opType >= BinExprAST.ASSIGN && ast.opType <= BinExprAST.MOD_ASSIGN) {
			// day la phep gan
			String varNameAssigned = list1.get(0).getVarName();
			// luu bien duoc gan vao danh sach
			tableAssignedVar.addAssignedVar(ast.line, varNameAssigned);
			if (ast.parent instanceof ExprStmtAST) {
				// neu return true <=> dang tim kiem lenh gan trong vong lap
				if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP))
					return "skip_adding_node";
			}
		}
		
		// truong hop phep gan thi DataDep = list2
		if (ast.opType == BinExprAST.ASSIGN && 
			(ast.parent instanceof ExprStmtAST || ast.parent instanceof ExprListAST))
			return list2;
		
		// cac truong hop khac thi add 2 danh sach du lieu phu thuoc
		if (list1 != null && list2 != null) {
			add2ListDataDep(list1, list2);
			return list1;
		}
		if (list1 == null && list2 != null)
			return list2;
		if (list1 != null && list2 == null)
			return list1;
		return null;
	}
	
	// CallExprAST
	public Object visitCallExprAST(CallExprAST ast, Object o)
			throws CompilationException {
		ArrayList<DataDep> list = (ArrayList<DataDep>) ast.e.visit(this, o);
		return list;
	}

	// EleExprAST
	// Bieu thuc truy xuat mang: array[...][...][...]
	public Object visitEleExprAST(EleExprAST ast, Object o) throws CompilationException { 
		String varName = ast.name.getText();
		ArrayList<Integer> linesOfVar = tableAssignedVar.getLinesOfAssignedVar(varName);
		ArrayList<DataDep> result = new ArrayList<DataDep>();
		for (int i = 0; i < linesOfVar.size(); i++)
			result.add(new DataDep(linesOfVar.get(i).intValue(), varName));
		if (result.size() == 0)
			result.add(new DataDep(0, varName));
		
		ArrayList<DataDep> resExprList = (ArrayList<DataDep>) ast.e.visit(this,o);
		if (resExprList != null)
			add2ListDataDep(result, resExprList);
		
		return result;
	}

	// ExprStmtAST
	public Object visitExprStmtAST(ExprStmtAST ast, Object o)
			throws CompilationException {
		ast.line = getLineOfExpr(ast.e);
		ast.e.line = ast.line;
		Object res = ast.e.visit(this, o);
		if (res instanceof String)
			if (((String) res).equals("skip_adding_node"))
				return null;

		ArrayList<DataDep> data = (ArrayList<DataDep>) res;
		String varNameAssigned = "";
		// lay thong tin cua variable duoc gan
		if (ast.e instanceof BinExprAST) {
			ExprAST leftSide = ((BinExprAST) ast.e).e1;
			if (leftSide instanceof VarExprAST)
				varNameAssigned = ((VarExprAST) leftSide).name.getText();
			else if (leftSide instanceof EleExprAST)
				varNameAssigned = ((EleExprAST) leftSide).name.getText();
		}
		else if (ast.e instanceof UnaryExprAST)
			varNameAssigned = ((VarExprAST) ((UnaryExprAST) ast.e).e).name.getText();
		ControlDep conDep = null;
		if (!currentLevelNode.isEmpty())
			conDep = new ControlDep(currentLevelNode);		
		Node assignNode = new Node(ast.line, TYPE.ASSIGN, data, conDep, null);
		assignNode.setAssignedVar(varNameAssigned);
		graph.addNode(assignNode);
		return null;
	}

	// IfThenStmtAST
	public Object visitIfThenStmtAST(IfThenStmtAST iAst, Object o)
			throws CompilationException {
		iAst.line = getLineOfExpr(iAst.e);
		// neu return true <=> dang tim kiem lenh gan trong vong lap
		if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP)) {
			iAst.s.visit(this, o);
			return null;
		}

		ArrayList<DataDep> data = (ArrayList<DataDep>) iAst.e.visit(this, o);
		ControlDep conDep = null;
		if (!currentLevelNode.isEmpty())
			conDep = new ControlDep(currentLevelNode);		
		Node ifNode = new Node(iAst.line, TYPE.CONDITION, data, conDep, null);		
		graph.addNode(ifNode);
		
		// backup lai node o phia tren cua lenh if
		ControlDep backupLevelNode = new ControlDep(currentLevelNode);
		
		// gan ifNode hien tai la node dieu khien cha
		currentLevelNode.set(ifNode, true);
		iAst.s.visit(this, o);
		
		// restore lai node da duoc backup
		currentLevelNode.set(backupLevelNode);
		return null;
	}
	
	// IfThenElseStmtAST
	public Object visitIfThenElseStmtAST(IfThenElseStmtAST iAst, Object o)
			throws CompilationException {
		iAst.line = getLineOfExpr(iAst.e);
		// neu return true <=> dang tim kiem lenh gan trong vong lap
		if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP)) {
			iAst.s1.visit(this, o);
			iAst.s2.visit(this, o);
			return null;
		}
		
		ArrayList<DataDep> data = (ArrayList<DataDep>) iAst.e.visit(this, o);
		ControlDep conDep = null;
		if (!currentLevelNode.isEmpty())
			conDep = new ControlDep(currentLevelNode);		
		Node ifNode = new Node(iAst.line, TYPE.CONDITION, data, conDep, null);
		graph.addNode(ifNode);
		
		// backup lai node o phia tren cua lenh if
		ControlDep backupLevelNode = new ControlDep(currentLevelNode);
		if (!checkEquals(o, STMT_IN_LOOP)) {
			// neu lenh IfElse nay khong nam trong bat ky lenh loop nao
			// su dung 1 backup table de luu cac bien duoc gan trong nhanh IF
			tableAssignedVar.useBackUpTable();
			currentLevelNode.set(ifNode, true);
			iAst.s1.visit(this, o);
			
			// ket thuc viec su dung backup table
			// luu lai trong 1 stack, quay tro ve voi main table.
			tableAssignedVar.finishUsingBackUpTable();
			currentLevelNode.set(ifNode, false);
			iAst.s2.visit(this, o);
			
			// copy lai backup table vo main table
			tableAssignedVar.restoreBackUpTable();
		}
		else {
			currentLevelNode.set(ifNode, true);
			iAst.s1.visit(this, o);
			currentLevelNode.set(ifNode, false);
			iAst.s2.visit(this, o);
		}
		currentLevelNode.set(backupLevelNode);
		return null;
	}

	// ForStmtAST
	public Object visitForStmtAST(ForStmtAST fAst, Object o)
			throws CompilationException {
		fAst.line = getLineOfExpr(fAst.e2);
		// neu return true <=> dang tim kiem lenh gan trong vong lap
		if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP)) {
			if (fAst.e1 != null)
				fAst.e1.visit(this, o);
			if (fAst.e3 != null)
				fAst.e3.visit(this, o);
			fAst.o.visit(this, o);
			return null;
		}
		
		// duyet lan dau so qua de tim cac phep gan trong than vong lap
		// khong add node trong lan duyet nay
		if (fAst.e1 != null)
			fAst.e1.visit(this, FIND_ASSIGN_STMT_IN_LOOP);
		if (fAst.e3 != null)
			fAst.e3.visit(this, FIND_ASSIGN_STMT_IN_LOOP);
		fAst.o.visit(this, FIND_ASSIGN_STMT_IN_LOOP);
		
		// duyet lan thu 2 de tim ra data dependence
		ArrayList<DataDep> data1 = null;
		ArrayList<DataDep> data2 = null;
		ArrayList<DataDep> data3 = null;
		if (fAst.e1 != null)
			data1 = (ArrayList<DataDep>) fAst.e1.visit(this, "");
		if (fAst.e2 != null)
			data2 = (ArrayList<DataDep>) fAst.e2.visit(this, "");
		if (fAst.e3 != null)
			data3 = (ArrayList<DataDep>) fAst.e3.visit(this, "");
		
		// tong hop lai cac du lieu phu thuoc nay vo bien data
		ArrayList<DataDep> data = new ArrayList<DataDep>();
		if (data1 != null) add2ListDataDep(data, data1);
		if (data2 != null) add2ListDataDep(data, data2);
		if (data3 != null) add2ListDataDep(data, data3);
		if (data.size() == 0)
			data = null;
		ControlDep conDep = null;
		if (!currentLevelNode.isEmpty())
			conDep = new ControlDep(currentLevelNode);
		Node forNode = new Node(fAst.line, TYPE.FORLOOP, data, conDep, null);
		graph.addNode(forNode);
		
		// backup lai node o phia tren cua lenh loop
		ControlDep backupLevelNode = new ControlDep(currentLevelNode);
		currentLevelNode.set(forNode, true);
		fAst.o.visit(this, STMT_IN_LOOP);
		currentLevelNode.set(backupLevelNode);
		return null;
	}
	// ForInitAST
	public Object visitForInitAST(ForInitAST ast, Object o)
			throws CompilationException {
		if (ast.d != null)
			ast.d.visit(this, o);
		if (ast.e != null)
			return ast.e.visit(this, o);
		return null;
	}

	// WhileStmtAST
	public Object visitWhileStmtAST(WhileStmtAST wAst, Object o)
			throws CompilationException {
		wAst.line = getLineOfExpr(wAst.e);
		// neu return true <=> dang tim kiem lenh gan trong vong lap
		if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP)) {
			wAst.o.visit(this, o);
			return null;
		}
		
		// duyet lan dau so qua de tim cac phep gan trong than vong lap
		wAst.o.visit(this, FIND_ASSIGN_STMT_IN_LOOP);		
		
		// bat dau duyet chinh thuc
		ArrayList<DataDep> data = (ArrayList<DataDep>) wAst.e.visit(this, o);
		ControlDep conDep = null;
		if (!currentLevelNode.isEmpty())
			conDep = new ControlDep(currentLevelNode);
		Node whileNode = new Node(wAst.line, TYPE.CONDITION, data, conDep, null);
		graph.addNode(whileNode);

		// backup lai node o phia tren cua lenh loop
		ControlDep backupLevelNode = new ControlDep(currentLevelNode);
		currentLevelNode.set(whileNode, true);
		wAst.o.visit(this, STMT_IN_LOOP);
		currentLevelNode.set(backupLevelNode);
		return null;
	}
	
	// DoStmtAST
	public Object visitDoStmtAST(DoStmtAST wAst, Object o)
			throws CompilationException {
		wAst.line = getLineOfExpr(wAst.e);
		// neu return true <=> dang tim kiem lenh gan trong vong lap
		if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP)) {
			wAst.o.visit(this, o);
			return null;
		}

		// duyet lan dau so qua de tim cac phep gan trong than vong lap
		wAst.o.visit(this, FIND_ASSIGN_STMT_IN_LOOP);
		
		// bat dau duyet chinh thuc
		ArrayList<DataDep> data = (ArrayList<DataDep>) wAst.e.visit(this, o);
		ControlDep conDep = null;
		if (!currentLevelNode.isEmpty())
			conDep = new ControlDep(currentLevelNode);
		Node doNode = new Node(wAst.line, TYPE.CONDITION, data, conDep, null);
		
		// backup lai node o phia tren cua lenh loop
		ControlDep backupLevelNode = new ControlDep(currentLevelNode);
		currentLevelNode.set(doNode, true);
		wAst.o.visit(this, STMT_IN_LOOP);
		currentLevelNode.set(backupLevelNode);
		
		graph.addNode(doNode);
		return null;
	}

	// RetStmtAST
	public Object visitRetStmtAST(RetStmtAST rAst, Object o)
			throws CompilationException {
		// neu return true <=> dang tim kiem lenh gan trong vong lap
		if (checkEquals(o, FIND_ASSIGN_STMT_IN_LOOP))
			return null;
		if (rAst.e != null) {		
			rAst.line = getLineOfExpr(rAst.e);
			ArrayList<DataDep> data = (ArrayList<DataDep>) rAst.e.visit(this, o);
			ControlDep conDep = null;
			if (!currentLevelNode.isEmpty())
				conDep = new ControlDep(currentLevelNode);			
			graph.addNode(new Node(rAst.line, TYPE.RETURN, data, conDep, null));
		}
		return null;
	}

	///////////////////////////////////////////////////////////////////////////////////
	// chua xu ly cac cau lenh ben duoi
	// BreakStmtAST
	public Object visitBreakStmtAST(BreakStmtAST ast, Object o)
			throws CompilationException {
		ast.line = ast.t.getLine();
		println("BreakStmtAST: " + ast.line);
		return null;
	}		
	// ContStmtAST
	public Object visitContStmtAST(ContStmtAST ast, Object o)
			throws CompilationException {
		ast.line = ast.t.getLine();
		println("ContStmtAST: " + ast.line);
		return null;
	}
	// SwitchStmtAST
	public Object visitSwitchStmtAST(SwitchStmtAST sAst, Object o)
			throws CompilationException {
		sAst.line = getLineOfExpr(sAst.e);
		println("SwitchStmtAST: " + sAst.line);
		sAst.e.visit(this, o);
		sAst.o.visit(this, o);
		return null;
	}	
	// CaseStmtAST
	public Object visitCaseStmtAST(CaseStmtAST cAst, Object o)
			throws CompilationException {
		cAst.line = getLineOfExpr(cAst.e);		
		println("CaseStmtAST: " + cAst.line);
		cAst.e.visit(this, o);
		cAst.s.visit(this, o);
		return null;
	}	
	// DefaultStmtAST
	public Object visitDefaultStmtAST(DefaultStmtAST dAst, Object o)
			throws CompilationException {
		dAst.line = getLineFromNode(dAst.s.o)-1;
		println("DefaultStmtAST: " + dAst.line);
		dAst.s.visit(this, o);
		return null;
	}
	//
	///////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////////////////////////////////////////////////////
	// chua can xet cac ham ben duoi
	// TernaryExprAST
	public Object visitTernaryExprAST(TernaryExprAST ast, Object o)
			throws CompilationException {
		ast.e1.visit(this, o);
		ast.e2.visit(this, o);
		ast.e3.visit(this, o);
		return null;
	}
	// TypeListAST
	public Object visitTypeListAST(TypeListAST ast, Object o)
			throws CompilationException {
		ast.t.visit(this, o);
		ast.tl.visit(this, o);
		return null;
	}	
	// ArrayTypeAST
	public Object visitArrayTypeAST(ArrayTypeAST ast, Object o)
			throws CompilationException {
		ast.type.visit(this, o);
		ast.el.visit(this, o);
		return null;
	}	
	// PointerTypeAST
	public Object visitPointerTypeAST(PointerTypeAST ast, Object o)
			throws CompilationException {
		ast.t.visit(this, o);
		return null;
	}
	// IntLiteralAST
	public Object visitIntLiteralAST(IntLiteralAST ast, Object o)
			throws CompilationException {
		//print(indentString() + ast.literal.getText());
		return null;
	}	
	//FloatLiteralAST
	public Object visitFloatLiteralAST(FloatLiteralAST ast, Object o)
			throws CompilationException {
		//print(indentString() + ast.literal.getText());
		return null;
	}	
	//BoolLiteralAST
	public Object visitBoolLiteralAST(BoolLiteralAST ast, Object o)
			throws CompilationException {
		//print(indentString() + ast.literal.getText());
		return null;
	}	
	//StringLiteralAST
	public Object visitStringLiteralAST(StringLiteralAST ast, Object o)
			throws CompilationException {
		//print(indentString() + ast.literal.getText());
		return null;
	}	
	//CharLiteralAST
	public Object visitCharLiteralAST(CharLiteralAST ast, Object o)
			throws CompilationException {
		//print(indentString() + ast.literal.getText());
		return null;
	}	
	// ArrayInitializer
	public Object visitArrayInitializerAST(ArrayInitializerAST ast, Object o)
	throws CompilationException {
		ast.v.visit(this, o);
		return null;
	}	
	// VarInitializerList
	public Object visitVarInitializerListAST(VarInitializerListAST ast, Object o)
		throws CompilationException {
		ast.v.visit(this, o);
		ast.vl.visit(this, o);
		return null;
	}
	// ArrayInitializerList
	public Object visitArrayInitializerListAST(ArrayInitializerListAST ast, Object o)
		throws CompilationException {
		ast.a.visit(this, o);
		ast.al.visit(this, o);
		return null;
	}
	//
	///////////////////////////////////////////////////////////////////////////////////

}
