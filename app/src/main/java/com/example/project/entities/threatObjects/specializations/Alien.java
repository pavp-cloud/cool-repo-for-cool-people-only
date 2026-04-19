package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;

public class Alien extends Threat {

    //CONSTANTS

    private final int baseAttack = 6;
    private final double attackScaling = 0.65;
    private final double specialScaling = 0.55;
    public Alien(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    public int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override // Does damage to both characters
    public int special(Character character1, Character character2) {
        int damage = (int)(baseAttack + (this.getExp() * specialScaling));

        if (character1 != null) {
            character1.takeDamage(damage);
        }
        if (character2 != null) {
            character2.takeDamage(damage);
        }
        return damage;
    }
}
