package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatCharacterSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Soldier extends Character implements CombatActor, CombatCharacterSpecial {

    //CONSTANTS
    private final int baseAttack = 8;
    private final int grenadeAttack = 10;
    private final double attackScaling = 0.5;
    private final double specialScaling = 1.5;
    private final double damageVulnerability = 0.8;

    public Soldier(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public int attack(){
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    //"throws" the grenade
    //its like attack, but with a higher base value
    // The attack hits himself as well because he is in the blast radius
    public int special(){
        int damage = (int) (grenadeAttack + (this.getExp() * specialScaling));
        int selfDamage = (int) (damage * 0.4);
        takeDamage(selfDamage);
        return damage;
    }

    public void endOfCombatPrep(Threat threat) {
        gainExp(threat.getExp());
        missionsCompleted++;
        increaseMaxHealth(threat.getExp());
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
