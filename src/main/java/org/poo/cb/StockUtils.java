package org.poo.cb;
import java.util.List;
public class StockUtils {
    public static double shortTermSMA(List<Double> values) {
        double sum = 0;

        for (int i = 0; i < 5; i++) {
            sum += values.get(i);
        }

        return sum / 5;
    }

    public static double longTermSMA(List<Double> values) {
        double sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += values.get(i);
        }

        return sum / 10;
    }
}