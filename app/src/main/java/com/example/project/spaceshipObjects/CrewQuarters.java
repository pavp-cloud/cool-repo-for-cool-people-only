package com.example.project.spaceshipObjects;

import com.example.project.entities.characterObjects.Character;

import java.util.ArrayList;

public class CrewQuarters {
    private ArrayList<Character> crewMembers = new ArrayList<>();


    /*
    this function adds a crew member to the crew quarters
     */
    public void addCrewMember(Character crewMember) {
        if (!crewMembers.contains(crewMember)) {
            crewMembers.add(crewMember);
            restoreHealth(crewMember);
        }
    }

    /*
    removes a crew member from the crew quarters
     */
    public void removeCrewMember(Character crewMember) {
        crewMembers.remove(crewMember);
    }

    /*
    getter for the list of the crew members in the crew quarters
     */
    public ArrayList<Character> getCrewMembers() {
        return crewMembers;
    }

    /*
    restores the health of the designated crew member
     */
    public void restoreHealth(Character crewMember) {
        crewMember.resetCurrentHealth(crewMember.getMaxHealth());
    }
}
