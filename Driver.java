import java.util.Arrays;
import java.io.File;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double c1[] = { 6, 3, 5 };
        int e1[] = { 0, 2, 3 };
        Polynomial p1 = new Polynomial(c1, e1);
        double c2[] = { -2, -3, -9 };
        int e2[] = { 1, 2, 4 };
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial p3 = p1.add(p2);
        System.out.println(Arrays.toString(p3.coef));
        System.out.println(Arrays.toString(p3.exp));
        System.out.println("p3(0.1) = " + p3.evaluate(0.1));
        if (p3.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        Polynomial p4 = p1.multiply(p2);
        System.out.println(Arrays.toString(p4.coef));
        System.out.println(Arrays.toString(p4.exp));
        File f1 = new File("/Users/muxun/b07lab1/poly.txt");
        Polynomial p5 = new Polynomial(f1);
        System.out.println(Arrays.toString(p5.coef));
        System.out.println(Arrays.toString(p5.exp));
        p4.saveToFile("complex.txt");
    }
}