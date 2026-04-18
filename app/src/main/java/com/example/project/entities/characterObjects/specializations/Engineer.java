package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.BasicAttacker;
import com.example.project.entities.entityInterfaces.BasicSpecial;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.threatObjects.Threat;

public class Engineer extends Character {
    private boolean combatArmorEquipped = false;

    //CONSTANTS
    private final int baseAttack = 5;
    private final double attackScaling = 0.6;
    private final double damageVulnerability = 0.7;
    private final double damageVulnerabilityArmored = 0.4;

    /*
    constructor for the Engineer class
     */
    public Engineer(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    /*
    attack method for the engineer using its damage modifiers
     */
    public int attack() {
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    /*
    special method for the engineer to don the combat armor to gain extra
    damage resistance in combat. if the armor is already equipped it does nothing.
     */
    public int special() {
        if(!getCombatArmor()) {
            setCombatArmor(true);
            return 0;
        } else {
            return 0;
        }
    }

    /*
    this methods resets the flag for if the combat armor is equipped along with the rest of the
    end of combat prep methods
     */
    public void endOfCombatPrep(Threat threat) {
        super.endOfCombatPrep(threat);
        setCombatArmor(false);
    }

    /*
    getter for the combat armor flag
     */
    public boolean getCombatArmor() {
        return this.combatArmorEquipped;
    }

    /*
    setter for the combat armor flag
     */
    public void setCombatArmor(boolean state) {
        this.combatArmorEquipped = state;
    }

    /*
    method for taking damage with the engineers custom damage resistance formula decided
    by if the combat armor is on or not
     */
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
