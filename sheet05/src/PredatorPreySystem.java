import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

abstract class PredatorPreySystem {
    /**
     * Calculate the population size for the next generation of x.
     * @param x: Current population of x
     * @param y: Current population of y
     * @return The calculated size
     */
    abstract double nextX(double x, double y);

    /**
     * Calculate the population size for the next generation of y.
     * @param x: Current population of x
     * @param y: Current population of y
     * @return The calculated size
     */
    abstract double nextY(double x, double y);

    /**
     * Generate a filename to store the results.
     * @return The filename
     */
    abstract String getFilename();

    /**
     * Calculate the temporal development of x and y.
     * @param x: The starting condition x(0)
     * @param y: The starting condition y(0)
     * @param iterations: The number of generations to evaluate.
     * @param results_x: This will hold the value of x for each generation.
     * @param results_y: This will hold the value of y for each generation.
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    void evaluateSystem(double x, double y, int iterations, List<Double> results_x, List<Double> results_y) throws FileNotFoundException, UnsupportedEncodingException {
        String filename = getFilename();
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        double current_x, current_y;
        results_x.add(x); results_y.add(y);

        System.out.println("Writing output to '" + filename + "'...");

        for (int i=0; i < iterations; i++) {
            current_x = this.nextX(x, y);
            current_y = this.nextY(x, y);

            // Zero if predator or prey extincts
            if ( current_x < 0 ) current_x = 0;
            if ( current_y < 0 ) current_y = 0;

            x = current_x; y = current_y;
            results_x.add(x); results_y.add(y);
            //System.out.println("i: " + i + "\t\tx: " + x + "\t\t\ty: " + y);
            writer.println(i + "\t" + x + "\t" + y);
        }
        writer.close();
    }
}
