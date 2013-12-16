package Transformer;

import java.io.*;
import java.util.*;
import org.antlr.runtime.*;
//import org.antlr.v4.runtime.atn.PredictionMode;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;
import Transformer.DependenceGraph.*;
import Transformer.TestCasesGenerator.*;

public class Main {

	// so dong lenh trong file source
	// +1 la boi label cua cac cau lenh bat dau tu 1
	//so dong lenh cua chuong trinh moi
	//duoc set tinh
	//hien thuc ham lay numLine tu dong
	//static int numLine = 9 + 1;
	static int numLine = 0;
	static int nTest = 0;
	// dong lenh sai
	// duoc set tinh, chi co tac dung de so sanh ket qua thong ke
	// bien bieu thi giai thuat se su dung
	// 0 : Tarantula
	// 1 : Ochiai
	// 2 : Jaccard
	// 3 : Slicing
	static int iMode = 0;
	static int failLine = 1;
	static int version = 1;
	static int nFail = 1;
	static int debug = 0;
	
	static int[] statement2line;
	static final long KILOBYTE = 1024L;
	
	static Slice sliceProg = new Slice();
	
	static float mintPercent = 1.0f; // min % cua pp spectrum-based
	static float mintPercentSlice = 1.0f; // min % cua pp spectrum-based with dynamic slicing

	static float maxtPercent = 1.0f; // max % cua pp spectrum-based
	static float maxtPercentSlice = 1.0f; // max % cua pp spectrum-based with dynamic slicing
			
	static String standardSourceFile = "standard_output_student.txt";
	static String mappingTableFile = "mapping_statement2line.txt";
	
	// bien dung de ghi ket qua phan tich ra file
	static PrintWriter writer;
	static PrintWriter writerFL;
	static PrintWriter writerFLOchiai;
	static PrintWriter writerFLJaccard;
	static PrintWriter writerFLSliceTarantula;
	static PrintWriter writerFLSliceOchiai;
	static PrintWriter writerAllPaths;
	static PrintWriter writerTestCasesAndPath;
	//static PrintWriter writerStatistic;
	
	// class cho biet % cau lenh can phan tich cho den
	// khi gap cau lenh sai
	// co 2 truong hop tot nhat (min) va xau nhat (max)
	private static class Percent {
		public float min = 0.0f;

		public float max = 0.0f;

