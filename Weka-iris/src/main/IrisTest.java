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
			int result1 = wic.isCorrectIris(wic.WekaarffFormat(1.4, 0.2, "Iris-virginica"));
			System.out.println("\nResult 1:- Expected:Iris-setosa | Result:" + wic.GetLabelName(result1));

			int result2 = wic.isCorrectIris(wic.WekaarffFormat(5, 2, "Iris-versicolor"));
			System.out.println("Result 2:- Expected:Iris-virginica | Result:" + wic.GetLabelName(result2));

			int result3 = wic.isCorrectIris(wic.WekaarffFormat(4.5, 1.5, "Iris-setosa"));
			System.out.println("Result 3:- Expected:Iris-versicolor | Result:" + wic.GetLabelName(result3));

		} catch (Exception e) {
			System.out.println("Initialization Failed");
		}
	}
}
