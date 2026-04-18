package com.example.project.entities.entityInterfaces;

public interface CombatActor {
    int getMaxHealth();
    int getCurrentHealth();
    String getName();
    int getExp();
    void healHealth(int heal);
    void takeDamage(int damage);
}
