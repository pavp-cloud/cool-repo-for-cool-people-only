package com.example.project;


public class Gundam extends Threat{
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private boolean missileUsed = false;
    private int baseAttack = 6;



    public Gundam(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
        this.missileUsed = missileUsed;
        this.baseAttack = baseAttack;
    }

    @Override
    public int attack(Character character1, Character character2) {

    }

    @Override
    public int special(Character character1, Character character2) {
        // shoots a missile for big damage
    }

    public void reloadMissile() {
        this.missileUsed = false;
    }

    public void missileUsed() {
        this.missileUsed = true;
    }

    @Override
    public void takeDamage(int damage) {

    }
}
