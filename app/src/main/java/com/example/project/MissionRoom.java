package com.example.project;
import java.util.ArrayList;
public class MissionRoom {
    private ArrayList<Mission> activeMisson;
    private ArrayList<Mission> pastMission;


    public Threat scanForThreats(){
        int selection = 0;

        switch (selection) {
            case 1:
                Pirate pirate = new Pirate(100, 100, "Bob", 3 + SpaceShip.getInstance().getDaysOnBoard());
                return pirate;
            case 2:
                Parasite parasite = new Parasite(100, 100, "Clyde", 3 + SpaceShip.getInstance().getDaysOnBoard());
                return parasite;
            case 3:
                Gundam gundam = new Gundam(100, 100, "Steve", 3 + SpaceShip.getInstance().getDaysOnBoard());
                return gundam;
            case 4:
                Alien alien = new Alien(100, 100, "Ron", 3 + SpaceShip.getInstance().getDaysOnBoard());
                return alien;
            case 5:
                Demon demon = new Demon(100, 100, "Asmodeus", 3 + SpaceShip.getInstance().getDaysOnBoard());
                return demon;
            default:
                return null;
        }
    }

    public Mission createMission(Threat threat, Character character1, Character character2){

    }
    public void selectCrewMembers(Mission mission){

    }

    public void runMission (Mission mission){

    }

    public void updateMissionStatus(){

    }
}
