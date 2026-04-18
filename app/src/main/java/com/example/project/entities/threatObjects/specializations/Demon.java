package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

public class Demon extends Threat {
    private int buffStackCounter = 1;

    //CONSTANTS

    private final int baseAttack = 2;
    private final double attackScaling = 0.6;

    public Demon(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }
    @Override
    protected int calculateDamage() {
        return (int) (baseAttack + ((this.getExp() * getBuffStackCounter()) * attackScaling));
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