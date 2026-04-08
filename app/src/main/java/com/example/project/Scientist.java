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
    private boolean usedExpPotion = false;

    //CONSTANTS
    private final int baseAttack = 2;
    private final int expPotionValue = 5;

    public Scientist(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public boolean getExpPotion() {
        return this.usedExpPotion;
    }

    public void setExpPotion(boolean state) {
        this.usedExpPotion = state;
    }

    public int attack(){
        return (int) (baseAttack + (this.getExp() * 0.5));
    }

    //exp potion
    //todo: check if its used, if it is, display so on the ui, have it not waste the turn
    public int special(){
        setExpPotion(true);
        return expPotionValue;
    }

    public void endOfCombatPrep() {
        setExpPotion(false);
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.2);
        this.currentHealth = currentHealth - damageTaken;
    }
}
