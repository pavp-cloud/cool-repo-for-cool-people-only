package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MissionRoom {
    private Mission activeMission = null;
    private ArrayList<Mission> pastMission = new ArrayList<>();
    private ArrayList<String> threatNames = new ArrayList<>(Arrays.asList(
            "Bob", "Karen", "Terry", "Becky", "RxR 808", "Anvaron the Exhalted",
            "Fungus", "Worm", "Matthew Money Bags", "Pavel Pavlov's Dog", "Heikki got a Heinikeen",
            "Vargmoth", "Kar the Blighted Sword", "Lil Reggie", "Monotone Noise",
            "Hans", "Only says, I'll be your end"
    ));


    public Threat scanForThreats(int selection){
        Collections.shuffle(threatNames);
        String name = threatNames.get(0);

        switch (selection) {
            case 1:
                Pirate pirate = new Pirate(100, 100, name , 3 + SpaceShip.getInstance().getDaysOnBoard());
                return pirate;
            case 2:
                Parasite parasite = new Parasite(100, 100, name , 3 + SpaceShip.getInstance().getDaysOnBoard());
                return parasite;
            case 3:
                Gundam gundam = new Gundam(100, 100, name , 3 + SpaceShip.getInstance().getDaysOnBoard());
                return gundam;
            case 4:
                Alien alien = new Alien(100, 100, name , 3 + SpaceShip.getInstance().getDaysOnBoard());
                return alien;
            case 5:
                Demon demon = new Demon(100, 100, name , 3 + SpaceShip.getInstance().getDaysOnBoard());
                return demon;
            default:
                return null;
        }
    }

    public Mission createMission(Threat threat){
         Mission mission = new Mission(threat);
         activeMission = mission;
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

    public Mission getActiveMission(){
        return activeMission;
    }

    public void updateMissionStatus(){
        pastMission.add(activeMission);
        activeMission = null;
    }
}
