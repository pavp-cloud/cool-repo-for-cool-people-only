package com.example.project.entities.characterObjects;

import static java.lang.Math.round;

import com.example.project.entities.entityInterfaces.BasicAttacker;
import com.example.project.entities.entityInterfaces.BasicSpecial;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.TargetedAttacker;
import com.example.project.entities.entityInterfaces.TargetedSpecial;
import com.example.project.entities.threatObjects.Threat;

public abstract class Character implements CombatActor, BasicAttacker, BasicSpecial {
    protected int maxHealth;
    protected int currentHealth;
    protected String name;
    protected int exp;
    protected int missionsCompleted = 0;
    protected boolean isDead = false;
    private final double maxHealthScaling = 1.5;
    // used to scale the health increase based on exp gained

    /*
    constructor for the character class
     */
    public Character(int maxHealth, int currentHealth, String name, int exp) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.name = name;
        this.exp = exp;
    }

    /*
    getter for the max health
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /*
    getter for the current health
     */
    public boolean isDead() {
        return isDead;
    }

    /*
    setter for the dead flag
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /*
    setter for the current health
     */
    public void setHealth(int health) {
        this.currentHealth = health;
    }

    /*
    increases the max health by a certain amount
     */
    public void increaseMaxHealth(int healthIncrease) {
        this.maxHealth += (int) round(healthIncrease * maxHealthScaling);
    }

    /*
    getter for the current health
     */
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    /*
    resets the current health to the max health
     */
    public void resetCurrentHealth(int maxHealth) {
        this.currentHealth = maxHealth;
    }

    /*
    getter for the name
     */
    public String getName() {
        return this.name;
    }

    /*
    getter for the exp
     */
    public int getExp() {
        return this.exp;
    }

    /*
    getter for if a character is alive or dead
     */
    public String getStatus() {
        if (isDead) {
            return "Dead";
        } else {
            return "Alive";
        }
    }

    /*
    getter for the missions completed by a character
     */
    public int getMissionsCompleted() {
        return this.missionsCompleted;
    }

    /*
    adds exp to the character
     */
    public void gainExp(int expChange) {
        this.exp += expChange;
    }

    /*
    heals the character
     */
    public void healHealth(int heal) {
        this.currentHealth += heal;
    }

    /*
    abstract method for taking damage
     */
    public abstract void takeDamage(int attackIntensity);

    /*
    abstract method for attacking
     */
    public abstract int attack();

    /*
    abstract method for using a characters special
     */
    public abstract int special();

    /*
    adjusts the health of the character
     */
    public void adjustHealth(int healthChange) {
        this.currentHealth += (int) (healthChange * maxHealthScaling);
    }

    /*
    abstract method for things needed to be executed at the end of combat
     */
    public void endOfCombatPrep(Threat threat) {
        int expChange = (int) (threat.getExp() * 0.3);
        gainExp(expChange);
        missionsCompleted++;
        increaseMaxHealth(expChange);
    }
}
