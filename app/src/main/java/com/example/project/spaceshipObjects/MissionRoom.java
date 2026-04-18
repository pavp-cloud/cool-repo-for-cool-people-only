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
    // currently pastMissions isn't used anywhere in the code. we plan to add a mission history later.
    private ArrayList<String> threatNames = new ArrayList<>(Arrays.asList(
            "Bob", "Karen", "Terry", "Becky", "RxR 808", "Anvaron the Exhalted",
            "Fungus", "Worm", "Matthew Money Bags", "Pavel Pavlov's Dog", "Heikki got a Heinikeen",
            "Vargmoth", "Kar the Blighted Sword", "Lil Reggie", "Monotone Noise",
            "Hans", "Only says, I'll be your end"
    ));


    /*
    this function scans for threats creates the threat object
     */
    public Threat scanForThreats(int selection){
        Collections.shuffle(threatNames);
        String name = threatNames.get(0);

        return radar.generateThreat(selection, name);
    }

    /*
    this function creates a mission object with the designated threat.
    the crew members are added later.
     */
    public Mission createMission(Threat threat){
         Mission mission = new Mission(threat);
         activeMission = mission;
         return mission;
    }

    /*
    this function starts the mission logic
     */
    public void runMission (Mission mission){

        mission.executeMission();
    }

    /*
    this function returns the active mission
     */
    public Mission getActiveMission(){
        return activeMission;
    }

    /*
    this function adds the mission to the past mission list
    and sets the active mission to null.
     */
    public void updateMissionStatus(){
        pastMission.add(activeMission);
        activeMission = null;
    }
}
