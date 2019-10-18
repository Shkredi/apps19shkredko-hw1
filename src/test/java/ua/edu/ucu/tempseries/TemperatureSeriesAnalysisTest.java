package ua.edu.ucu.tempseries;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis t1, t2, tNull;

    @Before
    public void setUp(){
        t1 = new TemperatureSeriesAnalysis(new double[]{-15.5d, 67d, 8.5d, -46.4d, 27d});
        tNull = new TemperatureSeriesAnalysis();
    }

    @Test(expected = InputMismatchException.class)
    public void impossibleTemp(){
        t2 = new TemperatureSeriesAnalysis(new double[]{-4, 5.43, -300, 21});
    }

    @Test
    public void average() {
        assertEquals(8.12, t1.average(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void averageNull(){
        tNull.average();
    }

    @Test
    public void deviation() {
        assertEquals(38.35046, t1.deviation(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deviationNull(){
        tNull.deviation();
    }

    @Test
    public void min() {
        assertEquals(-46.4, t1.min(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minNull(){
        tNull.min();
    }

    @Test
    public void max() {
        assertEquals(67, t1.max(),0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxNull(){
        tNull.max();
    }

    @Test
    public void findTempClosestToZero() {
        assertEquals(8.5, t1.findTempClosestToZero(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findTempClosestToZeroNull(){
        tNull.findTempClosestToZero();
    }

    @Test
    public void findTempClosestToValue() {
        assertEquals(27, t1.findTempClosestToValue(30), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findTempClosestToValueNull(){
        tNull.findTempClosestToValue(15);
    }

    @Test
    public void findTempsLessThen() {
        assertArrayEquals(new double[]{-15.5d, -46.4d}, t1.findTempsLessThen(5), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findTempsLessThenNull(){
        tNull.findTempsLessThen(35);
    }

    @Test
    public void findTempsGreaterThen() {
        assertArrayEquals(new double[]{67, 27}, t1.findTempsGreaterThen(20), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findTempsGreaterThenNull(){
        tNull.findTempsGreaterThen(28);
    }

    @Test
    public void summaryStatistics() {
        TempSummaryStatistics s = t1.summaryStatistics();
        assertEquals(8.12, s.getAvgTemp(), 0.001);
        assertEquals(38.35046, s.getDevTemp(),0.001);
        assertEquals(-46.4, s.getMinTemp(),0.001);
        assertEquals(67, s.getMaxTemp(), 0.001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void summaryStatisticsNull(){
        tNull.summaryStatistics();
    }

    @Test
    public void addTemps() {
        int newl = t1.addTemps(32.4, -21.15);
        assertEquals(7, newl);
    }

    @Test(expected = InputMismatchException.class)
    public void addTempsNull() {
        int newl = t1.addTemps(53.5, -300);
    }
}