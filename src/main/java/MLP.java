import java.util.ArrayList;
import java.util.List;

public class MLP {
    private List<Layer> layerList;
    private List<Double> inputValues;
    private double MSE;


    public MLP(List<Integer> structure) {
        layerList = new ArrayList<>();
        this.MSE = 0;
        for (int i = 1; i < structure.size(); i++) {
            layerList.add(new Layer());
            for (int j = 0; j < structure.get(i); j++) {
                layerList.get(i - 1).addNeuron(new Neuron(structure.get(i - 1)));
            }
        }
        System.out.println("nic");
    }

    public double getMSE() {
        return MSE;
    }

    public void setMSE(double MSE) {
        this.MSE = MSE;
    }

    public List<Layer> getLayerList() {
        return layerList;
    }

    public void makeStep(List<Double> inputValues, List<Double> desiredValues){

        List<Double> outputValues = goThroughNet(inputValues);

        backpropagation(outputValues, desiredValues);
    }

    public List<Double> goThroughNet(List<Double> inputValues) {
        this.inputValues = inputValues;

        List<Double> calculatedPreviousLayer = layerList.get(0).calculateSigmoidalFunctionsForNeurons(inputValues);
        for (int m = 1; m < layerList.size()-1; m++) {
            calculatedPreviousLayer = layerList.get(m).calculateSigmoidalFunctionsForNeurons(calculatedPreviousLayer);
        }
        calculatedPreviousLayer = layerList.get(layerList.size()-1).calculateLinearFunctionsForNeurons(calculatedPreviousLayer);

        return calculatedPreviousLayer;
    }

    private void backpropagation(List<Double> outputValues, List<Double> desiredValues) {
        calculateAllErrors(outputValues,desiredValues);
        updateAllWages();
    }

    private void calculateAllErrors(List<Double> outputValues, List<Double> desiredValues){

        List<Double> outputErrors = layerList.get(layerList.size() - 1).calculateErrorsForNeurons(outputValues, desiredValues);
        MSE+= 0.5*(outputErrors.get(0)*outputErrors.get(0)+outputErrors.get(1)*outputErrors.get(1));

        for (int i = layerList.size() - 2; i >= 0; i--) {
            outputErrors = layerList.get(i).calculateErrorsForNeurons(outputErrors,layerList.get(i+1));
        }
    }

    private void updateAllWages(){
        List<Double> calculatedFunctionsValuesFromPreviousLayer = new ArrayList<>();
        calculatedFunctionsValuesFromPreviousLayer.add(inputValues.get(0));
        calculatedFunctionsValuesFromPreviousLayer.add(inputValues.get(1));

        layerList.get(0).updateWagesForNeurons(calculatedFunctionsValuesFromPreviousLayer);
        for (int i = 1; i < layerList.size() ; i++) {
            if(i!=layerList.size()-1)
                layerList.get(i).updateWagesForNeurons(layerList.get(i-1).getFunctionsValuesList());
            else
                layerList.get(i).updateWagesForNeuronsInLinearLayer(layerList.get(i-1).getFunctionsValuesList());
        }

    }
}
