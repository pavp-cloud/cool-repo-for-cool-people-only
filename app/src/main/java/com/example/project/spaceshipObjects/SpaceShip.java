package com.example.project.spaceshipObjects;

import com.example.project.EntityGenerationClasses.BackgroundCheck;
import com.example.project.entities.characterObjects.Character;

import java.util.Random;
public class SpaceShip {
    private static SpaceShip instance;
    private MissionRoom missionRoom;
    private CrewQuarters crewQuarters;
    private PassengerManifest manifest;
    private TrainingRoom trainingRoom;
    private BackgroundCheck securityCheck;
    private static int daysOnBoard = 0;
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

    /*
    this method resets the game state to the initial state at launch.
    this includes all attributes like ship health and emptying all crew members
    from the manifest and active crew quarters.
     */
    public void resetGame() {
        shipHealth = 100;
        daysOnBoard = 0;

        // clears out all crewmembers
        while (!crewQuarters.getCrewMembers().isEmpty()) {
            crewQuarters.removeCrewMember(crewQuarters.getCrewMembers().get(0));
        }

        // re-initializes the modules to ensure a fresh game state
        missionRoom = new MissionRoom();
        manifest = new PassengerManifest();
        trainingRoom = new TrainingRoom();
        securityCheck = new BackgroundCheck();
    }

    /*
    getter for the crew quarters
     */
    public CrewQuarters getCrewQuarters() {
        return crewQuarters;
    }
    /*
    getter for the manifest
     */
    public PassengerManifest getManifest() {
        return manifest;
    }
    /*
    getter for the mission room
     */
    public MissionRoom getMissionRoom() {
        return missionRoom;
    }
    /*
    getter for the training room
     */
    public TrainingRoom getTrainingRoom() {
        return trainingRoom;
    }

    /*
    this method onboards a crew member and adds it to the manifest and crew quarters.
    it first calls for a background check on the crew member to create them with the
    correct attributes.
     */
    public void onboardCrewMember(int selection, String crewMemberName) {
        Character character = securityCheck.newCrewMember(selection, crewMemberName, getDaysOnBoard());
        if(character != null) {
            crewQuarters.addCrewMember(character);
            manifest.addPassenger(character);
        }
    }

    /*
    getter for the days on board the ship/days elapsed playing
     */
    public int getDaysOnBoard() {
        return daysOnBoard;
    }

    /*
    increments the days on board by 1
     */
    public void incrementDaysOnBoard() {
        daysOnBoard++;
    }

    /*
    getter for the ship health
     */
    public int getShipHealth() {
        return shipHealth;
    }

    /*
    this method is used to calculate damage to the ship in case of mission failure
     */
    public void damageShip() {
        Random random = new Random();
        int damage = random.nextInt(11);
        shipHealth -= daysOnBoard * damage;
    }
}
