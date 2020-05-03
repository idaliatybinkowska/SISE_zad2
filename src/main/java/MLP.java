import java.util.ArrayList;
import java.util.List;

public class MLP {
    private List<Layer> layerList;
    private List<Double> inputValues;
    private double MSE;

//    public MLP(List<Integer> structure, List<Record> setOfWagesForNeurons) {
//        layerList = new ArrayList<>();
//        this.MSE = 0;
//        for (int i = 1; i < structure.size(); i++) {
//            layerList.add(new Layer());
//            if(i == structure.size()-1) {
//                for (int j = 0; j < structure.get(i); j++) {
//                    layerList.get(i - 1).addNeuron(new Neuron(structure.get(i - 1)));
//                }
//            } else {
//                for (int j = 0; j < structure.get(i); j++) {
//                    layerList.get(i - 1).addNeuron(new Neuron(structure.get(i - 1), setOfWagesForNeurons));
//                }
//            }
//        }
//        System.out.println("nic");
//    }

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
//        for (int i = 0; i < inputValues.size(); i++) {
//            inputValues.set(i,inputValues.get(i)/1000.0);
//        }

        List<Double> outputValues = goThroughNet(inputValues);
//        for (int i = 0; i < outputValues.size(); i++) {
//            outputValues.set(i,outputValues.get(i)*1000.0);
//        }

        backpropagation(outputValues, desiredValues);
    }

    public List<Double> goThroughNet(List<Double> inputValues) {
        this.inputValues = inputValues;

        List<Double> calculatedPreviousLayer = layerList.get(0).calculateSigmoidalFunctionsForNeurons(inputValues);
        for (int m = 1; m < layerList.size()-1; m++) {
            calculatedPreviousLayer = layerList.get(m).calculateSigmoidalFunctionsForNeurons(calculatedPreviousLayer);
        }
        calculatedPreviousLayer = layerList.get(layerList.size()-1).calculateLinearFunctionsForNeurons(calculatedPreviousLayer);

        //System.out.println(calculatedPreviousLayer.size());
//        if(calculatedPreviousLayer.get(0)>1)
//        {
//            System.out.println("X in: "+inputValues.get(0)+", y in: "+inputValues.get(1));
//            System.out.println("X out: "+calculatedPreviousLayer.get(0)+", y out: "+calculatedPreviousLayer.get(1));
//            System.out.println("-------------------------");
//        }
        return calculatedPreviousLayer;
    }

    private void backpropagation(List<Double> outputValues, List<Double> desiredValues) {
        calculateAllErrors(outputValues,desiredValues);
        updateAllWages();
    }

    private void calculateAllErrors(List<Double> outputValues, List<Double> desiredValues){

        List<Double> outputErrors = layerList.get(layerList.size() - 1).calculateErrorsForNeurons(outputValues, desiredValues);
//        MSE+= sqrt(outputErrors.get(0)*outputErrors.get(0)+outputErrors.get(1)*outputErrors.get(1));
        MSE+= 0.5*(outputErrors.get(0)*outputErrors.get(0)+outputErrors.get(1)*outputErrors.get(1));

        for (int i = layerList.size() - 2; i >= 0; i--) {
            outputErrors = layerList.get(i).calculateErrorsForNeurons(outputErrors,layerList.get(i+1));
        }
        //System.out.println(MSE);
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

        //layerList.get(layerList.size()-1).updateWagesForNeurons(layerList.get(layerList.size()-2).getFunctionsValuesList());
    }
}
