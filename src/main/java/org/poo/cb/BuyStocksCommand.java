package org.poo.cb;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BuyStocksCommand implements Command {
    private final String email;
    private final String stock;
    private final int quantity;
    private final Map<String, List<Double>> stockDataMap;
    private final HashMap<String, User> users;

    BuyStocksCommand(String email, String stock, int quantity, Map<String, List<Double>> stockDataMap, HashMap<String, User> users) {
        this.email = email;
        this.stock = stock;
        this.quantity = quantity;
        this.stockDataMap = stockDataMap;
        this.users = users;
    }

    public void execute() {
        double price = stockDataMap.get(stock).get(0);
        double value = price * quantity;
        if (users.get(email).portfolio.accounts.get("USD").amount < value) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }
        double shortTermSMA = StockUtils.shortTermSMA(stockDataMap.get(stock));
        double longTermSMA = StockUtils.longTermSMA(stockDataMap.get(stock));
        if(shortTermSMA > longTermSMA && users.get(email).premium) {
            users.get(email).portfolio.accounts.get("USD").substract(value*0.95);
        } else {
            users.get(email).portfolio.accounts.get("USD").substract(value);
        }
        if (users.get(email).portfolio.actions.containsKey(stock)) {
            users.get(email).portfolio.actions.get(stock).adaugaActiuni(quantity);
        } else {
            Actiuni actiuni = new Actiuni(stock);
            actiuni.adaugaActiuni(quantity);
            users.get(email).portfolio.actions.put(stock, actiuni);
        }
    }
}
