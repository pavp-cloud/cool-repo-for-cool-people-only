package com.example.project;

import java.util.Random;
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
    public int getShipHealth() {
        return shipHealth;
    }

    public void incrementShipHealth(int healthIncrease) {
        shipHealth += healthIncrease;
    }

    public void damageShip() {
        Random random = new Random();
        int damage = random.nextInt(6);
        shipHealth -= daysOnBoard * damage;
    }
}
