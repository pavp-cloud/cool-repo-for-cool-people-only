package com.example.project;

public class Pirate extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;

    public Pirate(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }
    @Override
    public int special(Character character1, Character character2) {
        // steals exp
    }
    @Override
    public int attack(Character character1, Character character2) {

    }

    public void setExp(int value) {

    }

    @Override
    public void takeDamage(int damage) {

    }
}

