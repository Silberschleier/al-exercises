import java.util.ArrayList;
import java.util.List;

public class Sheet05 {

    public static void main(String[] args) {
        PredatorPreySystem system_a = new PredatorPreySystemA(0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
        List<Double> results_a_x= new ArrayList<>();
        List<Double> results_a_y= new ArrayList<>();

        system_a.evaluateSystem(0.5, 0.5, 20, results_a_x, results_a_y);
    }
}