		public Percent(float _min, float _max) {
			min = _min;
			max = _max;
		}
	}
	// trinhgiang-31/10/2013
	// dynamic slicing
	public static void getSliceOfTestcase(PDG graph, ArrayList<Integer> path, int stmt) {
		//Slice s = new Slice();
		if(stmt == 0) return;
		if(path.contains(stmt) && !sliceProg.contains(stmt)) {
			sliceProg.addLine(stmt);
		}
		Node n = graph.findNodeAtLine(stmt);
		if(n == null) return;
		// data criterion
		ArrayList<DataDep> dataDep = n.getDataDep();
		if(dataDep != null) {
			for(int i = 0; i < dataDep.size(); i++) {
				int indexDataDep = dataDep.get(i).getID();
				
				if(!sliceProg.contains(indexDataDep) && path.contains(indexDataDep)) {
					getSliceOfTestcase(graph, path, indexDataDep);
				}
			}
		}
		// control criterion
		ControlDep controlDep = (ControlDep)n.getControlDep();
		Node nControl = null;
		if(controlDep != null) nControl = controlDep.get();
		if(nControl != null) {
			int indexControlDep = nControl.getID();
			if(!sliceProg.contains(indexControlDep) && path.contains(indexControlDep)) {
				getSliceOfTestcase(graph, path, indexControlDep);
			}
		}
		// potential criterion
		ArrayList<Node> potDep = n.getPotDep();
		if(potDep != null) {
			for(int i = 0; i < potDep.size(); i++) {
				int indexNode = potDep.get(i).getID();
				// not look to contain path execution
				if(!sliceProg.contains(indexNode)) {
					getSliceOfTestcase(graph, path, indexNode);
				}
			}
		}
		return;
	}
	// thoi gian can thiet de sinh ra bo test cases doi voi tung options
	// index la option cua giai thuat sinh testcase
	// gia tri 0-4
	// phan tao test cases can den AST label va tat ca path execution
	public static void calcTime(int index, AST labelTree, List<String> paths) {
		Generator gen = null;
		// lua chon option
		switch (index) {
			case 0:
				writer.println("Random generator:");
				gen = new RandomGenerator(labelTree, paths);
				break;
			case 1:
				writer.println("Simple generator:");
				gen = new SimpleHillClimbingGenerator(labelTree, paths);
				break;
			case 2:
				writer.println("Steepest generator:");
				gen = new SteepestHillClimbingGenerator(labelTree, paths);
				break;
			case 3:
				writer.println("Simulater generator:");
				gen = new SimulatedAnnealingGenerator(labelTree, paths);
				break;
			case 4:
				writer.println("Genetic generator:");
				gen = new GeneticGenerator(labelTree, paths);
				break;
		}

		//gen.generate();
		
		// day thoi gian
		long[] times = new long[100];

		// viec sinh test case duoc thuc hien 100 lan
		// voi moi option, chi co 1 so lan trong 100 lan
		// nay duoc ghi lai test case, nhung lan khac chi
		// lay gia tri thoi gian, o day la 10 lan duoc ghi lai
		for (int i = 0; i < 100; i++) {
			times[i] = gen.generate();
			gen.write();
		}
		gen.close();

		// sap xep day thoi gian ghi nhan duoc
		// tu nho den lon
		Arrays.sort(times);

		// loai 5 gia tri dau va 5 gia tri cuoi
		// lay trung binh 90 gia tri con lai
		long sums = 0;
		for (int i = 5; i < 95; i++) {
			sums += times[i];
		}

		// viet ket qua ra file
		writer.println("Time = " + sums / 90);
	}
	//----------------khong can quan tam voi FL project-----------------//
	// tinh % cau lenh can kiem tra
	public static Percent getPercent(float[] score) {
		int minCheckLine = 0;
		int maxCheckLine = 0;
		
		// truong hop tot nhat, cau lenh sai duoc kiem tra
		// dau tien giua nhung cau lenh cung diem
		// tinh tong so cau lenh co diem cao hon cau lenh sai
		for (int i = 1; i < numLine; i++) {
			//if (i != failLine && score[i] > score[failLine]) {
			if (score[i] > score[failLine]) {
				minCheckLine++;
			}
		}
		//minCheckLine++;
		// truong hop xau nhat, cau lenh sai duoc kiem tra
		// cuoi cung giua nhung cau lenh cung diem
		// tinh tong so cau lenh co diem cao hon hoac bang cau lenh sai
		for (int i = 1; i < numLine; i++) {
			if (i != failLine && score[i] >= score[failLine]) {
			//if (score[i] >= score[failLine]) {
				maxCheckLine++;
			}
		}
		//System.out.println("Da get percent " + (float)minCheckLine / (numLine - 1) + "--" + (float)maxCheckLine / (numLine - 1));
		// tra ve ket qua la mot doi tuong percent
		// nho la numLine phai tru 1
		if(numLine == 1) return new Percent(0.0f, 0.0f);
		return new Percent((float)minCheckLine / (numLine - 1), (float)maxCheckLine / (numLine - 1));
	}

	//****************************cai chu yeu trong FL project***************************/
	// phuong phap tarantula
	public static Percent tarantula(int[] pass, int[] fail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		// cho phan tu 0 bang -1 vi thuc chat khong xet phan tu nay (chi xet tu 1)
		scores[0] = -1;
		
		// tinh diem cho tung cau lenh theo cong thuc
		for (int i = 1; i < numLine; i++) {
			if (fail[i] == 0) {
				scores[i] = 0;
			} else if (pass[i] == 0) {
				scores[i] = 1;
			} else {
				//cong thuc cua tarantula
				scores[i] = ((float)fail[i] / totalFail) / ((float)pass[i] / totalPass + (float)fail[i] / totalFail);
			}
		}
			
		return getPercent(scores);
	}

	
	//lay ket qua score table
	public static float[] tarantulaPrint(int[] pass, int[] fail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		// cho phan tu 0 bang -1 vi thuc chat khong xet phan tu nay (chi xet tu 1)
		scores[0] = -1;
		
		// tinh diem cho tung cau lenh theo cong thuc
		for (int i = 1; i < numLine; i++) {
			if (fail[i] == 0) {
				scores[i] = 0;
			} else if (pass[i] == 0) {
				scores[i] = 1;
			} else {
				//cong thuc cua tarantula
				scores[i] = ((float)fail[i] / totalFail) / ((float)pass[i] / totalPass + (float)fail[i] / totalFail);
			}
		}
			
		return scores;
	}
	//lay ket qua score table cua phuong phap slicing tarantula
	public static float[] tarantulaSlice(float[] newPass, float[] newFail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		// cho phan tu 0 bang -1 vi thuc chat khong xet phan tu nay (chi xet tu 1)
		scores[0] = -1;
		
		// tinh diem cho tung cau lenh theo cong thuc
		for (int i = 1; i < numLine; i++) {
			//System.out.println(newFail[i] + "---" + newPass[i]);
			if (newFail[i] == 0) {
				scores[i] = 0;
			} else if (newPass[i] == 0) {
				scores[i] = 1;
			} else {
				//cong thuc cua tarantula
				scores[i] = (newFail[i]) / (newPass[i] + newFail[i]);
			}
		}
			
		return scores;
	}
	
