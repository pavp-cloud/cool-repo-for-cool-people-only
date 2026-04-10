package com.example.project;

import java.util.ArrayList;

public class PassengerManifest {
    private ArrayList<Character> crewManifest = new ArrayList<>();


    public void addPassenger(Character crewMember) {
        crewManifest.add(crewMember);
    }

    public ArrayList<Character> getCrewManifest() {
        return crewManifest;
    }

}
