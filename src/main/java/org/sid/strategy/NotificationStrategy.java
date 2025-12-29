package org.sid.strategy;

import org.sid.builder.Transaction;

public interface NotificationStrategy {
    void handleNotification(String agentSource, Transaction transaction);
}
