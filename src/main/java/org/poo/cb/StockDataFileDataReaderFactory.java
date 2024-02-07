package org.poo.cb;

public class StockDataFileDataReaderFactory implements FileDataReaderFactory{
    public FileDataReader createFileDataReader() {
        return new StockDataFileDataReader();
    }
}
