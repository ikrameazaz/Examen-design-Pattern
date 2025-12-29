package org.sid;

import org.junit.jupiter.api.Test;
import org.sid.observer.Agent;
import org.sid.strategy.ScoringStrategy;
import org.sid.builder.Transaction;
import org.sid.builder.TransactionType;

import static org.junit.jupiter.api.Assertions.*;

public class AgentTest {

    @Test
    void testObserverStrategy() {
        Agent a1 = new Agent("A1");
        Agent a2 = new Agent("A2");
        ScoringStrategy s = new ScoringStrategy();
        a2.setStrategy(s);

        a1.subscribe(a2);

        Transaction t = Transaction.builder()
                .setId("T1")
                .setMontant(100)
                .setType(TransactionType.VENTE)
                .build();

        a1.addTransaction(t);

        assertEquals(100.0, s.getSolde());
    }
}
