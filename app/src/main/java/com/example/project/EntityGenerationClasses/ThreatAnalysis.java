package com.example.project.EntityGenerationClasses;

import com.example.project.entities.threatObjects.Threat;
import com.example.project.entities.threatObjects.specializations.Alien;
import com.example.project.entities.threatObjects.specializations.Demon;
import com.example.project.entities.threatObjects.specializations.Gundam;
import com.example.project.entities.threatObjects.specializations.Parasite;
import com.example.project.entities.threatObjects.specializations.Pirate;
import com.example.project.spaceshipObjects.SpaceShip;

public class ThreatAnalysis {

    // declaring constants for threat base health values
    private final int baseHealthPirate = 75;
    private final int baseHealthParasite = 90;
    private final int baseHealthGundam = 125;
    private final int baseHealthAlien = 51;
    private final int baseHealthDemon = 130;

    /*
    This function is used to assess the strength of a randomly designated threat
    for a mission. it takes a number and name as parameters and creates a threat with those
    parameters.
     */
    public Threat generateThreat(int selection, String name){
            switch(selection)
        {
            case 1:
                int startingHealthPirate = baseHealthPirate + (18 * SpaceShip.getInstance().getDaysOnBoard());
                Pirate pirate = new Pirate(startingHealthPirate, startingHealthPirate, name, 8 + (2 * SpaceShip.getInstance().getDaysOnBoard()));
                return pirate;
            case 2:
                int startingHealthParasite = baseHealthParasite + (23 * SpaceShip.getInstance().getDaysOnBoard());
                Parasite parasite = new Parasite(startingHealthParasite, startingHealthParasite, name, 13 + (5 * SpaceShip.getInstance().getDaysOnBoard()));
                return parasite;
            case 3:
                int startingHealthGundam = baseHealthGundam + (32 * SpaceShip.getInstance().getDaysOnBoard());
                Gundam gundam = new Gundam(startingHealthGundam, startingHealthGundam, name, 15 + (10 * SpaceShip.getInstance().getDaysOnBoard()));
                return gundam;
            case 4:
                int startingHealthAlien = baseHealthAlien + (28 * SpaceShip.getInstance().getDaysOnBoard());
                Alien alien = new Alien(startingHealthAlien, startingHealthAlien, name, 11 + (4 * SpaceShip.getInstance().getDaysOnBoard()));
                return alien;
            case 5:
                int startingHealthDemon = baseHealthDemon + (36 * SpaceShip.getInstance().getDaysOnBoard());
                Demon demon = new Demon(startingHealthDemon, startingHealthDemon, name, 16 + (12 * SpaceShip.getInstance().getDaysOnBoard()));
                return demon;
            default:
                return null;
        }
    }
}
