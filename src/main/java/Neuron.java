import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private List<Double> wagesIn;
    private List<Double> wagesOut;
    private double activationFunctionValue;
    private double error;

    public Neuron() {
        this.wagesIn = new ArrayList<>();
        this.wagesOut = new ArrayList<>();
        this.activationFunctionValue=0;
        this.error=0;
    }

    public Neuron(int numberOfWages) {
        this.wagesIn = new ArrayList<>();
        this.wagesOut = new ArrayList<>();
        this.activationFunctionValue = 0;
        this. error = 0;
        initializeWagesIn(numberOfWages);
    }

    public double getActivationFunctionValue() {
        return activationFunctionValue;
    }

    private void initializeWagesIn(int numberOfWages) {
        for (int i = 0; i < numberOfWages; i++) {
            //wages.add(Math.random());
            wagesIn.add((double)i+1);
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
        for (int i = 0; i < wagesIn.size(); i++) {
            activationFunctionValue += wagesIn.get(i) * calculatedValues.get(i);
        }
        //activationFunctionValue = 1.0/(1.0+Math.exp(-activationFunctionValue));
    }
//zmiana
//    public void calculateError(List<Double> calculatedValues,List<Double> wagesFromNextLayer) {
//        for (int i = 0; i < wagesFromNextLayer.size(); i++) {
//            error += wagesFromNextLayer.get(i) * calculatedValues.get(i);
//        }
//    }
    public void calculateError(List<Double> calculatedValues) {
        for (int i = 0; i < wagesOut.size(); i++) {
            error += wagesOut.get(i) * calculatedValues.get(i);
        }
    }

    public void updateWages(List<Double> inputValues) {
        for (int i = 0; i < wagesIn.size(); i++) {
            wagesIn.set(i,wagesIn.get(i)+0.2*error*inputValues.get(i));
        }

    }

    public List<Double> getWagesIn() {
        return wagesIn;
    }

    public List<Double> getWagesOut() {
        return wagesOut;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
