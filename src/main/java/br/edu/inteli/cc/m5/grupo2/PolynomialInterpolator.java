package br.edu.inteli.cc.m5.grupo2;

public class PolynomialInterpolator {

    private final double[] x;
    private final double[] y;
    private final double[] coefficients;

    public PolynomialInterpolator(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        coefficients = calculateCoefficients();
    }

    public double interpolate(double xValue) {
        double result = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(xValue, i);
        }
        return result;
    }

    private double[] calculateCoefficients() {
        int n = x.length;
        double[][] matrix = new double[n][n+1];

        for (int i = 0; i < n; i++) {
            matrix[i][0] = 1;
            for (int j = 1; j < n; j++) {
                matrix[i][j] = Math.pow(x[i], j);
            }
            matrix[i][n] = y[i];
        }

        for (int k = 0; k < n; k++) {
            for (int i = k+1; i < n; i++) {
                double factor = matrix[i][k] / matrix[k][k];
                for (int j = k+1; j <= n; j++) {
                    matrix[i][j] -= factor * matrix[k][j];
                }
            }
        }

        double[] coefficients = new double[n];
        for (int i = n-1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                sum += matrix[i][j] * coefficients[j];
            }
            coefficients[i] = (matrix[i][n] - sum) / matrix[i][i];
        }

        return coefficients;
    }
}