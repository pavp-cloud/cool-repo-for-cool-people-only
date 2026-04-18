package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatCharacterSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Soldier extends Character implements CombatActor, CombatCharacterSpecial {
    private boolean usedGrenade = false;

    //CONSTANTS
    private final int baseAttack = 5;
    private final int grenadeAttack = 10;
    private final double attackScaling = 0.5;
    private final double specialScaling = 0.8;
    private final double damageVulnerability = 1.0;

    public Soldier(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public boolean getUsedGrenade() {
        return this.usedGrenade;
    }

    public void setUsedGrenade(boolean state) {
        this.usedGrenade = state;
    }

    public int attack(){
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    //"throws" the grenade
    //its like attack, but with a higher base value
    //could also be higher scaling but thats details
    //todo: if we're going for the "reusing a one-time use action reloads it" thing we're gonna have to communicate that on the ui just fyi
    public int special(){
        if (usedGrenade) {
            setUsedGrenade(false);
            return 0;
        } else {
            setUsedGrenade(true);
            return (int) (grenadeAttack + (this.getExp() * specialScaling));
        }
    }

    public void endOfCombatPrep(Threat threat) {
        gainExp(threat.getExp());
        missionsCompleted++;
        increaseMaxHealth(threat.getExp());
        setUsedGrenade(false);
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
