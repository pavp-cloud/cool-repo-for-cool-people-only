package com.example.project;

public class Pirate extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;

    public Pirate(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    public int special() {
        // steals exp
    }

    public int attack() {

    }

    public void setExp(int value) {

    }
}

