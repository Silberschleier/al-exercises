import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/*
  Plot with: plot "SystemA_current.dat" using 1:2 w lines , "SystemA_current.dat" using 1:3 w lines
 */

public class Sheet05 {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PredatorPreySystem system_a = new PredatorPreySystemA(0.1, -0.1, 0.1, -0.09, 0, 0);

        PredatorPreySystem system_b = new PredatorPreySystemB(0.12, 0, 0, -0.01, -0.00006, 0, -0.009, 0.0005);
        //PredatorPreySystem system_b = new PredatorPreySystemB(0.2, 0, 0, -0.015, -0.0002, 0, -0.006, 0.00008);
        //PredatorPreySystem system_b = new PredatorPreySystemB(0.5, 0, 0, 0.4, -0.004, 0.001, -0.001, -0.002);
        List<Double> results_a_x= new ArrayList<>();
        List<Double> results_a_y= new ArrayList<>();
        List<Double> results_b_x= new ArrayList<>();
        List<Double> results_b_y= new ArrayList<>();

        system_a.evaluateSystem(80, 60, 1000, results_a_x, results_a_y);
        System.out.println("Means: x = " + getListMean(results_a_x) + ",\t\ty = " + getListMean(results_a_y));
        system_b.evaluateSystem(21, 13, 2000, results_b_x, results_b_y);
        System.out.println("Means: x = " + getListMean(results_b_x) + ",\t\ty = " + getListMean(results_b_y));
    }

    private static double getListMean(List<Double> list) {
        double sum = 0;
        for ( double value : list) {
            sum += value;
        }

        return sum / list.size();
    }
}
