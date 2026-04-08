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

    public Character getCrewMember(int index) {
        return crewMembers.get(index);
    }

    public void restoreHealth(Character crewMember) {
        crewMember.setCurrentHealth(crewMember.getMaxHealth());
    }
}
