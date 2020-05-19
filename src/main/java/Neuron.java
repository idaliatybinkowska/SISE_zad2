import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private List<Double> wages;
    private double activationFunctionValue;
    private double error;

    public Neuron() {
        this.wages = new ArrayList<>();
    }

    public Neuron(int numberOfWages) {
        wages = new ArrayList<>();
        initializeWages(numberOfWages);
        this.activationFunctionValue = 0;
        this.error = 0;
    }


    public double getActivationFunctionValue() {
        return activationFunctionValue;
    }


    private void initializeWages(int numberOfWages) {
        for (int i = 0; i < numberOfWages+1; i++) {
            wages.add(Math.random());
        }
    }


    public void calculateSigmoidalActivationFunction(List<Double> calculatedValues) {
        activationFunctionValue = 0;
        for (int i = 0; i < wages.size()-1; i++) {
            activationFunctionValue += wages.get(i) * calculatedValues.get(i);
        }
        activationFunctionValue += wages.get(wages.size()-1); //bias
        activationFunctionValue = 1.0/(1.0+Math.exp(-activationFunctionValue));
    }


    public void calculateLinearActivationFunction(List<Double> calculatedValues) {
        activationFunctionValue = 0;
        for (int i = 0; i < wages.size()-1; i++) {
            activationFunctionValue += wages.get(i) * calculatedValues.get(i);
        }
        activationFunctionValue +=  wages.get(wages.size()-1); //bias
    }

    public void calculateError(List<Double> calculatedValues,List<Double> wagesFromNextLayer) {
        error = 0;
        for (int i = 0; i < wagesFromNextLayer.size(); i++) {
            error += wagesFromNextLayer.get(i) * calculatedValues.get(i);
        }
    }

    public void updateWages(List<Double> inputValues) {
        for (int i = 0; i < wages.size()-1; i++) {
            wages.set(i,wages.get(i)+0.0001*error*activationFunctionValue*(1-activationFunctionValue)*inputValues.get(i));
        }
        wages.set(wages.size()-1,wages.get(wages.size()-1)+0.0001*error*activationFunctionValue*(1-activationFunctionValue)*1); //bias
    }

    public void updateWagesForLinearLayer(List<Double> inputValues) {
        for (int i = 0; i < wages.size()-1; i++) {
            wages.set(i,wages.get(i)+0.0001*error*inputValues.get(i));
        }
        wages.set(wages.size()-1,wages.get(wages.size()-1)+0.0001*error*1); //bias
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
