package com.example.project;


import static java.lang.Math.round;

public class Medic extends Character {

    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int missionCompleted;
    //representive of final blows, can be added in a different way to uml
    private int finalBlows;

    public Medic(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);

    }


    public int attack(){

    }


    public int special(){

    }
    //Medic will be able to heal other characters
    public void endOfCombatPrep() {

    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.5);
        this.currentHealth = currentHealth - damageTaken;
    }
}
