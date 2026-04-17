package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatCharacterSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Scientist extends Character implements CombatActor, CombatCharacterSpecial {
    private boolean usedExpPotion = false;

    //CONSTANTS
    private final int baseAttack = 2;
    private final int expPotionValue = 5;
    private final double attackScaling = 0.5;
    private final double damageVulnerability = 1.2;

    public Scientist(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public boolean getExpPotion() {
        return this.usedExpPotion;
    }

    public void setExpPotion(boolean state) {
        this.usedExpPotion = state;
    }

    public int attack(){
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    //exp potion
    //todo: if we're going for the "reusing a one-time use action reloads it" thing we're gonna have to communicate that on the ui just fyi
    public int special(){
        if(usedExpPotion) {
            setExpPotion(false);
            return 0;
        } else { //gonna have to figure out how we want this to function, exp to itself? the ally? everyone? to the enemy so killing them gives more?
            setExpPotion(true);
            exp += expPotionValue;
            return 0;
        }
    }

    public void endOfCombatPrep(Threat threat) {
        gainExp(threat.getExp());
        missionsCompleted++;
        increaseMaxHealth(threat.getExp());
        setExpPotion(false);
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
