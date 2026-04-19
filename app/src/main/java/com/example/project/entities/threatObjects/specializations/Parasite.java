package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;

public class Parasite extends Threat {

    //CONSTANTS

    private final int baseAttack = 7;
    private final double attackScaling = 0.7;
    private final double specialScaling = 0.65;

    public Parasite(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);

    }

    @Override
    public int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override
    public int special(Character character1, Character character2) { // Heals based on damage dealt
        int damage = (int)(baseAttack + (this.getExp() * specialScaling)); // Lower scaling to compensate for healing

        Character target = pickTarget(character1, character2);

        if (target != null) {
            target.takeDamage(damage);
            healHealth(damage); // Heals hp equal to the attack's damage (not accounting for resistances)
            return damage;
        }

        return 0;
    }
}
