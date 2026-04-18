package com.example.project.spaceshipObjects;

import com.example.project.entities.characterObjects.Character;

import java.util.ArrayList;

public class PassengerManifest {
    private ArrayList<Character> crewManifest = new ArrayList<>();

    /*
    adds a character to the manifest
     */
    public void addPassenger(Character crewMember) {
        if (!crewManifest.contains(crewMember)) {
            crewManifest.add(crewMember);
        }
    }

    /*
    sets the death flag of the character to true
     */
    public void recordDeath(Character crewMember) {
        crewMember.setDead(true);
    }

    /*
    getter for the crew manifest
     */
    public ArrayList<Character> getCrewManifest() {
        return crewManifest;
    }

}
