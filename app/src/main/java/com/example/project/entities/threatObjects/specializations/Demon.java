package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.TargetedAttacker;
import com.example.project.entities.entityInterfaces.TargetedSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Demon extends Threat {
    private int buffStackCounter = 1;

    //CONSTANTS

    private final int baseAttack = 8;
    private final double attackScaling = 0.56;

    public Demon(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }
    @Override // Damage additionally scales with the amount of buff stacks
    public int calculateDamage() {
        return (int) (baseAttack + ((this.getExp() * getBuffStackCounter()) * attackScaling));
    }

    @Override // Increments it's number of buff stacks and then feeds it into the damage calculation
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
}