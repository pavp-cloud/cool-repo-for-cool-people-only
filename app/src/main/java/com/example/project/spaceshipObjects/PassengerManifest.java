package com.example.project.spaceshipObjects;

import com.example.project.entities.characterObjects.Character;

import java.util.ArrayList;

public class PassengerManifest {
    private ArrayList<Character> crewManifest = new ArrayList<>();

    public void addPassenger(Character crewMember) {
        if (!crewManifest.contains(crewMember)) {
            crewManifest.add(crewMember);
        }
    }

    public void recordDeath(Character crewMember) {
        crewMember.setDead(true);
    }

    public ArrayList<Character> getCrewManifest() {
        return crewManifest;
    }

}
