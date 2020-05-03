import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neuronList;
    private List<Double> functionsValuesList;
    private List<Double> errorsValuesList;

    public Layer() {
        this.neuronList = new ArrayList<>();
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


    public List<Double> calculateSigmoidalFunctionsForNeurons(List<Double> calculatedValuesFromPreviousLayer) {
        //List<Double> currentValues = new ArrayList<>();
        functionsValuesList = new ArrayList<>();
        for (Neuron neuron: neuronList) {
            neuron.calculateSigmoidalActivationFunction(calculatedValuesFromPreviousLayer);
            functionsValuesList.add(neuron.getActivationFunctionValue());
        }
        return functionsValuesList;
    }

    public List<Double> calculateLinearFunctionsForNeurons(List<Double> calculatedValuesFromPreviousLayer) {
        //List<Double> currentValues = new ArrayList<>();
        functionsValuesList = new ArrayList<>();
        for (Neuron neuron: neuronList) {
            neuron.calculateLinearActivationFunction(calculatedValuesFromPreviousLayer);
            functionsValuesList.add(neuron.getActivationFunctionValue());
        }
        return functionsValuesList;
    }

    public List<Double> calculateErrorsForNeurons(List<Double> calculatedErrorsFromPreviousLayer, Layer nextLayer) {
        errorsValuesList = new ArrayList<>();
        for (int i = 0; i < neuronList.size(); i++) {
            neuronList.get(i).calculateError(calculatedErrorsFromPreviousLayer,this.getWagesPackage(nextLayer,i));
            errorsValuesList.add(neuronList.get(i).getError());
        }
        return errorsValuesList;
    }

    public List<Double> calculateErrorsForNeurons(List<Double> outputValues, List<Double> desiredValues) {
        errorsValuesList = new ArrayList<>();
        for (int i = 0; i < outputValues.size() ; i++) {
            neuronList.get(i).setError(desiredValues.get(i) - outputValues.get(i));
            errorsValuesList.add(desiredValues.get(i) - outputValues.get(i));
        }
        return errorsValuesList;
    }

    public void updateWagesForNeurons(List<Double> calculatedfunctionsValuesFromPreviousLayer) {
        for (Neuron neuron: neuronList) {
            neuron.updateWages(calculatedfunctionsValuesFromPreviousLayer);
        }
    }

    public void updateWagesForNeuronsInLinearLayer(List<Double> calculatedfunctionsValuesFromPreviousLayer) {
        for (Neuron neuron: neuronList) {
            neuron.updateWagesForLinearLayer(calculatedfunctionsValuesFromPreviousLayer);
        }
    }

    public List<Double> getWagesPackage(Layer nextLayer,int position) {

        List<Double> wagesList = new ArrayList<>();
        for (Neuron neuron : nextLayer.getNeuronList()) {
            wagesList.add(neuron.getWages().get(position));
        }
        return wagesList;
    }




}
