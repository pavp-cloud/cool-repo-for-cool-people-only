package com.example.project;

import static java.lang.Math.round;

public class Engineer extends Character {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private int missionCompleted;
    //representive of final blows, can be added in a different way to uml
    private int finalBlows;
    private boolean combatArmorEquipped = false;

    public Engineer(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }


    public int attack(){
        return (int) (2 + (this.getExp() * 0.5));

    }


    public int special(){
        this.combatArmorEquipped = true;
        return 0;

    }
    //equips combat armor

    public void endOfCombatPrep() {

    }

    public boolean getCombatArmor() {

    }

    public void setCombatArmor(boolean state) {

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
