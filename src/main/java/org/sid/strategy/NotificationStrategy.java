package org.sid.strategy;

import org.sid.model.TransactionEvent;

/**
 * Interface Strategy pour le traitement des notifications
 * Pattern Strategy : définit une famille d'algorithmes interchangeables
 */
public interface NotificationStrategy {
    /**
     * Traite une notification de transaction
     * 
     * @param event L'événement contenant l'agent émetteur et la transaction
     */
    void handleNotification(TransactionEvent event);
}
