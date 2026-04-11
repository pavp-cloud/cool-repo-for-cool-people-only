package com.example.project;

import static java.lang.Math.round;

public class Pilot extends Character {
    private int missionCompleted;
    //representive of final blows, can be added in a different way to uml
    private int finalBlows;
    private boolean droneDeployed = false;

    //CONSTANTS

    private final int baseAttack = 2;
    private final int droneAttack = 5;
    /*not too sure about the "etiquette" of using final,
    i think one of the presentations said "don't use them too much" but
    that's how you would define a constant*/

    public Pilot(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);
    }

    public boolean getDroneDeployed() {
        return this.droneDeployed;
    }

    public void setDroneDeployed(boolean state) {
        this.droneDeployed = state;
    }

    public int attack(){
        if (getDroneDeployed()) {
            return (int) (baseAttack + droneAttack + (this.getExp() * 0.5));
        } else {
            return (int) (baseAttack + (this.getExp() * 0.5));
        }
    }

    public int special(){
        setDroneDeployed(true);
        return 0;
    }
    //deploys drone

    public void endOfCombatPrep() {
        setDroneDeployed(false);
    }

    public void takeDamage(int attackIntensity) {
        int damageTaken = (int) round(attackIntensity * 1.0);
        this.currentHealth = currentHealth - damageTaken;
    }
}
