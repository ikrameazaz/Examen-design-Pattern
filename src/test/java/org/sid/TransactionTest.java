package org.sid;

import org.junit.jupiter.api.Test;
import org.sid.builder.Transaction;
import org.sid.builder.TransactionType;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    void testBuilder() {
        Transaction t = Transaction.builder()
                .setId("T1")
                .setMontant(100)
                .setType(TransactionType.VENTE)
                .build();

        assertNotNull(t);
        assertEquals("T1", t.getId());
    }
}
