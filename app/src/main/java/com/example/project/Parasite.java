package com.example.project;

public class Parasite extends Threat {
    private int maxHealth;
    private int currentHealth;
    private String name;
    private int exp;

    public Parasite(int maxHealth, int currentHealth, String name, int exp){
        super(maxHealth, currentHealth, name, exp);

    }

    @Override
    public int attack(){
    }

    @Override
    public int special() {
    }
}
