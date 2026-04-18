package com.example.project.entities.threatObjects.specializations;


import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Gundam extends Threat {
    private boolean missileUsed = false;

    //CONSTANTS

    private final int baseAttack = 2;
    private final double attackScaling = 0.5;
    private final double specialScaling = 2;


    public Gundam(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    protected int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override
    public int special(Character character1, Character character2) {
        //shoots a missile for big damage
        Random random = new Random();
        int target = random.nextInt(2); //returns int from 0 to 1 (higher bound is exclusive, so a bound of 2 gives a 1)
        int damage = (int)(baseAttack + (this.getExp() * specialScaling)); //The Big Damage

        if (missileUsed) { //if used, reload
            reloadMissile();
        } else {          //if not, do the Big Damage
            if (target == 0) {
                character1.takeDamage(damage);
            } else {
                character2.takeDamage(damage);
            }
            missileUsed();
        }

        return damage;
    }

    public void reloadMissile() {
        this.missileUsed = false;
    }

    public void missileUsed() {
        this.missileUsed = true;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }
}
