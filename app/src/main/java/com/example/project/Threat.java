package com.example.project;

public abstract class Threat {
    protected int maxHealth;
    protected int currentHealth;
    protected String name;
    protected int exp;


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
        this.currentHealth += healthChange;
    }

    public int getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }

    public abstract int attack(Character character1, Character character2);

    public abstract int special(Character character1, Character character2);

    public abstract void takeDamage(int damage);

    public void healHealth(int heal) { //modified to account for overheal
        int healthTotal = getCurrentHealth()+heal;
        setCurrentHealth(Math.min(healthTotal, getMaxHealth()));
    }

}
