import java.util.ArrayList;
import java.util.List;

public class Lap {
    private List<Record> recordList;

    public Lap() {
        recordList = new ArrayList<>();
    }

    public Lap(List<Record> recordList) {
        this.recordList = new ArrayList<>(recordList);
    }

    public List<Record> getRecordList() {
        return recordList;
    }

}
