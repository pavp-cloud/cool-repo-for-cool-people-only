package com.example.project;

public class Parasite extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int baseAttack = 3;

    public Parasite(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
        this.baseAttack = baseAttack;

    }

    @Override
    public int attack(Character character1, Character character2){

    }

    @Override
    public int special(Character character1, Character character2) {

    }

    @Override
    public void takeDamage(int damage) {

    }
}
