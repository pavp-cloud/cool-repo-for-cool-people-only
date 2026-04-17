package com.example.project.entities.characterObjects.specializations;

import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatCharacterSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Engineer extends Character implements CombatActor, CombatCharacterSpecial {
    private boolean combatArmorEquipped = false;

    //CONSTANTS
    private final int baseAttack = 2;

    public Engineer(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public int attack() {
        return (int) (baseAttack + (this.getExp() * 0.5));
    }

    //todo: check if its equipped, if it is, display so on the ui, have it not waste the turn
    //equips combat armor
    public int special() {
        setCombatArmor(true);
        return 0;
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

    /*wondering if we even need a setter or a getter if we only interact with
    the boolean from inside the class;
    I mean methods instead of direct access might be more appreciated but this just
    feels like clutter honestly*/
    public void setCombatArmor(boolean state) {
        this.combatArmorEquipped = state;
    }

    public void takeDamage(int attackIntensity) {
        if(combatArmorEquipped) {
            int damageTaken = (int) round(attackIntensity * 0.5);
            this.currentHealth = currentHealth - damageTaken;
        } else {
            int damageTaken = (int) round(attackIntensity * 0.8);
            this.currentHealth = currentHealth - damageTaken;
        }
    }
}
