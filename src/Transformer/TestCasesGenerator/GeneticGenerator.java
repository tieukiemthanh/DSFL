package Transformer.TestCasesGenerator;

import Transformer.Parser.*;
import Transformer.ASTs.*;
import Transformer.CodeGeneration.*;
import Transformer.DependenceGraph.*;

import java.util.*;
import java.io.*;

public class GeneticGenerator extends Generator {

	// lop gom input va diem cua input
	private class Input implements Comparable<Input> {
		public String input;
		public Integer score;

		public Input() {
			input = "";
			score = 0;
		}

		public Input(String _input) {
			input = _input;
			score = 0;
		}

		public Input(String _input, int _score) {
			input = _input;
			score = _score;
		}

		public String toString() {
			return input + " " + score;
		}

		// cac input duoc sap thu tu dua tren diem
		public int compareTo(Input other) {
			return score.compareTo(other.score);
		}
	}

	public GeneticGenerator() {
		System.out.println("Genetic Generator");
	}

	public GeneticGenerator(AST _tree, List<String> _paths) {
		System.out.println("Genetic Generator");
		tree = _tree;
		paths = _paths;
		try {
			writer = new PrintWriter("genetic.txt", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ham tinh trung binh tung input de cross over
	public String average(String _input1, String _input2) {
		String[] input1 = _input1.split(";");
		String[] input2 = _input2.split(";");

		String input = "";

		for (int i = 0; i < input1.length; i++) {
			int x = Integer.parseInt(input1[i]);
			int y = Integer.parseInt(input2[i]);
			input += ((x + y) / 2) + ";";
		}
	
		return input;
	}

	public int chooseIndex(int a, int b, int c, int d) {
		int[] l = {a, b, c, d};
		Random r = new Random();
		return l[r.nextInt(l.length)];
	}

	// 5 input moi duoc tao ra bang cross over
	public void crossOver(Input[] inputArray) {
		String input = "";
		for (int i = 0; i < Utils.NumPara; i++) {
			input += Utils.random() + ";";
		}

		inputArray[0] = new Input(input);
		/*
		inputArray[1] = new Input(average(inputArray[5].input,
								inputArray[chooseIndex(6, 7, 8, 9)].input));
		inputArray[2] = new Input(average(inputArray[6].input,
								inputArray[chooseIndex(5, 7, 8, 9)].input));
		inputArray[3] = new Input(average(inputArray[7].input,
								inputArray[chooseIndex(5, 6, 8, 9)].input));
		inputArray[4] = new Input(average(inputArray[8].input,
								inputArray[chooseIndex(5, 6, 7, 8)].input));
		*/
		inputArray[1] = new Input(average(inputArray[5].input, inputArray[6].input));
		inputArray[2] = new Input(average(inputArray[6].input, inputArray[7].input));
		inputArray[3] = new Input(average(inputArray[7].input, inputArray[8].input));
		inputArray[4] = new Input(average(inputArray[8].input, inputArray[9].input));
		/*
		input = "";
		for (int i = 0; i < Utils.NumPara; i++) {
			input += Utils.random() + ";";
		}

		inputArray[9] = new Input(input);
		*/
	}

	public void shuffleArray(Input[] ar) {
		Random rnd = new Random();
	
		for (int i = ar.length - 1; i > 0; i--) {
    		int index = rnd.nextInt(i + 1);
	
			// Simple swap
			Input a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public long generate() {
		try {
			testcases = "";

			int i = 0;

			Visitor walker = new GetPathVisitor("", false);
			
			long t1 = System.currentTimeMillis();
			
			// so test case bang so path nhan so test toi da moi path
			while (i < paths.size() * Utils.NumTestsPerPath) {
				String path = paths.get(i % paths.size());

				int score = 0;

				// tao ra 10 test case dau tien
				Input[] inputArray = new Input[10];

				// 10 test case dau tien duoc tao random
				for (int j = 0; j < 10; j++) {
					String input = "";
					
					for (int k = 0; k < Utils.NumPara; k++) {
						int x = Utils.random();
						input += x + ";";
					}
					//System.out.println("New input: " + input);
					// lay path cua tung test case
					String inputPath = (String) tree.visit(walker, input);
					
					// tinh diem cua tung test case
					score = Utils.compare(inputPath, path);
					// neu co test case dat yeu cau thi ghi nhan lai
					if (score == path.split(";").length) {
						if (!Utils.exist(testcases, input)) {
							testcases += input + "\n";
						}
						break;
					} 
					// neu khong thi dua test case do vao day
					else {
						inputArray[j] = new Input(input, score);
					}
				}

				// chua co test case dat yeu cau
				if (score != path.split(";").length) {
					outer:
					// da tao lan dau nen bay gio so lan lap tru di 1
					for (int k = 0; k < Utils.MaxLoop - 1; k++) {
						// tron lan cac test case
						shuffleArray(inputArray);

						// cac test case duoc sap thu tu
						Arrays.sort(inputArray);
						/*for (int l = 0; l < 10; l++) {
							System.out.println("Input: " + inputArray[l].input);
						}*/
						
						// cross over giua cac test case cao diem
						crossOver(inputArray);

						// chi can xet 5 test case moi
						for (int j = 0; j < 10 / 2; j++) {
							//if (j != 5 && j != 6 && j != 7 && j != 8) {
								String inputPath = (String) tree.visit(walker, inputArray[j].input);
								score = Utils.compare(inputPath, path);
								//System.out.println("Cross over input: " + inputArray[j].input);
								// neu co test case dat yeu cau thi ghi nhan lai
								if (score == path.split(";").length) {
									if (!Utils.exist(testcases, inputArray[j].input)) {
										testcases += inputArray[j].input + "\n";
									}
									break outer;
								}
								// neu khong chi can cap nhat lai diem cho test case moi
								else {
									inputArray[j].score = score;
								}
							//}
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