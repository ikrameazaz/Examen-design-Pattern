package org.sid.model;

/**
 * Énumération représentant le type de transaction.
 * VENTE : Transaction de vente (ajoute au solde)
 * ACHAT : Transaction d'achat (soustrait du solde)
 */
public enum TransactionType {
    VENTE,
    ACHAT
}
