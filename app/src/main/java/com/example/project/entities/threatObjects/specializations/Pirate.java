package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Pirate extends Threat {

    //CONSTANTS

    private final int baseAttack = 2;
    private final int baseExpSteal = 5;
    private final double attackScaling = 0.5;

    public Pirate(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }


    protected int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override
    public int special(Character character1, Character character2) {
        int stolenExp = baseExpSteal + (exp/10);
        Random random = new Random();
        int target = random.nextInt(2);

        if (target == 0) {
            if (character1.getExp() < baseExpSteal) {
                stolenExp = character1.getExp();
            }
            character1.gainExp(-stolenExp);
            setExp(getExp()+stolenExp);
        }
        else {
            if (character2.getExp() < baseExpSteal) {
                stolenExp = character2.getExp();
            }
            character2.gainExp(-stolenExp);
            setExp(getExp()+stolenExp);
        }
        return 0;
    }

    public void setExp(int value) {
        this.exp = value;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }
}

