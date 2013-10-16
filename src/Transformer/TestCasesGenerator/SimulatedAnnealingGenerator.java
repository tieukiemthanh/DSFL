package Transformer.TestCasesGenerator;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;
import Transformer.DependenceGraph.*;

import java.util.*;
import java.io.*;

public class SimulatedAnnealingGenerator extends Generator {

	float t = 0.0f;

	public SimulatedAnnealingGenerator() {
		System.out.println("Simulated Annealing Generator");
	}

	public SimulatedAnnealingGenerator(AST _tree, List<String> _paths) {
		System.out.println("Simulated Annealing Generator");
		tree = _tree;
		paths = _paths;
		try {
			writer = new PrintWriter("simulated.txt", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float epsilon(int d) {
		t = t - 0.5f > 1 ? t - 0.5f : 1;
		return (float) Math.exp(-d / t);
	}

	public long generate() {
		try {
			testcases = "";

			int i = 0;

			Visitor walker = new GetPathVisitor("", false);
			
			// thoi diem bat dau sinh test case
			long t1 = System.currentTimeMillis();
			
			// so test case can sinh ra bang so path cua chuong trinh nhan
			// so test case moi path
			while (i < paths.size() * Utils.NumTestsPerPath) {
				String path = paths.get(i % paths.size());
				
				outer:
				for (int k = 0; k < Utils.MaxLoop; k++) {
					// khoi dong lai t truoc moi lan sinh test case
					t = 10.5f;

					String input = "";
					
					// test case dau tien duoc sinh ra random
					for (int j = 0; j < Utils.NumPara; j++) {
						int x = Utils.random();
						input += x + ";";
					}
					// lay path cua test case
					String inputPath = (String) tree.visit(walker, input);

					// tinh diem cua test case
					int score = Utils.compare(inputPath, path);
					
					// neu dat yeu cau thi ghi nhan lai
					if (score == path.split(";").length) {
						if (!Utils.exist(testcases, input)) {
							testcases += input + "\n";
						}
						break outer;
					} else {
						// so lan thay doi bang so lan thay doi toi da nhan voi so bien
						for (int j = 0; j < Utils.MaxChange * Utils.NumPara; j++) {
							String newInput = Utils.change(input, j);
							String newInputPath = (String) tree.visit(walker, newInput);
							int newScore = Utils.compare(newInputPath, path);
							
							// chuyen sang input moi neu diem lon hon hoac
							// random < Epsilon
							if (newScore > score || (newScore < score && Math.random() < epsilon(score - newScore))) {
								input = newInput;
								score = newScore;
								j = 0;
							}

							// neu test case dat yeu cau thi ghi nhan lai
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