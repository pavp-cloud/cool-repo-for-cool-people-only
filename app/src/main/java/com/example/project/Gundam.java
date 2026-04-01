package com.example.project;

public class Gundam extends Threat{
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;

    public Gundam(int maxHealth, int currentHealth, String name, int exp, boolean missileUsed) {
        super(maxHealth, currentHealth, name, exp);
        this.missileUsed = missileUsed;;
    }


    public
}
