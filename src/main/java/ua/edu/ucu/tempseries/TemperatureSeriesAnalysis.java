package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final int MinTemp = -237;
    private double[] temperatureSeries;
    private int length;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.length = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double t: temperatureSeries) {
            if (t < MinTemp) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = Arrays.copyOf(temperatureSeries,
                                                temperatureSeries.length);
        this.length = temperatureSeries.length;
    }

    private void emptyError() {
        if (this.length == 0) {
            throw  new IllegalArgumentException();
        }
    }

    public double average() {
        this.emptyError();
        double sum = 0;
        for (double t: this.temperatureSeries) {
            sum += t;
        }
        return sum/this.length;
    }

    public double deviation() {
        this.emptyError();
        double s = 0;
        double a = this.average();
        for (double t: this.temperatureSeries) {
            s += (t-a)*(t-a);
        }
        return Math.sqrt(s/this.length);
    }

    public double min() {
        this.emptyError();
        double m = Integer.MAX_VALUE;
        for (double t: this.temperatureSeries) {
            if (t < m) {
                m = t;
            }
        }
        return m;
    }

    public double max() {
        this.emptyError();
        double m = Integer.MIN_VALUE;
        for (double t: this.temperatureSeries) {
            if (t > m) {
                m = t;
            }
        }
        return m;
    }

    public double findTempClosestToZero() {
        return Math.abs(this.findTempClosestToValue(0));
    }

    public double findTempClosestToValue(double tempValue) {
        this.emptyError();
        int mi = 0;
        double m = Integer.MAX_VALUE;
        for (int i = 0; i < this.length; i++) {
            double d = Math.abs(this.temperatureSeries[i] - tempValue);
            if (d < m) {
                mi = i;
                m = d;
            }
        }
        return this.temperatureSeries[mi];
    }

    public double[] findTempsLessThen(double tempValue) {
        this.emptyError();
        double[] tempSeries = new double[this.length];
        int n = 0;
        for (double t: this.temperatureSeries) {
            if (t < tempValue) {
                tempSeries[n] = t;
                n++;
            }
        }
        return Arrays.copyOf(tempSeries, n);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        this.emptyError();
        double[] tempSeries = new double[this.length];
        int n = 0;
        for (double t: this.temperatureSeries) {
            if (t > tempValue) {
                tempSeries[n] = t;
                n++;
            }
        }
        return Arrays.copyOf(tempSeries, n);
    }

    public TempSummaryStatistics summaryStatistics() {
        this.emptyError();
        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = this.min();
        double maxTemp = this.max();
        return new TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);
    }

    public int addTemps(double... temps) {
        for (double t: temps) {
            if (t < MinTemp) {
                throw new InputMismatchException();
            }
        }
        int newLength = this.length + temps.length;
        double[] tempSeries = new double[newLength];
        for (int i = 0; i < this.length; i++) {
            tempSeries[i] = this.temperatureSeries[i];
        }
        for (int i = this.length; i < newLength; i++) {
            tempSeries[i] = temps[i-this.length];
        }
        this.length = newLength;
        this.temperatureSeries = tempSeries;
        return newLength;
    }
}
