package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatThreatSpecial;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Demon extends Threat implements CombatActor, CombatThreatSpecial {
    private int buffStackCounter = 1;

    //CONSTANTS

    private final int baseAttack = 2;
    private final double attackScaling = 0.5;

    public Demon(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
        this.buffStackCounter = buffStackCounter;

    }
    @Override
    public int attack(Character character1, Character character2) {

        //basic base attack logic, can be copied everywhere but with different scaling
        //this guy gets extra buff scaling
        Random random = new Random();
        int target = random.nextInt(2); //returns int from 0 to 1 (higher bound is exclusive, so a bound of 2 gives a 1)
        int damage = (int)(baseAttack*getBuffStackCounter() + (this.getExp() * attackScaling));

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
    }

    public int getBuffStackCounter() {
        return buffStackCounter;
    }

    public void incrementBuffStackCounter() {
        buffStackCounter++;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }
}
//hello daddy