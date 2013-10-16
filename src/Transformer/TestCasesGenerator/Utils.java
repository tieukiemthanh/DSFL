package Transformer.TestCasesGenerator;

import java.util.*;
import java.io.*;

public class Utils {

	// can duoi
	//public static final int Min = -128;

	// can tren
	//public static final int Max = 127;
	public static final int Max = 256;

	// so lan lap toi da cho 1 path
	public static final int MaxLoop = 10;

	// so lan thay doi gia tri toi da cho 1 bien
	public static final int MaxChange = 12;

	// so parameter cua ham can kiem tra
	public static final int NumPara = 2;

	// so test case toi da cho moi path
	public static final int NumTestsPerPath = 100;

	// bien random de sinh test case
	public static Random r = new Random();

	// ham kiem tra xem test case da co hay chua
	// tra ve true neu co
	public static boolean exist(String testcases, String input) {
		String[] testcasesArray = testcases.split("\n");

		for (String testcase : testcasesArray) {
			if (testcase.equals(input)) {
				return true;
			}
		}

		return false;
	}

	// so sanh duong thuc thi can sinh ra voi duong thuc thi do
	// test case sinh ra
	/*
	public static int compare(String path1, String path2) {
		// chia thanh cac steps
		String[] steps1 = path1.split(";");
		String[] steps2 = path2.split(";");

		int score = 0;

		int size1 = steps1.length;
		int size2 = steps2.length;

		int max = size1 > size2 ? size1 : size2;
		int min = size1 < size2 ? size1 : size2;

		// moi step giong duoc cong 1 diem
		for (int i = 0; i < min; i++) {
			if (steps1[i].equals(steps2[i])) {
				score++;
			} else {
				break;
			}
		}

		// tru diem do chenh lenh chieu dai
		score -= max - min;

		return score;
	}
	*/
	public static int compare(String inputPath, String path) {
		// cac buoc cua duong thuc thi can sinh test case
		String[] steps = path.split(";");

		// diem khoi tao
		int score = 0;

		// voi moi step neu duong thuc thi cua input co chua step
		// nay thi cong diem
		for (String step : steps) {
			if (inputPath.contains(step + ";")) {
				score++;
			}
		}

		// list chua nhung vong lap co the trong input path
		List<String> loops = new ArrayList<String>();

		// cac buoc tren duong thuc thi cua input path
		String[] inputSteps = inputPath.split(";");
		
		for (int i = 0; i < inputSteps.length; i++) {
			// lay tung step
			String step = inputSteps[i];
			
			// tim index co cung gia tri step
			int lastIndex = findLastIndex(step, inputSteps, i);
			
			// neu i khong bang lastIndex co nghia co vong lap
			if (i != lastIndex) {
				// lay ra vong lap
				loops.add(createLoop(inputSteps, i, lastIndex));
				
				// cap nhat i, bo qua nhung gia tri giua
				i = lastIndex;
			}
		}

		// tru di nhung step thua khong nam trong loop
		for (int i = 0; i < inputSteps.length; i++) {
			// lay tung step
			String step = inputSteps[i];

			// neu step khong nam trong path va cung khong
			// nam trong vong lap thi tru diem
			if (checkStep(step, loops, path)) {
				score--;
			}
		}

		return score;

	}

	// ham kiem tra step thua, tra ve true neu thua
	public static boolean checkStep(String step, List<String> loops, String path) {
		// neu step co trong path thi tra ve false
		if (path.contains(step + ";")) {
			return false;
		}
		// neu step khong co trong path nhung co trong loops thi tra ve false
		else {
			for (String loop : loops) {
				if (loop.contains(step + ";")) {
					return false;
				}
			}
		}
		// tra ve true
		return true;
	}

	// ham tim index cuoi cung co gia tri giong step
	public static int findLastIndex(String step, String[] inputSteps, int begin) {
		int lastIndex = begin;

		for (int i = begin + 1; i < inputSteps.length; i++) {
			if (step.equals(inputSteps[i])) {
				lastIndex = i;
			}
		}

		return lastIndex;
	}

	// ham tao string loop tu begin den end
	public static String createLoop(String[] inputSteps, int begin, int end) {
		String out = "";
	
		for (int i = begin; i <= end; i++) {
			out += inputSteps[i] + ";";
		}
	
		return out;
	}

	// sinh test case random
	public static int random() {
		return r.nextInt(Utils.Max) - Utils.Max / 2;
		//return Utils.Min + (int)(Math.random() * ((Utils.Max - Utils.Min) + 1));
	}

	// thay doi test case
	public static String change(String _input, int index) {
		//System.out.println("Old test: " + _input);
		String[] input = _input.split(";");
		String output = "";

		// gia tri index cho biet can thay doi bien nao va nhu the nao
		// gia su input co length bang 2
		// index = 0 nghia la thay doi bien co chi so bang 0 % 2 = 0
		// va thay doi theo offset co chi so bang 0 / 2 = 0
		// index = 1 nghia la thay doi bien co chi so bang 1 % 2 = 1
		// va thay doi theo offset co chi so bang 1 / 2 = 0
		// index = 2 nghia la thay doi bien co chi so bang 2 % 2 = 0
		// va thay doi theo offset co chi so bang 2 / 2 = 1
		for (int i = 0; i < input.length; i++) {
			if (i == index % input.length) {
				output += (Integer.parseInt(input[i]) + offset(index / input.length)) + ";";
			} else {
				output += input[i] + ";";
			}
		}
	
		//System.out.println("New test: " + output);

		return output;
	}

	// do doi khi thay doi test case
	public static int offset(int index) {
		switch (index) {
			case 0: return 1;
			case 1: return -1;
			case 2: return 2;
			case 3: return -2;
			case 4: return 5;
			case 5: return -5;
			case 6:	return 10;
			case 7: return -10;
			case 8: return 50;
			case 9: return -50;
			case 10: return 100;
			case 11: return -100;
		}
		return 0;
	}

	// viet test case ra file
	public static void write(PrintWriter writer, String testcases) {
		try {
			writer.println("Begin test cases.");
			writer.print(testcases);
			writer.println("End test cases.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}