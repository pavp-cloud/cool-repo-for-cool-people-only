package com.example.project;

public class SpaceShip {
    private static SpaceShip instance;
    private MissionRoom missionRoom;
    private CrewQuarters crewQuarters;
    private PassengerManifest manifest;
    private TrainingRoom trainingRoom;
    private BackgroundCheck securityCheck;
    private static int daysOnBoard = 1;
    private static int shipHealth = 100;


    private SpaceShip() {
        missionRoom = new MissionRoom();
        crewQuarters = new CrewQuarters();
        manifest = new PassengerManifest();
        trainingRoom = new TrainingRoom();
        securityCheck = new BackgroundCheck();
    }
    public static SpaceShip getInstance() {
        if(instance == null) {
            instance = new SpaceShip();
        }
        return instance;
    }

    public CrewQuarters getCrewQuarters() {
        return crewQuarters;
    }

    public PassengerManifest getManifest() {
        return manifest;
    }

    public MissionRoom getMissionRoom() {
        return missionRoom;
    }
    public TrainingRoom getTrainingRoom() {
        return trainingRoom;
    }

    /*
    public void runTrainingRoom() {
        // moves to the training room view
        // todo: implement into UI
    }

    public void runMissionRoom() {
        // moves to the mission room view
        // todo: implement into UI
    }

    public void checkManifest() {
        // moves to the manifest view
        // todo: implement into UI
    }

    public void checkCrewQuarters() {
        // moves to the crew quarter view
        // todo: implement into UI
    }
    */

    public void onboardCrewMember(int selection, String crewMemberName) {
        Character character = securityCheck.newCrewMember(selection, crewMemberName, getDaysOnBoard());
        if(character != null) {
            crewQuarters.addCrewMember(character);
            manifest.addPassenger(character);
        }
    }

    public int getDaysOnBoard() {
        return daysOnBoard;
    }
    public void incrementDaysOnBoard() {
        daysOnBoard++;
    }

    public void incrementShipHealth(int healthIncrease) {
        shipHealth += healthIncrease;
    }

    public void damageShip() {
        shipHealth -= daysOnBoard;
    }
}
