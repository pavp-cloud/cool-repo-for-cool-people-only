package com.example.project;

public class Alien extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;


    public Alien(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);

    }

    @Override
    public int attack() {
    }

    @Override
    public int special() {
        //shoots laser that does AOE
    }

    public int shootLaserBeam() {

    }
    @Override
    public void takeDamage(int damage) {

    }
}
