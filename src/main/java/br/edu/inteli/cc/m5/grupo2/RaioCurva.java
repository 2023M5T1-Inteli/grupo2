package br.edu.inteli.cc.m5.grupo2;

import java.util.Arrays;

public class RaioCurva {
    private final double[] x, y, a, b, c, d;

    public RaioCurva(double[] x, double[] y) {
        int n = x.length;
        this.x = Arrays.copyOf(x, n);
        this.y = Arrays.copyOf(y, n);
        this.a = new double[n];
        this.b = new double[n];
        this.c = new double[n];
        this.d = new double[n];

        double[] h = new double[n - 1];
        double[] alpha = new double[n - 1];

        for (int i = 0; i < n - 1; i++) {
            h[i] = x[i + 1] - x[i];
            alpha[i] = (y[i + 1] - y[i]) / h[i];
        }

        double[] l = new double[n];
        double[] mu = new double[n - 1];
        double[] z = new double[n];

        l[0] = 1;
        mu[0] = 0;
        z[0] = 0;

        for (int i = 1; i < n - 1; i++) {
            l[i] = 2 * (x[i + 1] - x[i - 1]) - h[i - 1] * mu[i - 1];
            mu[i] = h[i] / l[i];
            z[i] = (alpha[i] - alpha[i - 1] - h[i - 1] * z[i - 1]) / l[i];
        }

        l[n - 1] = 1;
        z[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            c[i] = z[i] - mu[i] * c[i + 1];
            b[i] = alpha[i] - h[i] * (c[i + 1] + 2 * c[i]) / 3;
            d[i] = (c[i + 1] - c[i]) / (3 * h[i]);
        }

        System.arraycopy(y, 0, a, 0, n);
    }

    public double interpolate(double xVal) {
        int i = Arrays.binarySearch(x, xVal);
        if (i >= 0) {
            return y[i];
        }

        i = -i - 2;
        double dx = xVal - x[i];
        return a[i] + b[i] * dx + c[i] * dx * dx + d[i] * dx * dx * dx;
    }
}

