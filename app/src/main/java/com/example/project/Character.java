package com.example.project;

import static java.lang.Math.round;

public abstract class Character {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    /*
    private int missionsCompleted;
    private int finalBlows;
    private int trainingCompleted;
    //statistics variables, implementation to be done
     */

    public Character(int maxHealth, int currentHealth, String name, int exp) {
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void increaseMaxHealth(int healthIncrease) {
        this.maxHealth += round(healthIncrease * 1.5);
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(int healthChange) {
        this.currentHealth += healthChange;
    }

    public String getName() {
        return this.name;
    }

    public int getExp() {
        return this.exp;
    }

    public void gainExp(int expChange) {
        this.exp += expChange;
    }

    public void healHealth(int heal) {
        this.currentHealth += heal;
    }

    public abstract void takeDamage (int attackIntensity);
    public abstract int attack();
    public abstract int special();
    public abstract void endOfCombatPrep();
}
