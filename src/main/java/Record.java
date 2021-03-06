import java.util.ArrayList;
import java.util.List;

public class Record {
    private int no;
    private List<Double> measurement_xy;
    private List<Double> reference_xy;


    public Record(int no, double measurement_x, double measurement_y, double reference_x, double reference_y) {
        this.no = no;
        this.measurement_xy = new ArrayList<>();
        this.reference_xy = new ArrayList<>();
        this.measurement_xy.add(measurement_x);
        this.measurement_xy.add(measurement_y);
        this.reference_xy.add(reference_x);
        this.reference_xy.add(reference_y);
    }

    public Record() {
    }

    public List<Double> getMeasurement_xy() {
        return measurement_xy;
    }

    public List<Double> getReference_xy() {
        return reference_xy;
    }

    public int getNo() {
        return no;
    }


    @Override
    public String toString() {
        return "Record{" +
                "no=" + no +
                ", measurement_xy=" + measurement_xy +
                ", reference_xy=" + reference_xy +
                '}';
    }

}
