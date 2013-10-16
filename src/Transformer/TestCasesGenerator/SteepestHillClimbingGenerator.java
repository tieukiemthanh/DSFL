package Transformer.TestCasesGenerator;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;
import Transformer.DependenceGraph.*;

import java.util.*;
import java.io.*;

public class SteepestHillClimbingGenerator extends Generator {

	public SteepestHillClimbingGenerator() {
		System.out.println("Steepest Hill Climbing Generator");
	}

	public SteepestHillClimbingGenerator(AST _tree, List<String> _paths) {
		System.out.println("Steepest Hill Climbing Generator");
		tree = _tree;
		paths = _paths;
		try {
			writer = new PrintWriter("steepest.txt", "UTF-8");
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
			
			// so test case bang so path cua chuong trinh nhan voi
			// so test case toi da moi path
			while (i < paths.size() * Utils.NumTestsPerPath) {
				String path = paths.get(i % paths.size());
			
				outer:
				// moi path duoc thu sinh MaxLoop lan
				for (int k = 0; k < Utils.MaxLoop; k++) {
					String input = "";
					
					// test case dau tien duoc sinh random
					for (int j = 0; j < Utils.NumPara; j++) {
						int x = Utils.random();
						input += x + ";";
					}

					// lay path cua test case
					String inputPath = (String) tree.visit(walker, input);

					// tinh diem cua test case
					int score = Utils.compare(inputPath, path);
					
					// neu dat yeu cau thi ghi nhan lai test case
					if (score == path.split(";").length) {
						if (!Utils.exist(testcases, input)) {
							testcases += input + "\n";
						}
						break outer;
					} else {
						while (true) {
							// maxScore bang score hien tai
							int maxScore = score;
			
							// maxInput bang input hien tai
							String maxInput = input;
			
							// lap qua tat ca cac input co the sinh ra tu input hien tai
							for (int j = 0; j < Utils.MaxChange * Utils.NumPara; j++) {
								String newInput = Utils.change(input, j);
								String newInputPath = (String) tree.visit(walker, newInput);
								int newScore = Utils.compare(newInputPath, path);

								// lay ra input co diem lon nhat
								if (newScore > maxScore) {
									maxScore = newScore;
									maxInput = newInput;
								}
							}

							// neu co input co diem lon hon score ban dau thi cap nhat input
							// lap lai vong lap
							if (maxScore > score) {
								score = maxScore;
								input = maxInput;
							} else {
								break;
							}

							// neu input dat yeu cau thi ghi nhan lai
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