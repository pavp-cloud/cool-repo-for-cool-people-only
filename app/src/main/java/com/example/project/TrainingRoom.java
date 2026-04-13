package com.example.project;

import java.util.ArrayList;

public class TrainingRoom {
    private Character trainee = null;

    public void addTrainee(Character trainee) {
        this.trainee = trainee;
    }

    public Character getTrainees() {
        return trainee;
    }

    public void trainCrewMember(Character trainee) {
        int selection = 0; // selection for 1 of 3 difficulties to train on

        switch (selection) {

            case 1:
                // level 1 training cannot fail
                trainee.gainExp(5);
                break;

            case 2:
                if (Math.random() < 0.6) {
                    trainee.gainExp(10);
                } else {
                    // training fails
                    return;
                }
                break;

            case 3:
                if (Math.random() < 0.3) {
                    trainee.gainExp(15);
                } else {
                    // training fails
                    return;
                }
                break;

        }
    }
}
