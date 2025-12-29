package org.sid.strategy;

import org.sid.agent.Agent;
import org.sid.models.Transaction;

public class DefaultStrategy implements NotificationStrategy {

    @Override
    public void handleNotification(Agent source, Transaction transaction) {
        System.out.println("[DefaultStrategy] Notification de " + source.getNom() + " : " + transaction);
    }
}
