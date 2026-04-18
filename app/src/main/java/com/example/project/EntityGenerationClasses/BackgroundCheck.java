package com.example.project.EntityGenerationClasses;

import com.example.project.entities.characterObjects.specializations.Engineer;
import com.example.project.entities.characterObjects.specializations.Medic;
import com.example.project.entities.characterObjects.specializations.Pilot;
import com.example.project.entities.characterObjects.specializations.Scientist;
import com.example.project.entities.characterObjects.specializations.Soldier;
import com.example.project.entities.characterObjects.Character;

public class BackgroundCheck {
    //CONSTANTS
    // global exp multiplier
    private final int globalExpMultiplier = 4;

    // base heath values for all available specs
    private final int baseHealthMedic = 40;
    private final int baseHealthSoldier = 60;
    private final int baseHealthScientist = 25;
    private final int baseHealthPilot = 55;
    private final int baseHealthEngineer = 90;

    // hp scales with the amount of day passed/missions completed overall
    private final int healthScaling = 10;

    public Character newCrewMember(int selection, String crewMemberName, int daysOnBoard) {

        // using a number generated beforehand it selects a random spec to recruit
    switch (selection) {
        case 1:
            int startingHealthMedic = baseHealthMedic + (healthScaling * daysOnBoard);
            Medic medic = new Medic(startingHealthMedic, startingHealthMedic, crewMemberName, daysOnBoard * globalExpMultiplier);
            return medic;

        case 2:
            int startingHealthSoldier = baseHealthSoldier + (healthScaling * daysOnBoard);
            Soldier soldier = new Soldier(startingHealthSoldier, startingHealthSoldier, crewMemberName, daysOnBoard * globalExpMultiplier);
            return soldier;

        case 3:
            int startingHealthScientist = baseHealthScientist + (healthScaling * daysOnBoard);
            Scientist scientist = new Scientist(startingHealthScientist, startingHealthScientist, crewMemberName, daysOnBoard * globalExpMultiplier);
            return scientist;

        case 4:
            int startingHealthPilot = baseHealthPilot + (healthScaling * daysOnBoard);
            Pilot pilot = new Pilot(startingHealthPilot, startingHealthPilot, crewMemberName, daysOnBoard * globalExpMultiplier);
            return pilot;

        case 5:
            int startingHealthEngineer = baseHealthEngineer + (healthScaling * daysOnBoard);
            Engineer engineer = new Engineer(startingHealthEngineer, startingHealthEngineer, crewMemberName, daysOnBoard * globalExpMultiplier);
            return engineer;

        default:

            return null;
        }
    }
}
