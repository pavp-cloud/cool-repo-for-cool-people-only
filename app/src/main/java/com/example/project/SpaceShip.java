package com.example.project;

public class SpaceShip {
    private static SpaceShip instance;
    private MissionRoom missionRoom;
    private CrewQuarters quarters;
    private PassengerManifest manifest;
    private TrainingRoom trainingRoom;
    private static int daysOnBoard = 1;
    private static int shipHealth = 100;


    private SpaceShip() {
        missionRoom = new MissionRoom();
        quarters = new CrewQuarters();
        manifest = new PassengerManifest();
        trainingRoom = new TrainingRoom();
    }
    public static SpaceShip getInstance() {
        if(instance == null) {
            instance = new SpaceShip();
        }
        return instance;
    }
    public void runTrainingRoom() {

    }

    public void runMissionRoom() {

    }

    public void checkManifest() {

    }

    public void checkCrewQuarters() {

    }

    public Character onboardCrewMember() {
        int selection = 0; // each recruitment choice has a different number that calls the specific constructor

        String crewMemeberName = "Bob";
        switch (selection) {

            case 1:
                Medic medic = new Medic(100, 100, crewMemeberName, 0 + getDaysOnBoard();
                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            case 5:

                break;
        }
    }

    public int getDaysOnBoard() {
        return daysOnBoard;
    }
    public void incrementDaysOnBoard() {
        daysOnBoard++;
    }
}
