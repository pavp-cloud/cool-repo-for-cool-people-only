package com.example.project;

import java.util.Random;

public class Demon extends Threat{

    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int buffStackCounter;

    //CONSTANTS

    private final int baseAttack = 2;
    public Demon(int maxHealth, int currentHealth, String name, int exp, int buffStackCounter) {
        super(maxHealth, currentHealth, name, exp);
        this.buffStackCounter = buffStackCounter;

    }
    @Override
    public int attack(Character character1, Character character2) {
        //basic base attack logic, can be copied everywhere but with different scaling
        //this guy gets extra buff scaling
        Random random = new Random();
        int target = random.nextInt(2); //returns int from 0 to 1 (higher bound is exclusive, so a bound of 2 gives a 1)
        int damage = (int)(baseAttack*getBuffStackCounter() + (this.getExp() * 0.5));

        if (target == 0) {
            character1.takeDamage(damage);
        }
        else {
            character2.takeDamage(damage);
        }
        return 0;
    }

    @Override
    public int special(Character character1, Character character2) {
        incrementBuffStackCounter();
        return 0;
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
        this.currentHealth += damage;
    }
}