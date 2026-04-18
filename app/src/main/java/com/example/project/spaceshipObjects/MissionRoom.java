package com.example.project.spaceshipObjects;

import com.example.project.EntityGenerationClasses.ThreatAnalysis;
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
    private ThreatAnalysis radar = new ThreatAnalysis();
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

        return radar.generateThreat(selection, name);
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
