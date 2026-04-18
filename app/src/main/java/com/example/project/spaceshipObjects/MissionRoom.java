package com.example.project.spaceshipObjects;

import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;
import com.example.project.entities.threatObjects.specializations.Alien;
import com.example.project.entities.threatObjects.specializations.Demon;
import com.example.project.entities.threatObjects.specializations.Gundam;
import com.example.project.entities.threatObjects.specializations.Parasite;
import com.example.project.entities.threatObjects.specializations.Pirate;
import com.example.project.mission.Mission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
                int startingHealthPirate = 60 + (6 * SpaceShip.getInstance().getDaysOnBoard());
                Pirate pirate = new Pirate(startingHealthPirate, startingHealthPirate, name , 4 + (2 * SpaceShip.getInstance().getDaysOnBoard()));
                return pirate;
            case 2:
                int startingHealthParasite = 70 + (3 * SpaceShip.getInstance().getDaysOnBoard());
                Parasite parasite = new Parasite(startingHealthParasite, startingHealthParasite, name , 6 + (5 * SpaceShip.getInstance().getDaysOnBoard()));
                return parasite;
            case 3:
                int startingHealthGundam = 100 + (10 * SpaceShip.getInstance().getDaysOnBoard());
                Gundam gundam = new Gundam(startingHealthGundam, startingHealthGundam, name , 12 + (10 * SpaceShip.getInstance().getDaysOnBoard()));
                return gundam;
            case 4:
                int startingHealthAlien = 51 + (8 * SpaceShip.getInstance().getDaysOnBoard());
                Alien alien = new Alien(startingHealthAlien, startingHealthAlien, name , 5 + (4 * SpaceShip.getInstance().getDaysOnBoard()));
                return alien;
            case 5:
                int startingHealthDemon = 120 + (12 * SpaceShip.getInstance().getDaysOnBoard());
                Demon demon = new Demon(startingHealthDemon, startingHealthDemon, name , 15 + (12 * SpaceShip.getInstance().getDaysOnBoard()));
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
        ArrayList<com.example.project.entities.characterObjects.Character> activeCharacters = SpaceShip.getInstance().getCrewQuarters().getCrewMembers();

        if (activeCharacters.size() >= 2) {
            com.example.project.entities.characterObjects.Character character1 = activeCharacters.get(0);
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
