package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.BasicAttacker;
import com.example.project.entities.entityInterfaces.BasicSpecial;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

public class Pilot extends Character {
    private boolean droneDeployed = false;

    //CONSTANTS

    private final int baseAttack = 4;
    private int droneAttack = 6;
    private final double attackScaling = 0.6;
    private final double damageVulnerability = 1.0;

    /*
    constructor for the Pilot class
     */
    public Pilot(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    /*
    getter for the drone deployed flag
     */
    public boolean getDroneDeployed() {
        return this.droneDeployed;
    }

    /*
    setter for the drone deployed flag
     */
    public void setDroneDeployed(boolean state) {
        this.droneDeployed = state;
    }

    /*
    attack method for the Pilot class. it has 2 versions, one with the drone and one without.
    the drone adds a bonus flat damage to the attacks of the pilot
     */
    public int attack(){
        if (getDroneDeployed()) {
            return (int) (baseAttack + droneAttack + (int) (this.getExp() * 0.2) + (this.getExp() * attackScaling));
        } else {
            return (int) (baseAttack + (this.getExp() * attackScaling));
        }
    }

    /*
    special method for the pilot class. It deploys a drone to do coordinated strikes with its attacks.
    if the drone is already deployed it increases the damage done by the drone by 3.
     */
    public int special(){
        if(!getDroneDeployed()) {
            setDroneDeployed(true);
            return 0;
        } else {
            droneAttack += 3;
            return 0;
        }
    }

    /*
    method for things needed to be executed at the end of combat specific to the pilot
     */
    public void endOfCombatPrep(Threat threat) {
        super.endOfCombatPrep(threat);
        setDroneDeployed(false);
        droneAttack = 5;
    }

    /*
    method for taking damage with the pilots custom damage resistance formula
     */
    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
