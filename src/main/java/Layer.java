import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neuronList;
    private List<Double> functionsValuesList;
    private List<Double> errorsValuesList;

    public Layer() {
        this.neuronList = new ArrayList<>();
//        this.functionsValuesList = new ArrayList<>();
//        this.errorsValuesList = new ArrayList<>();
    }

    public List<Double> getFunctionsValuesList() {
        return functionsValuesList;
    }

    public List<Double> getErrorsValuesList() {
        return errorsValuesList;
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
        //List<Double> currentValues = new ArrayList<>();
        functionsValuesList = new ArrayList<>();
        for (Neuron neuron: neuronList) {
            neuron.calculateActivationFunction(calculatedValuesFromPreviousLayer);
            functionsValuesList.add(neuron.getActivationFunctionValue());
        }
        return functionsValuesList;
    }

    public List<Double> calculateErrorsForNeurons(List<Double> calculatedErrorsFromPreviousLayer) {
        //List<Double> currentValues = new ArrayList<>();
        errorsValuesList = new ArrayList<>();
        for (Neuron neuron: neuronList) {
            neuron.calculateError(calculatedErrorsFromPreviousLayer);
            errorsValuesList.add(neuron.getError());
        }
        return errorsValuesList;
    }

    public List<Double> calculateErrorsForNeurons(List<Double> outputValues, List<Double> desiredValues) {
        errorsValuesList = new ArrayList<>();
        for (int i = 0; i < outputValues.size() ; i++) {
            errorsValuesList.add(desiredValues.get(i) - outputValues.get(i));
        }
        return errorsValuesList;
    }

    public void updateWagesForNeurons(List<Double> calculatedfunctionsValuesFromPreviousLayer) {
        for (Neuron neuron: neuronList) {
            neuron.updateWages(calculatedfunctionsValuesFromPreviousLayer);
        }
    }




}
