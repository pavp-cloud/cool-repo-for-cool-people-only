package com.example.project.entities.threatObjects;

import com.example.project.entities.characterObjects.Character;

import java.util.Random;

public abstract class Threat {
    protected int maxHealth;
    protected int currentHealth;
    protected String name;
    protected int exp;
    protected Random random = new Random();

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

    public void adjustCurrentHealth(int healthChange) {
        this.currentHealth += healthChange;
    }

    public int getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }

    public int attack(Character character1, Character character2) {
        int target = random.nextInt(2);
        int damage = calculateDamage();

        if (target == 0) {
            character1.takeDamage(damage);
        } else {
            character2.takeDamage(damage);
        }
        return 0;
    }

    public abstract int special(Character character1, Character character2);

    protected abstract int calculateDamage();

    public abstract void takeDamage(int damage);

    public void healHealth(int heal) {
        adjustCurrentHealth(heal);
    }

}
