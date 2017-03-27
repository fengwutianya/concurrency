package ch04;

/**
 * Created by xuan on 2017/3/27 0027.
 */
public class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        this.intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data: " + intData;
    }
}
