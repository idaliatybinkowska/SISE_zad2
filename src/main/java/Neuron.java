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

    public Neuron(int numberOfWages,List<Record> setOfWagesForNeuron) {
        wages = new ArrayList<>();
        initializeWages(numberOfWages, setOfWagesForNeuron);
        this.activationFunctionValue = 0;
        this.error = 0;
    }

    public double getActivationFunctionValue() {
        return activationFunctionValue;
    }

//    private void initializeWages(int numberOfWages,List<Record> setOfWagesForNeurons) {
//        for (int i = 0; i < numberOfWages; i++) {
//            //wages.add(Math.random()*6000);
//            int randomNumber = (int)(Math.random()*1540);
//            wages.add(setOfWagesForNeurons.get(randomNumber).getMeasurement_xy().get(0));
//            wages.add(setOfWagesForNeurons.get(randomNumber).getMeasurement_xy().get(1));
//            //wages.add((double)i+1);
//        }
//    }

    private void initializeWages(int numberOfWages,List<Record> setOfWagesForNeurons) {
        //wages.add(Math.random());
        int randomNumber = (int)(Math.random()*1540);
        wages.add(setOfWagesForNeurons.get(randomNumber).getMeasurement_xy().get(0));
        wages.add(setOfWagesForNeurons.get(randomNumber).getMeasurement_xy().get(1));
        //wages.add((double)i+1);
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

    public void calculateGaussActivationFunction(List<Double> calculatedValues) {
        activationFunctionValue = 0;
        for (int i = 0; i < wages.size(); i++) {
            activationFunctionValue += wages.get(i) * calculatedValues.get(i);
        }
        //activationFunctionValue += 1.0; //bias
        activationFunctionValue = 1.0/(1.0+Math.exp(-activationFunctionValue));
    }
//    public void calculateGaussActivationFunction(List<Double> calculatedValues) {
//        activationFunctionValue = 0;
////        System.out.println(wages);
////        System.out.println(calculatedValues);
//        //exponent = e^(-B*||x-c||^2)
//        double exponent = -0.0000001*Math.pow(Math.sqrt(Math.pow(calculatedValues.get(0)- wages.get(0),2.0)+Math.pow(calculatedValues.get(1)- wages.get(1),2.0)),2.0);
////        System.out.println(exponent);
//        activationFunctionValue = Math.exp(exponent);
//        //System.out.println(activationFunctionValue);
//    }

    public void calculateLinearActivationFunction(List<Double> calculatedValues) {
        activationFunctionValue = 0;
        for (int i = 0; i < wages.size(); i++) {
            activationFunctionValue += wages.get(i) * calculatedValues.get(i);
        }
        activationFunctionValue += 1.0; //bias

    }

    public void calculateError(List<Double> calculatedValues,List<Double> wagesFromNextLayer) {
        error = 0;
        for (int i = 0; i < wagesFromNextLayer.size(); i++) {
            error += wagesFromNextLayer.get(i) * calculatedValues.get(i);
        }
    }

    public void updateWages(List<Double> inputValues) {
        for (int i = 0; i < wages.size(); i++) {
            wages.set(i,wages.get(i)+0.2*error*inputValues.get(i));
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
