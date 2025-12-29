package org.sid.strategy;

import org.sid.agent.Agent;
import org.sid.models.Transaction;

public interface NotificationStrategy {
    void handleNotification(Agent source, Transaction transaction);
}
