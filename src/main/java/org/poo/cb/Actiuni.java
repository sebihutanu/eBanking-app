package org.poo.cb;
public class Actiuni {
    public String numeCompanie;
    int numarActiuni;
    Actiuni (String numeCompanie) {
        this.numeCompanie = numeCompanie;
        this.numarActiuni = 0;
    }
    public void adaugaActiuni(int numarActiuni) {
        this.numarActiuni += numarActiuni;
    }
    public String toString() {
        return "{\"stockName\":" + "\"" + numeCompanie + "\"" + ",\"amount\":" + numarActiuni + "}";
    }
}