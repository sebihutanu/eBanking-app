package org.poo.cb;

import java.util.*;

public class Bank {
    private static Bank instance;
    HashMap<String, User> users;
    Map<String, List<Double>> stockDataMap;
    double[][] exchangeRatesMatrix;

    public Bank() {
        users = new HashMap<>();
        stockDataMap = new HashMap<>();
    }
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void reset() {
        instance = new Bank();
    }

    public void readData(FileDataReaderFactory factory, String filePath) {
        FileDataReader fileDataReader = factory.createFileDataReader();
        fileDataReader.readData(this, filePath);
    }
}
