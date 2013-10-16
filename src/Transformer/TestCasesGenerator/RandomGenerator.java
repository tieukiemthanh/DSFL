package Transformer.TestCasesGenerator;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;
import Transformer.DependenceGraph.*;

import java.util.*;
import java.io.*;

public class RandomGenerator extends Generator {

	public RandomGenerator() {
		System.out.println("Random Generator");
	}

	public RandomGenerator(AST _tree, List<String> _paths) {
		System.out.println("Random Generator");
		tree = _tree;
		paths = _paths;
		try {
			writer = new PrintWriter("random.txt", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long generate() {
		try {
			testcases = "";

			int i = 0;

			Visitor walker = new GetPathVisitor("", false);
			
			// thoi diem bat dau sinh test case
			long t1 = System.currentTimeMillis();
			
			// so test case bang so path trong chuong trinh nhan voi
			// so test case toi da moi path
			while (i < paths.size() * Utils.NumTestsPerPath) {
				String path = paths.get(i % paths.size());
				
				outer:
				// so lan lap ung voi moi path
				for (int k = 0; k < Utils.MaxLoop; k++) {
					String input = "";
					//System.out.println(k);
					// dau tien test case duoc sinh ra random
					for (int j = 0; j < Utils.NumPara; j++) {
						int x = Utils.random();
						input += x + ";";
					}

					// lay duong thuc thi
					String inputPath = (String) tree.visit(walker, input);

					// tinh diem cua test case
					int score = Utils.compare(inputPath, path);
					
					// neu dat yeu cau ghi nhan lai test case
					if (score == path.split(";").length) {
						if (!Utils.exist(testcases, input)) {
							testcases += input + "\n";
						}
						break outer;
					}
				}

				i++;
			}

			// thoi diem ket thuc sinh test case
			long t2 = System.currentTimeMillis();

			return t2 - t1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}