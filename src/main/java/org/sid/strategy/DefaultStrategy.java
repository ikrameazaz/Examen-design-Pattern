package org.sid.strategy;

import org.sid.builder.Transaction;

public class DefaultStrategy implements NotificationStrategy {

    @Override
    public void handleNotification(String agentSource, Transaction transaction) {
        System.out.println("Notification de " + agentSource + " : " + transaction);
    }
}
