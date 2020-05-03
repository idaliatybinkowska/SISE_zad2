import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Neuron {
    static double test = 0.1;
    private List<Double> wages;
    private double activationFunctionValue;
    private double error;
    static int p = 0;
    static List<Double> wagi = new ArrayList<>(Arrays.asList(
            -3.3439834E23,
            2.05820285E24,
            4.767106E19,

            -2.74533E26,
            1.6895515E27,
            3.913519E22,

            -5.3299953E17,
            3.229854E18,
            7.520148E13,

            -1.779643E16,
            1.095058E17,
            2.536660E12,

            -6.669629E10,
            -6.666841E10,
            -6.339682E10,
            -6.662379E10,
            -5.537630E9,

            -2.196265E12,
            -2.195555E12,
            -2.094698E12,
            -2.194228E12,
            -1.853737E11,

            -20.449663,
            -19.254268,
            -19.844757,
            -19.195687,
            -20.641396,

            1949019.645349,
            1774637.050266,
            -109.829127,
            80.405748,
            -21.228115,
            -13.521183,
            -24.619082,
            -32.365661,
            -387.868327,
            517.498156,
            405.098327,
            72.025202,
            1036.045419,
            1665.974797));

    public Neuron() {
        this.wages = new ArrayList<>();
    }

    public Neuron(int numberOfWages) {
        wages = new ArrayList<>();
        initializeWages(numberOfWages);
        this.activationFunctionValue = 0;
        this.error = 0;
    }

//    public Neuron(int numberOfWages,List<Record> setOfWagesForNeuron) {
//        wages = new ArrayList<>();
//        initializeWages(numberOfWages, setOfWagesForNeuron);
//        this.activationFunctionValue = 0;
//        this.error = 0;
//    }

    public double getActivationFunctionValue() {
        return activationFunctionValue;
    }


//    private void initializeWages(int numberOfWages,List<Record> setOfWagesForNeurons) {
//        //wages.add(Math.random());
//        int randomNumber = (int)(Math.random()*1540);
//        wages.add(setOfWagesForNeurons.get(randomNumber).getMeasurement_xy().get(0));
//        wages.add(setOfWagesForNeurons.get(randomNumber).getMeasurement_xy().get(1));
//        //wages.add((double)i+1);
//    }

    private void initializeWages(int numberOfWages) {
        for (int i = 0; i < numberOfWages+1; i++) {
//            wages.add(wagi.get(p));
//            p++;
//            wages.add(test);
//            test = test + 0.1;
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

    public void calculateSigmoidalActivationFunction(List<Double> calculatedValues) {
        activationFunctionValue = 0;
        for (int i = 0; i < wages.size()-1; i++) {
            activationFunctionValue += wages.get(i) * calculatedValues.get(i);
        }
        activationFunctionValue += wages.get(wages.size()-1); //bias
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
            wages.set(i,wages.get(i)+0.2*error*activationFunctionValue*(1-activationFunctionValue)*inputValues.get(i));
        }
        wages.set(wages.size()-1,wages.get(wages.size()-1)+0.2*error*activationFunctionValue*(1-activationFunctionValue)*1); //bias
    }

    public void updateWagesForLinearLayer(List<Double> inputValues) {
        for (int i = 0; i < wages.size()-1; i++) {
            wages.set(i,wages.get(i)+0.2*error*inputValues.get(i));
        }
        wages.set(wages.size()-1,wages.get(wages.size()-1)+0.2*error*1); //bias
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
