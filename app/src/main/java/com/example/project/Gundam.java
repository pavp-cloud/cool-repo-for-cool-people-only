package com.example.project;


import java.util.Random;

public class Gundam extends Threat{
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private boolean missileUsed = false;

    //CONSTANTS

    private final int baseAttack = 2;


    public Gundam(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    public int attack(Character character1, Character character2) {
        //basic base attack logic, can be copied everywhere but with different scaling
        Random random = new Random();
        int target = random.nextInt(2); //returns int from 0 to 1 (higher bound is exclusive, so a bound of 2 gives a 1)
        int damage = (int)(baseAttack + (this.getExp() * 0.5));

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
        //shoots a missile for big damage


        return 0;
    }

    public void reloadMissile() {
        this.missileUsed = false;
    }

    public void missileUsed() {
        this.missileUsed = true;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth += damage;
    }
}
