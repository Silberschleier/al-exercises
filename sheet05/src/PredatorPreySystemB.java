public class PredatorPreySystemB extends PredatorPreySystem {
    private double a, b, c, d, e, f, g, h;

    PredatorPreySystemB(double a, double b, double c, double d, double e, double f, double g, double h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    public double nextX(double x, double y) {
        return x + this.a * x + this.b * y + e * x * x + this.g * x * y;
    }

    public double nextY(double x, double y) {
        return y + this.c * x + this.d * y + this.f * y * y + this.h * x * y;
    }

    String getFilename(double x, double y) {
        return "SystemB_current.dat";
    }
}
