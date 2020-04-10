import java.util.ArrayList;
import java.util.List;

public class MLP {
    private List<Layer> layerList;

    public MLP(List<Integer> structure) {
        layerList = new ArrayList<>();
        for (int i = 1; i < structure.size(); i++) {
            layerList.add(new Layer());
            for (int j = 0; j < structure.get(i); j++)
                layerList.get(i - 1).addNeuron(new Neuron(structure.get(i - 1)));
        }

    }

    public List<Layer> getLayerList() {
        return layerList;
    }

    public List<Double> goThroughNet(double x, double y) {
        List<Double> inputValues = new ArrayList<>();
        inputValues.add(x);
        inputValues.add(y);

        List<Double> calculatedPreviousLayer = layerList.get(0).calculateFunctionsForNeurons(inputValues);
        for (int m = 1; m < layerList.size(); m++) {
            calculatedPreviousLayer = layerList.get(m).calculateFunctionsForNeurons(calculatedPreviousLayer);
        }
        return calculatedPreviousLayer;
    }

    public void backpropagation(List<Double> outputValues, List<Double> desiredValues) {
        List<Double> outputErrors = new ArrayList<>();
        outputErrors = layerList.get(layerList.size() - 1).calculateErrorsForNeurons(outputValues, desiredValues);
        for (int i = layerList.size() - 2; i >= 0; i--) {
            outputErrors = layerList.get(i).calculateErrorsForNeurons(outputErrors);
        }
        for (Layer l: layerList) {
            System.out.println(l.getErrorsValuesList().size());
        }
    }


}
