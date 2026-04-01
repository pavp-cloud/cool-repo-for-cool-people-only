package com.example.project;

public class Gundam extends Threat{
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private boolean missileUsed;


    public Gundam(int maxHealth, int currentHealth, String name, int exp, boolean missileUsed) {
        super(maxHealth, currentHealth, name, exp);
        this.missileUsed = missileUsed;;
    }

    public int attack() {

    }

    public int special() {
        // shoots a missile for big damage
    }

    public void reloadMissile() {

    }

    public void setMissileUsed(boolean state) {

    }
}
