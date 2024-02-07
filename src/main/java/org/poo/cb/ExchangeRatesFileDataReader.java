package org.poo.cb;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class ExchangeRatesFileDataReader implements FileDataReader{
    public void readData(Bank bank, String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> data = csvReader.readAll();

            bank.exchangeRatesMatrix = new double[data.size() - 1][data.get(0).length - 1];

            for (int i = 1; i < data.size(); i++) {
                for (int j = 1; j < data.get(i).length; j++) {
                    bank.exchangeRatesMatrix[i - 1][j - 1] = Double.parseDouble(data.get(i)[j]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
