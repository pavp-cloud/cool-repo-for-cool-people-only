package com.example.project;

import static java.lang.Math.round;

public abstract class Character {
    protected int maxHealth;
    protected int currentHealth;
    protected String name;
    protected int exp;
    protected int missionsCompleted = 0;
    protected boolean isDead = false;
    /*
    private int finalBlows;
    private int trainingCompleted;
    //statistics variables, implementation to be done
     */

    public Character(int maxHealth, int currentHealth, String name, int exp) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.name = name;
        this.exp = exp;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
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

    public String getStatus() {
        if(isDead) {
            return "Dead";
        } else {
            return "Alive";
        }
    }

    public int getMissionsCompleted() { return this.missionsCompleted; }

    public void gainExp(int expChange) {
        this.exp += expChange;
    }

    public void healHealth(int heal) {
        this.currentHealth += heal;
    }

    public abstract void takeDamage (int attackIntensity);
    public abstract int attack();
    public abstract int special();
    public abstract void endOfCombatPrep(Threat threat);
}
