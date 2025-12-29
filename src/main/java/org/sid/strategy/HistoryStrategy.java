package org.sid.strategy;

import org.sid.builder.Transaction;
import java.util.ArrayList;
import java.util.List;

public class HistoryStrategy implements NotificationStrategy {
    private List<Transaction> historique = new ArrayList<>();

    @Override
    public void handleNotification(String agentSource, Transaction transaction) {
        historique.add(transaction);
        System.out.println("Historique: " + historique.size() + " transactions");
    }

    public List<Transaction> getHistorique() {
        return historique;
    }

    public int getHistoriqueSize() {
        return historique.size();
    }
}
