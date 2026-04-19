package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.BasicAttacker;
import com.example.project.entities.entityInterfaces.BasicSpecial;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

public class Soldier extends Character {

    //CONSTANTS
    private final int baseAttack = 10;
    private final int grenadeAttack = 17;
    private final double attackScaling = 0.6;
    private final double specialScaling = 1.5;
    private final double damageVulnerability = 0.7;

    /*
    constructor for the Soldier class
     */
    public Soldier(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    /*
    attack method for the soldier using its damage modifiers
     */
    public int attack(){
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    /*
    special method for the soldier to throw a grenade at the enemy for big damage. it also
    deals damage to itself for being in the blast radius of the grenade.
     */
    public int special(){
        int damage = (int) (grenadeAttack + (this.getExp() * specialScaling));
        int selfDamage = (int) (damage * 0.4);
        takeDamage(selfDamage);
        return damage;
    }

    /*
    method for taking damage with the soldiers custom damage resistance formula
     */
    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
