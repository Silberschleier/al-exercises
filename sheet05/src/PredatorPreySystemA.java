public class PredatorPreySystemA extends PredatorPreySystem {
    private double a, b, c, d, e, f;

    PredatorPreySystemA(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public double nextX(double x, double y) {
        return x + this.a * x + this.b * y + e * x * x;
    }

    public double nextY(double x, double y) {
        return y + this.c * x + this.d * y + this.f * y * y;
    }
}
