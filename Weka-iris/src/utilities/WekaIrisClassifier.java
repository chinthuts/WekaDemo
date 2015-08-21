package utilities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Initializing - Initializing all the weka components - Loading iris.2D.arff -
 * Specifying the classifier. - Building a model with the training data and
 * classifier
 */
public class WekaIrisClassifier {

	private Classifier scheme;
	private Instances trainerData;
	private String trainingFilePath;

	/**
	 * Initializing weka model in this constructor.
	 */
	public WekaIrisClassifier() {
		this.trainingFilePath = "iris.2D.arff";
		trainerData = loadarffFile(trainingFilePath);
		initializer();
	}

	/**
	 * Loading arff file from string and creating a Instance that weka model can
	 * recognize
	 */

	private Instances loadarffFileString(String arff) throws Exception {
		byte[] bytearff = arff.toString().getBytes();
		InputStream istream = new ByteArrayInputStream(bytearff);
		DataSource source = new DataSource(istream);

		return setClassIndex(source);
	}

	/**
	 * Loading arff file from .arff file from a specific path and creating a
	 * Instance that weka model can recognize
	 */
	private Instances loadarffFile(String filePath) {
		try {
			DataSource source = new DataSource(trainingFilePath);
			return setClassIndex(source);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Instances setClassIndex(DataSource source) {
		try {
			trainerData = source.getDataSet();

			if (trainerData.classIndex() == -1) {
				trainerData.setClassIndex(trainerData.numAttributes() - 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return trainerData;
	}

	/**
	 * Building a model with weka training data and a classifier.
	 */
	private void initializer() {
		try {
			scheme = new FilteredClassifier();
			// Specifying the classifier. (If we are using any filter, then we
			// should also specify it here).
			scheme.setOptions(weka.core.Utils.splitOptions("weka.classifiers.rules.NNge -G 5 -I 5"));

			scheme.buildClassifier(trainerData);
		} catch (Exception e) {
			System.out.println("initializeClassifier\n");
			e.printStackTrace();
		}
	}

	/**
	 * Checking if the test data is correctly classified.
	 */
	public int isCorrectIris(String arff) {
		try {
			Instances testData = loadarffFileString(arff);
			Evaluation evaluator = new Evaluation(trainerData);

			Instance in = testData.instance(0);
			int prediction = (int) evaluator.evaluateModelOnce(scheme, in);
			// Returns the prediction
			return prediction;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
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
