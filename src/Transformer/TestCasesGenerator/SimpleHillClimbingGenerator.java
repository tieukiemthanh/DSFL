package Transformer.TestCasesGenerator;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;
import Transformer.DependenceGraph.*;

import java.util.*;
import java.io.*;

public class SimpleHillClimbingGenerator extends Generator {

	public SimpleHillClimbingGenerator() {
		System.out.println("Simple Hill Climbing Generator");
	}

	public SimpleHillClimbingGenerator(AST _tree, List<String> _paths) {
		System.out.println("Simple Hill Climbing Generator");
		tree = _tree;
		paths = _paths;
		try {
			writer = new PrintWriter("simple.txt", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public long generate() {
		try {
			testcases = "";

			int i = 0;

			Visitor walker = new GetPathVisitor("", false);
			
			// thoi diem truoc khi sinh test case
			long t1 = System.currentTimeMillis();

			// so test case bang so path trong chuong trinh nhan voi
			// so test case toi da moi path
			while (i < paths.size() * Utils.NumTestsPerPath) {
				String path = paths.get(i % paths.size());
							
				// so lan thu voi moi path
				outer:
				for (int k = 0; k < Utils.MaxLoop; k++) {
					String input = "";
					// dau tien cac test case duoc sinh ra random
					for (int j = 0; j < Utils.NumPara; j++) {
						int x = Utils.random();
						input += x + ";";
					}

					// lay path cua test case vua sinh ra
					String inputPath = (String) tree.visit(walker, input);
					
					// tinh diem cua path nay
					int score = Utils.compare(inputPath, path);
					
					// neu dat yeu cau thi ghi lai test case
					if (score == path.split(";").length) {
						if (!Utils.exist(testcases, input)) {
							testcases += input + "\n";
						}
						break outer;
					} else {
						// so lan thay doi test case MaxChange * NumPara
						for (int j = 0; j < Utils.MaxChange * Utils.NumPara; j++) {
							//System.out.println(j);
					
							// thay doi test case
							String newInput = Utils.change(input, j);
							
							// tim duong thuc thi cua test case moi
							String newInputPath = (String) tree.visit(walker, newInput);

							// tinh diem cua test case moi
							int newScore = Utils.compare(newInputPath, path);

							// cap nhat lai test case neu diem moi lon hon diem cu
							if (newScore > score) {
								input = newInput;
								score = newScore;
								j = 0;
							}

							// neu diem dat yeu cau thi ghi nhan lai test case
							if (score == path.split(";").length) {
								if (!Utils.exist(testcases, input)) {
									testcases += input + "\n";
								}
								break outer;
							}
						}
					}
				}	
				
				i++;
			}

			// thoi diem ket thuc viec sinh test case
			long t2 = System.currentTimeMillis();

			return t2 - t1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}