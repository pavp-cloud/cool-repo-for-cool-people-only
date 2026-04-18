package com.example.project.spaceshipObjects;

import com.example.project.entities.characterObjects.Character;

import java.util.ArrayList;

public class PassengerManifest {
    private ArrayList<Character> crewManifest = new ArrayList<>();
    //adds crew to manifest and shows stats
    public void addPassenger(Character crewMember) {
        if (!crewManifest.contains(crewMember)) {
            crewManifest.add(crewMember);
        }
    }
    //shows if crew member is dead
    public void recordDeath(Character crewMember) {
        crewMember.setDead(true);
    }

    public ArrayList<Character> getCrewManifest() {
        return crewManifest;
    }

}
