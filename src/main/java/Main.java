import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(12);
        integerList.add(10);
        integerList.add(8);
        integerList.add(2);

        List<Double> desiredValues = new ArrayList<>();
        desiredValues.add(1.1);
        desiredValues.add(2.1);

        List<Double> inputValues = new ArrayList<>();
        inputValues.add(1.0);
        inputValues.add(2.0);

        List<Record> setOfWagesForNeurons = null;
        try {
            setOfWagesForNeurons = XLSXReader.readXLSX("pozyxAPI_dane_pomiarowe/pozyxAPI_only_localization_measurement1.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MLP mlp = new MLP(integerList, setOfWagesForNeurons);
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

        for (int i = 0; i < 1000 ; i++) {
            for (Lap lap : lapList) {
                for (Record record : lap.getRecordList()) {

                    mlp.makeStep(record.getMeasurement_xy(), record.getReference_xy());


                    //mlp.backpropagation(mlp.goThroughNet(record.getMeasurement_xy()),record.reference_xy);
                }
            }
//            if(i > 9998) {
                System.out.println(mlp.getMSE() / (1540.0 * 12.0));
//            }


            mlp.setMSE(0.0);
        }

    }


}



