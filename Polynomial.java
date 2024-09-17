import java.lang.Math;

public class Polynomial {
    double coef[];

    public Polynomial() {
        double zero[] = { 0 };
        this.coef = zero;
    }

    public Polynomial(double[] array) {
        this.coef = array;
    }

    public Polynomial add(Polynomial poly) {
        double temp[];
        if (coef.length > poly.coef.length) {
            temp = coef;
            for (int i = 0; i < poly.coef.length; i++) {
                temp[i] += poly.coef[i];
            }
        } else {
            temp = poly.coef;
            for (int i = 0; i < coef.length; i++) {
                temp[i] += coef[i];
            }
        }
        Polynomial result = new Polynomial(temp);
        return result;
    }

    public double evaluate(double x) {
        double result = 0;
        int n = coef.length;
        for (int i = 0; i < n; i++) {
            result += coef[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double root) {
        double y = 0;
        int n = coef.length;
        for (int i = 0; i < n; i++) {
            y += coef[i] * Math.pow(root, i);
        }
        if (y == 0.0) {
            return true;
        } else {
            return false;
        }
    }
}