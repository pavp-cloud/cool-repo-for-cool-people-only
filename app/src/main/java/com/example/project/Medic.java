package com.example.project;


import static java.lang.Math.round;

public class Medic extends Character {

    //CONSTANTS
    private final int baseAttack = 2;
    private final int healingPower = 10;

    public Medic(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }


    public int attack(){
        return (int) (baseAttack + (this.getExp() * 0.5));
    }

    //Medic will be able to heal other characters
    public int special(){
        return healingPower;
    }

    public void endOfCombatPrep(Threat threat) {
        exp += threat.getExp();
        missionsCompleted++;
        maxHealth += (int) (threat.getExp() * 1.5);
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.5);
        this.currentHealth = currentHealth - damageTaken;
    }
}
