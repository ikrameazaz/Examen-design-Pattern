package org.sid.adapter;

import org.sid.observer.Agent;
import java.util.HashMap;

public class VGAToHDMIAdapter implements HDMI {
    private VGA vga;

    public VGAToHDMIAdapter(VGA vga) {
        this.vga = vga;
    }

    @Override
    public void display(HashMap<String, Agent> agents) {
        String data = "Agents en memoire : " + agents.size() + "\n";
        for (Agent a : agents.values()) {
            data += "- " + a.getNom() + "\n";
        }
        vga.show(data);
    }
}
