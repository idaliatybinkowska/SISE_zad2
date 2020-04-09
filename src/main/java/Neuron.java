import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private List<Double> wages;

    public Neuron() {
        this.wages = new ArrayList<>();
    }

    public Neuron(int numberOfWages) {
        wages = new ArrayList<>();
        initializeWages(numberOfWages);
    }

    private void initializeWages(int numberOfWages) {
        for (int i = 0; i < numberOfWages; i++) {
            wages.add(Math.random());
        }
    }

    public double calculateActivationFunction(List<Double> calculatedValues) {
        double value = 0;
        for (int i = 0; i < wages.size(); i++) {
            value += wages.get(i) * calculatedValues.get(i);
        }
        value = 1.0/(1.0+Math.exp(-value));
        return value;
    }

    public List<Double> getWages() {
        return wages;
    }
}
