package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.BasicAttacker;
import com.example.project.entities.entityInterfaces.BasicSpecial;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

public class Scientist extends Character {

    //CONSTANTS
    private final int baseAttack = 4;
    private final int expPotionValue = 7;
    private final double attackScaling = 0.3;
    private final double damageVulnerability = 1.5;

    /*
    constructor for the Scientist class
     */
    public Scientist(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    /*
    attack method for the Scientist class
     */
    public int attack(){
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    /*
    special method for the Scientist class. The scientist is able to brew an exp potion and drink it
    mid-combat for permanent effects.
     */
    public int special(){
        gainExp(expPotionValue);
        increaseMaxHealth(expPotionValue);
        return  0;
    }

    /*
    method for taking damage with the scientists custom damage resistance formula
     */
    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
