package org.sid;

import org.junit.jupiter.api.Test;
import org.sid.singleton.Container;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    @Test
    void testSingleton() {
        Container c1 = Container.getInstance();
        Container c2 = Container.getInstance();
        assertSame(c1, c2);
    }
}
