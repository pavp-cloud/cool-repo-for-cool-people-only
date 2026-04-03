package com.example.project;

public class Demon extends Threat{

    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int buffStackCounter;

    public Demon(int maxHealth, int currentHealth, String name, int exp, int buffStackCounter) {
        super(maxHealth, currentHealth, name, exp);
        this.buffStackCounter = buffStackCounter;

    }
    @Override
    public int attack() {

    }

    @Override
    public int special() {
        // gains a buff to damage
    }

    public int getBuffStackCounter() {
        return buffStackCounter;
    }

    public void incrementBuffStackCounter() {
        buffStackCounter++;
    }

    @Override
    public void takeDamage(int damage) {

    }
}
//hello daddy