import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Sheet05 {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PredatorPreySystem system_a = new PredatorPreySystemA(0.1, -0.1, 0.1, 0.1, 0, 0);
        PredatorPreySystem system_b = new PredatorPreySystemB(0.1, -0.1, 0.1, 0.1, 0, 0, 0, 0);
        List<Double> results_a_x= new ArrayList<>();
        List<Double> results_a_y= new ArrayList<>();
        List<Double> results_b_x= new ArrayList<>();
        List<Double> results_b_y= new ArrayList<>();

        system_a.evaluateSystem(0.5, 0.5, 60, results_a_x, results_a_y);
        //system_b.evaluateSystem(0.5, 0.5, 60, results_b_x, results_b_y);
    }
}
