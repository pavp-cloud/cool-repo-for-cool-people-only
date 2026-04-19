package com.example.project.entities.threatObjects.specializations;


import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Gundam extends Threat {

    private final int baseAttack = 7;
    private final double attackScaling = 0.5;
    private final double specialScaling = 2;


    public Gundam(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    public int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override
    public int special(Character character1, Character character2) {
        // Deals a large amount of damage both to one character and to itself
        Character target = pickTarget(character1, character2);
        int damage = (int)(baseAttack + (this.getExp() * specialScaling));
        int recoilDamage = (int) (damage * 0.7);

        if (target != null) {
            target.takeDamage(damage);
        }
        takeDamage(recoilDamage);

        return damage;
    }

}
