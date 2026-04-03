package com.example.project;

public class Alien extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;


    public Alien(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);

    }

    @Override
    public int attack(Character character1, Character character2) {
        return(int)(2 + (this.getExp() * 0.5));

    }

    @Override
    public int special(Character character1, Character character2) {
        this.attack(character1);
        this.attack(character2);
        //shoots laser that does AOE
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth += damage;
    }
}
