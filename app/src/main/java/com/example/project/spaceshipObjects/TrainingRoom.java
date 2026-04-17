package com.example.project.spaceshipObjects;

import com.example.project.entities.characterObjects.Character;

public class TrainingRoom {
    private int dailyUsages = 3;
    private com.example.project.entities.characterObjects.Character trainee = null;

    public void addTrainee(com.example.project.entities.characterObjects.Character trainee) {
        this.trainee = trainee;
    }

    public com.example.project.entities.characterObjects.Character removeTrainee() {
        com.example.project.entities.characterObjects.Character temp = trainee;
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

    public int trainCrewMember(int selection) {
        if (dailyUsages > 0) {
            decrementDailyUsages();

            switch (selection) {

                case 1:
                    // level 1 training cannot fail
                    trainee.gainExp(5);
                    trainee.increaseMaxHealth(5);
                    return 0;


                case 2:
                    if (Math.random() < 0.6) {
                        trainee.gainExp(10);
                        trainee.increaseMaxHealth(10);
                        return 0;
                    } else {
                        // training fails
                        return 1;
                    }

                case 3:
                    if (Math.random() < 0.3) {
                        trainee.gainExp(15);
                        trainee.increaseMaxHealth(15);
                        return 0;
                    } else {
                        // training fails
                        return 1;
                    }

                default:
                    // training fails for whatever reason(out of bounds int, no crew memeber as trainee, ect...)
                    return 1;
            }
        } else {
            return 1;
        }
    }
}
