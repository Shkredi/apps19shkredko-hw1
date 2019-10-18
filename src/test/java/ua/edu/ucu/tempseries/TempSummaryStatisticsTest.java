package ua.edu.ucu.tempseries;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TempSummaryStatisticsTest {
    TempSummaryStatistics t1;

    @Before
    public void setUp() throws Exception {
        t1 = new TempSummaryStatistics(8.12, 38.35046,-46.4, 67);
    }

    @Test
    public void getAvgTemp() {
        assertEquals(8.12, t1.getAvgTemp(), 0.001);
    }

    @Test
    public void getDevTemp() {
        assertEquals(38.35046, t1.getDevTemp(),0.001);
    }

    @Test
    public void getMinTemp() {
        assertEquals(-46.4, t1.getMinTemp(),0.001);
    }

    @Test
    public void getMaxTemp() {
        assertEquals(67, t1.getMaxTemp(),0.001);
    }
}