package com.example.project;
import java.util.ArrayList;
public class MissionRoom {
    private ArrayList<Mission> activeMission;
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
    // hi

    public Mission createMission(Threat threat){
         Mission mission = new Mission(threat);
         activeMission.add(mission);
         return mission;
    }
    public void selectCrewMembers(Mission mission){
        ArrayList<Character> activeCharacters = SpaceShip.getInstance().getCrewQuarters().getCrewMembers();

        // prelim dummy to test app
        Character Character1 = activeCharacters.get(0);
        Character Character2 = activeCharacters.get(1);// choose crew members for mission from crew quarters
        // then add them to the mission
        mission.addCrewMembers(Character1, Character2);
    }

    public void runMission (Mission mission){
        mission.executeMission();
    }

    public void updateMissionStatus(){
        pastMission.addAll(activeMission);
        activeMission.clear();
    }
}
