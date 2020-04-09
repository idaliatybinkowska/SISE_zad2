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

//    public List<Double> calculateFunctionsForNeurons(List<Double> calculatedValuesFromPreviousLayer) {
//        List<Double> currentValues = new ArrayList<>();
//        for (Neuron neuron: neuronList) {
//            currentValues.add(neuron.calculateActivationFunction(calculatedValuesFromPreviousLayer));
//        }
//        return currentValues;
//    }

    public List<Double> calculateFunctionsForNeurons(List<Double> calculatedValuesFromPreviousLayer) {
        List<Double> currentValues = new ArrayList<>();
        for (Neuron neuron: neuronList) {
            neuron.calculateActivationFunction(calculatedValuesFromPreviousLayer);
            currentValues.add(neuron.getActivationFunctionValue());
        }
        return currentValues;
    }

    public List<Double> calculateErrorsForNeurons(List<Double> calculatedErrorsFromPreviousLayer) {
        List<Double> currentValues = new ArrayList<>();
        for (Neuron neuron: neuronList) {
            neuron.calculateError(calculatedErrorsFromPreviousLayer);
            currentValues.add(neuron.getError());
        }
        return currentValues;
    }
}
