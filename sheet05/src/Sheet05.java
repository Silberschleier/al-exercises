import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Maren Pielka, Christopher Schmidt
 *
 * The first equations are implemented in PredatorPreySystemA and the second equations in PredatorPreySystemB
 *
 * To generate plots of the temporal development use the command: plot "SystemX_current.dat" using 1:2 w lines , "SystemX_current.dat" using 1:3 w lines
 * To generate a phase plot use: plot "SystemX_current.dat" using 2:3 w lines
 * Where X should be replaced with A or B depending on the desired system.
 *
 * 1st equations:
 * --------------
 * - A run with the chosen parameters yields the means x = 1.792384198855281, y = 1.4695323032234806
 * - By increasing 'a' to 0.0105 both means increase to x = 2.010675421432229,	y = 1.695315265263361
 *
 * 2nd equations:
 * --------------
 * - A run with the chosen parameters yields the means x = 19.998811986216516, y = 13.195535898334644
 * - By increasing 'a' to 0.15 the means change to x = 19.969444273909435, y = 16.51452613275245
 * - While the mean of x doesn't change much, the mean of y increases significantly.
 */


public class Sheet05 {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PredatorPreySystem system_a = new PredatorPreySystemA(0.01, -0.01, 0.007,-0.01, -0.001, 0.001);

        PredatorPreySystem system_b = new PredatorPreySystemB(0.12, 0, 0, -0.01, -0.00006, 0, -0.009, 0.0005);
        List<Double> results_a_x= new ArrayList<>();
        List<Double> results_a_y= new ArrayList<>();
        List<Double> results_b_x= new ArrayList<>();
        List<Double> results_b_y= new ArrayList<>();

        system_a.evaluateSystem(1.7, 1.4, 5000, results_a_x, results_a_y);
        system_b.evaluateSystem(20, 14, 2000, results_b_x, results_b_y);
        System.out.println("Means for SystemA: x = " + getListMean(results_a_x) + ",\t\ty = " + getListMean(results_a_y));
        System.out.println("Means for SystemB: x = " + getListMean(results_b_x) + ",\t\ty = " + getListMean(results_b_y));
    }

    private static double getListMean(List<Double> list) {
        double sum = 0;
        for ( double value : list) {
            sum += value;
        }

        return sum / list.size();
    }
}
