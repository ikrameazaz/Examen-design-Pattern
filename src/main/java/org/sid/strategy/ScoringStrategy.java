package org.sid.strategy;

import org.sid.builder.Transaction;
import org.sid.builder.TransactionType;

public class ScoringStrategy implements NotificationStrategy {
    private double solde = 0.0;

    @Override
    public void handleNotification(String agentSource, Transaction transaction) {
        if (transaction.getType() == TransactionType.VENTE) {
            solde += transaction.getMontant();
        } else {
            solde -= transaction.getMontant();
        }
        System.out.println("Solde mis a jour: " + solde);
    }

    public double getSolde() {
        return solde;
    }
}
