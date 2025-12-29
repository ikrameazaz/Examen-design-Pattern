package org.sid.strategy;

import org.sid.agent.Agent;
import org.sid.models.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryStrategy implements NotificationStrategy {
    private final List<Transaction> historique = new ArrayList<>();

    @Override
    public void handleNotification(Agent source, Transaction transaction) {
        historique.add(transaction);
        System.out.println("[HistoryStrategy] Transaction ajoutée. Historique: " + historique.size());
    }

    public List<Transaction> getHistorique() {
        return Collections.unmodifiableList(historique);
    }

    public int getHistoriqueSize() {
        return historique.size();
    }

    public void clearHistorique() {
        historique.clear();
    }
}
