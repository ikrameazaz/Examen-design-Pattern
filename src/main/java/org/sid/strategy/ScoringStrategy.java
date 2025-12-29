package org.sid.strategy;

import org.sid.agent.Agent;
import org.sid.models.Transaction;
import org.sid.models.TransactionType;

public class ScoringStrategy implements NotificationStrategy {
    private double solde = 0.0;

    @Override
    public void handleNotification(Agent source, Transaction transaction) {
        if (transaction.getType() == TransactionType.VENTE) {
            solde += transaction.getMontant();
        } else if (transaction.getType() == TransactionType.ACHAT) {
            solde -= transaction.getMontant();
        }
        System.out.println("[ScoringStrategy] Nouveau solde: " + solde);
    }

    public double getSolde() {
        return solde;
    }

    public void resetSolde() {
        this.solde = 0.0;
    }
}
