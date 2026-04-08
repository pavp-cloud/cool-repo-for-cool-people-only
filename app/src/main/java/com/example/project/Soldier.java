package com.example.project;

import static java.lang.Math.round;

public class Soldier extends Character {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int missionCompleted;
    //representive of final blows, can be added in a different way to uml
    private int finalBlows;
    private boolean usedGrenade = false;

    //CONSTANTS
    private final int baseAttack = 2;
    private final int grenadeAttack = 10;

    public Soldier(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public boolean getUsedGrenade() {
        return this.usedGrenade;
    }

    public void setUsedGrenade(boolean state) {
        this.usedGrenade = state;
    }

    public int attack(){
        return (int) (baseAttack + (this.getExp() * 0.5));
    }

    //"throws" the grenade
    //its like attack, but with a higher base value
    //could also be higher scaling but thats details
    //todo: check if its equipped, if it is, display so on the ui, have it not waste the turn
    public int special(){
        setUsedGrenade(true);
        return (int) (grenadeAttack + (this.getExp() * 0.5));
    }

    public void endOfCombatPrep() {
        setUsedGrenade(false);
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.0);
        this.currentHealth = currentHealth - damageTaken;
    }
}
