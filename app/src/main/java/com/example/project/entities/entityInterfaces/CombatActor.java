package com.example.project.entities.entityInterfaces;

public interface CombatActor {
    int getMaxHealth();
    int getCurrentHealth();
    String getName();
    int getExp();
    void takeDamage(int damage);
}
