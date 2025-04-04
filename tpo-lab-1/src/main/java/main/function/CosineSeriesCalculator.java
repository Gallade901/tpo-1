package main.function;

public class CosineSeriesCalculator {
    //cos(x) = 1 - x²/2! + x⁴/4! - x⁶/6! + ...
    public static double calculateCosine(double x, int terms) {
        double sum = 0;
        for (int n = 0; n < terms; n++) {
            double term = Math.pow(-1, n) * Math.pow(x, 2 * n) / factorial(2 * n);
            sum += term;
        }
        return sum;
    }

    private static double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}