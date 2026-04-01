package com.example.project;

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
    }

    public void setMaxHealth(int healthIncrease) {
    }

    public int getCurrentHealth() {
    }

    public void setCurrentHealth(int healthChange) {
    }

    public String getName() {
    }

    public int getExp() {
    }

    public void gainExp(int expChange) {
    }

    public void healHealth(int heal) {
    }

    public abstract int attack();
    public abstract int special();
    public abstract void endOfCombatPrep();
}
