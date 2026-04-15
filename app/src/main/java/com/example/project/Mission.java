package com.example.project;

import java.util.Random;

public class Mission {
    private Character crewMember1 = null;
    private Character crewMember2 = null;
    private Threat missionTarget;
    private boolean isPlayerTurn = true;
    private boolean crew1Moved = false;
    private boolean crew2Moved = false;

    public boolean isPlayerTurn() {return isPlayerTurn;}
    public boolean isCrew1Moved() {return crew1Moved;}
    public boolean isCrew2Moved() {return crew2Moved;}

    public Mission(Threat missionTarget) {
        this.missionTarget = missionTarget;
    }
    public Character getCrewMember1(){return crewMember1;}
    public Character getCrewMember2(){return crewMember2;}
    public Threat getMissionTarget(){return missionTarget;}

    public void addCrewMembers (Character crewMember1, Character crewMember2) {
        this.crewMember1 = crewMember1;
        this.crewMember2 = crewMember2;
    }

    public void executeMission() {
        new Thread(() -> {
            while (!isGameOver()){
                if (!isPlayerTurn) {
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // The CombatView will call enemyTurn() to sync animations, 
                    // so we just wait here if we're using CombatView.
                    // If running headless, we'd call it here.
                }
                try{ Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            }
            endMission();
        }).start();
    }

    /**
     * Handles a specific crew member's action.
     * @param crewIndex 1 for crewMember1, 2 for crewMember2
     * @param type 0 for basic attack, 1 for special attack
     */
    public void playerTurn(int crewIndex, int type) {
        if (!isPlayerTurn) return;

        // Prevent acting twice or acting if dead
        if (crewIndex == 1 && (crew1Moved || crewMember1 == null || crewMember1.getCurrentHealth() <= 0)) return;
        if (crewIndex == 2 && (crew2Moved || crewMember2 == null || crewMember2.getCurrentHealth() <= 0)) return;

        Character activeMember = (crewIndex == 1) ? crewMember1 : crewMember2;

        if (activeMember != null) {
            if (type == 0) {
                missionTarget.takeDamage(activeMember.attack());
            } else if (type == 1) {
                missionTarget.takeDamage(activeMember.special());
            }
        }

        if (crewIndex == 1) crew1Moved = true;
        else crew2Moved = true;

        // Check if all available crew members have acted
        if (allCrewMoved()) {
            isPlayerTurn = false;
        }
    }

    public boolean allCrewMoved() {
        boolean c1Done = (crewMember1 == null || crewMember1.getCurrentHealth() <= 0 || crew1Moved);
        boolean c2Done = (crewMember2 == null || crewMember2.getCurrentHealth() <= 0 || crew2Moved);
        return c1Done && c2Done;
    }

    public int enemyTurn() {
        if (isPlayerTurn) return -1;

        Random random = new Random();
        int action = random.nextInt(2);
        if (action == 0) {
            missionTarget.attack(crewMember1, crewMember2);
        } else {
            missionTarget.special(crewMember1, crewMember2);
        }

        // Reset move flags for next round
        isPlayerTurn = true;
        crew1Moved = false;
        crew2Moved = false;

        return action;
    }

    public void endMission() {

        SpaceShip ship = SpaceShip.getInstance();
        
        // Always increment days when a mission ends
        ship.incrementDaysOnBoard();
        
        // If all crew members are dead (GameOver), damage the ship
        boolean crewDead = (crewMember1 == null || crewMember1.getCurrentHealth() <= 0) &&
                          (crewMember2 == null || crewMember2.getCurrentHealth() <= 0);
        if (crewDead) {
            ship.damageShip();
        }

        CrewQuarters crewQuarters = ship.getCrewQuarters();
        PassengerManifest manifest = ship.getManifest();

        //checking for if member is dead to add back to crew quarters
        if (crewMember1 != null){
            if (crewMember1.getCurrentHealth() > 0){
                crewMember1.endOfCombatPrep(missionTarget);
                crewQuarters.addCrewMember(crewMember1);
            } else {
                manifest.recordDeath(crewMember1);
            }
        }
        if (crewMember2 != null){
            if (crewMember2.getCurrentHealth() > 0){
                crewMember2.endOfCombatPrep(missionTarget);
                crewQuarters.addCrewMember(crewMember2);
            } else {
                manifest.recordDeath(crewMember2);
            }
        }
    }
    public boolean isGameOver() {
        boolean crewDead = (crewMember1 == null || crewMember1.getCurrentHealth() <= 0) &&
                (crewMember2 == null || crewMember2.getCurrentHealth() <= 0);
        boolean targetDead = (missionTarget == null || missionTarget.getCurrentHealth() <= 0);
        return crewDead || targetDead;
    }
}
