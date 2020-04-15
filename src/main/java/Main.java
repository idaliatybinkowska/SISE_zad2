import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);integerList.add(4);integerList.add(2);

        List<Double> desiredValues = new ArrayList<>();
        desiredValues.add(1.1);desiredValues.add(2.1);

        List<Double> inputValues = new ArrayList<>();
        inputValues.add(1.0);inputValues.add(2.0);

        MLP mlp = new MLP(integerList);
//        mlp.makeStep(inputValues,desiredValues);
        //mlp.backpropagation(mlp.goThroughNet(inputValues),desiredValues);
        List<Lap> lapList = new ArrayList<>();
        try {

            for (int i = 1; i < 13; i++) {
                lapList.add(new Lap(XLSXReader.readXLSX("pozyxAPI_dane_pomiarowe/pozyxAPI_only_localization_measurement" + i + ".xlsx")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Lap lap : lapList) {
            for (Record record : lap.getRecordList()) {
                mlp.makeStep(record.getMeasurement_xy(),record.getReference_xy());
                //mlp.backpropagation(mlp.goThroughNet(record.getMeasurement_xy()),record.reference_xy);
            }
        }
        System.out.println("koniec");

    }
}
