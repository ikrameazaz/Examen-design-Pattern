package org.sid.adapter;

public class VGADisplay implements VGA {
    @Override
    public void show(String data) {
        System.out.println("--- Ecran VGA (Ancien) ---");
        System.out.println(data);
        System.out.println("--------------------------");
    }
}
