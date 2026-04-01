package com.example.project;

public class Demon extends Threat{

    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;

    public Demon(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);

    }
    @Override
    public int attack() {
    }

    @Override
    public int special() {
        //Shoots laser for AOE
        int shootLaser;
    }
}
