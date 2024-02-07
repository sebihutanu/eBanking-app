package org.poo.cb;

import java.util.*;

public class Portfolio {
    HashMap<String, Cont> accounts;
    HashMap<String, Actiuni> actions;
    public String toString() {
        Map<String, Actiuni> sortedActiuni = new TreeMap<>(actions);

        StringBuilder actiuniString = new StringBuilder("[");
        boolean isFirst = true;
        for (Actiuni actiune : sortedActiuni.values()) {
            if (!isFirst) {
                actiuniString.append(",");
            }
            actiuniString.append(actiune.toString());
            isFirst = false;
        }
        actiuniString.append("]");

        List<String> conturiString = new ArrayList<>();
        for (String key : accounts.keySet()) {
            conturiString.add(accounts.get(key).toString());
        }
        Collections.reverse(conturiString);

        return "{\"stocks\":" + actiuniString + ",\"accounts\":" + conturiString + "}";
    }
}
