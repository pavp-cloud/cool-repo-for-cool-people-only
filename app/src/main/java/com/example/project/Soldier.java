package com.example.project;

import static java.lang.Math.round;

public class Soldier extends Character {
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
    //todo: if we're going for the "reusing a one-time use action reloads it" thing we're gonna have to communicate that on the ui just fyi
    public int special(){
        if (usedGrenade) {
            setUsedGrenade(false);
            return 0;
        } else {
            setUsedGrenade(true);
            return (int) (grenadeAttack + (this.getExp() * 0.5));
        }
    }

    public void endOfCombatPrep(Threat threat) {
        gainExp(threat.getExp());
        missionsCompleted++;
        increaseMaxHealth(threat.getExp());
        setUsedGrenade(false);
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.0);
        this.currentHealth = currentHealth - damageTaken;
    }
}
