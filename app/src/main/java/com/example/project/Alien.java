package com.example.project;

public class Alien extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int baseAttack = 5;


    public Alien(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
        this.baseAttack = baseAttack;

    }

    @Override
    public int attack(Character character1, Character character2) {
        return(int)(2 + (this.getExp() * 0.5));

    }

    @Override
    public int special(Character character1, Character character2) {
        int damage = (int)(2 + (this.getExp() * 0.25));
        character2.takeDamage(damage);
        character1.takeDamage(damage);
        //shoots laser that does AOE
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth += damage;
    }
}
