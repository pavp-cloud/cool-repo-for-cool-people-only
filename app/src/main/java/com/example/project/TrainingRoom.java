package com.example.project;

import java.util.ArrayList;

public class TrainingRoom {
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

    public int trainCrewMember(int selection) {

        switch (selection) {

            case 1:
                // level 1 training cannot fail
                trainee.gainExp(5);
                return 0;


            case 2:
                if (Math.random() < 0.6) {
                    trainee.gainExp(10);
                    return 0;
                } else {
                    // training fails
                    return 1;
                }

            case 3:
                if (Math.random() < 0.3) {
                    trainee.gainExp(15);
                    return 0;
                } else {
                    // training fails
                    return 1;
                }

            default:
                // training fails for whatever reason(out of bounds int, no crew memeber as trainee, ect...)
                return 1;
        }
    }
}
