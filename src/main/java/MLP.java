import java.util.ArrayList;
import java.util.List;

public class MLP {
    private List<Layer> layerList;

    public MLP(List<Integer> structure) {
        layerList = new ArrayList<>();
        for (int i = 1; i < structure.size(); i++) {
            layerList.add(new Layer());
            for (int j = 0; j < structure.get(i) ; j++)
                layerList.get(i-1).addNeuron(new Neuron(structure.get(i-1)));
        }
    }

    public List<Layer> getLayerList() {
        return layerList;
    }

    public List<Double> goThroughNet(int i, List<Double> inputValues) {
        //List<Double> inputValues = new ArrayList<>();
        //inputValues.add(x);inputValues.add(y);
        if(i==0) return layerList.get(0).calculateFunctionsForNeurons(inputValues);
        else return goThroughNet(i-1,layerList.get(i).calculateFunctionsForNeurons(inputValues));

    }
}
