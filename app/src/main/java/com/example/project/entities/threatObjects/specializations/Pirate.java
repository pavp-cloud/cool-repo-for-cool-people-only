package com.example.project.entities.threatObjects.specializations;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;

import java.util.Random;

public class Pirate extends Threat {

    //CONSTANTS

    private final int baseAttack = 2;
    private final int baseExpSteal = 5;

    public Pirate(int maxHealth, int currentHealth, String name, int exp) {
        super(maxHealth, currentHealth, name, exp);
    }

    @Override
    public int special(Character character1, Character character2) {
        /*how i envision it is it'll steal exp from the character with higher exp
        with a safeguard in place in a case where stolen exp would be higher than
        the character's current exp
         */
        /*how i would do it is just make a copy of the character with higher exp,
        make changes to it and then it'll reflect onto that character
        i dunno how to actually implement that or if thats even good but there's a way to copy an object
        with all of its references*/
        /*if we don't care enough we could just randomize targeting normally and avoid this entirely
        in which case this should function:
        */
        int stolenExp = baseExpSteal + (exp/10);
        Random random = new Random();
        int target = random.nextInt(2);

        if (target == 0) {
            if (character1.getExp() < baseExpSteal) {
                stolenExp = character1.getExp();
            }
            character1.gainExp(-stolenExp);
            setExp(getExp()+stolenExp);
        }
        else {
            if (character2.getExp() < baseExpSteal) {
                stolenExp = character2.getExp();
            }
            character2.gainExp(-stolenExp);
            setExp(getExp()+stolenExp);
        }
        return 0;
        //but it'd be cool if we could figure out selective targeting, i'm blanking on it it's probably easier than i think
    }
    @Override
    public int attack(Character character1, Character character2) {
        //basic base attack logic, can be copied everywhere but with different scaling
        Random random = new Random();
        int target = random.nextInt(2); //returns int from 0 to 1 (higher bound is exclusive, so a bound of 2 gives a 1)
        int damage = (int)(baseAttack + (this.getExp() * 0.5));

        if (target == 0) {
            character1.takeDamage(damage);
        }
        else {
            character2.takeDamage(damage);
        }
        return 0; //also returning ints feels like a remnant, we're already interacting with character HP through a takeDamage method within attack method
    }

    public void setExp(int value) {
        this.exp = value;
    }

    @Override
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
    }
}

