import java.util.List;

public abstract class PredatorPreySystem {
    abstract public double nextX(double x, double y);
    abstract public double nextY(double x, double y);

    void evaluateSystem(double x, double y, int iterations, List<Double> results_x, List<Double> results_y) {
        double current_x, current_y;
        results_x.add(x); results_y.add(y);

        for (int i=0; i < iterations; i++) {
            current_x = this.nextX(x, y);
            current_y = this.nextY(x, y);

            x = current_x; y = current_y;
            results_x.add(x); results_y.add(y);
            System.out.println("x: " + x + "\t\t\ty: " + y);
        }
    }
}
