package org.poo.cb;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StockDataFileDataReader implements FileDataReader{
    public void readData(Bank bank, String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> data = csvReader.readAll();

            for (int i = 1; i < data.size(); i++) {
                String stockName = data.get(i)[0];
                List<Double> values = new ArrayList<>();

                for (int j = 1; j < data.get(i).length; j++) {
                    values.add(Double.parseDouble(data.get(i)[j]));
                }
                Collections.reverse(values);
                bank.stockDataMap.put(stockName, values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
