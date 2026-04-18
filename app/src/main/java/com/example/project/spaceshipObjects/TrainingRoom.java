package com.example.project.spaceshipObjects;

import com.example.project.entities.characterObjects.Character;

public class TrainingRoom {
    private int dailyUsages = 3;
    private Character trainee = null;

    public void addTrainee(Character trainee) {
        this.trainee = trainee;
    }

    public Character removeTrainee() {
        Character temp = trainee;
        trainee = null;
        return temp;
    }

    public Character getTrainees() {
        return trainee;
    }

    public int getDailyUsages() {
        return dailyUsages;
    }
    public void resetDailyUsages() {
        dailyUsages = 3;
    }
    public void decrementDailyUsages() {
        dailyUsages--;
    }

    /*
    this method contains the logic for how much exp a crew member will gain
    after a successful training session or if the training succeeded in the first place
     */
    public int trainCrewMember(int selection) {
        if (dailyUsages > 0) {
            decrementDailyUsages();

            switch (selection) {

                case 1:
                    // level 1 training cannot fail
                    trainee.gainExp(8);
                    trainee.increaseMaxHealth(8);
                    return 0;


                case 2:
                    if (Math.random() < 0.7) {
                        trainee.gainExp(14);
                        trainee.increaseMaxHealth(14);
                        return 0;
                    } else {
                        trainee.gainExp(5);
                        trainee.increaseMaxHealth(5);
                        // training fails
                        return 1;
                    }

                case 3:
                    if (Math.random() < 0.4) {
                        trainee.gainExp(17);
                        trainee.increaseMaxHealth(17);
                        return 0;
                    } else {
                        trainee.gainExp(4);
                        trainee.increaseMaxHealth(4);
                        // training fails
                        return 1;
                    }

                default:
                    // if training fails for whatever reason
                    return 1;
            }
        } else {
            return 1;
        }
    }
}
