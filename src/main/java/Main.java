import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            XLSXReader.readXLSX("pozyxAPI_dane_pomiarowe/pozyxAPI_only_localization_measurement1.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
