package com.example.project;

import static java.lang.Math.random;

import java.lang.Math;

public class Demon extends Threat{

    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int buffStackCounter = 1;
    private int baseAttack = 8;

    public Demon(int maxHealth, int currentHealth, String name, int exp, int buffStackCounter) {
        super(maxHealth, currentHealth, name, exp);
        this.buffStackCounter = buffStackCounter;
        this.baseAttack = baseAttack;

    }
    @Override
    public int attack(Character character1, Character character2) {
        if(Math.random() < 0.5) {
            character1.takeDamage(this.baseAttack * (SpaceShip.getInstance().getDaysOnBoard() / 2) * getBuffStackCounter()););
        }
    }

    @Override
    public int special(Character character1, Character character2) {
        incrementBuffStackCounter();
        return 0;
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