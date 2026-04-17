package com.example.project.entities.characterObjects.specializations;


import static java.lang.Math.round;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.CombatCharacterSpecial;
import com.example.project.entities.threatObjects.Threat;

public class Medic extends Character implements CombatActor, CombatCharacterSpecial {

    //CONSTANTS
    private final int baseAttack = 2;
    private final int healingPower = 10;
    private final double attackScaling = 0.5;
    private final double specialScaling = 0.2;
    private final double damageVulnerability = 1.5;

    public Medic(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }


    public int attack(){
        return (int) (baseAttack + (this.getExp() * attackScaling));
    }

    //Medic will be able to heal itself
    public int special(){
        healHealth((int) (healingPower + (this.getExp() * specialScaling)));
        return 0;
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
