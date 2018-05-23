import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public abstract class PredatorPreySystem {
    abstract public double nextX(double x, double y);
    abstract public double nextY(double x, double y);
    abstract String getFilename(double x, double y);

    void evaluateSystem(double x, double y, int iterations, List<Double> results_x, List<Double> results_y) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(getFilename(x, y), "UTF-8");
        double current_x, current_y;
        results_x.add(x); results_y.add(y);

        for (int i=0; i < iterations; i++) {
            current_x = this.nextX(x, y);
            current_y = this.nextY(x, y);

            // Zero if predator or prey extincts
            if ( current_x < 0 ) current_x = 0;
            if ( current_y < 0 ) current_y = 0;

            x = current_x; y = current_y;
            results_x.add(x); results_y.add(y);
            System.out.println("i: " + i + "\t\tx: " + x + "\t\t\ty: " + y);
            writer.println(i + "\t" + x + "\t" + y);
        }
        writer.close();
    }
}
