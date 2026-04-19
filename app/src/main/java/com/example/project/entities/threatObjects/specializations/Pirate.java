package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.TargetedAttacker;
import com.example.project.entities.entityInterfaces.TargetedSpecial;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Pirate extends Threat {

    //CONSTANTS
    private final int baseAttack = 2;
    private final int baseExpSteal = 6;
    private final double attackScaling = 0.4;

    public Pirate(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    public int calculateDamage() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    @Override // Special move, stealing character experience to buff itself
    public int special(Character character1, Character character2) {
        int stolenExp = baseExpSteal + (exp/10);

        Character target = pickTarget(character1, character2);

        if (target != null) {
            // Accounting for possibly stealing more XP than a character would have
            if (target.getExp() < baseExpSteal) {
                stolenExp = target.getExp();
            }
            target.gainExp(-stolenExp);
            setExp(getExp() + stolenExp);
        }

        return 0;
    }

    public void setExp(int value) {
        this.exp = value;
    }
}
