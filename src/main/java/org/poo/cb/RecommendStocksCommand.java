package org.poo.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendStocksCommand implements Command{
    private final ArrayList<String> stocks = new ArrayList<>();
    private final Map<String, List<Double>> stockDataMap;
    RecommendStocksCommand(Map<String, List<Double>> stockDataMap) {
        this.stockDataMap = stockDataMap;
    }
    private static String recommendStocks(ArrayList<String> actiuni) {
        StringBuilder recomandare = new StringBuilder("{\"stockstobuy\":[");
        for (String actiune : actiuni) {
            recomandare.append("\"").append(actiune).append("\"");
            if(actiuni.indexOf(actiune) != actiuni.size() - 1) {
                recomandare.append(",");
            }
        }
        recomandare.append("]}");
        return recomandare.toString();
    }

    public void execute() {
        for(Map.Entry<String, List<Double>> entry : stockDataMap.entrySet()) {
            String key = entry.getKey();
            List<Double> value = entry.getValue();
            double shortTermSMA = StockUtils.shortTermSMA(value);
            double longTermSMA = StockUtils.longTermSMA(value);
            if(shortTermSMA > longTermSMA) {
                stocks.add(key);
            }
        }
        String recommendedStocks = recommendStocks(stocks);
        System.out.println(recommendedStocks);
    }
}
