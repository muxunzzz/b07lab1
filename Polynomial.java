import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
    double coef[]; // array to store the coefficients
    int exp[]; // array to store the exponents

    // constructor that initializes all attributes to 0
    public Polynomial() {
        double coef[] = { 0 };
        int exp[] = { 0 };
        this.coef = coef;
        this.exp = exp;
    }

    // constructor that initializes all attributes according to the argument
    public Polynomial(double[] coef, int[] exp) {
        this.coef = coef;
        this.exp = exp;
    }

    // constructor that initializes all attributes based on the input file
    public Polynomial(File input) {
        try {
            Scanner sc = new Scanner(input);
            String s1[] = sc.nextLine().split("(?=[+-])");

            sc.close();

            int n = s1.length;
            double coef[] = new double[n];
            int exp[] = new int[n];

            for (int i = 0; i < n; i++) {
                int index = s1[i].indexOf("x");

                if (index == -1) {
                    coef[i] = Double.parseDouble(s1[i]);
                    exp[i] = 0;
                } else {
                    String coeffPart = s1[i].substring(0, index);
                    if (coeffPart.equals("") || coeffPart.equals("+")) {
                        coef[i] = 1.0;
                    } else if (coeffPart.equals("-")) {
                        coef[i] = -1.0;
                    } else {
                        coef[i] = Double.parseDouble(coeffPart);
                    }

                    if (index == s1[i].length() - 1) {
                        exp[i] = 1;
                    } else {
                        exp[i] = Integer.parseInt(s1[i].substring(index + 1));
                    }
                }
            }

            this.coef = coef;
            this.exp = exp;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // method that adds the calling object and the argument
    public Polynomial add(Polynomial poly) {
        int max1 = max(exp);
        int max2 = max(poly.exp);

        int temp[] = { max1, max2 };
        int n = max(temp) + 1;
        System.out.println(n);

        double c[] = new double[n];
        int e[] = new int[n];

        for (int a = 0; a < n; a++) {
            c[a] = 0;
            e[a] = a;
        }

        for (int i = 0; i < exp.length; i++) {
            c[exp[i]] += coef[i];
        }
        for (int j = 0; j < exp.length; j++) {
            c[poly.exp[j]] += poly.coef[j];
        }

        Polynomial result = new Polynomial(c, e);
        result = result.eliminate();
        return result;
    }

    // method that evaluates the polynomial at x
    public double evaluate(double x) {
        double result = 0;
        int n = exp.length;
        for (int i = 0; i < n; i++) {
            result += coef[i] * Math.pow(x, exp[i]);
        }
        return result;
    }

    // method that determines whether x is a root of the polynomial or not
    public boolean hasRoot(double x) {
        if (this.evaluate(x) == 0.0) {
            return true;
        } else {
            return false;
        }
    }

    // method that multiplies the calling object and the argument
    public Polynomial multiply(Polynomial poly) {
        int max1 = max(exp);
        int max2 = max(poly.exp);

        int n = max1 + max2 + 1;

        double c[] = new double[n];
        int e[] = new int[n];

        for (int a = 0; a < n; a++) {
            c[a] = 0;
            e[a] = a;
        }

        for (int i = 0; i < exp.length; i++) {
            for (int j = 0; j < poly.exp.length; j++) {
                c[exp[i] + poly.exp[j]] += coef[i] * poly.coef[j];
            }
        }

        Polynomial result = new Polynomial(c, e);
        result = result.eliminate();
        return result;
    }

    // method that saves the polynomial in textual format in the corresponding file
    public void saveToFile(String name) {
        String str = Double.toString(coef[0]);
        if (exp[0] > 1) {
            str += "x" + Integer.toString(exp[0]);
        } else if (exp[0] == 1) {
            str += "x";
        }
        for (int i = 1; i < exp.length; i++) {
            if (coef[i] < 0) {
                str += Double.toString(coef[i]);
            } else {
                str += "+" + Double.toString(coef[i]);
            }
            if (exp[i] > 1) {
                str += "x" + Integer.toString(exp[i]);
            } else if (exp[i] == 1) {
                str += "x";
            }
        }
        try {
            FileWriter file = new FileWriter(name);
            file.write(str);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // helper methods

    // eliminates the polynomial to exclude zero coefficients
    public Polynomial eliminate() {
        int n = exp.length;
        for (int i = 0; i < n; i++) {
            if (coef[i] == 0.0) {
                n--;
            }
        }
        double c[] = new double[n];
        int e[] = new int[n];
        int a = 0;
        for (int i = 0; i < exp.length; i++) {
            if (coef[i] != 0.0) {
                c[a] = coef[i];
                e[a] = exp[i];
                a++;
            }
        }
        Polynomial result = new Polynomial(c, e);
        return result;
    }

    // returns the largest number in an integer array
    public int max(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > result) {
                result = array[i];
            }
        }
        return result;
    }
}