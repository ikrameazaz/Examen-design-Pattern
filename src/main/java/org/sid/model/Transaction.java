package org.sid.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe Transaction - Immutable
 * Représente une transaction avec un id, une date, un montant et un type.
 * Utilise le pattern Builder pour la création.
 */
public class Transaction {
    private final String id;
    private final LocalDateTime date;
    private final double montant;
    private final TransactionType type;

    /**
     * Constructeur privé - accessible uniquement via le Builder
     */
    private Transaction(String id, LocalDateTime date, double montant, TransactionType type) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.type = type;
    }

    // Getters uniquement (immutable)
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

    /**
     * Builder Pattern - Inner class pour construire des Transaction
     */
    public static class TransactionBuilder {
        private String id;
        private LocalDateTime date;
        private double montant;
        private TransactionType type;

        public TransactionBuilder() {
            // Valeurs par défaut
            this.date = LocalDateTime.now();
        }

        public TransactionBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public TransactionBuilder withMontant(double montant) {
            this.montant = montant;
            return this;
        }

        public TransactionBuilder withType(TransactionType type) {
            this.type = type;
            return this;
        }

        /**
         * Construit et retourne une nouvelle Transaction après validation
         * 
         * @throws IllegalStateException si les données ne sont pas valides
         */
        public Transaction build() {
            validate();
            return new Transaction(id, date, montant, type);
        }

        /**
         * Valide les données avant la création
         */
        private void validate() {
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
        }
    }

    /**
     * Méthode factory pour obtenir un nouveau Builder
     */
    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }
}
