package org.sid.model;

/**
 * Classe TransactionEvent - utilisée pour les notifications
 * Contient le nom de l'agent émetteur et la transaction ajoutée
 */
public class TransactionEvent {
    private final String nomAgent;
    private final Transaction transaction;

    public TransactionEvent(String nomAgent, Transaction transaction) {
        this.nomAgent = nomAgent;
        this.transaction = transaction;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public String toString() {
        return String.format("TransactionEvent{agent='%s', transaction=%s}", nomAgent, transaction);
    }
}
