package main;

import utilities.WekaIrisClassifier;

public class IrisTest {
	/**
	 * Iris Test Main
	 */
	public static void main(String[] args) {
		new IrisTest();
	}

	public IrisTest() {
		System.out.println("Weka Testing");
		try {
			/**
			 * Initializing Weka model
			 */
			WekaIrisClassifier wic = new WekaIrisClassifier();
			System.out.println("Initialized");

			/**
			 * Testing the model with hardcoded values
			 */
			int result1 = wic.isCorrectIris(WekaarffFormat(1.4, 0.2, "Iris-virginica"));
			System.out.println("\nResult 1:- Expected:Iris-setosa | Result:" + GetLabelName(result1));

			int result2 = wic.isCorrectIris(WekaarffFormat(5, 2, "Iris-versicolor"));
			System.out.println("Result 2:- Expected:Iris-virginica | Result:" + GetLabelName(result2));

			int result3 = wic.isCorrectIris(WekaarffFormat(4.5, 1.5, "Iris-setosa"));
			System.out.println("Result 3:- Expected:Iris-versicolor | Result:" + GetLabelName(result3));

		} catch (Exception e) {
			System.out.println("Initialization Failed");
		}
	}

	/**
	 * Creating arff formatted string
	 */
	public String WekaarffFormat(double petalLength, double petalWidth, String prediction) {
		String arffFile = "@relation iris-weka.filters.unsupervised.attribute.Remove-R1-2 \n\n"
				+ "@attribute petallength numeric\n" + "@attribute petalwidth numeric\n"
				+ "@attribute class {Iris-setosa,Iris-versicolor,Iris-virginica}\n\n" + "@data\n";
		arffFile += petalLength + "," + petalWidth + "," + prediction + "\n";

		return arffFile;
	}

	/**
	 * Determine correct class from result number
	 */
	public String GetLabelName(int key) {
		switch (key) {
		case 0:
			return "Iris-setosa";
		case 1:
			return "Iris-versicolor";
		case 2:
			return "Iris-virginica";
		default:
			return "Iris-setosa";
		}

	}
}
