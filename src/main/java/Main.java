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


        MLP mlp = new MLP(integerList);

        List<Lap> lapList = new ArrayList<>();
        try {
            for (int i = 1; i < 13; i++) {
                lapList.add(new Lap(XLSXReader.readXLSX("pozyxAPI_dane_pomiarowe/pozyxAPI_only_localization_measurement" + i + ".xlsx")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Uczenie
        for (int i = 0; i < 100 ; i++) {
            for (Lap lap : lapList) {
                for (Record record : lap.getRecordList()) {
                    mlp.makeStep(record.getMeasurement_xy(), record.getReference_xy());
                }
            }
            System.out.println(mlp.getMSE() / (1540.0 * 12.0));
            mlp.setMSE(0.0);
        }


        Lap testLap = null;
        try {
                testLap = new Lap(XLSXReader.readXLSX("pozyxAPI_dane_pomiarowe/dane_testowe.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Double> results = new ArrayList<>();
        for (Record record : testLap.getRecordList()) {

            List<Double> outputValues =  mlp.goThroughNet(record.getMeasurement_xy());
            results.add(outputValues.get(0));
            results.add(outputValues.get(1));
        }
        for (Double d: results) {
            System.out.println("Results: "+d);
        }

        try {
            XLSXReader.writeToXLSX("pozyxAPI_dane_pomiarowe/dane_testowe.xlsx",results);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}



