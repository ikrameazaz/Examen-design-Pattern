package org.sid;

import org.sid.builder.Transaction;
import org.sid.builder.TransactionType;
import org.sid.observer.Agent;
import org.sid.singleton.Container;
import org.sid.strategy.HistoryStrategy;
import org.sid.strategy.ScoringStrategy;
import org.sid.adapter.ConsoleDisplay;
import org.sid.adapter.VGADisplay;
import org.sid.adapter.VGAToHDMIAdapter;
import org.sid.adapter.VGA;
import org.sid.security.SecurityContext;

public class Main {

        public static void main(String[] args) {
                System.out.println("=== EXAMEN DESIGN PATTERNS ===\n");

                // 1. BUILDER
                System.out.println("--- 1. Builder Pattern ---");
                Transaction t1 = Transaction.builder()
                                .setId("T1")
                                .setMontant(1000)
                                .setType(TransactionType.VENTE)
                                .build();
                System.out.println(t1);

                // 2. SINGLETON
                System.out.println("\n--- 2. Singleton Pattern ---");
                Container c1 = Container.getInstance();
                Container c2 = Container.getInstance();
                System.out.println("Meme instance ? " + (c1 == c2));

                // 3. OBSERVER & STRATEGY
                System.out.println("\n--- 3. Observer & Strategy Patterns ---");
                Agent a1 = new Agent("Agent1");
                Agent a2 = new Agent("Agent2");

                a1.subscribe(a2);
                a2.setStrategy(new ScoringStrategy());

                a1.addTransaction(t1);

                // 4. ADAPTER
                System.out.println("\n--- 4. Adapter Pattern ---");
                SecurityContext.login("admin", "admin123");
                c1.addAgent(a1);
                c1.addAgent(a2);
                SecurityContext.logout();

                c1.setDisplay(new ConsoleDisplay());
                c1.afficher();

                System.out.println("\n[Adapter VGA->HDMI]");
                VGA vga = new VGADisplay();
                c1.setDisplay(new VGAToHDMIAdapter(vga));
                c1.afficher();

                System.out.println("\n=== FIN ===");
        }
}