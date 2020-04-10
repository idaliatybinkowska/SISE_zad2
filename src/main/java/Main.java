import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(4);
        integerList.add(2);
        MLP mlp = new MLP(integerList);
        List<Double> nic = new ArrayList<>();
        nic.add(1.1);
        nic.add(1.99);
        mlp.backpropagation(mlp.goThroughNet(1.0, 2.0),nic);
        try {
            List<Lap> lapList = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                lapList.add(new Lap(XLSXReader.readXLSX("pozyxAPI_dane_pomiarowe/pozyxAPI_only_localization_measurement"+i+".xlsx")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
