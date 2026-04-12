package com.example.project;

import java.util.ArrayList;

public class MissionRoom {
    private ArrayList<Mission> activeMission = new ArrayList<>();
    private ArrayList<Mission> pastMission = new ArrayList<>();


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

    public Mission createMission(Threat threat){
         Mission mission = new Mission(threat);
         activeMission.add(mission);
         return mission;
    }
    
    public void selectCrewMembers(Mission mission){
        ArrayList<Character> activeCharacters = SpaceShip.getInstance().getCrewQuarters().getCrewMembers();

        if (activeCharacters.size() >= 2) {
            Character character1 = activeCharacters.get(0);
            Character character2 = activeCharacters.get(1);
            mission.addCrewMembers(character1, character2);
        }
    }

    public void runMission (Mission mission){
        mission.executeMission();
    }

    public void updateMissionStatus(){
        pastMission.addAll(activeMission);
        activeMission.clear();
    }
}
