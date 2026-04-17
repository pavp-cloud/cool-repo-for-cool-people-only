package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatThreatSpecial;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Parasite extends Threat implements CombatActor, CombatThreatSpecial {

    //CONSTANTS

    private final int baseAttack = 2;
    private final double attackScaling = 0.5;
    private final double specialScaling = 0.2;

    public Parasite(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);

    }

    @Override
    public int attack(Character character1, Character character2) {
        //basic base attack logic, can be copied everywhere but with different scaling
        Random random = new Random();
        int target = random.nextInt(2); //returns int from 0 to 1 (higher bound is exclusive, so a bound of 2 gives a 1)
        int damage = (int)(baseAttack + (this.getExp() * attackScaling));

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
        int damage = (int)(baseAttack + (this.getExp() * specialScaling)); //lower scaling to compensate for healing

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
