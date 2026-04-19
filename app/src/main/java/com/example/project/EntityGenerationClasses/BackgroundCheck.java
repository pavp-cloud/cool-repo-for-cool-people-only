package com.example.project.EntityGenerationClasses;

import com.example.project.entities.characterObjects.specializations.Engineer;
import com.example.project.entities.characterObjects.specializations.Medic;
import com.example.project.entities.characterObjects.specializations.Pilot;
import com.example.project.entities.characterObjects.specializations.Scientist;
import com.example.project.entities.characterObjects.specializations.Soldier;
import com.example.project.entities.characterObjects.Character;

public class BackgroundCheck {
    //CONSTANTS
    // exp multiplier for new recruits to catch up slightly
    private final int globalExpMultiplier = 14;

    // base heath values for all available specs
    private final int baseHealthMedic = 65;
    private final int baseHealthSoldier = 95;
    private final int baseHealthScientist = 45;
    private final int baseHealthPilot = 75;
    private final int baseHealthEngineer = 125;

    // hp scales slightly with the amount of day passed/missions completed overall
    private final int healthScaling = 37;

    /*
    this function is used to create a new crew member. It takes a randomly generated number,
    a name given by the user and the days on board to create a character. the days on board
    are used to scale up new recruits to catch up to where you are in the game.
     */
    public Character newCrewMember(int selection, String crewMemberName, int daysOnBoard) {

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
