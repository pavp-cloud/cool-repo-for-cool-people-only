package com.example.project;


import static java.lang.Math.round;

public class Medic extends Character {
    private int missionCompleted;
    //representive of final blows, can be added in a different way to uml
    private int finalBlows;

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

    public void endOfCombatPrep() {
        //empty
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.5);
        this.currentHealth = currentHealth - damageTaken;
    }
}
