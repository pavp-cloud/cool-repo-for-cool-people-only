package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.TargetedAttacker;
import com.example.project.entities.entityInterfaces.TargetedSpecial;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Parasite extends Threat {

    //CONSTANTS

    private final int baseAttack = 2;
    private final double attackScaling = 0.5;
    private final double specialScaling = 0.2;

    public Parasite(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);

    }

    @Override
    protected int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override
    public int special(Character character1, Character character2) { // Heals based on damage dealt
        Random random = new Random();
        int target = random.nextInt(2);
        int damage = (int)(baseAttack + (this.getExp() * specialScaling)); // Lower scaling to compensate for healing

        if (target == 0) { // Basic targeting
            character1.takeDamage(damage);
        }
        else {
            character2.takeDamage(damage);
        }

        healHealth(damage); // Heals hp equal to the attack's damage (not accounting for resistances)
        return damage;
    }
}
