package com.example.project;


public class Gundam extends Threat{
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;
    private boolean missileUsed = false;


    public Gundam(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    public int attack() {

    }

    @Override
    public int special() {
        // shoots a missile for big damage
    }

    public void reloadMissile() {

    }

    public void setMissileUsed(boolean state) {

    }

    @Override
    public void takeDamage(int damage) {

    }
}
