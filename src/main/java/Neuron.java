import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private List<Double> wages;
    private double activationFunctionValue = 0;
    private double error = 0;

    public Neuron() {
        this.wages = new ArrayList<>();
    }

    public Neuron(int numberOfWages) {
        wages = new ArrayList<>();
        initializeWages(numberOfWages);
    }

    public double getActivationFunctionValue() {
        return activationFunctionValue;
    }

    private void initializeWages(int numberOfWages) {
        for (int i = 0; i < numberOfWages; i++) {
            wages.add(Math.random());
        }
    }

//    public double calculateActivationFunction(List<Double> calculatedValues) {
////        double value = 0;
//        for (int i = 0; i < wages.size(); i++) {
//            activationFunctionValue += wages.get(i) * calculatedValues.get(i);
//        }
//        activationFunctionValue = 1.0/(1.0+Math.exp(-activationFunctionValue));
//        return activationFunctionValue;
//    }

    public void calculateActivationFunction(List<Double> calculatedValues) {
        for (int i = 0; i < wages.size(); i++) {
            activationFunctionValue += wages.get(i) * calculatedValues.get(i);
        }
        activationFunctionValue = 1.0/(1.0+Math.exp(-activationFunctionValue));
    }

    public void calculateError(List<Double> calculatedValues) {
        for (int i = 0; i < wages.size(); i++) {
            error += wages.get(i) * calculatedValues.get(i);
        }
    }



    public List<Double> getWages() {
        return wages;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
