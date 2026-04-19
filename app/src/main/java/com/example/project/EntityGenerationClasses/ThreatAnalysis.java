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
    private final int baseHealthPirate = 125;
    private final int baseHealthParasite = 130;
    private final int baseHealthGundam = 145;
    private final int baseHealthAlien = 51;
    private final int baseHealthDemon = 150;

    /*
    This function is used to assess the strength of a randomly designated threat
    for a mission. it takes a number and name as parameters and creates a threat with those
    parameters.
     */
    public Threat generateThreat(int selection, String name){
            switch(selection)
        {
            case 1:
                int startingHealthPirate = baseHealthPirate + (108 * SpaceShip.getInstance().getDaysOnBoard());
                Pirate pirate = new Pirate(startingHealthPirate, startingHealthPirate, name, 24 + (24 * SpaceShip.getInstance().getDaysOnBoard()));
                return pirate;
            case 2:
                int startingHealthParasite = baseHealthParasite + (93 * SpaceShip.getInstance().getDaysOnBoard());
                Parasite parasite = new Parasite(startingHealthParasite, startingHealthParasite, name, 20 + (26 * SpaceShip.getInstance().getDaysOnBoard()));
                return parasite;
            case 3:
                int startingHealthGundam = baseHealthGundam + (112 * SpaceShip.getInstance().getDaysOnBoard());
                Gundam gundam = new Gundam(startingHealthGundam, startingHealthGundam, name, 28 + (34 * SpaceShip.getInstance().getDaysOnBoard()));
                return gundam;
            case 4:
                int startingHealthAlien = baseHealthAlien + (148 * SpaceShip.getInstance().getDaysOnBoard());
                Alien alien = new Alien(startingHealthAlien, startingHealthAlien, name, 21 + (32 * SpaceShip.getInstance().getDaysOnBoard()));
                return alien;
            case 5:
                int startingHealthDemon = baseHealthDemon + (126 * SpaceShip.getInstance().getDaysOnBoard());
                Demon demon = new Demon(startingHealthDemon, startingHealthDemon, name, 36 + (36 * SpaceShip.getInstance().getDaysOnBoard()));
                return demon;
            default:
                return null;
        }
    }
}