	//phuong phap ochiai
	//lay ve bang score
	public static float[] ochiaiSlice(float[] pass, float[] fail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		scores[0] = -1;

		for (int i = 1; i < numLine; i++) {
			//System.out.println(fail[i] + "---" + pass[i]);
			if (fail[i] == 0) {
				scores[i] = 0;
			} //else if(pass[i] == 0) {
				//scores[i] = 1;
			//}
			else {
				// chinh lai dung cong thuc
				scores[i] = (fail[i]*totalFail) / (float)Math.sqrt(totalFail * (pass[i]*totalPass + fail[i]*totalFail));
			}
		}
		
		return scores;
	}
	
	// phuong phap ochiai
	public static Percent ochiai(int[] pass, int[] fail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		scores[0] = -1;

		for (int i = 1; i < numLine; i++) {
			if (fail[i] == 0) {
				scores[i] = 0;
			} else {
				scores[i] = fail[i] / (float)Math.sqrt(totalFail * (pass[i] + fail[i]));
			}
		}
		
		return getPercent(scores);
	}

	//phuong phap ochiai
	//lay ve bang score
	public static float[] ochiaiPrint(int[] pass, int[] fail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		scores[0] = -1;

		for (int i = 1; i < numLine; i++) {
			//System.out.println(fail[i] + "---" + pass[i]);
			if (fail[i] == 0) {
				scores[i] = 0;
			} else {
				scores[i] = fail[i] / (float)Math.sqrt(totalFail * (pass[i] + fail[i]));
			}
		}
		
		return scores;
	}
	
	// phuong phap jaccard
	public static Percent jaccard(int[] pass, int[] fail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		scores[0] = -1;

		for (int i = 1; i < numLine; i++) {
			if (fail[i] == 0) {
				scores[i] = 0;
			} else {
				scores[i] = (float)fail[i] / (totalFail + pass[i]);
			}
		}
		
		return getPercent(scores);
	}
	
	// phuong phap jaccard
	//lay bang score
	public static float[] jaccardPrint(int[] pass, int[] fail, int totalPass, int totalFail) {
		float[] scores = new float[numLine];
		scores[0] = -1;

		for (int i = 1; i < numLine; i++) {
			if (fail[i] == 0) {
				scores[i] = 0;
			} else {
				scores[i] = (float)fail[i] / (totalFail + pass[i]);
			}
		}
		
		return scores;
	}

