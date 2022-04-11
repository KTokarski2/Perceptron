import java.util.Arrays;

public class MyVector {

    double [] vector;
    String type;

    public MyVector (double [] vector, String type) {
        this.vector = vector;
        this.type = type;
    }

    @Override
    public String toString() {
        return "MyVector{" +
                "vector=" + Arrays.toString(vector) +
                ", type='" + type + '\'' +
                '}';
    }
}
