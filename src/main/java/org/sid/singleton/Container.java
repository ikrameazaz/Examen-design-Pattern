package org.sid.singleton;

import org.sid.adapter.HDMI;
import org.sid.aspects.annotations.Log;
import org.sid.aspects.annotations.SecuredBy;
import org.sid.observer.Agent;

import java.util.HashMap;

public class Container {
    private static Container instance;
    private HashMap<String, Agent> agents;
    private HDMI display;

    private Container() {
        this.agents = new HashMap<>();
    }

    public static synchronized Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    @Log
    @SecuredBy(roles = { "ADMIN" })
    public void addAgent(Agent agent) {
        if (agent != null) {
            agents.put(agent.getNom(), agent);
        }
    }

    @Log
    @SecuredBy(roles = { "ADMIN" })
    public void removeAgent(String nom) {
        agents.remove(nom);
    }

    public Agent getAgent(String nom) {
        return agents.get(nom);
    }

    public void setDisplay(HDMI display) {
        this.display = display;
    }

    @Log
    public void afficher() {
        if (display != null) {
            display.display(agents);
        } else {
            System.out.println("Pas d'ecran connecte.");
        }
    }

    // Pour les tests
    public static void resetInstance() {
        instance = null;
    }

    public int getAgentCount() {
        return agents.size();
    }
}