	// tra ve cay AST tu file duoc chi dinh
	public static AST getTree(String sourceFilename) {
		try {
			// System.out.println("\nCreate AST again from file: " + sourceFilename);
			// using ANTLR library again to lexer and parser the standard code
			CPPParser parser = new CPPParser(new CommonTokenStream(new CPPLexer(new ANTLRReaderStream(new BufferedReader(new FileReader(sourceFilename))))));
			//parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
			// create AST Tree from standard code
			AST studentTree = parser.parse();
			
			//khac gi voi cay AST thong thuong
			//co the xuat cay AST ra file
			Visitor walker = new AddLabelVisitor("", false);
			
			//in ra cay AST voi label
			//viet them class ASTPrinterVisitorLabel
			//AST studentTreeLabel = (AST) studentTree.visit(walker, false);
			//Visitor walkerPrintLabel = new AstPrinterVisitor("astLabel.txt");
			//studentTreeLabel.visit(walkerPrintLabel, false);
			
			return (AST) studentTree.visit(walker, false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// tao label tree cho chuong trinh can kiem tra de lay path thuc thi sau nay
	public static AST getLabelTree(AST tree) {
		try {
			//trinhgiang-16/10/2013
			//lay so dong lenh trong chuong trinh
			AddLabelVisitor walkerLabel = new AddLabelVisitor("", false);
			AST temp = (AST) tree.visit(walkerLabel, false);
			numLine = walkerLabel.getNum();
			
			//Visitor walker = new AddLabelVisitor("", false);
			//return (AST) tree.visit(walker, false);
			//in ra cay AST thong thuong
			Visitor walkerPrint = new AstPrinterVisitor("ast.txt");
			temp.visit(walkerPrint, false);
			//tree.visit(walkerPrint, false);
			
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// tra ve tat ca cac path trong chuong trinh
	public static List<String> getAllPaths(AST labelTree) {
		try {
			Visitor walker = new GetAllPathsVisitor("", false);
			return (List<String>) labelTree.visit(walker, false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// chon file test case duoc sinh ra tu cac option
	public static BufferedReader getReader(int index) {
		try {
			switch (index) {
				case 0:
					writer.println("Random generator:");
					//writerFL.println("Random generator:");
					return new BufferedReader(new FileReader("random.txt"));
				case 1:
					//writer.println("Simple generator:");
					writerFL.println("Simple generator:");
					return new BufferedReader(new FileReader("simple.txt"));
				case 2:
					//writer.println("Steepest generator:");
					writerFL.println("Steepest generator:");
					return new BufferedReader(new FileReader("steepest.txt"));
				case 3:
					//writer.println("Simulated generator:");
					writerFL.println("Simulated generator:");
					return new BufferedReader(new FileReader("simulated.txt"));
				case 4:
					//writer.println("Genetic generator:");
					writerFL.println("Genetic generator:");
					return new BufferedReader(new FileReader("genetic.txt"));
				//giai thuat tao test case cua anh duc anh
				//trinhgiang-16/10/2013
				case 5:
					//writer.println("Concolic and Symblic Execution:");
					//writerFL.println("Concolic and Symblic Execution:");
					return new BufferedReader(new FileReader("concolicSE.txt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static float average(float[] percents) {
		float sum = 0.0f;

		Arrays.sort(percents);

		for (int i = 5; i < 95; i++) {
			sum += percents[i];
		}

		return sum / 90;
	}
	
	// so sanh output giua chuong trinh can kiem tra va chuong trinh mau
	// de lay thong tin ve dung sai cua chuong trinh
	// chuong trinh mau chi ton tai de giup viec so sanh ket qua output
	// duoc de dang, khong co vai tro gi trong thi nghiem
	public static void readTestCasesSpectrum(int index, AST labelTree, AST solutionTree, int mode) {
		try {
			float[] tarantulaScores = new float[numLine];
			//float[] tarantulaScoresSlice = new float[numLine];
			//float[] ochiaiScores = new float[numLine];
			//float[] jaccardScores = new float[numLine];
			
			int totalPass = 0; // tong so test case dung
			int totalFail = 0; // tong so test case sai

			int step = 0;
			
			// dung cho tarantula
			int[] pass = new int[numLine]; // so test case dung ung voi tung cau lenh
			int[] fail = new int[numLine]; // so test case sai ung voi tung cau lenh
			
			Visitor walker1 = new GetPathVisitor("", false);
			Visitor walker2 = new RunSimulatorVisitor("", false);
				
		   // lay tap testcase ung voi tung option
			BufferedReader br = getReader(index);

			String testcase = br.readLine();
			int nTestcase = 0;
			
			while (testcase != null) {
				// khoi tao khi vao 1 bo test case moi
				if (testcase.contains("Begin test cases.")) {
					totalPass = totalFail = 0;
					for (int i = 1; i < numLine; i++) {
						pass[i] = fail[i] = 0;
					}
					//System.out.println(step++);
				}
				// ket thuc test case, danh gia chat luong cua bo test case nay
				else if (testcase.contains("End test cases.")) {
					nTest = nTestcase;
					if(mode == 0) {
						// tarantula technique computation
						tarantulaScores = tarantulaPrint(pass, fail, totalPass, totalFail);
					} else if(mode == 1) {
						tarantulaScores = ochiaiPrint(pass, fail, totalPass, totalFail);
					} else if(mode == 2) {
						tarantulaScores = jaccardPrint(pass, fail, totalPass, totalFail);
					}
					//tarantulaScoresSlice = tarantulaSlice(newPass, newFail, totalPass, totalFail);
					
					// tarantula technique evaluation
					Percent tPercent = getPercent(tarantulaScores);
					//Percent tPercentSlice = getPercent(tarantulaScoresSlice);
					mintPercent = tPercent.min;
					maxtPercent = tPercent.max;
					//mintPercentSlice = tPercentSlice.min;
					//maxtPercentSlice = tPercentSlice.max;
					
					step++;
				}
				// so sanh ket qua cua chuong trinh can kiem tra va chuong trinh mau
				// chi de kiem tra viec dung sai
				else {
					String path = (String) labelTree.visit(walker1, testcase);
					//System.out.println(path);
					// tao array list chua path execution
					ArrayList<Integer> pathArrayList = new ArrayList<Integer>();
					String[] arrPath = path.split(";");
					for(String p : arrPath) {
						pathArrayList.add(Integer.parseInt(p));
					}
					
					//simulator ket qua cua sinh vien
					String studentResult = (String) labelTree.visit(walker2, testcase);
					//simulator ket qua cua solution
					String solutionResult = (String) solutionTree.visit(walker2, testcase);
					
					//in testcase va path thuc thi tuong ung ra file testcaseandpath.txt
					//writerTestCasesAndPath.println(testcase);
					//mapping statement to line code
					String stmt2line = "";
					String[] pathArray = path.split(";");
					for(String pathUnit : pathArray)
					{
						stmt2line += statement2line[Integer.parseInt(pathUnit)] + ";";
					}
				
					nTestcase++;
					// test case pass
					if (studentResult.equals(solutionResult)) {
						writerTestCasesAndPath.println("1:" + solutionResult + ":" + studentResult + ":" + stmt2line);
						totalPass++; // tang tong so test case pass
						for (int i = 1; i < numLine; i++) {
							if (pathArrayList.contains(i)) {
								pass[i]++; // tang gia tri pass cho cac cau lenh
										   // tren duong thuc thi
							}
						}
					}
					// test case fail
					else {
						writerTestCasesAndPath.println("0:" + solutionResult + ":" + studentResult + ":" + stmt2line);
						totalFail++; // tang tong so test case fail
						for (int i = 1; i < numLine; i++) {
							if (pathArrayList.contains(i)) {
								fail[i]++; // tang gia tri fail cho cac cau lenh
										   // tren duong thuc thi
							}
						}
					}
				}
				testcase = br.readLine();
			}
	
			//tam thoi in ket qua FL cua tarantula
			//writerFL.println("Tarantula technique");
			if(mode == 0) {
				// tarantula
				for(int j = 1; j < numLine; j++)
					writerFL.printf("%d:%.3f\n", statement2line[j], tarantulaScores[j]);
			} else if(mode == 1) {
				// ochiai
				for(int j = 1; j < numLine; j++)
					writerFLOchiai.printf("%d:%.3f\n", statement2line[j], tarantulaScores[j]);
			} else if(mode == 2) {
				// jaccard
				for(int j = 1; j < numLine; j++)
					writerFLJaccard.printf("%d:%.3f\n", statement2line[j], tarantulaScores[j]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// so sanh output giua chuong trinh can kiem tra va chuong trinh mau
	// de lay thong tin ve dung sai cua chuong trinh
	// chuong trinh mau chi ton tai de giup viec so sanh ket qua output
	// duoc de dang, khong co vai tro gi trong thi nghiem
	public static void readTestCasesSlicing(int index, AST labelTree, AST solutionTree, PDG graph, int mode) {
		try {
			//float[] tarantulaScores = new float[numLine];
			float[] tarantulaScoresSlice = new float[numLine];
			//float[] ochiaiScores = new float[numLine];
			//float[] jaccardScores = new float[numLine];
			
			int totalPass = 0; // tong so test case dung
			int totalFail = 0; // tong so test case sai

			int step = 0;
			
			// tinh toan lai pass and fail
			float[] newPass = new float[numLine];
			float[] newFail = new float[numLine];
					
			// thong ke moi path execution cua moi test case
			ArrayList<ArrayList<Integer>> testCasePath = new ArrayList<ArrayList<Integer>>();
			
			// xac dinh tinh dung sai cua 1 test case
			ArrayList<Integer> pTestcase = new ArrayList<Integer>();
			
			Visitor walker1 = new GetPathVisitor("", false);
			Visitor walker2 = new RunSimulatorVisitor("", false);
				
		   // lay tap testcase ung voi tung option
			BufferedReader br = getReader(index);

			String testcase = br.readLine();
			int nTestcase = 0;
			
			while (testcase != null) {
				// khoi tao khi vao 1 bo test case moi
				if (testcase.contains("Begin test cases.")) {
					totalPass = totalFail = 0;
					for (int i = 1; i < numLine; i++) {
						newPass[i] = newFail[i] = 0;
					}
					//System.out.println(step++);
				}
				// ket thuc test case, danh gia chat luong cua bo test case nay
				else if (testcase.contains("End test cases.")) {
					//int nSizeSlice = sliceProg.getSize();
					//int[] sumFreq = new int[nTestcase];
					//for(int k = 0; k < nTestcase; k++) {
						//sumFreq[k] = 0;
					//}
					nTest = nTestcase;
					// tinh tan suat cua moi cau lenh
					float[][] freqSlice = new float[numLine][nTestcase];
					for(int j = 0; j < nTestcase; j++) {
						for(int i = 1; i < numLine; i++) {
							if(sliceProg.contains(i)) {
								//if(passSlice[i].contains(j+1) || failSlice[i].contains(j+1)) {
									//freqSlice[i][j] = 1;
									//sumFreq[j]++;
								//}
								ArrayList<Integer> temp = testCasePath.get(j);
								int tempSize = temp.size();
								int k = Collections.frequency(temp, i); // chu y tinh dung dan
								freqSlice[i][j] = (float)k /tempSize;
							}
							else {
								freqSlice[i][j] = 0;
							}
						}
					}
					
					// tinh tong moi hang cua freq
					float[] sumColFreq = new float[nTestcase];
					for(int j = 0; j < nTestcase; j++) {
						float sum = 0;
						for(int i = 1; i < numLine; i++)
							sum = sum + freqSlice[i][j];
						sumColFreq[j] = sum;
					}
					// tinh new pass with slicing metric
					for(int i = 1; i < numLine; i++) {
						float TSPass = 0.0f;
						for(int j = 0; j < nTestcase; j++) {
							TSPass += (float)((1 - pTestcase.get(j))*freqSlice[i][j])/(sumColFreq[j]);
						}
						if(totalPass == 0) newPass[i] = 0; 
						else {
							newPass[i] = (float)TSPass/totalPass;
						}
						//System.out.println("Pass " + i + " " + newPass[i]);
					}
					// tinh new fail with slicing metric
					for(int i = 1; i < numLine; i++) {
						float TSFail = 0.0f;
						for(int j = 0; j < nTestcase; j++) {
							TSFail += (pTestcase.get(j)*freqSlice[i][j])/(float)(sumColFreq[j]);
						}
						if(totalFail == 0) newFail[i] = 0; 
						else {
							newFail[i] = (float)TSFail/totalFail;
						}
						//System.out.println("Fail " + i + " " + newFail[i]);
					}
					
					// PSS-SFL
					if(mode == 3)
						tarantulaScoresSlice = tarantulaSlice(newPass, newFail, totalPass, totalFail);
					// su dung ky thuat Ochiai
					else
						tarantulaScoresSlice = ochiaiSlice(newPass, newFail, totalPass, totalFail);
					
					// tarantula technique evaluation
					//Percent tPercent = getPercent(tarantulaScores);
					Percent tPercentSlice = getPercent(tarantulaScoresSlice);
					//mintPercent = tPercent.min;
				    //maxtPercent = tPercent.max;
					mintPercentSlice = tPercentSlice.min;
					maxtPercentSlice = tPercentSlice.max;
					
					step++;
				}
				// so sanh ket qua cua chuong trinh can kiem tra va chuong trinh mau
				// chi de kiem tra viec dung sai
				else {
					//duong thuc thi cua 1 testcase cu the
					//se duoc dung trong dynamic slicing
					String path = (String) labelTree.visit(walker1, testcase);
					//System.out.println(path);
					// tao array list chua path execution
					ArrayList<Integer> pathArrayList = new ArrayList<Integer>();
					String[] arrPath = path.split(";");
					for(String p : arrPath) {
						pathArrayList.add(Integer.parseInt(p));
					}
					
					// cap nhap vao path execution cua test case
					testCasePath.add(pathArrayList);
					
					//simulator ket qua cua sinh vien
					String studentResult = (String) labelTree.visit(walker2, testcase);
					//simulator ket qua cua solution
					String solutionResult = (String) solutionTree.visit(walker2, testcase);
					
					//in testcase va path thuc thi tuong ung ra file testcaseandpath.txt
					//writerTestCasesAndPath.println(testcase);
					//mapping statement to line code
					String stmt2line = "";
					String[] pathArray = path.split(";");
					for(String pathUnit : pathArray)
					{
						stmt2line += statement2line[Integer.parseInt(pathUnit)] + ";";
					} 
					//writerTestCasesAndPath.println(studentResult + ":" + stmt2line);
				
					nTestcase++;
					// test case pass
					if (studentResult.equals(solutionResult)) {
						writerTestCasesAndPath.println("1:" + solutionResult + ":" + studentResult + ":" + stmt2line);
						totalPass++; // tang tong so test case pass
						pTestcase.add(0); // test case passed
						for (int i = 1; i < numLine; i++) {
							if (pathArrayList.contains(i)) {
								//pass[i]++; // tang gia tri pass cho cac cau lenh
										   // tren duong thuc thi
								//passSlice[i].add(nTestcase);
							}
						}
					}
					// test case fail
					else {
						writerTestCasesAndPath.println("0:" + solutionResult + ":" + studentResult + ":" + stmt2line);
						totalFail++; // tang tong so test case fail
						pTestcase.add(1); // test case failed
						// chi xay dung slice cho nhung test cases fail
						//Slice sData = getSliceOfTestcase(graph, pathArrayList, pathArrayList.get(pathArrayList.size()-1));
						//sliceProg.addSlice(sData);
						getSliceOfTestcase(graph, pathArrayList, pathArrayList.get(pathArrayList.size()-1));
						
						for (int i = 1; i < numLine; i++) {
							if (pathArrayList.contains(i)) {
								//fail[i]++; // tang gia tri fail cho cac cau lenh
										   // tren duong thuc thi
								//failSlice[i].add(nTestcase);
							}
						}
					}
				}
				testcase = br.readLine();
			}
	
			//writerFL.println("Tarantula technique with dynamic slicing");
			if(mode == 3) {
				for(int j = 1; j < numLine; j++)
					writerFLSliceTarantula.printf("%d:%.3f\n", statement2line[j], tarantulaScoresSlice[j]);
			}
			else {
				for(int j = 1; j < numLine; j++)
					writerFLSliceOchiai.printf("%d:%.3f\n", statement2line[j], tarantulaScoresSlice[j]);
			}
			//writeToFile("evaluation.txt", eval);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//trinhgiang-21/10/2013
	//in content ra file
	public static void writeToFile(String filename, String content) {
		try{
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
		}catch (Exception e){System.out.println("error in writeToFile "+filename);}
	}
	
	public static void main(String[] args) {		
		try {
			// lay thong tin chay
			// args[0] = student.c 
			// args[1] = version
			version = Integer.parseInt(args[1]);
			// args[2] = failLine
			failLine = Integer.parseInt(args[2]);
			// args[3] = mode
			iMode = Integer.parseInt(args[3]);
			
			// file ket qua
			//writer = new PrintWriter("result.txt", "UTF-8");
			
			//file chua ket qua fault localization
			writerFL = new PrintWriter("FL.txt", "UTF-8");
			writerFLOchiai = new PrintWriter("FLOchiai.txt", "UTF-8");
			writerFLJaccard = new PrintWriter("FLJaccard.txt", "UTF-8");
			writerFLSliceTarantula = new PrintWriter("FLSliceTarantula.txt", "UTF-8");
			writerFLSliceOchiai = new PrintWriter("FLSliceOchiai.txt", "UTF-8");
			
			//file chua tat ca cac paths cua chuong trinh
			//writerAllPaths = new PrintWriter("allpaths.txt", "UTF-8");
			
			//file chua tap testcases da dung
			writerTestCasesAndPath = new PrintWriter("testcasesandpath.txt", "UTF-8");
			
			// tao cay AST cho chuong trinh can kiem tra
			// co the lay numLine tu day
			long t1 = System.currentTimeMillis();
			AST labelTree = getLabelTree(getTree(args[0]));
			AST solutionTree = getTree("solution.c");
			long t2 = System.currentTimeMillis();

			if(debug == 1) return;
			
			//trinhgiang-21/10/2013
			//Standardize source
			Visitor walkerC = new PrettyOutputVisitor(standardSourceFile, false);
			labelTree.visit(walkerC, "no_output_line");
				
			//in ra mapping table
			Ast2MappingTableVisitor ast2Table = new Ast2MappingTableVisitor();
			labelTree.visit(ast2Table, "");
			MappingTable mapTable = ast2Table.getMappingTable();
	
			//trinhgiang-21/10/2013
			//mapping table statement to line
			ArrayList<Integer> mapTableArray = new ArrayList<Integer>();
			statement2line = new int[mapTable.getSize()+1];
			//cau lenh bat dau tu 1
			statement2line[0] = -1;
			mapTableArray.add(-1);
			
			for(int i = 1; i < mapTable.getSize()+1; i++)
			{
				statement2line[i] = mapTable.getStatementId(i-1);
				mapTableArray.add(mapTable.getStatementId(i-1));
				//System.out.println(statement2line[i]);
			}
			//ghi mapping table ra file
			//System.out.println(mapTable.toString());
			writeToFile(mappingTableFile, mapTable.toString());
		
			if(iMode == 3 || iMode == 4) // Dynamic Slicing
			{
				//in ra program dependence graph
				String PDGFilename = "output_graph.txt";
				Ast2GraphVisitor ast2PDG = new Ast2GraphVisitor();
				labelTree.visit(ast2PDG, "");
				// use for dynamic slicing
				PDG graph = ast2PDG.getProgramDependenceGraph();
				graph.updatePD();
				writeToFile(PDGFilename, graph.toString());
			
				// concolic test cases
				for (int i = 5; i < 6; i++) {
					// su dung cong thuc cua ochiai
					readTestCasesSlicing(i, labelTree, solutionTree, graph, iMode);
					//writer.println("***********************");
					//writerFL.println("***********************");
				}
				
				writerFL.close();
				writerFLSliceTarantula.close();
				writerFLSliceOchiai.close();
				writerTestCasesAndPath.close();
				writerFLJaccard.close();
				writerFLOchiai.close();
				
				long tSlicing = System.currentTimeMillis();
				
				// Get the Java runtime
				Runtime runtime = Runtime.getRuntime();
				// Run the garbage collector
				runtime.gc();
				// Calculate the used memory
				long memory = (runtime.totalMemory() - runtime.freeMemory()) / KILOBYTE;
			
				// in thong tin thong ke
				FileWriter fstream = new FileWriter("statistic.txt", true);
				BufferedWriter out = new BufferedWriter(fstream);
				String astTime = (t2 - t1) + "";
				String memoryConsum = memory + ""; // don vi kbytes
				String sliceSt = (tSlicing - t2) + "\t\t\t\t\t" + String.format("%.4f",mintPercentSlice) + "\t\t" + String.format("%.4f",maxtPercentSlice);
				out.write(version + "\t\t" + iMode + "\t\t" + (numLine - 1) + "\t\t" + nTest + "\t\t\t" + memoryConsum + "\t\t\t\t\t" + astTime + "\t\t\t\t\t" + sliceSt +"\n");
				out.close();
				
				System.out.println("Relevant slice");
				System.out.println(sliceProg.toString());
			}
			else // Tarantula
			{
				// concolic test cases
				for (int i = 5; i < 6; i++) {
					readTestCasesSpectrum(i, labelTree, solutionTree, iMode);
					//writer.println("***********************");
					//writerFL.println("***********************");
				}
				writerFL.close();
				writerFLSliceTarantula.close();
				writerFLSliceOchiai.close();
				writerTestCasesAndPath.close();
				writerFLJaccard.close();
				writerFLOchiai.close();
				
				long tTarantula = System.currentTimeMillis();
				
				// Get the Java runtime
				Runtime runtime = Runtime.getRuntime();
				// Run the garbage collector
				runtime.gc();
				// Calculate the used memory
				long memory = (runtime.totalMemory() - runtime.freeMemory()) / KILOBYTE;
			
				// in thong tin thong ke
				FileWriter fstream = new FileWriter("statistic.txt", true);
				BufferedWriter out = new BufferedWriter(fstream);
				String astTime = (t2 - t1) + "";
				String memoryConsum = memory + "";
				String tarantulaSt = (tTarantula - t2) + "\t\t\t\t\t" + String.format("%.4f",mintPercent) + "\t\t" + String.format("%.4f",maxtPercent);
				out.write(version + "\t\t" + iMode + "\t\t" + (numLine - 1) + "\t\t" + nTest + "\t\t\t" + memoryConsum + "\t\t\t\t\t" + astTime + "\t\t\t\t\t" + tarantulaSt +"\n");
				out.close();
			}		
		
			
			// lay tat ca cac path
			//dung cho viec tao testcase
			//day la qua trinh phan tich tinh
			/*
			List<String> paths = getAllPaths(labelTree);
			for(String path : paths) {
				//day la nhung duong thuc thi co ban
				//ung dung mon testing
				writerAllPaths.println(path);
			}
			*/
			
			/*
			String testcase = "3.0;2.0";
			String path = (String) labelTree.visit(new GetPathVisitor("", false), testcase);
			System.out.println(path);
			*/
			
			/*
			for (String path : paths) {
				System.out.println(path);
			}

			//System.out.println(Utils.compare("1;2;3;4;5;3;6;", "1;2;3;4;5;6;"));
			/*			
			String testcase = "3;";
			String path = (String) labelTree.visit(new GetPathVisitor("", false), testcase);
			System.out.println(path);
			
			System.out.println(path.split(";").length);

			System.out.println(Utils.compare(path, paths.get(0)));

			for (int i = 0; i < Utils.MaxChange; i++) {
				System.out.println(Utils.change(testcase, i));
			}
			*/
			//System.out.println(Utils.compare("1;2;3;4;5;3;", "1;2;3;4;"));
			
			//writer.println("Compare time:");
			//writer.println("***********************");

			// tinh thoi gian sinh test cases cho tung options
			//chi chay 1 lan
			/*
			for (int i = 0; i < 1; i++) {
				calcTime(i, labelTree, paths);
				writer.println("***********************");
			}
			*/
			
			
			//writer.println();
			//writer.println("Compare percent:");
			//writer.println("***********************");

			// doc test cases sinh ra do tung option va phan tich
			// chi chay 1 lan
			// day la qua trinh dynamic analyst nen khong dung tat ca cac paths 
			// lay test case tu tao
			// trinhgiang-16/10/2013	
			//writer.close();
			//writerAllPaths.close();
			//writerStatistic.close();
			/*
			for (int i = 1; i < 2; i++) {
				calcTime(i, labelTree, paths);
			//	writer.println("***********************");
			}
			*/
			/*
			int count1 = 0;
			int countm1 = 0;
			for (int i = 0; i < 1000; i++) {
			int j = Utils.random();
			if (j == 1)
				count1++;
			if (j == -1)
				countm1++;
			
			}
		
			System.out.println(count1);
			System.out.println(countm1);*/
			//System.out.println(Utils.exist("-1;\n2;\n", "1;"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/*
Random Generator
Simple Hill Climbing Generator
Steepest Hill Climbing Generator
Simulated Annealing Generator
Genetic Generator
2931
31904
31323
31416
4010
*/

/*
Random Generator
Simple Hill Climbing Generator
Steepest Hill Climbing Generator
Simulated Annealing Generator
Genetic Generator
346
3415
3375
3259
347
*/
