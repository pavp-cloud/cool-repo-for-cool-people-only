package com.example.project.entities.characterObjects.specializations;


import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.BasicAttacker;
import com.example.project.entities.entityInterfaces.BasicSpecial;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

public class Medic extends Character {

    //CONSTANTS
    private final int baseAttack = 8;
    private final int healingPower = 15;
    private final double attackScaling = 0.75;
    private final double specialScaling = 0.4;
    private final double damageVulnerability = 1.2;

    /*
    constructor for the Medic class
     */
    public Medic(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    /*
    attack method for the Medic class using its damage modifiers
     */
    public int attack(){
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    /*
    special method for the medic allowing it to heal itself in combat
     */
    public int special(){
        int healing = (int) (healingPower + (this.getExp() * specialScaling));

        setHealth(Math.min(getMaxHealth(), getCurrentHealth()+healing));
        return 0;
    }

    /*
    method for taking damage with the medics custom damage resistance formula
     */
    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
