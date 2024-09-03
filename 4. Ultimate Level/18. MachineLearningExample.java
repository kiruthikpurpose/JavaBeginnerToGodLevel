import org.apache.commons.math3.stat.regression.SimpleRegression;

public class MachineLearningExample {
    public static void main(String[] args) {
        SimpleRegression regression = new SimpleRegression(true);

        regression.addData(1, 1);
        regression.addData(2, 2);
        regression.addData(3, 3);
        regression.addData(4, 4);
        regression.addData(5, 5);

        System.out.println("Slope: " + regression.getSlope());
        System.out.println("Intercept: " + regression.getIntercept());
        System.out.println("Prediction for 6: " + regression.predict(6));
    }
}
