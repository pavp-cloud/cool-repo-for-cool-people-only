package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.TargetedAttacker;
import com.example.project.entities.entityInterfaces.TargetedSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Alien extends Threat {

    //CONSTANTS

    private final int baseAttack = 2;
    private final double attackScaling = 0.5;
    private final double specialScaling = 0.25;
    public Alien(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    protected int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override // Does damage to both characters
    public int special(Character character1, Character character2) {
        int damage = (int)(baseAttack + (this.getExp() * specialScaling));

        character2.takeDamage(damage);
        character1.takeDamage(damage);
        return damage;
    }
}
