package com.example.project.backgroundChecks;

import com.example.project.entities.characterObjects.specializations.Engineer;
import com.example.project.entities.characterObjects.specializations.Medic;
import com.example.project.entities.characterObjects.specializations.Pilot;
import com.example.project.entities.characterObjects.specializations.Scientist;
import com.example.project.entities.characterObjects.specializations.Soldier;
import com.example.project.entities.characterObjects.Character;
import com.example.project.spaceshipObjects.SpaceShip;

public class BackgroundCheck {
    //CONSTANTS
    //EXP MULTIPLIER
    private final int globalExpMultiplier = 2;
    //BASE HEALTH
    private final int baseHealthMedic = 80;
    private final int baseHealthSoldier = 100;
    private final int baseHealthScientist = 40;
    private final int baseHealthPilot = 75;
    private final int baseHealthEngineer = 110;
    //HP SCALING PER DAY ON BOARD
    private final int healthScaling = 10;
    //HP FORMULA: BASE HEALTH + (HEALTH SCALING * DAYS ON BOARD)
    //keep in mind since base days on board is 1 creating characters on day 1 creates them already scaled instead of with baseHP


    public Character newCrewMember(int selection, String crewMemberName, int daysOnBoard) {

    switch (selection) {
        // crew member stats are subject to changes and addition of scaling
        // with scaling in mind, the base hp values have been cut by 20-30
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
