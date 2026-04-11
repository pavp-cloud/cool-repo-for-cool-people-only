package com.example.project;

import java.util.ArrayList;

public class CrewQuarters {
    private ArrayList<Character> crewMembers = new ArrayList<>();


    public void addCrewMember(Character crewMember) {
        crewMembers.add(crewMember);
        restoreHealth(crewMember);
    }

    public void removeCrewMember(Character crewMember) {
        crewMembers.remove(crewMember);
    }

    public ArrayList<Character> getCrewMembers() {
        return crewMembers;
    }

    public void restoreHealth(Character crewMember) {
        crewMember.setCurrentHealth(crewMember.getMaxHealth());
    }
}
