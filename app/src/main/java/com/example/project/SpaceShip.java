package com.example.project;

public class SpaceShip {
    private static SpaceShip instance;
    private MissionRoom missionRoom;
    private CrewQuarters crewQuarters;
    private PassengerManifest manifest;
    private TrainingRoom trainingRoom;
    private static int daysOnBoard = 1;
    private static int shipHealth = 100;


    private SpaceShip() {
        missionRoom = new MissionRoom();
        crewQuarters = new CrewQuarters();
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

    public void onboardCrewMember() {
        int selection = 0; // each recruitment choice has a different number that calls the specific constructor

        String crewMemberName = "Bob";
        switch (selection) {
            // crew member stats are subject to changes and addition of scaling
            case 1:
                Medic medic = new Medic(100, 100, crewMemberName, getDaysOnBoard());
                crewQuarters.addCrewMember(medic);
                manifest.addPassenger(medic);
                break;

            case 2:
                Soldier soldier = new Soldier(100, 100, crewMemberName, getDaysOnBoard());
                crewQuarters.addCrewMember(soldier);
                manifest.addPassenger(soldier);
                break;

            case 3:
                Scientist scientist = new Scientist(100, 100, crewMemberName, getDaysOnBoard());
                crewQuarters.addCrewMember(scientist);
                manifest.addPassenger(scientist);
                break;

            case 4:
                Pilot pilot = new Pilot(100, 100, crewMemberName, getDaysOnBoard());
                crewQuarters.addCrewMember(pilot);
                manifest.addPassenger(pilot);
                break;

            case 5:
                Engineer engineer = new Engineer(100, 100, crewMemberName, getDaysOnBoard());
                crewQuarters.addCrewMember(engineer);
                manifest.addPassenger(engineer);
                break;
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
