package org.sid.agent;

import org.sid.aspects.annotations.Cachable;
import org.sid.aspects.annotations.Log;
import org.sid.models.Transaction;
import org.sid.strategy.DefaultStrategy;
import org.sid.strategy.NotificationStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agent {
    private String nom;
    private List<Transaction> transactions;
    private List<Agent> observateurs;
    private NotificationStrategy strategy;

    public Agent(String nom) {
        this.nom = nom;
        this.transactions = new ArrayList<>();
        this.observateurs = new ArrayList<>();
        this.strategy = new DefaultStrategy();
    }

    public void subscribe(Agent agent) {
        if (agent != null && !observateurs.contains(agent) && agent != this) {
            observateurs.add(agent);
        }
    }

    public void unsubscribe(Agent agent) {
        observateurs.remove(agent);
    }

    private void notifyObservers(Transaction transaction) {
        for (Agent obs : observateurs) {
            obs.update(this, transaction);
        }
    }

    public void update(Agent source, Transaction transaction) {
        strategy.handleNotification(source, transaction);
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public NotificationStrategy getStrategy() {
        return strategy;
    }

    @Log
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
            notifyObservers(transaction);
        }
    }

    @Log
    @Cachable
    public Transaction getMaxTransaction() {
        if (transactions.isEmpty()) {
            return null;
        }
        return transactions.stream()
                .max(Comparator.comparingDouble(Transaction::getMontant))
                .orElse(null);
    }

    public void afficher() {
        System.out.println("Agent: " + nom);
        System.out.println("Transactions: " + transactions.size());
        for (Transaction t : transactions) {
            System.out.println("  - " + t);
        }
    }

    public String getNom() {
        return nom;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public List<Agent> getObservateurs() {
        return observateurs;
    }

    @Override
    public String toString() {
        return "Agent{nom='" + nom + "'}";
    }
}
