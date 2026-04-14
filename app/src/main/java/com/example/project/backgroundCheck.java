package com.example.project;

public class backgroundCheck {


    public Character newCrewMember(int selection, String crewMemberName, int daysOnBoard) {


    switch (selection) {
        // crew member stats are subject to changes and addition of scaling
        case 1:
            Medic medic = new Medic(100, 100, crewMemberName, daysOnBoard * 2);
            return medic;

        case 2:
            Soldier soldier = new Soldier(150, 150, crewMemberName, daysOnBoard * 2);
            return soldier;

        case 3:
            Scientist scientist = new Scientist(60, 60, crewMemberName, daysOnBoard * 2);
            return scientist;

        case 4:
            Pilot pilot = new Pilot(90, 90, crewMemberName, daysOnBoard * 2);
            return pilot;

        case 5:
            Engineer engineer = new Engineer(110, 110, crewMemberName, daysOnBoard * 2);
            return engineer;

        default:

            return null;
        }
    }
}
