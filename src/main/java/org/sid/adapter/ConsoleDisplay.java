package org.sid.adapter;

import org.sid.observer.Agent;
import java.util.HashMap;

public class ConsoleDisplay implements HDMI {

    @Override
    public void display(HashMap<String, Agent> agents) {
        System.out.println("--- Affichage HDMI Standard ---");
        for (Agent agent : agents.values()) {
            System.out.println("Agent: " + agent.getNom() + " (" + agent.getTransactionCount() + " tx)");
        }
        System.out.println("-------------------------------");
    }
}
