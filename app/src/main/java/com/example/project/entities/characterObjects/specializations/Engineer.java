package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatCharacterSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Engineer extends Character implements CombatActor, CombatCharacterSpecial {
    private boolean combatArmorEquipped = false;

    //CONSTANTS
    private final int baseAttack = 5;
    private final double attackScaling = 0.6;
    private final double damageVulnerability = 0.7;
    private final double damageVulnerabilityArmored = 0.4;

    public Engineer(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public int attack() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }


    public int special() {
        if(!getCombatArmor()) {
            setCombatArmor(true);
            return 0;
        } else {
            return 0;
        }
    }

    //flag reset
    public void endOfCombatPrep(Threat threat) {
        gainExp(threat.getExp());
        missionsCompleted++;
        increaseMaxHealth(threat.getExp());
        setCombatArmor(false);
    }

    public boolean getCombatArmor() {
        return this.combatArmorEquipped;
    }

    public void setCombatArmor(boolean state) {
        this.combatArmorEquipped = state;
    }

    public void takeDamage(int attackIntensity) {
        if(combatArmorEquipped) {
            int damageTaken = (int) round(attackIntensity * damageVulnerabilityArmored);
            this.currentHealth = currentHealth - damageTaken;
        } else {
            int damageTaken = (int) round(attackIntensity * damageVulnerability);
            this.currentHealth = currentHealth - damageTaken;
        }
    }
}
