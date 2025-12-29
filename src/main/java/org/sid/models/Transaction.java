package org.sid.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String id;
    private final LocalDateTime date;
    private final double montant;
    private final TransactionType type;

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
        return String.format("Transaction{id='%s', date=%s, montant=%.2f, type=%s}",
                id, date.format(formatter), montant, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Transaction that = (Transaction) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static class Builder {
        private String id;
        private LocalDateTime date;
        private double montant;
        private TransactionType type;

        public Builder() {
            this.date = LocalDateTime.now();
        }

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
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalStateException("L'id de la transaction est obligatoire");
            }
            if (date == null) {
                throw new IllegalStateException("La date de la transaction est obligatoire");
            }
            if (montant < 0) {
                throw new IllegalStateException("Le montant ne peut pas être négatif");
            }
            if (type == null) {
                throw new IllegalStateException("Le type de transaction est obligatoire");
            }
            return new Transaction(id, date, montant, type);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
