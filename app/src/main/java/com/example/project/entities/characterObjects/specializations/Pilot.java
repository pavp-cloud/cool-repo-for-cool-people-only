package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatCharacterSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Pilot extends Character implements CombatActor, CombatCharacterSpecial {
    private boolean droneDeployed = false;

    //CONSTANTS

    private final int baseAttack = 4;
    private int droneAttack = 5;
    private final double attackScaling = 0.6;
    private final double damageVulnerability = 1.0;
    /*not too sure about the "etiquette" of using final,
    i think one of the presentations said "don't use them too much" but
    that's how you would define a constant*/

    public Pilot(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public boolean getDroneDeployed() {
        return this.droneDeployed;
    }

    public void setDroneDeployed(boolean state) {
        this.droneDeployed = state;
    }

    public int attack(){
        if (getDroneDeployed()) {
            return (int) (baseAttack + droneAttack + (int) (this.getExp() * 0.2) + (this.getExp() * attackScaling));
        } else {
            return (int) (baseAttack + (this.getExp() * attackScaling));
        }
    }

    public int special(){
        if(!getDroneDeployed()) {
            setDroneDeployed(true);
            return 0;
        } else {
            droneAttack += 3;
            return 0;
        }
    }
    //deploys drone

    public void endOfCombatPrep(Threat threat) {
        gainExp(threat.getExp());
        missionsCompleted++;
        increaseMaxHealth(threat.getExp());
        setDroneDeployed(false);
        droneAttack = 5;
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * damageVulnerability);
        this.currentHealth = currentHealth - damageTaken;
    }
}
