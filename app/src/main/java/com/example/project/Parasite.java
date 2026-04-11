package com.example.project;

import java.util.Random;

public class Parasite extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;

    //CONSTANTS

    private final int baseAttack = 2;

    public Parasite(int maxHealth, int currentHealth, String name, int exp){
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
    public int special(Character character1, Character character2) { //lifesteal
        Random random = new Random();
        int target = random.nextInt(2); //returns int from 0 to 1 (higher bound is exclusive, so a bound of 2 gives a 1)
        int damage = (int)(baseAttack + (this.getExp() * 0.2)); //lower scaling to compensate for healing

        if (target == 0) { //basic targeting
            character1.takeDamage(damage);
        }
        else {
            character2.takeDamage(damage);
        }

        healHealth(damage); //heals hp equal to the attack's damage (not accounting for resistances)
        return 0;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }
}
