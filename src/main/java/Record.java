import java.util.ArrayList;
import java.util.List;

public class Record {
    private int no;
    List<Double> measurement_xy;
    List<Double> reference_xy;
//    private double measurement_x;
//    private double measurement_y;
//    private double reference_x;
//    private double reference_y;

    public Record(int no, double measurement_x, double measurement_y, double reference_x, double reference_y) {
        this.no = no;
        this.measurement_xy = new ArrayList<>();
        this.reference_xy = new ArrayList<>();
        this.measurement_xy.add(measurement_x);
        this.measurement_xy.add(measurement_y);
        this.reference_xy.add(reference_x);
        this.reference_xy.add(reference_y);
    }

//    public Record(int no, double measurement_x, double measurement_y, double reference_x, double reference_y) {
//        this.no = no;
//        this.measurement_x = measurement_x;
//        this.measurement_y = measurement_y;
//        this.reference_x = reference_x;
//        this.reference_y = reference_y;
//    }

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

//    public double getMeasurement_x() {
//        return measurement_x;
//    }
//
//    public double getMeasurement_y() {
//        return measurement_y;
//    }
//
//    public double getReference_x() {
//        return reference_x;
//    }
//
//    public double getReference_y() {
//        return reference_y;
//    }


    @Override
    public String toString() {
        return "Record{" +
                "no=" + no +
                ", measurement_xy=" + measurement_xy +
                ", reference_xy=" + reference_xy +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Record{" +
//                "no=" + no +
//                ", measurement_x=" + measurement_x +
//                ", measurement_y=" + measurement_y +
//                ", reference_x=" + reference_x +
//                ", reference_y=" + reference_y +
//                '}';
//    }
}
