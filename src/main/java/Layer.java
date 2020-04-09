import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neuronList;

    public Layer() {
        this.neuronList = new ArrayList<>();
    }

    public boolean addNeuron(Neuron neuron) {
        return neuronList.add(neuron);

    }

    public List<Neuron> getNeuronList() {
        return neuronList;
    }

    public List<Double> calculateFunctionsForNeurons(List<Double> calculatedValuesFromPreviousLayer) {
        List<Double> currentValues = new ArrayList<>();
        for (Neuron neuron: neuronList) {
            currentValues.add(neuron.calculateActivationFunction(calculatedValuesFromPreviousLayer));
        }
        return currentValues;
    }
}
