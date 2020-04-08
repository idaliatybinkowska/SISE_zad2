import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Lap> lapList = new ArrayList<>();
            for (int i = 1; i < 13; i++) {
                lapList.add(new Lap(XLSXReader.readXLSX("pozyxAPI_dane_pomiarowe/pozyxAPI_only_localization_measurement"+i+".xlsx")));
            }
            lapList.stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
