package com.example.project;

import static java.lang.Math.round;

public class Scientist extends Character {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int missionCompleted;
    //representive of final blows, can be added in a different way to uml
    private int finalBlows;
    private boolean usedExpPotion;

    public Scientist(int maxHealth, int currentHealth, String name, int exp, boolean usedExpPotion){
        super(maxHealth, currentHealth, name, exp);
        this.usedExpPotion = usedExpPotion;
    }

    public boolean getExpPotion() {

    }

    public void setExpPotion(boolean state) {

    }

    public int attack(){

    }
    public int special(){

    }
    //exp potion

    public void endOfCombatPrep() {

    }
    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.2);
        this.currentHealth = currentHealth - damageTaken;
    }
}
