package org.poo.cb;

public class ExchangeRatesFileDataReaderFactory implements FileDataReaderFactory{
    public FileDataReader createFileDataReader() {
        return new ExchangeRatesFileDataReader();
    }
}
