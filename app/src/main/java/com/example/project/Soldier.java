package com.example.project;

public class Soldier extends Character {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int missionCompleted;
    //representive of final blows, can be added in a different way to uml
    private int finalBlows;
    private boolean usedGrenade;

    public Soldier(int maxHealth, int currentHealth, String name, int exp, boolean usedGrenade){
        super(maxHealth, currentHealth, name, exp);
        this.usedGrenade = usedGrenade;
    }

    public boolean getUsedGrenade() {

    }

    public void setUsedGrenade(boolean state) {

    }
    public int attack(){

    }
    public int special(){

    }
    //grenade

    public void endOfCombatPrep() {

    }

    public void takeDamage(int attackIntensity) {

    }
}
