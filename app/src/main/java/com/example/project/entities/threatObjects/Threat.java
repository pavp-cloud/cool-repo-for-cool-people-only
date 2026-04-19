package com.example.project.entities.threatObjects;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.entityInterfaces.CombatActor;
import com.example.project.entities.entityInterfaces.TargetedAttacker;
import com.example.project.entities.entityInterfaces.TargetedSpecial;

import java.util.Random;

public abstract class Threat implements CombatActor, TargetedAttacker, TargetedSpecial {
    protected int maxHealth;
    protected int currentHealth;
    protected String name;
    protected int exp;

    //Random for targeting
    protected Random random = new Random();

    public Threat(int maxHealth, int currentHealth, String name, int exp) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.name = name;
        this.exp = exp;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void adjustCurrentHealth(int healthChange) {
        this.currentHealth += healthChange;
    }

    public int getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }

    // Every threat will have a universal basic attack with their own scaling
    public int attack(Character character1, Character character2) {
        int damage = calculateDamage();
        Character target = pickTarget(character1, character2);
        if (target != null) {
            target.takeDamage(damage);
        }
        return 0;
    }

    public Character pickTarget(Character character1, Character character2) {
        if (character1 != null && character2 != null) {
            int target = random.nextInt(2);
            if (target == 0) {
                return character1;
            } else {
                return character2;
            }
        } else if (character1 != null) {
            return character1;
        } else if (character2 != null) {
            return character2;
        }
        return null;
    }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }

    // Every threat will have its own special method that will take both characters as input
    public abstract int special(Character character1, Character character2);

    /* Every threat will implement their own damage calculations and feed them into
    the universal attack method
     */
    public abstract int calculateDamage();

    public void healHealth(int heal) {
        adjustCurrentHealth(heal);
    }

}
