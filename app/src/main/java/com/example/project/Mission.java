package com.example.project;

import java.util.Random;

public class Mission {
    private Character crewMember1 = null;
    private Character crewMember2 = null;
    private Threat missionTarget;

    public Mission(Threat missionTarget) {
        this.missionTarget = missionTarget;
    }
    public Character getCrewMember1(){return crewMember1;}
    public Character getCrewMember2(){return crewMember2;}
    public Threat getMissionTarget(){return missionTarget;}

    public void addCrewMembers (Character crewMember1, Character crewMember2) {
        this.crewMember1 = crewMember1;
        this.crewMember2 = crewMember2;
    }

    public int enemyTurn() {
        Random random = new Random();
        int action = random.nextInt(2);
        if (action == 0) {
            missionTarget.attack(crewMember1, crewMember2);
        } else {
            missionTarget.special(crewMember1, crewMember2);
        }
        return action;
    }

    public void executeMission() {
        // This could be where the combat loop starts or UI is triggered
    }

}
