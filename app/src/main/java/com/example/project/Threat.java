package com.example.project;

public abstract class Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;


    public Threat(int maxHealth, int currentHealth, String name, int exp) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.name = name;
        this.exp = exp;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int healthChange) {

    }

    public int getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }

    public abstract int attack();

    public abstract int special();

    public void takeDamage(int damage) {

    }

    public void healHealth(int heal) {

    }

    public void targeting() {

    }

}
