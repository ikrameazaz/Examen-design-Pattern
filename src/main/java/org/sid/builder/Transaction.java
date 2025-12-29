package org.sid.builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String id;
    private LocalDateTime date;
    private double montant;
    private TransactionType type;

    private Transaction(String id, LocalDateTime date, double montant, TransactionType type) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Transaction{id='" + id + "', date=" + date.format(formatter) +
                ", montant=" + montant + ", type=" + type + "}";
    }

    public static class Builder {
        private String id;
        private LocalDateTime date = LocalDateTime.now();
        private double montant;
        private TransactionType type;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder setMontant(double montant) {
            this.montant = montant;
            return this;
        }

        public Builder setType(TransactionType type) {
            this.type = type;
            return this;
        }

        public Transaction build() {
            if (id == null || id.isEmpty()) {
                throw new IllegalStateException("Id obligatoire");
            }
            if (montant < 0) {
                throw new IllegalStateException("Montant negatif interdit");
            }
            if (type == null) {
                throw new IllegalStateException("Type obligatoire");
            }
            return new Transaction(id, date, montant, type);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
